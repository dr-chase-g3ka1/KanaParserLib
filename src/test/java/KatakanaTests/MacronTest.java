/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KatakanaTests;


import com.chase.hiraganakatakanaparser.KatakanaLetters;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Levente Daradics
 */
public class MacronTest {
    private static KatakanaLetters parser;
    
    @BeforeClass
    public static void setup()  {
        parser = KatakanaLetters.getInstance();
    }
    
    @Test
    public void macronTests()   {
        assertEquals('ū', parser.convertVowelToMacroned(Character.toLowerCase('U')));
        assertEquals('ū', parser.macronToLowerCase('Ū'));
        
    }
    
}
