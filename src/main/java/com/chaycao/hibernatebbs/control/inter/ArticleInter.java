package com.chaycao.hibernatebbs.control.inter;


import com.chaycao.hibernatebbs.bean.Article;

import java.util.List;

/**
 * Created by chaycao on 2017/7/26.
 */
public interface ArticleInter {

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> selectAll();

    /**
     * 添加文章
     * @param title 标题
     * @param comment 内容
     * @param userId 用户ID
     * @return
     */
    public boolean add(String title, String comment, int userId);

    /**
     * 根据文章列表中的索引查找文章
     * @param index
     * @return
     */
    public Article selectByIndex(int index);
}
