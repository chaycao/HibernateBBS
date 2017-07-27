package com.chaycao.hibernatebbs.dao;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chayc on 2017/7/27.
 */
public class ArticleDao {

    public static boolean add(final Article article) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Integer id = null;
        try{
            id = (Integer) session.save(article);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
        if (id.equals(null))
            return false;
        else
            return true;
    }

    public static boolean delete(final Article article) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try{
            session.delete(article);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            success = false;
        }finally {
            HibernateUtil.closeSession();
        }
        return success;
    }

    public static boolean update(final Article article) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try{
            session.update(article);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            success = false;
        }finally {
            HibernateUtil.closeSession();
        }
        return success;
    }

    public static List<Article> selectAll() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<Article> articles = new ArrayList<Article>();
        try{
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.Article");
            articles = query.list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
        return articles;
    }


}
