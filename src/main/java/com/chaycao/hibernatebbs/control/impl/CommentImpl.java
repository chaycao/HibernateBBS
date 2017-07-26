package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.Comment;
import com.chaycao.hibernatebbs.control.inter.CommentInter;
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
     * @param context
     * @param userId
     * @param articleId
     * @return
     */
    public boolean add(String context, int userId, int articleId) {
        Connection con = DBUtil.getConn();
        String sql = "INSERT INTO comment(id, context, time, user_id, article_id) values (null, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, context);
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(3, userId);
            ps.setInt(4, articleId);
            success = ps.executeUpdate() > 0 ? true : false;
            ps.close();
            DBUtil.close(con, null, ps, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 根据文章iD查询所有的评论
     * @param articleId
     * @return
     */
    public List<Comment> selectByArticleId(int articleId) {
        Connection con = DBUtil.getConn();
        String sql = "SELECT comment.context, comment.time, user.user_name " +
                "FROM comment " +
                "LEFT JOIN user on comment.user_id = user.id " +
                "LEFT JOIN article on comment.article_id = article.id " +
                "WHERE article.id = " + articleId + " " +
                "ORDER BY comment.time ";
        ResultSet rs = null;
        List<Comment> commentList = new ArrayList<Comment>();
        try {
            // 查询
            Statement st = con.createStatement();
            rs = DBUtil.executeQuery(st, sql);

            // 处理结果
            if(rs != null)
            {
                while (rs.next()){
                    String context = rs.getString(1);
                    Date time = rs.getDate(2);
                    String userName = rs.getString(3);
//                    Comment comment = new Comment(context, time, userName);
//                    commentList.add(comment);
                }
            }

            // 关闭连接
            DBUtil.close(con, st, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
