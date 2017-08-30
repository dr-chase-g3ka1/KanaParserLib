/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KatakanaTests;

import com.chase.katakana.RomajiLettersKat;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dr.Chase
 */
public class FromRomajiTest {
    private static RomajiLettersKat parser;
    
    @BeforeClass
    public static void setup()  {
        parser = RomajiLettersKat.getInstance();
    }
    
    @Test
    public void simpleWords()   {
        assertEquals("メギツネ", parser.parseKatakanaString("megitsune"));
        assertEquals("メギ", parser.parseKatakanaString("megi"));
        assertEquals("テレビ", parser.parseKatakanaString("terebi"));
        assertEquals("アメリカ", parser.parseKatakanaString("Amerika"));
        assertEquals("ピンポン", parser.parseKatakanaString("pinpon"));
        assertEquals("スズキ", parser.parseKatakanaString("Suzuki"));
        assertEquals("トヨタ", parser.parseKatakanaString("Toyota"));
        assertEquals("ココ", parser.parseKatakanaString("koko"));
        assertEquals("ゴミ", parser.parseKatakanaString("gomi"));
        assertEquals("メガネ", parser.parseKatakanaString("megane"));
    }
    
    @Test
    public void chineeseLoanWords()   {

    }
}
