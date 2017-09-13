/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.kanji;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dr.Chase
 */
@Entity
@Table(name = "kanji_reading")
public class KanjiReading implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reading_id")
    private Long readingId;
    
    @ManyToOne
    //@Column(name = "kanji_id")
    @JoinColumn(name = "kanjiId")
    private KanjiChar kanjiChar;
    
    @Column(name = "reading", unique = true)
    private String reading;
    
    @Column( name = "on_yun")
    @Enumerated(EnumType.STRING)
    private ReadingType readingType;
    
    public KanjiReading() { }
    
    public KanjiReading(String reading, ReadingType readingType) {
        this.reading = reading;
        this.readingType = readingType;
    }

    public Long getReadingId() {
        return readingId;
    }

    public void setReadingId(Long readingId) {
        this.readingId = readingId;
    }

    public KanjiChar getKanjiChar() {
        return kanjiChar;
    }

    public void setKanjiChar(KanjiChar kanjiChar) {
        this.kanjiChar = kanjiChar;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public ReadingType getReadingType() {
        return readingType;
    }

    public void setReadingType(ReadingType readingType) {
        this.readingType = readingType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.readingId);
        hash = 17 * hash + Objects.hashCode(this.kanjiChar);
        hash = 17 * hash + Objects.hashCode(this.reading);
        hash = 17 * hash + Objects.hashCode(this.readingType);
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
        final KanjiReading other = (KanjiReading) obj;
        if (!Objects.equals(this.reading, other.reading)) {
            return false;
        }
        if (!Objects.equals(this.readingId, other.readingId)) {
            return false;
        }
        if (!Objects.equals(this.kanjiChar, other.kanjiChar)) {
            return false;
        }
        if (this.readingType != other.readingType) {
            return false;
        }
        return true;
    }
    
    
}
