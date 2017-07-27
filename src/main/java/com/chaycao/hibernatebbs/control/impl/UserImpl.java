package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.User;
import com.chaycao.hibernatebbs.control.inter.UserInter;
import com.chaycao.hibernatebbs.dao.UserDao;
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

    public UserImpl() {}

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(final User user) {
        return UserDao.add(user);
    }

    /**
     * 登录
     * @param userName
     * @param userPassword
     * @return
     */
    public User login(final String userName, final String userPassword) {
        return UserDao.selectByNamePassword(userName, userPassword);
    }

    public static void main(String[] args) {
        System.out.println(new UserImpl().login("cy", "cy").getUserName());
    }
}
