/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.testrun;

import static com.chase.utils.Utils.makeScannerFromResource;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.chase.hibernate.Queries;
import com.chase.kanji.*;
import com.neovisionaries.i18n.LanguageCode;
import java.util.HashSet;
/**
 *
 * @author Dr.Chase
 */
public class TestRunMain {
    public static void main(String[] args)  {
        Scanner nextLineScanner = makeScannerFromResource("kanjidic/kanjidic-utf8");
        Scanner inLineScanner;
        nextLineScanner.nextLine();
        StringBuilder sb = new StringBuilder();
        Queries query = new Queries();
        
        int limit = 6355; //3965 - 3970 van T2 //
        boolean higherStart = true;
        for(int index = 0; index < limit; index++) {
            
            System.out.println("------- " + index + " -------");
            String actualLine = nextLineScanner.nextLine();
            if(index > 3962 && index < 3964 && higherStart)    {
                continue;
            }
            System.out.println(actualLine);
            inLineScanner = new Scanner(actualLine);
            
            Character kanjiCh = inLineScanner.next(".").charAt(0);
            sb.append(kanjiCh);
            sb.append(" ");
            String unicode = inLineScanner.findInLine("U([a-f0-9]){4}");
            sb.append(unicode);
            sb.append(" ");
            
            List<String> list1 = getHiraganaReadings(actualLine);
            sb.append(appendStringListToStringBuilder( list1));
            List<String> list2 = getKatakanaReadings(actualLine);
            sb.append(appendStringListToStringBuilder( list2));
            
            List<String> meaningList = getMeaningsFromKanjidic(actualLine);
            System.out.println(getMeaningsFromKanjidic(actualLine));
            
            System.out.println(getNameReadings(actualLine));
            
            // HIBERNATE!! 
            
            KanjiChar kanjiChar = new KanjiChar(kanjiCh);
            kanjiChar.setKanjiMeaning(new HashSet<KanjiMeaning>());
            kanjiChar.setKanjiReading(new HashSet<KanjiReading>());
            
            kanjiChar.setUnicodeCode(unicode);
            query.saveOrUpdateKanjiChar(kanjiChar);
            
            for(String str : list1)  {
                KanjiReading kr = query.saveKanjiReading(str, ReadingType.KUN_YOMI);
                kanjiChar.addReading(kr);
                kr.setKanjiChar(kanjiChar);
            }
            
            for(String str : list2)  {
                KanjiReading kr = query.saveKanjiReading(str, ReadingType.ON_YOMI);
                kanjiChar.addReading(kr);
                kr.setKanjiChar(kanjiChar);
            }
            
            for(String str : meaningList)   {
                KanjiMeaning km = query.saveKanjiMeaning(str, LanguageCode.en);
                kanjiChar.addMeaning(km);
                km.setKanjiChar(kanjiChar);
            }
            
            
            query.updateKanjiChar(kanjiChar);
            
            
            
        }
        try {
                writeOutputToFileUTF8(sb.toString());
            }
        catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Error!");
        }
        
    }
    
    private static List<String> getHiraganaOrKatakanaReadings(String fullLine, String pattern, int zeroOrOne)   {
        //(\\p{IsHiragana})+          ([.-])*(\\p{IsHiragana})+([.-])*
        // zero is before T1 in kanjidic, one is after T1 in kanjidic,
        String[] nonNameReadings = fullLine.split("T1 "); // + "[(\\p{IsHiragana}) | (\\p{IsKatakana})]"
        //System.out.println(nonNameReadings[zeroOrOne]);
        Scanner sc;
        try {
            sc = new Scanner(nonNameReadings[zeroOrOne]);
        } catch(java.lang.ArrayIndexOutOfBoundsException ex)    {
            return null;
        }
        
        List<String> lista = new ArrayList();
        
        while(sc.hasNext())  {
            String temp = "";
            try {
                temp = sc.next("([.-])*" + pattern + "+" + "([.-])*" + pattern + "*");
            } catch(InputMismatchException ex)  {
                sc.next();
            }
            if(!temp.equals(""))    {
                lista.add(temp);
            }
        }
        //System.out.println(lista);
        return lista;
    }
    
    public static List<String> getHiraganaReadings(String fullLine)    {
        return getHiraganaOrKatakanaReadings(fullLine, "(\\p{IsHiragana})", 0);
    }
    
    public static List<String> getKatakanaReadings(String fullLine)    {
        return getHiraganaOrKatakanaReadings(fullLine, "(\\p{IsKatakana})", 0);
    }
    
    public static List<String> getNameReadings(String fullLine) {
        return getHiraganaOrKatakanaReadings(fullLine, "[ (\\p{IsKatakana}) | (\\p{IsHiragana}) ]", 1);
    }
    
    public static List<String> getMeaningsFromKanjidic(String fullLine) {
        String[] splitInHalf = fullLine.split("([.-])*[ (\\p{IsHiragana}) | (\\p{IsKatakana}) ]+([.-])* \\{");
        String meanings = splitInHalf[1];
        String[] meaningsArray = meanings.split("\\} \\{");
        
        List<String> listaOfMeanings = new ArrayList();
        for(String str: meaningsArray)  {
            str = str.replaceAll("[\\{ | \\}]", " " );
            str = str.trim();
            listaOfMeanings.add(str);
            
        }
        
        return listaOfMeanings;
    }
    
    public static StringBuilder appendStringListToStringBuilder(List<String> list)  {
        StringBuilder sb = new StringBuilder();
        for(String string : list)  {
            sb.append(" ").append(string).append(" ");
        }
        sb.append("\n");
        return sb;
    }
    
    public static void writeOutputToFileUTF8(String output) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("output.txt"), "UTF-8"));
        try {
            out.write(output);
        } finally {
            out.close();
        }
    }
}
