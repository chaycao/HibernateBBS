package com.chaycao.hibernatebbs.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by chaycao on 2017/7/26.
 */
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "context")
    private String context;

    @Column(name = "time")
    private Date time;

    @Column(name = "user_id")
    private User user;

    @Column(name = "article_id")
    private Article article;

    //-------------------Constructor-----------------------------//
    public Comment(String context, Date time, User user, Article article) {
        this.context = context;
        this.time = time;
        this.user = user;
        this.article = article;
    }

    //-------------------Get & Set-----------------------------//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
