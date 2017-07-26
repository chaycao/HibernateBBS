package com.chaycao.hibernatebbs.control.inter;


import com.chaycao.hibernatebbs.bean.Comment;

import java.util.List;

/**
 * Created by chaycao on 2017/7/26.
 */
public interface CommentInter {

    /**
     * 添加评论
     * @param context
     * @param userId
     * @param articleId
     * @return
     */
    public boolean add(String context, int userId, int articleId);

    /**
     * 根据文章iD查询所有的评论
     * @param articleId
     * @return
     */
    public List<Comment> selectByArticleId(int articleId);
}
