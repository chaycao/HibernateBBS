package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.bean.User;
import com.chaycao.hibernatebbs.control.inter.ArticleInter;
import com.chaycao.hibernatebbs.dao.ArticleDao;
import com.chaycao.hibernatebbs.dao.UserDao;
import com.chaycao.hibernatebbs.util.DBUtil;
import com.chaycao.hibernatebbs.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chaycao on 2017/7/27.
 */
public class ArticleImpl implements ArticleInter {

    // 单例模式
    public final static ArticleImpl INSTANCE = new ArticleImpl();

    public ArticleImpl() {}

    // 缓存文章列表
    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList() {
        return articleList;
    }

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> selectAll() {
        List<Article> articles = ArticleDao.selectAll();
        this.articleList = articles;
        return articles;
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    public boolean add(Article article) {
        return ArticleDao.add(article);
    }

    /**
     * 根据文章列表中的索引查找文章
     * @param index
     * @return
     */
    public Article selectByIndex(int index) {
        return articleList.get(index);
    }

    public static void main(String[] args) {
        System.out.println(ArticleImpl.INSTANCE.selectAll().size());
    }
}
