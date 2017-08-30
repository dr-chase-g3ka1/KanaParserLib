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
package com.chase.katakana;

import com.chase.katakana.KatakanaLetters;
import com.google.common.collect.BiMap;

/**
 *
 * @author Levente Daradics
 */
public class RomajiLettersKat {

    private volatile BiMap<String, Character> mapRomajiKeyKatValue;
    private volatile BiMap<String, String> mapRomajiToKatakanaDigraphs;
    private static volatile RomajiLettersKat uniqueInstance;
    private volatile BiMap<Character, Character> mapMacronedLatinToLatinVowel;
    private volatile BiMap<Character, Character> mapUCaseMacronToLCaseMacron;
    private volatile BiMap<Character, Character> mapLCaseMacronToUCaseMacron;
    
    private RomajiLettersKat()  {
        KatakanaLetters katakanaToRomParser = KatakanaLetters.getInstance();
        mapRomajiKeyKatValue = katakanaToRomParser.getMapKatakanaKeyRomValue().inverse();
        mapRomajiToKatakanaDigraphs = katakanaToRomParser.getMapKatakanaDigraphs().inverse();
        mapMacronedLatinToLatinVowel = katakanaToRomParser.getMapLatinToMacronedLatinVowel().inverse();
        mapUCaseMacronToLCaseMacron  = katakanaToRomParser.getMapUCaseMacronToLCaseMacron();
        mapLCaseMacronToUCaseMacron = katakanaToRomParser.getMapUCaseMacronToLCaseMacron().inverse();
    }
    
    public static RomajiLettersKat getInstance()    {
        if(uniqueInstance == null)  {
            uniqueInstance = new RomajiLettersKat();
        }
        return uniqueInstance;
    }
    
    public boolean isRomajiDigraph(String romaji)  {
        return mapRomajiToKatakanaDigraphs.containsKey(romaji.toUpperCase());
    }
    
    public boolean isRegularRomajiLetter(String romaji)  {
        return mapRomajiKeyKatValue.containsKey(romaji.toUpperCase());
    }
    
    public boolean isRomajiLetter(String romaji)    {
        return ( isRegularRomajiLetter(romaji) ||
            isRomajiDigraph(romaji) 
        );
    }
    
    /**
     * This method actually tells you wether the param char is a printable
     * ASCII char or not.
     * @param ch the character being evaluated
     * @return true if it's param is a printable ASCII char
     */
    public static boolean isRomajiChar(char ch)   {
        int hexadecimal = (int) ch;
        return ( hexadecimal >= 0x20 &&  hexadecimal <= 0x7e);
    }
    
