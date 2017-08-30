/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

import com.chase.hiragana.HiraganaLetters;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Dr.Chase
 */
public class IsThisCharHiraganaTest {
    @Test
    public void testHiraganaChars() {
        System.out.format("あ is: %04x\n", (int)'あ' );
        assertTrue(HiraganaLetters.isHiraganaChar('あ'));
        assertTrue(HiraganaLetters.isHiraganaChar('え'));
        assertTrue(HiraganaLetters.isHiraganaChar('ぜ'));
        assertTrue(HiraganaLetters.isHiraganaChar('ま'));
        assertTrue(HiraganaLetters.isHiraganaChar('つ'));
        assertTrue(HiraganaLetters.isHiraganaChar('る'));
        assertTrue(HiraganaLetters.isHiraganaChar('ば'));
        assertTrue(HiraganaLetters.isHiraganaChar('ゃ'));
        assertTrue(HiraganaLetters.isHiraganaChar('ぁ'));    // first
        assertTrue(HiraganaLetters.isHiraganaChar('ん'));    // last
    }
    
    @Test
    public void testNonHiraganaChars()  {
        assertFalse(HiraganaLetters.isHiraganaChar('a'));
        assertFalse(HiraganaLetters.isHiraganaChar('C'));
        assertFalse(HiraganaLetters.isHiraganaChar('葉'));
        assertFalse(HiraganaLetters.isHiraganaChar('漢'));
        assertFalse(HiraganaLetters.isHiraganaChar('テ'));
    }
}
