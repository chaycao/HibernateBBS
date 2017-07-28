package com.chaycao.hibernatebbs.servlet;

import com.chaycao.hibernatebbs.bean.Article;
import com.chaycao.hibernatebbs.bean.Comment;
import com.chaycao.hibernatebbs.control.impl.ArticleImpl;
import com.chaycao.hibernatebbs.control.impl.CommentImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chaycao on 2017/7/9.
 *
 * 显示文章内容
 */
@WebServlet(name="toArticleContext"
        , urlPatterns = "/toArticleContext")
public class ToArticleContextServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String query = request.getQueryString();
        String[] reslut = query.split("=");
        int index = Integer.parseInt(reslut[1]);
        request.setAttribute("index", index);
        Article article = ArticleImpl.INSTANCE.getByIndex(index);
        request.setAttribute("title", article.getTitle());
        request.setAttribute("userName", article.getUser().getUserName());
        request.setAttribute("time", article.getTime());
        request.setAttribute("context", article.getContext());
        List<Comment> commentList = CommentImpl.INSTANCE.listByArticle(article);
        request.setAttribute("commentList", commentList);
        // 转发
        request.getRequestDispatcher("/articleContext.jsp").forward(request, response);
    }
}
