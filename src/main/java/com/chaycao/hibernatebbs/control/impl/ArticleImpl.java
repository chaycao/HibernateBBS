package com.chaycao.hibernatebbs.control.impl;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.control.inter.ArticleInter;
import com.chaycao.hibernatebbs.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chaycao on 2017/7/9.
 */
public class ArticleImpl implements ArticleInter {
    /**
     * 单例模式
     */
    public final static ArticleImpl INSTANCE = new ArticleImpl();

    public ArticleImpl() {}

    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList() {
        return articleList;
    }
    /**
     * 查询所有文章
     * @return
     */
    public List<Article> selectAll() {
        Connection con = DBUtil.getConn();
        String sql = "SELECT article.id, title, context, time, user.user_name " +
                "FROM article " +
                "LEFT JOIN user on article.user_id = user.id " +
                "ORDER BY article.id ";
        ResultSet rs = null;
        List<Article> articleList = new ArrayList<Article>();
        try {
            // 查询
            Statement st = con.createStatement();
            rs = DBUtil.executeQuery(st, sql);

            // 处理结果
            if(rs != null)
            {
                while (rs.next()){
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String context = rs.getString(3);
                    Date time = rs.getDate(4);
                    String userName = rs.getString(5);
//                    Article article = new Article(id, title, context, time, userName);
//                    articleList.add(article);
                }
            }

            // 关闭连接
            DBUtil.close(con, st, null, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.articleList = articleList; //保存在成员变量中
        return articleList;
    }

    /**
     * 添加文章
     * @param title 标题
     * @param comment 内容
     * @param userId 用户ID
     * @return
     */
    public boolean add(String title, String comment, int userId) {
        Connection con = DBUtil.getConn();
        String sql = "INSERT INTO article(id, title, context, time, user_id) values (null, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, comment);
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(4, userId);
            success = ps.executeUpdate() > 0 ? true : false;
            ps.close();
            DBUtil.close(con, null, ps, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


    /**
     * 根据文章列表中的索引查找文章
     * @param index
     * @return
     */
    public Article selectByIndex(int index) {
        return articleList.get(index);
    }
}
