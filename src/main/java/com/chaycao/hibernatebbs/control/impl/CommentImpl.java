package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.bean.Comment;
import com.chaycao.hibernatebbs.control.inter.CommentInter;
import com.chaycao.hibernatebbs.dao.CommentDao;
import com.chaycao.hibernatebbs.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chaycao on 2017/7/11.
 */
public class CommentImpl implements CommentInter {

    /**
     * 单例模式
     */
    public final static CommentImpl INSTANCE = new CommentImpl();

    public CommentImpl() {}

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public boolean save(Comment comment) {
        return CommentDao.save(comment);
    }

    /**
     * 根据文章查询所有的评论
     * @param article
     * @return
     */
    public List<Comment> listByArticle(Article article) {
        return CommentDao.listByArticle(article);
    }
}
