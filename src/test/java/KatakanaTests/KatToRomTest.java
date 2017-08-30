/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KatakanaTests;

import com.chase.katakana.KatakanaLetters;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Dr.Chase
 */
public class KatToRomTest {
    private static KatakanaLetters parser;
    
    @BeforeClass
    public static void setup()  {
        parser = KatakanaLetters.getInstance();
    }
    
    @Test
    public void monographTests()   {
        assertEquals("A", parser.convertKatakanaLetterToRomaji("ア"));
        assertEquals("I", parser.convertKatakanaLetterToRomaji("イ"));
        assertEquals("U", parser.convertKatakanaLetterToRomaji("ウ"));
        assertEquals("E", parser.convertKatakanaLetterToRomaji("エ"));
        assertEquals("O", parser.convertKatakanaLetterToRomaji("オ"));
        
        assertEquals("KA", parser.convertKatakanaLetterToRomaji("カ"));
        assertEquals("KI", parser.convertKatakanaLetterToRomaji("キ"));
        assertEquals("KU", parser.convertKatakanaLetterToRomaji("ク"));
        assertEquals("KE", parser.convertKatakanaLetterToRomaji("ケ"));
        assertEquals("KO", parser.convertKatakanaLetterToRomaji("コ"));
        
        assertEquals("SA", parser.convertKatakanaLetterToRomaji("サ"));
        assertEquals("SHI", parser.convertKatakanaLetterToRomaji("シ"));
        assertEquals("SU", parser.convertKatakanaLetterToRomaji("ス"));
        assertEquals("SE", parser.convertKatakanaLetterToRomaji("セ"));
        assertEquals("SO", parser.convertKatakanaLetterToRomaji("ソ"));
        
        assertEquals("PA", parser.convertKatakanaLetterToRomaji("パ"));
        assertEquals("PI", parser.convertKatakanaLetterToRomaji("ピ"));
        assertEquals("PU", parser.convertKatakanaLetterToRomaji("プ"));
        assertEquals("PE", parser.convertKatakanaLetterToRomaji("ペ"));
        assertEquals("PO", parser.convertKatakanaLetterToRomaji("ポ"));
    }
    
    @Test
    public void digraphTests()   {
        assertEquals("KYA", parser.convertKatakanaLetterToRomaji("キャ"));
        assertEquals("SHA", parser.convertKatakanaLetterToRomaji("シャ"));
        assertEquals("CHA", parser.convertKatakanaLetterToRomaji("チャ"));
        assertEquals("NYA", parser.convertKatakanaLetterToRomaji("ニャ"));
        assertEquals("HYA", parser.convertKatakanaLetterToRomaji("ヒャ"));
        assertEquals("MYA", parser.convertKatakanaLetterToRomaji("ミャ"));
        assertEquals("RYA", parser.convertKatakanaLetterToRomaji("リャ"));
        assertEquals("GYA", parser.convertKatakanaLetterToRomaji("ギャ"));
        assertEquals("JA", parser.convertKatakanaLetterToRomaji("ジャ"));
        assertEquals("BYA", parser.convertKatakanaLetterToRomaji("ビャ"));
        assertEquals("PYA", parser.convertKatakanaLetterToRomaji("ピャ"));
        
        assertEquals("KYO", parser.convertKatakanaLetterToRomaji("キョ"));
        assertEquals("SHO", parser.convertKatakanaLetterToRomaji("ショ"));
        assertEquals("CHO", parser.convertKatakanaLetterToRomaji("チョ"));
        assertEquals("NYO", parser.convertKatakanaLetterToRomaji("ニョ"));
        assertEquals("HYO", parser.convertKatakanaLetterToRomaji("ヒョ"));
        assertEquals("MYO", parser.convertKatakanaLetterToRomaji("ミョ"));
        assertEquals("RYO", parser.convertKatakanaLetterToRomaji("リョ"));
        assertEquals("GYO", parser.convertKatakanaLetterToRomaji("ギョ"));
        assertEquals("JO", parser.convertKatakanaLetterToRomaji("ジョ"));
        assertEquals("BYO", parser.convertKatakanaLetterToRomaji("ビョ"));
        assertEquals("PYO", parser.convertKatakanaLetterToRomaji("ピョ"));
    }
    
    @Test
    public void convertKatakanaLetterToRomajiTest() {
        assertEquals("KKA", parser.convertKatakanaLetterToRomaji("ッカ"));
        assertEquals("HHA", parser.convertKatakanaLetterToRomaji("ッハ"));
    }
    
    @Test
    public void shortWordTestsSimple()   {
        assertEquals("katakana", parser.parseKatakanaString("カタカナ"));
        //assertEquals("wikipedia", parser.parseKatakanaString("ウィキペディア"));
        assertEquals("terebi", parser.parseKatakanaString("テレビ"));
        assertEquals("amerika", parser.parseKatakanaString("アメリカ"));
        assertEquals("pinpon", parser.parseKatakanaString("ピンポン"));
        assertEquals("hito", parser.parseKatakanaString("ヒト"));
        assertEquals("suzuki", parser.parseKatakanaString("スズキ"));
        assertEquals("toyota", parser.parseKatakanaString("トヨタ"));
        assertEquals("megane", parser.parseKatakanaString("メガネ"));
        assertEquals("baka", parser.parseKatakanaString("バカ"));
        
    }
    
    @Test
    public void soukonWordTests()   {
        assertEquals("saka", parser.parseKatakanaString("サカ"));
        assertEquals("sakka", parser.parseKatakanaString("サッカ"));
        assertEquals("beddo", parser.parseKatakanaString("ベッド"));
        assertEquals("mahha", parser.parseKatakanaString("マッハ"));
        assertEquals("bahha", parser.parseKatakanaString("バッハ"));
    }
    
    @Test
    public void vowelElongationMarkTests()  {
        assertEquals("rāmen", parser.parseKatakanaString("ラーメン"));
        assertEquals("mājan", parser.parseKatakanaString("マージャン"));
        assertEquals("chāhan", parser.parseKatakanaString("チャーハン"));
        assertEquals("chāshū", parser.parseKatakanaString("チャーシュー"));
        assertEquals("shūmai", parser.parseKatakanaString("シューマイ"));
    }
    
    @Test
    public void specialCharacters() {
        assertEquals("gimichoko!!", parser.parseKatakanaString("ギミチョコ!!"));
    }
}
