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

/**
 *
 * @author Levente Daradics
 */
public class RomajiLettersHir {
    protected static RomajiLettersHir uniqueInstance;
    
    private volatile BiMap<String, Character> mapRomajiKeyHiraganaValue;
    private volatile BiMap<String, String> mapRomajiToHiraganaDigraphs;
    //private volatile BiMap<String, String> mapIrregularRomajiToHiraganaMonographs;
    
    public BiMap<String, String> getMapRomajiToHiraganaDigraphs() {
        return mapRomajiToHiraganaDigraphs;
    }
    
    private RomajiLettersHir() {
        HiraganaLetters hiraganaLettersNew = HiraganaLetters.getInstance();
        mapRomajiKeyHiraganaValue = hiraganaLettersNew.
                getMapHiraganaKeyRomanjiValue().inverse();
        mapRomajiToHiraganaDigraphs = hiraganaLettersNew.
                getMapHiraganaDigraphs().inverse();
//        mapIrregularRomajiToHiraganaMonographs = hiraganaLettersNew.
//                getMapIrregularMonographs().inverse();
        hiraganaLettersNew = null;
    }
    public static RomajiLettersHir getInstance()    {
        if(uniqueInstance == null)  {
            uniqueInstance = new RomajiLettersHir();
        }
        return uniqueInstance;
    }

    public boolean isRomajiDigraph(String romaji)  {
        return mapRomajiToHiraganaDigraphs.containsKey(romaji.toUpperCase());
    }
    
    public boolean isRegularRomajiLetter(String romaji)  {
        return mapRomajiKeyHiraganaValue.containsKey(romaji.toUpperCase());
    }
    
    public boolean isRomajiLetter(String romaji)    {
        if( isRegularRomajiLetter(romaji) ||
            isRomajiDigraph(romaji) //||
            //isIrregularMonograph(romaji)
        )   {
            return true;
        }
        else    {
            return false;
        }
    }
    
    /**
     * This method actually tells you wether the param char is a printable
     * ASCII char or not.
     * @param ch the character being evaluated
     * @return true if it's param is a printable ASCII char
     */
    public static boolean isRomajiChar(char ch)   {
        boolean result = false;
        int hexadecimal = (int) ch;
        if( hexadecimal >= 0x20 &&  hexadecimal <= 0x7e)  {
            return true;
        }
        return result;
    }
    
    public static boolean isRomajiString(String ascii)  {
        for(int iii = 0; iii < ascii.length(); iii++)    {
            if(!isRomajiChar(ascii.charAt(iii))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * This method converts a valid romaji string to Hiragana string.
     * Vowels with macrons are currently not supported.
     * @param romaji the valid romaji input
     * @return the Hiragana string output
     */
    public String convertToHiragana(String romaji)   {
        RomajiLettersHir parser = RomajiLettersHir.getInstance();
        String localRomaji = romaji.toUpperCase();
        int hiraganaWritten = 0;
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
                    if(mapRomajiKeyHiraganaValue.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiKeyHiraganaValue.
                                get(temp)
                        );
                        iii++;
                        hiraganaWritten++;
                        continue;
                    }
                    if(mapRomajiToHiraganaDigraphs.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiToHiraganaDigraphs.
                                get(temp)
                        );
                        iii++;
                        hiraganaWritten++;
                        continue;
                    }
                }
                if(localRomaji.length() >= iii + 3)    {
                    String temp = localRomaji.substring(iii, iii + 3);
                    if(mapRomajiKeyHiraganaValue.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiKeyHiraganaValue.
                                get(temp)
                        );
                        iii += 2;
                        hiraganaWritten++;
                        continue;
                    }
                    if(mapRomajiToHiraganaDigraphs.containsKey(temp)) {
                        outputSB.append(
                            mapRomajiToHiraganaDigraphs.
                                get(temp)
                        );
                        iii += 2;
                        hiraganaWritten++;
                        continue;
                    }
                }
                if(parser.isRegularRomajiLetter(
                    Character.toString(localRomaji.charAt(iii))) &&
                    mapRomajiKeyHiraganaValue.
                        containsKey(
                            Character.toString(localRomaji.charAt(iii))
                        )
                )
                  {
                    outputSB.append(
                        mapRomajiKeyHiraganaValue.
                            get(Character.toString(localRomaji.charAt(iii)))
                    );
                    hiraganaWritten++;
                }
                else    {
                    if((int)localRomaji.charAt(iii) >= 0x41 && 
                            (int)localRomaji.charAt(iii) <= 0x5a)    {
                        outputSB.append('っ');
                    }
                    else    {
                        outputSB.append(localRomaji.charAt(iii));
                    }
                }
            }
        }
        return outputSB.toString();
    }
}
