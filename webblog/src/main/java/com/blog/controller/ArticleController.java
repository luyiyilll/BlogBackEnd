package com.blog.controller;

import com.blog.domin.Article;
import com.blog.domin.Statistics;
import com.blog.domin.User;
import com.blog.reposity.ArticleRepository;
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

    /**
     * 用户发表文章
     */
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

    /**
     * 获得用户刚发表的文章
     */
    @GetMapping("postcontent")
    public Article getPostContent(int uid){
        return articleService.getPostContent(uid);
    }

    /**
     * 根据文章id获取文章
     */
    @GetMapping("content")
    public Optional<Article> findBiId(Integer aid){
        return articleRepository.findById(aid);
    }

    /**
     * 用户编辑文章
     */
    @PostMapping("edit")
    @ResponseBody
    public String editArticle(@RequestParam String title,@RequestParam String content,@RequestParam String atype, @RequestParam int aid){
        if (articleService.editArticle(title,content,atype,aid)>0){
            return "编辑成功";
        }else{
            return "编辑失败";
        }
    }

    /**
     * 用户删除文章
     */
    @PostMapping("delete")
    @ResponseBody
    public String deleteArticle(@RequestParam int aid){
        if(articleService.deleteArticle(aid)>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    /**
     * 获取全部文章
     */
    @GetMapping("all")
    public List<Article> getALL(){
        return articleRepository.findAll();
    }


    /**
     * 获取某个用户发表的文章
     */
    @PostMapping("useralist")
    public List<Article> getUserAList(String username){
        return articleService.getUserAList(username);
    }


    /**
     * 根据文章类型分类并返回
     */
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

    /**
     *搜索文章
     */
    @PostMapping("search")
    public ArrayList getSearchContent(@RequestParam String keyword){
        return articleService.getSearchContent(keyword);
    }

    /**
     * 得到分类文章
     */
    @PostMapping("typearticle")
    public ArrayList getTypeArticle(@RequestParam String type){
        return articleService.getTypeArticle(type);
    }


    /**
     * 获取所有文章以及其用户信息
     */
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

    /**
     *获取分类文章信息
     */
    @GetMapping("alltypearticlesinfo")
    public ArrayList<Article> allTypeArticle(String type) throws Exception{
        List<Article> articles=null;
        String typename="";
        switch (type) {
            case "all":
                articles = articleRepository.findAll();
                break;
            case "life":
                typename="程序人生";
                articles= articleService.getTypeArticles(typename);
                break;
            case "server":
                typename="服务器端";
                articles= articleService.getTypeArticles(typename);
                break;
            case "front":
                typename="HTML/CSS";
                articles= articleService.getTypeArticles(typename);
        }

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

    /**
     * 获取所有分类
     */
    @GetMapping("alltype")
    public List<String> getAllType(){
        return  articleService.getAllType();
    }


    /**
     * 根据时间分类返回文章
     */
//    public ArrayList timeLineArticle(){
//        return articleService.timeLineArticle();
//    }


}
