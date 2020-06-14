package com.blog.controller;

import com.blog.domin.Article;
import com.blog.domin.Statistics;
import com.blog.domin.User;
import com.blog.reposity.ArticleRepository;
import com.blog.reposity.StatisticsRepository;
import com.blog.reposity.UserReposity;
import com.blog.service.ArticleService;
import com.blog.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserReposity userReposity;

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/post")
    @ResponseBody
    public String postArticle(@RequestParam String title, @RequestParam String content,@RequestParam String atype, @RequestParam int uid) throws Exception{
        int num=articleService.postArticle(title,content,atype,new Date(),uid);
        if(num>0){
            Article article=articleService.getPostContent(uid);
            statisticsService.insertRecord(article.getId(),article.getUser_id());
            return "发表成功";
        }else{
            throw new Exception("发表失败");
        }
    }

    @GetMapping("postcontent")
    public Article getPostContent(int uid){
       return articleService.getPostContent(uid);
    }

    @GetMapping("content")
        public Optional<Article> findBiId(Integer aid){
        return articleRepository.findById(aid);
    }

    @PostMapping("edit")
    @ResponseBody
    public String editArticle(@RequestParam String title,@RequestParam String content,@RequestParam String atype, @RequestParam int aid){
        if (articleService.editArticle(title,content,atype,aid)>0){
            return "编辑成功";
        }else{
            return "编辑失败";
        }
    }

    @PostMapping("delete")
    @ResponseBody
    public String deleteArticle(@RequestParam int aid){
        if(articleService.deleteArticle(aid)>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @GetMapping("all")
    public List<Article> getALL(){
        return articleRepository.findAll();
    }


    @PostMapping("useralist")
    public List<Article> getUserAList(String username){
        return articleService.getUserAList(username);
    }


    @GetMapping("sort")
    public ArrayList<Article> getSortAList(int type) throws Exception{
        List<Article> articles=articleService.getSortAList(type);
        ArrayList allInfo= new ArrayList();
        ListIterator listIterator=articles.listIterator();

        while (listIterator.hasNext()){
            Article a= (Article) listIterator.next();
            ArrayList o=new ArrayList();
            o.add(a);

            Article article= articleRepository.findById(a.getId());
            int user_id=article.getUser_id();
            User user=userReposity.getUserInfoById(user_id);
            ArrayList uList=new ArrayList();
            uList.add(user.getId());
            uList.add(user.getUsername());
            uList.add(user.getAvatar());
            o.add(uList);
            allInfo.add(o);

        }
        if(allInfo!=null){
            return allInfo;
        }else{
            throw new Exception("无文章");
        }

    }

    @PostMapping("search")
    public ArrayList getSearchContent(@RequestParam String keyword){
       return articleService.getSearchContent(keyword);
    }

    @PostMapping("typearticle")
    public ArrayList getTypeArticle(@RequestParam String type){
        return articleService.getTypeArticle(type);
    }

//    @GetMapping("allarticlesinfo")
//    public ArrayList allArticle(){
//        return articleService.getAllArticle();
//    }



    @GetMapping("allarticlesinfo")
    public ArrayList<Article> allArticle() throws Exception{
        List<Article> articles=articleRepository.findAll();
        ArrayList all= new ArrayList();
        ListIterator listIterator=articles.listIterator();

        while (listIterator.hasNext()){
            Article a= (Article) listIterator.next();
            ArrayList o=new ArrayList();
            o.add(a);

            Article article= articleRepository.findById(a.getId());
            int user_id=article.getUser_id();
            User user=userReposity.getUserInfoById(user_id);
            ArrayList uList=new ArrayList();
            uList.add(user.getId());
            uList.add(user.getUsername());
            uList.add(user.getAvatar());
            o.add(uList);

            Statistics statistics=statisticsService.getArticleInfo(a.getId());
            ArrayList sList=new ArrayList();
            sList.add(statistics.getClick_num());
            sList.add(statistics.getComment_num());
            sList.add(statistics.getLikes_num());
            o.add(sList);
            all.add(o);

        }
        if(all!=null){
            return all;
        }else{
            throw new Exception("无文章");
        }

    }
}
