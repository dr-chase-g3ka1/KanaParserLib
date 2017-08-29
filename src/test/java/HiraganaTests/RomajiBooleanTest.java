/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HiraganaTests;

/**
 *
 * @author Dr.Chase
 */

import com.chase.hiraganakatakanaparser.RomajiLettersHir;
import org.junit.*;
import static org.junit.Assert.*;

public class RomajiBooleanTest {
    static RomajiLettersHir romajiLetters;
    
    @BeforeClass
    public static void setup()  {
        romajiLetters = RomajiLettersHir.getInstance();
    }
    
    @Test
    public void simpleRomajiCharTest()  {
        assertTrue(romajiLetters.isRomajiLetter("a"));
        assertTrue(romajiLetters.isRomajiLetter("i"));
        assertTrue(romajiLetters.isRomajiLetter("u"));
        assertTrue(romajiLetters.isRomajiLetter("e"));
        assertTrue(romajiLetters.isRomajiLetter("o"));
        
        assertTrue(romajiLetters.isRomajiLetter("ka"));
        assertTrue(romajiLetters.isRomajiLetter("ki"));
        assertTrue(romajiLetters.isRomajiLetter("ku"));
        assertTrue(romajiLetters.isRomajiLetter("ke"));
        assertTrue(romajiLetters.isRomajiLetter("ko"));
        
        assertTrue(romajiLetters.isRomajiLetter("sa"));
        assertTrue(romajiLetters.isRomajiLetter("su"));
        assertTrue(romajiLetters.isRomajiLetter("se"));
        assertTrue(romajiLetters.isRomajiLetter("so"));
        
        assertTrue(romajiLetters.isRomajiLetter("ta"));
        assertTrue(romajiLetters.isRomajiLetter("te"));
        assertTrue(romajiLetters.isRomajiLetter("to"));
        
        assertTrue(romajiLetters.isRomajiLetter("na"));
        assertTrue(romajiLetters.isRomajiLetter("ni"));
        assertTrue(romajiLetters.isRomajiLetter("nu"));
        assertTrue(romajiLetters.isRomajiLetter("ne"));
        assertTrue(romajiLetters.isRomajiLetter("no"));
        
        assertTrue(romajiLetters.isRomajiLetter("ha"));
        assertTrue(romajiLetters.isRomajiLetter("hi"));
        assertTrue(romajiLetters.isRomajiLetter("he"));
        assertTrue(romajiLetters.isRomajiLetter("ho"));
        
        assertTrue(romajiLetters.isRomajiLetter("ma"));
        assertTrue(romajiLetters.isRomajiLetter("mi"));
        assertTrue(romajiLetters.isRomajiLetter("mu"));
        assertTrue(romajiLetters.isRomajiLetter("me"));
        assertTrue(romajiLetters.isRomajiLetter("mo"));
        
        assertTrue(romajiLetters.isRomajiLetter("ya"));
        assertTrue(romajiLetters.isRomajiLetter("yu"));
        assertTrue(romajiLetters.isRomajiLetter("yo"));
        
        assertTrue(romajiLetters.isRomajiLetter("ra"));
        assertTrue(romajiLetters.isRomajiLetter("ri"));
        assertTrue(romajiLetters.isRomajiLetter("ru"));
        assertTrue(romajiLetters.isRomajiLetter("re"));
        assertTrue(romajiLetters.isRomajiLetter("ro"));
        
        assertTrue(romajiLetters.isRomajiLetter("wa"));
        assertTrue(romajiLetters.isRomajiLetter("wo"));
    }
    
}
