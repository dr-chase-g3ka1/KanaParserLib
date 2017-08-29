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
package com.chase.hiraganakatakanaparser;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Levente Daradics
 */
public class Utils {
    
    
    public static BiMap<Character, Character> loadChrChrMap(File file) {
        String json = readInput(file); 
        Gson gson = new Gson();
        Type type =  
            new TypeToken<HashMap<Character, Character>>(){}.getType();
        Map<Character, Character> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public static BiMap<Character, String> loadChStrMap(File file) {
        String json = readInput(file); 
        Gson gson = new Gson();
        Type type =  
            new TypeToken<HashMap<Character, String>>(){}.getType();
        Map<Character, String> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public static BiMap<String, String> loadStrStrMap(File file) {
        String json = readInput(file);
        Gson gson = new Gson();
        
        Type type =  
            new TypeToken<HashMap<String, String>>(){}.getType();

        Map<String, String> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public static BiMap<Character, Character> loadChrChrMap(String resource) {
        String json = readInput(resource); 
        Gson gson = new Gson();
        Type type =  
            new TypeToken<HashMap<Character, Character>>(){}.getType();
        Map<Character, Character> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public static BiMap<Character, String> loadChStrMap(String resource) {
        String json = readInput(resource); 
        Gson gson = new Gson();
        Type type =  
            new TypeToken<HashMap<Character, String>>(){}.getType();
        Map<Character, String> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public static BiMap<String, String> loadStrStrMap(String resource) {
        String json = readInput(resource);
        Gson gson = new Gson();
        
        Type type =  
            new TypeToken<HashMap<String, String>>(){}.getType();

        Map<String, String> map =  
            gson.fromJson(json, type);
        return HashBiMap.create(map);
    }
    
    public String mapToJSON(Map map)    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(map);
    }
    
    
    public static String readInput(File filename) {
        StringBuilder buffer = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char)ch);
            }
            in.close();
            return buffer.toString();
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String readInput(String filename) {
        StringBuilder buffer = new StringBuilder();
        try {
            InputStream is = Utils.class.getResourceAsStream("/" + filename);
            InputStreamReader isr = new InputStreamReader(is, "UTF8");
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char)ch);
            }
            in.close();
            return buffer.toString();
        } 
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
