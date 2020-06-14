package com.blog.service;

import com.blog.domin.Article;
import com.blog.domin.Comment;
import com.blog.domin.Like;
import com.blog.reposity.ArticleRepository;
import com.blog.reposity.CommentRepository;
import com.blog.reposity.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<Comment> findArticleComment(int aid){
        return commentRepository.findArticleComment(aid);
    }

    public int insertComment(int aid,String content,String username,String avatar){
        int commemt_num=statisticsRepository.getCommentNum(aid);
        int num=commemt_num+1;
        statisticsRepository.updateCommentNum(num,aid);
        return commentRepository.insertComment(aid,content,new Date(),username,avatar);
    }

    public int updateComment(int cid,String content){
        return commentRepository.updateCommetn(content,cid);
    }

    public int deleteComment(int cid){
        return commentRepository.deleteComment(cid);
    }

    /*管理系统*/

    public ArrayList getAllArticleC(){
        List<Article> articles=articleRepository.findAll();
        ArrayList all= new ArrayList();
        ListIterator listIterator=articles.listIterator();
        while (listIterator.hasNext()){
            ArrayList ac=new ArrayList();
            Article a= (Article) listIterator.next();
            List<Comment> comments= commentRepository.findArticleComment(a.getId());
            ac.add(a);
            ac.add(comments);
            all.add(ac);
        }
        return all;
    }
}
