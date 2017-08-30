/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

import com.chase.hiragana.HiraganaLetters;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Dr.Chase
 */
public class HiraganaDigraphTest {
    static private HiraganaLetters digraphTester;
    
    @BeforeClass
    public static void setUpOnce()  {
        digraphTester = HiraganaLetters.getInstance();
    }
    
    @Test
    public void testKiDigraphs()    {
        System.out.format("き is: %04x\n", (int)"ぎゃ".charAt(0) );
        assertTrue(digraphTester.isDigraph("きゃ"));
        assertTrue(digraphTester.isDigraph("きゅ"));
        assertTrue(digraphTester.isDigraph("きょ"));
    }
    @Test
    public void testDigraphsWithCharArguments()    {
        assertTrue(digraphTester.isDigraph('き', 'ゃ'));
        assertTrue(digraphTester.isDigraph('き', 'ゅ'));
        assertTrue(digraphTester.isDigraph('き', 'ょ'));
    }    
    @Test
    public void testShiDigraphs()    {
        assertTrue(digraphTester.isDigraph("しゃ"));
        assertTrue(digraphTester.isDigraph("しゃ"));
        assertTrue(digraphTester.isDigraph("しゃ"));
    }
    @Test
    public void testChiDigraphs()    {
        assertTrue(digraphTester.isDigraph("ちゃ"));
        assertTrue(digraphTester.isDigraph("ちゅ"));
        assertTrue(digraphTester.isDigraph("ちょ"));
    }
    @Test
    public void testNiDigraphs()    {
        assertTrue(digraphTester.isDigraph("にゃ"));
        assertTrue(digraphTester.isDigraph("にゃ"));
        assertTrue(digraphTester.isDigraph("にゃ"));
    }
    @Test
    public void testHiDigraphs()    {
        assertTrue(digraphTester.isDigraph("ひゃ"));
        assertTrue(digraphTester.isDigraph("ひゃ"));
        assertTrue(digraphTester.isDigraph("ひゃ"));
    }
    @Test
    public void testMiDigraphs()    {
        assertTrue(digraphTester.isDigraph("みゃ"));
        assertTrue(digraphTester.isDigraph("みゃ"));
        assertTrue(digraphTester.isDigraph("みゃ"));
    }
    @Test
    public void testRiDigraphs()    {
        assertTrue(digraphTester.isDigraph("りゃ"));
        assertTrue(digraphTester.isDigraph("りゃ"));
        assertTrue(digraphTester.isDigraph("りゃ"));
    }
    @Test
    public void testGiDigraphs()    {
        assertTrue(digraphTester.isDigraph("ぎゃ"));
        assertTrue(digraphTester.isDigraph("ぎゅ"));
        assertTrue(digraphTester.isDigraph("ぎょ"));
    }
    @Test
    public void testZiDigraphs()    {
        assertTrue(digraphTester.isDigraph("じゃ"));
        assertTrue(digraphTester.isDigraph("じゅ"));
        assertTrue(digraphTester.isDigraph("じょ"));
    }
    @Test
    public void testBiDigraphs()    {
        assertTrue(digraphTester.isDigraph("びゃ"));
        assertTrue(digraphTester.isDigraph("びゅ"));
        assertTrue(digraphTester.isDigraph("びょ"));
    }
    @Test
    public void testPiDigraphs()    {
        assertTrue(digraphTester.isDigraph("ぴゃ"));
        assertTrue(digraphTester.isDigraph("ぴゅ"));
        assertTrue(digraphTester.isDigraph("ぴょ"));
    }
    @Test
    public void testNonDigraphChars() {
        assertFalse(digraphTester.isDigraph('ひ', 'や'));
        assertFalse(digraphTester.isDigraph('ひ', 'ゆ'));
        assertFalse(digraphTester.isDigraph('ひ', 'よ'));
    }
    @Test
    public void testNonDigraphStrings() {
        assertFalse(digraphTester.isDigraph("みや"));
        assertFalse(digraphTester.isDigraph("みゆ"));
        assertFalse(digraphTester.isDigraph("みよ"));
        
        assertFalse(digraphTester.isDigraph("へほ"));
        assertFalse(digraphTester.isDigraph("せら"));
        assertFalse(digraphTester.isDigraph("ゐう"));
    }
}