    public static boolean isRomajiString(String ascii)  {
        for(int iii = 0; iii < ascii.length(); iii++)    {
            if(!isRomajiChar(ascii.charAt(iii))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isSoukonDigraph(String input)   {
        String upperInput = input.toUpperCase();
        return 
            ((upperInput.length() == 4 || upperInput.length() == 3)&&
            isRomajiDigraph(upperInput.substring(1)) &&
            Character.valueOf(upperInput.charAt(0)).equals(upperInput.charAt(1)))
            && !(Character.valueOf(upperInput.charAt(0)).equals('n')
                || (Character.valueOf(upperInput.charAt(0)).equals('N')))
        ;
    }
    
    private boolean isSoukonMonograph(String input)   {
        String upperInput = input.toUpperCase();
        return 
            upperInput.length() == 3
            && isRegularRomajiLetter(upperInput.substring(1))
            && Character.valueOf(upperInput.charAt(0)).equals(upperInput.charAt(1))
            && !(Character.valueOf(upperInput.charAt(0)).equals('n')
                || (Character.valueOf(upperInput.charAt(0)).equals('N')))
        ;
    }
    
    
    public String parseKatakanaLetter(String input)    {
        String upperInput = input.toUpperCase();
        StringBuilder sb = new StringBuilder();
        if(isSoukonDigraph(upperInput))   {
            sb.append('ッ')
              .append(mapRomajiToKatakanaDigraphs.get(upperInput));
        }
        if(isRomajiDigraph(upperInput) && upperInput.length() == 3)  {
            sb.append(mapRomajiToKatakanaDigraphs.get(upperInput));
        }
        if(isSoukonMonograph(upperInput))   {
            sb.append('ッ')
              .append(mapRomajiKeyKatValue.get(upperInput));
        }
        if((upperInput.length() == 2 || upperInput.length() == 1) && isRegularRomajiLetter(upperInput))   {
            sb.append(mapRomajiKeyKatValue.get(upperInput));
        }
        return sb.toString();
    }
    
    /**
     * This method will make Katakana String fro ma valid Romaji string.
     * Macron vowels are currently not supported.
     * @param romaji the valid romaji String as input
     * @return  the Katakana String output
     */
    public String parseKatakanaString(String romaji)   {
        RomajiLettersKat parser = RomajiLettersKat.getInstance();
        String localRomaji = romaji.toUpperCase();
        int katakanaWritten = 0;
        StringBuilder outputSB = new StringBuilder();
        StringBuilder romajiLetter = new StringBuilder();
        for(int iii = 0; iii < localRomaji.length(); iii++) {
            
            if(!isRomajiChar(localRomaji.charAt(iii)))   {
                outputSB.append(localRomaji.charAt(iii));
                continue;
            }
            else    {
                if(localRomaji.length() >= iii + 2)    {
                    String temp = localRomaji.substring(iii, iii + 2);
                    if(mapRomajiKeyKatValue.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiKeyKatValue.
                                get(temp)
                        );
                        iii++;
                        katakanaWritten++;
                        continue;
                    }
                    if(mapRomajiToKatakanaDigraphs.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiToKatakanaDigraphs.
                                get(temp)
                        );
                        iii++;
                        katakanaWritten++;
                        continue;
                    }
                }
                if(localRomaji.length() >= iii + 3)    {
                    String temp = localRomaji.substring(iii, iii + 3);
                    if(mapRomajiKeyKatValue.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiKeyKatValue.
                                get(temp)
                        );
                        iii += 2;
                        katakanaWritten++;
                        continue;
                    }
                    if(mapRomajiToKatakanaDigraphs.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiToKatakanaDigraphs.
                                get(temp)
                        );
                        iii += 2;
                        katakanaWritten++;
                        continue;
                    }
                }
                if(parser.isRegularRomajiLetter(
                    Character.toString(localRomaji.charAt(iii))) &&
                    mapRomajiKeyKatValue.
                        containsKey(
                            Character.toString(localRomaji.charAt(iii))
                        )
                )
                  {
                    outputSB.append(
                        mapRomajiKeyKatValue.
                            get(Character.toString(localRomaji.charAt(iii)))
                    );
                    katakanaWritten++;
                }
                else    {
                    if((int)localRomaji.charAt(iii) >= 0x41 && 
                            (int)localRomaji.charAt(iii) <= 0x5a)    {
                        outputSB.append('ッ');
                    }
                    else    {
                        outputSB.append(localRomaji.charAt(iii));
                    }
                }
            }
        }
        return outputSB.toString();
    }
    
    /**
     * Deprecated
     * @param input
     * @return 
     * @deprecated 
     */
    public String parseKatakanaStringOld(String input)   {
        String upperInput = input.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < upperInput.length(); index++) {
            if(upperInput.length() >= index + 4) {
                String temp = upperInput.substring(index, index + 4);
                if(isSoukonDigraph(temp))   {
                    sb.append(parseKatakanaLetter(temp));
                    index += 3;
                    continue;
                }   
            }
            if(upperInput.length() >= index + 3) {
                String temp = upperInput.substring(index, index + 3);
                if(isSoukonDigraph(temp))   {
                    sb.append(parseKatakanaLetter(temp));
                }
                if(isSoukonMonograph(upperInput))    {
                    sb.append(parseKatakanaLetter(temp));
                }
                index += 2;
                continue;
            }
            if(upperInput.length() >= index + 2) {
                String temp = upperInput.substring(index, index + 2);
                if(isRegularRomajiLetter(temp))   {
                    sb.append(parseKatakanaLetter(temp));
                }
                index++;
                continue;
            }
            if(upperInput.length() >= index + 1) {
                String temp = upperInput.substring(index, index + 1);
                if(isRegularRomajiLetter(temp))   {
                    sb.append(parseKatakanaLetter(temp));
                }
                continue;
            }
            sb.append(input.charAt(index));
        }
        return sb.toString();
    }
}
