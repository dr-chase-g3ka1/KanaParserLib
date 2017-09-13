/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chase.hibernate;

import com.chase.kanji.KanjiChar;
import com.chase.kanji.KanjiMeaning;
import com.chase.kanji.KanjiReading;
import org.hibernate.query.Query;
import org.hibernate.Session;
import static com.chase.hibernate.SessionUtil.getSession;
import com.chase.kanji.ReadingType;
import com.neovisionaries.i18n.LanguageCode;
import org.hibernate.Transaction;

/**
 *
 * @author Dr.Chase
 */
public class Queries {
    public KanjiChar findKanjiByChar(char kanji)    {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            output = findKanjiByChar(session, kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    public KanjiChar saveKanjiChar(char kanji)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            output = saveKanjiChar(session, kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    public void saveOrUpdateKanjiChar(KanjiChar kanji)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        //return output;
    }
    
    public void saveKanjiChar(KanjiChar kanji)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            session.save(kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        //return output;
    }
    
    public void mergeKanjiChar(KanjiChar kanji)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            session.merge(kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        //return output;
    }
    
    public void updateKanjiChar(KanjiChar kanji)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiChar output = null;
        try {
            tx = session.beginTransaction();
            session.update(kanji);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        //return output;
    }
    
    public KanjiReading findReadingByReading(String reading)    {
        Session session = getSession();
        Transaction tx = null;
        KanjiReading output = null;
        try {
            tx = session.beginTransaction();
            output = (KanjiReading) findReadingByReading(session, reading);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    public KanjiMeaning findMeaningByMenaingAndLanguageCode(String meaning, LanguageCode lc)    {
        Session session = getSession();
        Transaction tx = null;
        KanjiMeaning output = null;
        try {
            tx = session.beginTransaction();
            output = findMeaningByMeaningAndLanguageCode(session, meaning, lc);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    public void saveKanjiReading(KanjiReading reading)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiReading output = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(reading);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
    }
    
    public KanjiReading saveKanjiReading(String reading, ReadingType rt)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiReading output = null;
        try {
            tx = session.beginTransaction();
            output = saveKanjiReading(session, reading, rt);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    
    public KanjiMeaning saveKanjiMeaning(KanjiMeaning meaning)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiMeaning output = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(meaning);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    public KanjiMeaning saveKanjiMeaning(String meaning, LanguageCode lc)  {
        Session session = getSession();
        Transaction tx = null;
        KanjiMeaning output = null;
        try {
            tx = session.beginTransaction();
            output = saveKanjiMeaning(session, meaning, lc);
            tx.commit();
        }
        catch(Exception ex) {
            if(tx != null)  {
                tx.rollback();
                throw ex;
            }
        }
        finally {
            session.close();
        }
        return output;
    }
    
    private KanjiChar findKanjiByChar(Session session, char kanji) {
        KanjiChar kanjiChar = (KanjiChar)session.createQuery(
                "select kc " +
                "from KanjiChar kc " +
                "where kc.kanjiChar =:kchar")
        .setParameter("kchar", Character.toString(kanji))
        .uniqueResult();
        return kanjiChar;
    }
    
    private KanjiReading findReadingByReading(Session session, String readingInput)    {
        KanjiReading kanjiReading = (KanjiReading) session.createQuery(   
                "select kr " +
                "from KanjiReading kr " +
                "where kr.reading =:read")
        .setParameter("read", readingInput)
        .uniqueResult();
        return kanjiReading;
    }
    
    private KanjiReading findReadingByReadingAndReadingType(Session session, String readingInput, ReadingType readingType)    {
        KanjiReading kanjiReading = (KanjiReading) session.createQuery(   
                "select kr " +
                "from KanjiReading kr " +
                "where kr.reading =:read " +
                "and kr.readingType =:readingType")
        .setParameter("read", readingInput)
        .setParameter("readingType", readingType)
        .uniqueResult();
        return kanjiReading;
    }
    
    
    private KanjiMeaning findMeaningByMeaning(Session session, String meaningInput)  {
        KanjiMeaning kanjiMeaning = (KanjiMeaning) session.createQuery(
                "select km " +
                "from KanjiMeaning km " +
                "where km.meaning = :meaningInput"
        )
        .setParameter("meaningInput", meaningInput)
        .uniqueResult();
        return kanjiMeaning;
    }
    
    private KanjiMeaning findMeaningByMeaningAndLanguageCode(Session session, String meaningInput, LanguageCode langCode)  {
        KanjiMeaning kanjiMeaning = (KanjiMeaning) session.createQuery(
                "select km " +
                "from KanjiMeaning km " +
                "where km.meaning = :meaningInput " +
                "and km.langCode = :langCode"
        )
        .setParameter("meaningInput", meaningInput)
        .setParameter("langCode", langCode)
        .uniqueResult();
        return kanjiMeaning;
    }
    
    private KanjiMeaning saveKanjiMeaning(Session session, String meaning, LanguageCode langCode)   {
        KanjiMeaning kanjiMeaning = findMeaningByMeaningAndLanguageCode(session, meaning, langCode);
        if(kanjiMeaning == null)    {
            kanjiMeaning = new KanjiMeaning(meaning, langCode);
            session.save(kanjiMeaning);
        }
        return kanjiMeaning;
    }
    
    private KanjiReading saveKanjiReading(Session session, String reading, ReadingType readingType) {
        KanjiReading kr = findReadingByReadingAndReadingType(session, reading, readingType);
        if(kr == null)   {
            kr = new KanjiReading(reading ,readingType);
            session.save(kr);
        }
        return kr;
    }
    
    private KanjiChar saveKanjiChar(Session session, char kanjiChar)  {
        KanjiChar kc = findKanjiByChar(session, kanjiChar);
        if(kc == null)  {
            kc = new KanjiChar(kanjiChar);
            session.save(kc);
        }
        return kc;
    }
    
}
