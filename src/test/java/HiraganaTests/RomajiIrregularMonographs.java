/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

import com.chase.hiragana.RomajiLettersHir;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Dr.Chase
 */
public class RomajiIrregularMonographs {
    static RomajiLettersHir romajiLetters;
    
    @BeforeClass
    public static void setup()  {
        romajiLetters = RomajiLettersHir.getInstance();
    }
    
    @Test
    public void irregularMonographTest()    {
        assertTrue(romajiLetters.isRomajiLetter("shi"));
        assertTrue(romajiLetters.isRomajiLetter("tsu"));
        assertTrue(romajiLetters.isRomajiLetter("chi"));
        assertTrue(romajiLetters.isRomajiLetter("fu"));
        assertTrue(romajiLetters.isRomajiLetter("ji"));
    }
}
