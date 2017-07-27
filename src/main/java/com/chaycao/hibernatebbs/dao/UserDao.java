package com.chaycao.hibernatebbs.dao;

import com.chaycao.hibernatebbs.bean.User;
import com.chaycao.hibernatebbs.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chayc on 2017/7/27.
 */
public class UserDao {

    public static boolean add(final User user) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Integer id = null;
        try{
            id = (Integer) session.save(user);
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

    public static boolean delete(final User user) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try{
            session.delete(user);
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

    public static boolean update(final User user) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        try{
            session.update(user);
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

    public static List<User> selectAll() {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<User> users = new ArrayList<User>();
        try{
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.User");
            users = query.list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
        return users;
    }

    /**
     * 通过 name,password 查询用户
     * @param name
     * @param password
     * @return
     */
    public static User selectByNamePassword (String name, String password) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        boolean success = true;
        User user = null;
        try{
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.User u " +
                    "where u.userName=?0 and u.userPassword=?1 ");
            query.setParameter(0, name, (Type) StringType.INSTANCE);
            query.setParameter(1, password, (Type)StringType.INSTANCE);
            user = (User)query.uniqueResult();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
        return user;
    }
}
