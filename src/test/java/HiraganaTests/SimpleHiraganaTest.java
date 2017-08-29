/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

import com.chase.hiraganakatakanaparser.HiraganaLetters;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dr.Chase
 */
public class SimpleHiraganaTest {
    static private HiraganaLetters hiraganaParser;
    
    @BeforeClass
    public static void setUpOnce()  {
        hiraganaParser = HiraganaLetters.getInstance();
    }
    
    @Test
    public void oneWordTests()  {
        assertEquals("sakka",   hiraganaParser.parseHiraganaString("さっか"));
        assertEquals("saka",    hiraganaParser.parseHiraganaString("さか"));
        assertEquals("watashi",    hiraganaParser.parseHiraganaString("わたし"));
        assertEquals("boku",    hiraganaParser.parseHiraganaString("ぼく"));
        assertEquals("anata",    hiraganaParser.parseHiraganaString("あなた"));
        assertEquals("arigatou gozaimasu",    hiraganaParser.parseHiraganaString("ありがとう ございます"));
        //assertEquals("konnichiwa",    hiraganaParser.parseHiraganaString("こんにちは"));
        //assertEquals("konbanwa",    hiraganaParser.parseHiraganaString("こんばんは"));
        assertEquals("sayonara",    hiraganaParser.parseHiraganaString("さよなら"));
        
        assertEquals("hiragana",        hiraganaParser.parseHiraganaString("ひらがな"));
        assertEquals("ichi",            hiraganaParser.parseHiraganaString("いち"));
        assertEquals("ni",              hiraganaParser.parseHiraganaString("に"));
        assertEquals("san",             hiraganaParser.parseHiraganaString("さん"));
        assertEquals("yon",             hiraganaParser.parseHiraganaString("よん"));
        assertEquals("roku",            hiraganaParser.parseHiraganaString("ろく"));
        assertEquals("shichi",          hiraganaParser.parseHiraganaString("しち"));
        assertEquals("nijiyonjuugofun", hiraganaParser.parseHiraganaString("にじよんじゅうごふん"));
        assertEquals("nigatsu",        hiraganaParser.parseHiraganaString("にがつ"));
        assertEquals("sangatsu",        hiraganaParser.parseHiraganaString("さんがつ"));
        assertEquals("mokuyoubi",        hiraganaParser.parseHiraganaString("もくようび"));
        
    }
}
