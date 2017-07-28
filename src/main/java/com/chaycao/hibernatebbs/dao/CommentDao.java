package com.chaycao.hibernatebbs.dao;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.bean.Comment;
import com.chaycao.hibernatebbs.bean.User;
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
public class CommentDao {

    public static boolean save(final Comment comment) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try {
            session.save(comment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            success = false;
        } finally {
            HibernateUtil.closeSession();
        }
        return success;
    }

    public static boolean remove(final Comment comment) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try {
            session.delete(comment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            success = false;
        } finally {
            HibernateUtil.closeSession();
        }
        return success;
    }

    public static boolean update(final Comment comment) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try {
            session.update(comment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            success = false;
        } finally {
            HibernateUtil.closeSession();
        }
        return success;
    }

    public static List<Comment> listAll() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<Comment> comments = new ArrayList<Comment>();
        try {
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.Comment");
            comments = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return comments;
    }

    public static List<Comment> listByArticle(Article article) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<Comment> comments = new ArrayList<Comment>();
        try {
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.Comment c " +
                    "where c.article=?0");
            query.setParameter(0, article);
            comments = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return comments;
    }

}
