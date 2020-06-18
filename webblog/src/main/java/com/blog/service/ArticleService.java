package com.blog.service;

import com.blog.domin.Article;
import com.blog.domin.User;
import com.blog.reposity.ArticleRepository;
import com.blog.reposity.CommentRepository;
import com.blog.reposity.StatisticsRepository;
import com.blog.reposity.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserReposity userReposity;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;

    public int postArticle(String title, String content,String atype, Date postdate, int uid){

       return articleRepository.postArticle(title,content,atype,postdate,uid);
    }

    public Article getPostContent(int uid){
        return articleRepository.getPostContent(uid);
    }

    public Article findById(int aid){ return articleRepository.findById(aid);}

    public int editArticle(String title,String content,String atype,int aid){
        return articleRepository.eidtArticle(title,content,atype,aid);
    }

    public int deleteArticle(int aid){
        commentRepository.deleteC(aid);
        return articleRepository.deleteArticle(aid);
    }

    public List<Article> getUserAList(String username){
        User user=userReposity.findByName(username);
        return articleRepository.getUserAList(user.getId());

    }

    public List<Article> getSortAList(int type){
        ArrayList articles=new ArrayList();
       switch (type){
           case 0://活跃
               List<Integer> aid1=commentRepository.getSortActive();
               for (int id : aid1) {
                   articles.add(articleRepository.findById(id));
               }
               break;
           case 1://投票最多
               List<Integer> aid2 =statisticsRepository.getSortPost();
               for (int id : aid2) {
                   articles.add(articleRepository.findById(id));
               }
               break;
           case 2://最近

               List<Integer> aid3 = articleRepository.getSortLast();
               for (int id : aid3) {
                   articles.add(articleRepository.findById(id));
                   System.out.println("----------");
                   System.out.println(articles);
               }
               break;
           case 3://零回复
               List<Integer> aid4=statisticsRepository.getSortZero();
               for (int id : aid4) {
                   articles.add(articleRepository.findById(id));
               }
       }
       return articles;
    }

    public ArrayList getSearchContent(String keyword){

        return  articleRepository.getSearchContent(keyword);


    }

    public ArrayList getTypeArticle(String type){
            return articleRepository.getTypeArticle(type);
    }


    public List<String> getAllType(){return articleRepository.getAllType();}

    public List<Article> getTypeArticles(String type){return articleRepository.getTypeArticles(type);}


//    public ArrayList timeLineArticle(){
//        List<Article> articles=articleRepository.findAll();
//        ArrayList all= new ArrayList();
//        ListIterator listIterator=articles.listIterator();
//        ArrayList o = new ArrayList();
//        while (listIterator.hasNext()) {
//            Article a = (Article) listIterator.next();
//            String date=a.getPost_date().substring(0,4);
//            o.add(date);
//        }
//        ArrayList dateList  = new ArrayList<String>(new HashSet<String>(o));
//        ArrayList allList=new ArrayList();
//        ArrayList list=new ArrayList();
//        while (listIterator.hasNext()){
//            Article a = (Article) listIterator.next();
//            if(dateList.contains(a.getPost_date().substring(0,4))){
//
//                list.add(a.getPost_date().substring(0,4));
//            }
//        }
//    }

    /*管理系统*/



//    public ArrayList getAllArticle(){
//
//    }
//    public ArrayList getAllArticleInfo(){
//        List<Article> all= articleRepository.findAll();
//
//        ArrayList allInfo= (ArrayList) all;
//
//        for (Article article : all) {
//            Optional<User> user= userReposity.findById(article.getUser_id());
//            System.out.println(article);
//            User userinfo=user.get();
//            ArrayList u=new ArrayList();
//            u.add(userinfo.getId());
//            u.add(userinfo.getUsername());
//            u.add(userinfo.getAvatar());
//
//            System.out.println("u");
//            System.out.println(u);
//            allInfo.add(u);
//
//            System.out.println("allInfo");
//            System.out.println(allInfo);
//
//        }
//
//        return allInfo;
//
//    }
}
