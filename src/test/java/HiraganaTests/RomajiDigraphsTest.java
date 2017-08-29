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

public class RomajiDigraphsTest {
    static RomajiLettersHir romajiLetters;
    
    @BeforeClass
    public static void setUp()  {
        romajiLetters = RomajiLettersHir.getInstance();
    }
    
    /**
     *  source: https://en.wikipedia.org/wiki/Hiragana
     */
    @Test
    public void yaColumnTests() {
        assertTrue(romajiLetters.isRomajiDigraph("kya"));
        assertTrue(romajiLetters.isRomajiDigraph("sha"));
        assertTrue(romajiLetters.isRomajiDigraph("cha"));
        assertTrue(romajiLetters.isRomajiDigraph("nya"));
        assertTrue(romajiLetters.isRomajiDigraph("hya"));
        assertTrue(romajiLetters.isRomajiDigraph("mya"));
        assertTrue(romajiLetters.isRomajiDigraph("rya"));
        
        assertTrue(romajiLetters.isRomajiDigraph("gya"));
        assertTrue(romajiLetters.isRomajiDigraph("ja"));
        assertTrue(romajiLetters.isRomajiDigraph("bya"));
        assertTrue(romajiLetters.isRomajiDigraph("pya"));
    }
    
    @Test
    public void yuColumnTests() {
        assertTrue(romajiLetters.isRomajiDigraph("kyu"));
        assertTrue(romajiLetters.isRomajiDigraph("shu"));
        assertTrue(romajiLetters.isRomajiDigraph("chu"));
        assertTrue(romajiLetters.isRomajiDigraph("nyu"));
        assertTrue(romajiLetters.isRomajiDigraph("hyu"));
        assertTrue(romajiLetters.isRomajiDigraph("myu"));
        assertTrue(romajiLetters.isRomajiDigraph("ryu"));
        
        assertTrue(romajiLetters.isRomajiDigraph("gyu"));
        assertTrue(romajiLetters.isRomajiDigraph("ju"));
        assertTrue(romajiLetters.isRomajiDigraph("byu"));
        assertTrue(romajiLetters.isRomajiDigraph("pyu"));
    }
    
    @Test
    public void yoCollumnTests()    {
        assertTrue(romajiLetters.isRomajiDigraph("kyo"));
        assertTrue(romajiLetters.isRomajiDigraph("sho"));
        assertTrue(romajiLetters.isRomajiDigraph("cho"));
        assertTrue(romajiLetters.isRomajiDigraph("nyo"));
        assertTrue(romajiLetters.isRomajiDigraph("hyo"));
        assertTrue(romajiLetters.isRomajiDigraph("myo"));
        assertTrue(romajiLetters.isRomajiDigraph("ryo"));
        
        assertTrue(romajiLetters.isRomajiDigraph("gyo"));
        assertTrue(romajiLetters.isRomajiDigraph("jo"));
        assertTrue(romajiLetters.isRomajiDigraph("byo"));
        assertTrue(romajiLetters.isRomajiDigraph("pyo"));
    }
    
    
    
    
}
