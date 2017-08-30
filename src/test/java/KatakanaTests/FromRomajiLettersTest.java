/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KatakanaTests;

import com.chase.katakana.RomajiLettersKat;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Dr.Chase
 */
public class FromRomajiLettersTest {
    private static RomajiLettersKat parser;
    
    @BeforeClass
    public static void setup()  {
        parser = RomajiLettersKat.getInstance();
    }
    
    @Test
    public void parseLettersMonographsFirstRow()    {
        assertEquals("ア", parser.parseKatakanaLetter("a"));
        assertEquals("イ", parser.parseKatakanaLetter("i"));
        assertEquals("ウ", parser.parseKatakanaLetter("u"));
        assertEquals("エ", parser.parseKatakanaLetter("e"));
        assertEquals("オ", parser.parseKatakanaLetter("o"));
    }
    
    @Test
    public void parseStringMonographsFirstRow()    {
        assertEquals("ア", parser.parseKatakanaString("a"));
        assertEquals("イ", parser.parseKatakanaString("i"));
        assertEquals("ウ", parser.parseKatakanaString("u"));
        assertEquals("エ", parser.parseKatakanaString("e"));
        assertEquals("オ", parser.parseKatakanaString("o"));
    }
    
    @Test
    public void parseLettersMonographsSecondRow()    {
        assertEquals("カ", parser.parseKatakanaLetter("ka"));
        assertEquals("キ", parser.parseKatakanaLetter("ki"));
        assertEquals("ク", parser.parseKatakanaLetter("ku"));
        assertEquals("ケ", parser.parseKatakanaLetter("ke"));
        assertEquals("コ", parser.parseKatakanaLetter("ko"));

    }
    
    @Test
    public void parseStringMonographsSecondRow()    {    
        assertEquals("カ", parser.parseKatakanaString("ka"));
        assertEquals("キ", parser.parseKatakanaString("ki"));
        assertEquals("ク", parser.parseKatakanaString("ku"));
        assertEquals("ケ", parser.parseKatakanaString("ke"));
        assertEquals("コ", parser.parseKatakanaString("ko"));
    }
    
    @Test
    public void parseLettersMonographsSeventhtRow()    {
        assertEquals("マ", parser.parseKatakanaLetter("ma"));
        assertEquals("ミ", parser.parseKatakanaLetter("mi"));
        assertEquals("ム", parser.parseKatakanaLetter("mu"));
        assertEquals("メ", parser.parseKatakanaLetter("me"));
        assertEquals("モ", parser.parseKatakanaLetter("mo"));
    }
    
    @Test
    public void parseStringMonographsSeventhtRow()    {
        assertEquals("マ", parser.parseKatakanaString("ma"));
        assertEquals("ミ", parser.parseKatakanaString("mi"));
        assertEquals("ム", parser.parseKatakanaString("mu"));
        assertEquals("メ", parser.parseKatakanaString("me"));
        assertEquals("モ", parser.parseKatakanaString("mo"));
    }
}
