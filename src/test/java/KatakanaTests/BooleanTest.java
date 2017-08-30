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
public class BooleanTest {
    private static KatakanaLetters parser;
    
    @BeforeClass
    public static void setup()  {
        parser = KatakanaLetters.getInstance();
    }
    
    @Test
    public void isKatakanaCharTest()    {
        assertTrue(parser.isKatakanaChar('ア'));
        assertTrue(parser.isKatakanaChar('キ'));
        assertTrue(parser.isKatakanaChar('ケ'));
        assertTrue(parser.isKatakanaChar('ヒ'));
        assertTrue(parser.isKatakanaChar('ル'));
        assertTrue(parser.isKatakanaChar('ベ'));
        assertTrue(parser.isKatakanaChar('ン'));
        assertTrue(parser.isKatakanaChar('ス'));
        assertTrue(parser.isKatakanaChar('ノ'));
        assertTrue(parser.isKatakanaChar('バ'));
        assertTrue(parser.isKatakanaChar('フ'));
        assertTrue(parser.isKatakanaChar('ユ'));
        assertTrue(parser.isKatakanaChar('ヲ'));
        assertTrue(parser.isKatakanaChar('ダ'));
        assertTrue(parser.isKatakanaChar('プ'));
        assertTrue(parser.isKatakanaChar('ポ'));
        
        assertFalse(parser.isKatakanaChar('つ'));
        assertFalse(parser.isKatakanaChar('し'));
        assertFalse(parser.isKatakanaChar('ち'));
        assertFalse(parser.isKatakanaChar('め'));
        assertFalse(parser.isKatakanaChar('あ'));
        
        assertFalse(parser.isKatakanaChar('教'));
        assertFalse(parser.isKatakanaChar('育'));
        assertFalse(parser.isKatakanaChar('漢'));
        assertFalse(parser.isKatakanaChar('字'));
        assertFalse(parser.isKatakanaChar('用'));
    }
    
    @Test
    public void yaDigraphsTest()  {
        assertTrue(parser.isDigraph("キャ"));
        assertTrue(parser.isDigraph("シャ"));
        assertTrue(parser.isDigraph("チャ"));
        assertTrue(parser.isDigraph("ニャ"));
        assertTrue(parser.isDigraph("ヒャ"));
        assertTrue(parser.isDigraph("ミャ"));
        assertTrue(parser.isDigraph("リャ"));
        assertTrue(parser.isDigraph("ギャ"));
        assertTrue(parser.isDigraph("ジャ"));
        assertTrue(parser.isDigraph("ビャ"));
        assertTrue(parser.isDigraph("ピャ"));
    }
    
    @Test
    public void yuDigraphsTest()  {
        assertTrue(parser.isDigraph("キュ"));
        assertTrue(parser.isDigraph("シュ"));
        assertTrue(parser.isDigraph("チュ"));
        assertTrue(parser.isDigraph("ニュ"));
        assertTrue(parser.isDigraph("ヒュ"));
        assertTrue(parser.isDigraph("ミュ"));
        assertTrue(parser.isDigraph("リュ"));
        assertTrue(parser.isDigraph("ギュ"));
        assertTrue(parser.isDigraph("ジュ"));
        assertTrue(parser.isDigraph("ビュ"));
        assertTrue(parser.isDigraph("ピュ"));
    }
    
    @Test
    public void yoDigraphsTest()  {
        assertTrue(parser.isDigraph("キョ"));
        assertTrue(parser.isDigraph("ショ"));
        assertTrue(parser.isDigraph("チョ"));
        assertTrue(parser.isDigraph("ニュ"));
        assertTrue(parser.isDigraph("ヒュ"));
        assertTrue(parser.isDigraph("ミュ"));
        assertTrue(parser.isDigraph("リュ"));
        assertTrue(parser.isDigraph("ギュ"));
        assertTrue(parser.isDigraph("ジュ"));
        assertTrue(parser.isDigraph("ビュ"));
        assertTrue(parser.isDigraph("ピュ"));
    }
    
}
