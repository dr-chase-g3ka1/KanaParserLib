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

    Gson:
    Gson Copyright 2008-2017 Google Inc.
    Licensed under the Apache License, Version 2.0
    
    Google-guava:
    Guava Copyright 2009-2017 Google Inc.
    Licensed under the Apache License, Version 2.0

 */
package com.chase.hiragana;

import com.chase.utils.Utils;
import com.google.common.collect.BiMap;

/**
 * This class is used to parse Hiragana Strings to romaji.
 * This class uses the singleton pattern.
 * @author Levente Daradics
 */
public class HiraganaLetters {
    private static HiraganaLetters uniqueInstanceHLN;

    private volatile BiMap<Character, String> mapHiraganaKeyRomanjiValue;
    private volatile BiMap<String, String> mapHiraganaDigraphs;
    private volatile BiMap<String, String> mapIrregularMonographs;
    
    /**
     * Getter for the Hiragana-key Romaji-value BiMap with monographs.
     * @return the map of monographs with Hiragana Keys and Romaji values
     */
    public BiMap<Character, String> getMapHiraganaKeyRomanjiValue() {
        return mapHiraganaKeyRomanjiValue;
    }
    
    /**
     * Getter for the Hiragana-key Romaji-value BiMap with digraphs.
     * @return the map of digraphs with Hiragana Keys and Romaji values
     */
    public BiMap<String, String> getMapHiraganaDigraphs() {
        return mapHiraganaDigraphs;
    }
    
    /**
     * Getter for the Hiragana-key Romaji-value BiMap with digraphs.
     * @return the map of irregular monographs with Hiragana Keys and Romaji values
     * @deprecated 
     */
    public BiMap<String, String> getMapIrregularMonographs() {
        return mapIrregularMonographs;
    }    
    
    /**
     * Parses a Hiragana String to romaji.
     * It skips non-Hiragana characters. The output is always a lower-case String.
     * @param hiragana the Hiragana string you want to parse
     * @return 
     */
    public String parseHiraganaString(String hiragana)  {
        String romanji;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int iii = 0; iii < hiragana.length(); iii++)    {
            sb2.delete(0, sb2.length());
            Character hiraganaChar = hiragana.charAt(iii);
            if( isHiraganaChar(hiraganaChar)  )  {
                if(0 == hiraganaChar.compareTo('\u3063') )    { //     っ - gemination
                    if(hiragana.length() > iii + 1) {   
                        if(isHiraganaChar(hiragana.charAt(iii + 1)) && hiragana.length() > iii + 2)    {
                            if(isDigraph(hiragana.charAt(iii + 1), hiragana.charAt(iii + 2)))   {
                                sb2.append(hiragana.charAt(iii + 1))
                                   .append(hiragana.charAt(iii + 2));
                                sb.append(mapHiraganaDigraphs.get(sb2.toString()).charAt(0));
                            }
                        }
                        if(isHiraganaChar(hiragana.charAt(iii + 1)) )    {
                            sb.append(mapHiraganaKeyRomanjiValue.get(hiragana.charAt(iii + 1))
                                    .charAt(0));
                        }
                        continue;
                    }
                }
                if(hiragana.length() > iii + 1) {    
                    if(isDigraph(hiraganaChar, hiragana.charAt(iii + 1)))   {
                        sb2.append(hiraganaChar)
                           .append(hiragana.charAt(iii + 1));
                        String tempDigraphString = sb2.toString();
                        sb.append(mapHiraganaDigraphs.get(tempDigraphString));
                        ++iii;
                        continue;
                    }
                }
                sb.append(mapHiraganaKeyRomanjiValue.get(hiraganaChar));
            }
            else    {
                sb.append(hiraganaChar);
            }
            
        }
        romanji = sb.toString().toLowerCase();
        return romanji;
    }
    
    private HiraganaLetters()  {
        mapHiraganaKeyRomanjiValue = Utils.loadChStrMap("jsons/hiragana/hiraganaKeyRomajiValueSimpler.json");
        mapHiraganaDigraphs =  Utils.loadStrStrMap("jsons/hiragana/hiraganaDigraphs.json");
        mapIrregularMonographs = Utils.loadStrStrMap("jsons/hiragana/irregularMonographs.json");
    }
    
    /**
     * Returns an instance of this class, create class with this method instead
     * of the new keyword.
     * This is because of the singleton pattern.
     * @return an instance of this class.
     */
    public static HiraganaLetters getInstance()   {
        if (uniqueInstanceHLN == null)  {
            uniqueInstanceHLN = new HiraganaLetters();
        }
        return uniqueInstanceHLN;
    }
    
    /**
     * Determines ether two hiragana characters are digraphs or not.
     * There are two Character type params because a digraph consist of two
     * characters.
     * See what is a hiragana digraph.
     * @link https://en.wikipedia.org/wiki/Hiragana
     * @param first first character
     * @param second second character
     * @return true if theese two hiragana characters are a digraph
     */
    public boolean isDigraph(Character first, Character second)    {
        StringBuilder sb = new StringBuilder();
        sb.append(first)
                .append(second);
        return mapHiraganaDigraphs.containsKey(sb.toString());
    }
    
    /**
     * Determines ether two hiragana characters are digraphs or not.
     * Conveninece method.
     * @param hiragana
     * @return 
     */
    public boolean isDigraph(String hiragana)   {
        HiraganaLetters hiraganaLettersNew = HiraganaLetters.getInstance();
        return hiraganaLettersNew.isDigraph(hiragana.charAt(0), hiragana.charAt(1)); 
    }
    
    /**
     * Determines wether a character is a valid Hiragana character or not.
     * @param ch
     * @return 
     */
    public static boolean isHiraganaChar(char ch)   {
        boolean result = false;
        int hexadecimal = (int) ch;
        if( hexadecimal >= 0x3041 &&  hexadecimal <= 0x3093)  {
            return true;
        }
        return result;
    }
}
