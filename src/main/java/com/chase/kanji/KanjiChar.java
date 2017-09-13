/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.kanji;

import java.util.Objects;
import java.util.Set;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;




/**
 *
 * @author Dr.Chase
 */
@Entity(name = "kanji_char")
@Table(name = "kanji_char")
public class KanjiChar implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "kanjiId")
    Long kanjiId;
    
    @Column(name = "kanji_char" , unique=true)
    Character kanjiChar;
    
    @Column(name = "unicode_code", unique=true)
    String unicodeCode;
    
    @Column(name = "jis_coding_hex", unique=true)
    String jisCodingHex;
    
    @OneToMany(mappedBy = "kanjiChar", fetch = FetchType.LAZY)
    @Cascade({CascadeType.ALL})
    Set<KanjiMeaning> kanjiMeaning;
    
    @OneToMany(mappedBy = "kanjiChar", fetch = FetchType.LAZY)
    @Cascade({CascadeType.ALL})
    Set<KanjiReading> kanjiReading;
    
    public KanjiChar()  { }

    public KanjiChar(Character kanjiChar) {
        this.kanjiChar = kanjiChar;
    }

    public Long getKanjiId() {
        return kanjiId;
    }

    public void setKanjiId(Long kanjiId) {
        this.kanjiId = kanjiId;
    }

    public Character getKanjiChar() {
        return kanjiChar;
    }

    public void setKanjiChar(Character kanjiChar) {
        this.kanjiChar = kanjiChar;
    }

    public String getUnicodeCode() {
        return unicodeCode;
    }

    public void setUnicodeCode(String unicodeCode) {
        this.unicodeCode = unicodeCode;
    }

    public String getJisCodingHex() {
        return jisCodingHex;
    }

    public void setJisCodingHex(String jisCodingHex) {
        this.jisCodingHex = jisCodingHex;
    }

    public Set<KanjiMeaning> getKanjiMeaning() {
        return kanjiMeaning;
    }

    public void setKanjiMeaning(Set<KanjiMeaning> kanjiMeaning) {
        this.kanjiMeaning = kanjiMeaning;
    }

    public Set<KanjiReading> getKanjiReading() {
        return kanjiReading;
    }

    public void setKanjiReading(Set<KanjiReading> kanjiReading) {
        this.kanjiReading = kanjiReading;
    }
    
    public void addReading(KanjiReading kr) {
        if(kr != null)  {
            if(!kanjiReading.contains(kr))  {
                kanjiReading.add(kr);
            }
        }
        
    }
    
    public void removeReading(KanjiReading kr) {
        if(kr != null)  {
            if(kanjiReading.contains(kr))  {
                kanjiReading.remove(kr);
            }
        }
    }
    
    public void addMeaning(KanjiMeaning km) {
        if(km != null)  {
            if(!kanjiMeaning.contains(km))  {
                kanjiMeaning.add(km);
            }
        }
    }
    
    public void removeMeaning(KanjiReading km) {
        if(km != null)  {
            if(kanjiReading.contains(km))  {
                kanjiReading.remove(km);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.kanjiId);
        hash = 71 * hash + Objects.hashCode(this.kanjiChar);
        hash = 71 * hash + Objects.hashCode(this.unicodeCode);
        hash = 71 * hash + Objects.hashCode(this.jisCodingHex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KanjiChar other = (KanjiChar) obj;
        if (!Objects.equals(this.unicodeCode, other.unicodeCode)) {
            return false;
        }
        if (!Objects.equals(this.jisCodingHex, other.jisCodingHex)) {
            return false;
        }
        if (!Objects.equals(this.kanjiId, other.kanjiId)) {
            return false;
        }
        if (!Objects.equals(this.kanjiChar, other.kanjiChar)) {
            return false;
        }
        return true;
    }

    
    
    
}
