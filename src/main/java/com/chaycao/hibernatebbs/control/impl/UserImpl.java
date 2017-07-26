//package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.User;
import com.chaycao.hibernatebbs.control.inter.UserInter;
import com.chaycao.hibernatebbs.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chaycao on 2017/7/26.
 */
public class UserImpl implements UserInter {

    /**
     * 单例模式
     */
    public final static UserImpl INSTANCE = new UserImpl();

    private static SessionFactory factory;

    public UserImpl() {}

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(final User user) {
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

    /**
     * 登录
     * @param userName
     * @param userPassword
     * @return
     */
    public User login(String userName, String userPassword) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        User user = null;
        try{
            Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.User u " +
                    "where u.userName=?0 and u.userPassword=?1 ");
            query.setParameter(0, userName, (Type) StringType.INSTANCE);
            query.setParameter(1, userPassword, (Type)StringType.INSTANCE);
            List<User> users = query.list();
            if(users.size() > 0)
                user = users.get(0);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
        HibernateUtil.sessionFactory.close();
        return user;
    }

    public void test(){
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM com.chaycao.hibernatebbs.bean.User u " +
//                    "where user_name = :userName and user_password = :userPassword ");
                "where u.userName=?0 and u.userPassword=?1 ");
        query.setParameter(0, "a", (Type) StringType.INSTANCE);
        query.setParameter(1, "a", (Type)StringType.INSTANCE);
        List<User> users = query.list();
        System.out.println(users.size());
    }

    public static void main(String[] args) {
        User user = new UserImpl().login("a", "a");
        if(user == null)
            System.out.println("shibai");
        else
            System.out.println(user.getUserName());
    }
}
