/* 
    Copyright © 2017 Levente Daradics
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


    This class uses the following open-source libraries:

    Google-guava:
    Guava Copyright 2009-2017 Google Inc.
    Licensed under the Apache License, Version 2.0
    
 */
package com.chase.hiraganakatakanaparser;

import com.google.common.collect.BiMap;

import java.io.*;
/**
 * This class is used to parse Katakana Strings to romaji.
 * This class uses the singleton pattern.
 * @author Levente Daradics
 */
public class KatakanaLetters {
    private volatile BiMap<Character, String> mapKatakanaKeyRomValue;
    private volatile BiMap<String, String> mapKatakanaDigraphs;
    private static volatile KatakanaLetters uniqueInstance;
    private volatile BiMap<Character, Character> mapLatinToMacronedLatinVowel;
    private volatile BiMap<Character, Character> mapUCaseMacronToLCaseMacron;
    
    private KatakanaLetters()   {
        mapKatakanaKeyRomValue = Utils.loadChStrMap("jsons/katakana/katakanaKeyRomajiValueSimpler.json");
        mapKatakanaDigraphs = Utils.loadStrStrMap("jsons/katakana/katakanaDigraphs.json");
        mapLatinToMacronedLatinVowel = Utils.loadChrChrMap("jsons/katakana/macronLatinLetters.json");
        mapUCaseMacronToLCaseMacron = Utils.loadChrChrMap("jsons/UCaseMacronToLCase.json");
    }
    
    /**
     * Returns an instance of this class, create class with this method instead
     * of the new keyword.
     * This is because of the singleton pattern.
     * @return an instance of this class.
     */
    public static KatakanaLetters getInstance() {
        if (uniqueInstance == null)  {
            return new KatakanaLetters();
        }
        return uniqueInstance;
    }
    
    /**
     * Getter for the Katakana-key Romaji-value BiMap with monographs.
     * @return the map of monographs with Katakana Keys and Romaji values
     */
    public BiMap<Character, String> getMapKatakanaKeyRomValue() {
        return mapKatakanaKeyRomValue;
    }
    
    /**
     * Getter for the Katakana-key Romaji-value BiMap with digraphs.
     * @return the map of digraphs with Katakana Keys and Romaji values
     */
    public BiMap<String, String> getMapKatakanaDigraphs() {
        return mapKatakanaDigraphs;
    }

    /**
     * Returns a map with latin vowel keys, and macroned laton vowel values.
     * For example: a -> ā
     * @return map with latin vowel keys, and macroned laton vowel values
     */
    public BiMap<Character, Character> getMapLatinToMacronedLatinVowel() {
        return mapLatinToMacronedLatinVowel;
    }
    
    /**
     * Returns a map with upper case latin vowel keys, and lower case macroned laton vowel values.
     * For example: A -> ā
     * @return map with upper case latin vowel keys, and lower case macroned laton vowel values
     */
    public BiMap<Character, Character> getMapUCaseMacronToLCaseMacron() {
        return mapUCaseMacronToLCaseMacron;
    }
    
    /**
     * Determines wether the input char is a valid Katakana char or not.
     * The range of katakana characters in the utf table start from 0x30A1 to 0x30F6
     * including the vowel elongation mark. This doesn't include half-width katakana.
     * @param ch the character it will examine
     * @return true if the input char is a katakana character
     */
    public boolean isKatakanaChar(char ch) {
        return (((int) ch >= 0x30A1 && (int)ch <= 0x30F6) || (int)ch == 0x30FC );
    }
    
    /**
     * Convenince method to find wether the input char is a vovel elongation mark.
     * @param ch the character the method examines
     * @return true if the input char is a vovel elongation mark (ー)
     */
    public boolean isVowelElongationMark(char ch)   {
        return ((int) ch == 0x30FC);
    }
    
    /**
     * A method to convert latin (ASCII) vovel chars to their macroned pairs.
     * @param ch the vowel it will convert
     * @return the macroned vovel character
     */
    public char convertVowelToMacroned(char ch) {
        return mapLatinToMacronedLatinVowel.get(ch);
    }
    
    /**
     * Converts upper case macroned vowels to lower case macroned vowels.
     * @param ch the upper case macron vowel
     * @return the lower case macron vowel
     */
    public char macronToLowerCase(char ch)  {
        return mapUCaseMacronToLCaseMacron.get(ch);
    }
    
