/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.kanji;

/**
 *
 * @author Dr.Chase
 */
public enum ReadingType {
    ON_YOMI("On-Yomi"),
    KUN_YOMI("Kun-Yomi"),
    PERSON_NAME("Person Name")
    ;
    
    private final String text;
    
    private ReadingType(final String text)  {
        this.text = text;
    }
    
    @Override
    public String toString()    {
        return text;
    }
}
