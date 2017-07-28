package com.chaycao.hibernatebbs.control.inter;


import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.bean.User;

import java.util.Date;
import java.util.List;

/**
 * Created by chaycao on 2017/7/26.
 */
public interface ArticleInter {

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> listAll();

    /**
     * 添加文章
     * @param article
     * @return
     */
    public boolean save(Article article);

    /**
     * 根据文章列表中的索引查找文章
     * @param index
     * @return
     */
    public Article getByIndex(int index);
}