    /**
     * This method will tell you wether the input string is a Digraph katakna character pair or not.
     * @param str the string which will be evaluated
     * @return true if the string is a valid katakana digraph
     */
    public boolean isDigraph(String str)   {
        return mapKatakanaDigraphs.containsKey(str);
    }
    
    /**
     * This method will tell you wether the input string is a Digraph katakna character pair or not.
     * @link https://en.wikipedia.org/wiki/Katakana
     * @param ch1 first character of the possible digraph
     * @param ch2 second character of the possible digraph
     * @return true if the input character pair is a digraph
     */
    public boolean isDigraph(char ch1, char ch2)  {
        StringBuilder sb = new StringBuilder();

        sb.append(ch1).append(ch2);
        
        return isDigraph(sb.toString());
    }
    
    /**
     * Convenience method to find out wether the input char is a soukon (ッ) or not.
     * @param soukonMaybe the character which will be evaluated
     * @return true if the input char is a soukon
     */
    public boolean isSoukonChar(Character soukonMaybe) {
        return (soukonMaybe.equals('ッ'));
    }
    
    /**
     * This method converts different kinds of katakana letters to romaji strings.
     * Katakana letters can have multiple characters.
     * @param input the string containing valid katakana letters
     * @return the romaji eqivalent of the input string 
     */
    public String convertKatakanaLetterToRomaji(String input)   {
        StringBuilder sb = new StringBuilder();
        String output = null;
        String temp = null;
        
        if (input.length() == 3 && isSoukonChar(input.charAt(0))) {
            temp = input.substring(1);

            if (isDigraph(temp)) {
                sb.append(mapKatakanaDigraphs.get(temp).charAt(0) );
                sb.append(mapKatakanaDigraphs.get(temp));

                output = sb.toString();
            }
        }

        if (input.length() == 2 && isSoukonChar(input.charAt(0))) {
            sb.append(mapKatakanaKeyRomValue.get(input.charAt(1)).charAt(0) );
            sb.append(mapKatakanaKeyRomValue.get(input.charAt(1)));
            
            output = sb.toString();
        }

        if (input.length() == 2 && isDigraph(input)) {
            sb.append(mapKatakanaDigraphs.get(input));
            output = sb.toString();
        }

        if (input.length() == 1 && isKatakanaChar(input.charAt(0)) && !isVowelElongationMark(input.charAt(0))) {
            sb.append(mapKatakanaKeyRomValue.get(input.charAt(0)));

            output = sb.toString();
        }

        return output;
    }
    
    /**
     * This method will romanize words or sentences written in Katakana.
     * 
     * @param katakana the katakana string to romanize
     * @return the romaji string
     */
    public String parseKatakanaString(String katakana)  {
        String romaji;
        StringBuilder sb = new StringBuilder();

        for (int index = 0; index < katakana.length(); index++) {
            Character katakanaChar = katakana.charAt(index);
            if(isKatakanaChar(katakanaChar)) {
                if (katakana.length() >= index + 3 &&
                    isSoukonChar(katakana.charAt(index)) &&
                    isDigraph(katakana.charAt(index+1), katakana.charAt(index+2))) {

                    String temp = katakana.substring(index, index + 3);

                    sb.append(convertKatakanaLetterToRomaji(temp));

                    index+=2;

                    continue;
                }

                if (katakana.length() >= index + 2 && isSoukonChar(katakana.charAt(index))) {
                    String temp = katakana.substring(index, index + 2);

                    sb.append(convertKatakanaLetterToRomaji(temp));

                    index++;

                    continue;
                }

                if (katakana.length() >= index + 2 && isDigraph(katakana.substring(index, index + 2))) {
                    String temp = katakana.substring(index, index + 2);

                    sb.append(convertKatakanaLetterToRomaji(temp));

                    index++;

                    continue;
                }

                if (katakana.length() >= index + 1 && isKatakanaChar(katakana.charAt(0)) && !isVowelElongationMark(katakana.charAt(index))) {
                    String temp = katakana.substring(index, index + 1);

                    sb.append(convertKatakanaLetterToRomaji(temp));

                    continue;
                }

                if(isVowelElongationMark(katakana.charAt(index)))   {
                    char tempCh = sb.charAt(sb.length() - 1);

                    tempCh = mapLatinToMacronedLatinVowel.get(tempCh);

                    sb.replace(sb.length() - 1, sb.length(), Character.toString(tempCh));

                    continue;
                }
            }
            else    {
                sb.append(katakanaChar);
            }
        }

        romaji = sb.toString().toLowerCase();
        
        return romaji;
    }
}
