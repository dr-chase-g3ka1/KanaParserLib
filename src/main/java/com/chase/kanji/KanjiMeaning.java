/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.kanji;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.neovisionaries.i18n.LanguageCode;
import java.util.Objects;
import javax.persistence.JoinColumn;

/**
 *
 * @author Dr.Chase
 */
@Entity
@Table(name = "kanji_meaning")
public class KanjiMeaning implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "meaning_id")
    private Long meaningId;
    
    //@Column(name = "kanji_id")
    @ManyToOne
    @JoinColumn(name = "kanjiId")
    private KanjiChar kanjiChar;
    
    @Column(name = "meaning")
    private String meaning;
    
    @Column(name = "lang_code")
    @Enumerated(EnumType.STRING)
    private LanguageCode langCode;

    public KanjiMeaning() {
        
    }

    public KanjiMeaning(String meaning, LanguageCode langCode) {
        this.meaning = meaning;
        this.langCode = langCode;
    }
    
    
    
    public Long getMeaningId() {
        return meaningId;
    }

    public void setMeaningId(Long meaningId) {
        this.meaningId = meaningId;
    }

    public KanjiChar getKanjiChar() {
        return kanjiChar;
    }

    public void setKanjiChar(KanjiChar kanjiChar) {
        this.kanjiChar = kanjiChar;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public LanguageCode getLangCode() {
        return langCode;
    }

    public void setLangCode(LanguageCode langCode) {
        this.langCode = langCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.meaningId);
        hash = 97 * hash + Objects.hashCode(this.kanjiChar);
        hash = 97 * hash + Objects.hashCode(this.meaning);
        hash = 97 * hash + Objects.hashCode(this.langCode);
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
        final KanjiMeaning other = (KanjiMeaning) obj;
        if (!Objects.equals(this.meaning, other.meaning)) {
            return false;
        }
        if (!Objects.equals(this.meaningId, other.meaningId)) {
            return false;
        }
        if (!Objects.equals(this.kanjiChar, other.kanjiChar)) {
            return false;
        }
        if (this.langCode != other.langCode) {
            return false;
        }
        return true;
    }
    
    
}
