/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

import com.chase.hiraganakatakanaparser.RomajiLettersHir;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dr.Chase
 */
public class RomajiParseTest {
    static private RomajiLettersHir romajiParser;
    
    @BeforeClass
    public static void setUpOnce()  {
        romajiParser = RomajiLettersHir.getInstance();
    }
    
    @Test
    public void oneLetterTests()    {
        assertEquals("あおい", romajiParser.convertToHiragana("aoi"));
        
    }
    
    @Test
    public void oneSimpleWordTests()    {
        assertEquals("あなた", romajiParser.convertToHiragana("anata"));
        assertEquals("ぼく", romajiParser.convertToHiragana("boku"));
        assertEquals("かれ", romajiParser.convertToHiragana("kare"));
        assertEquals("かのじょ", romajiParser.convertToHiragana("kanojo"));
        assertEquals("さっか", romajiParser.convertToHiragana("sakka"));
        
        assertEquals("これ", romajiParser.convertToHiragana("kore"));
        assertEquals("ここ", romajiParser.convertToHiragana("koko"));
        assertEquals("この", romajiParser.convertToHiragana("kono"));
        assertEquals("それ", romajiParser.convertToHiragana("sore"));
        assertEquals("ありがとう", romajiParser.convertToHiragana("arigatou"));
        assertEquals("ありがとう ございます", romajiParser.convertToHiragana("arigatou gozaimasu"));
        assertEquals("どういたしまして", romajiParser.convertToHiragana("douitashimashite"));
        assertEquals("さよなら", romajiParser.convertToHiragana("sayonara"));
    }
}
