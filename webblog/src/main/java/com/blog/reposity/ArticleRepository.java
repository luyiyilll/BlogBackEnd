package com.blog.reposity;


import com.blog.domin.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into tb_article(title,content,atype,post_date,user_id) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    public int postArticle(String title, String content,String atype, Date date, int id);

    @Query(value = "select * from tb_article where user_id=?1 order by post_date desc limit 1",nativeQuery = true)
    public Article getPostContent(int uid);

    @Query(value =  "select * from tb_article where user_id = ?1 order by post_date desc ",nativeQuery = true)
    public List<Article> getUserAList(int uid);

    @Query(value = "select * from tb_article where id = ?1",nativeQuery = true)
    public Article findById(int aid);


    @Transactional
    @Modifying
    @Query(value = "update tb_article set title=?1,content=?2,atype=?3 where id=?4",nativeQuery = true)
    public int eidtArticle(String title,String content,String atype,int aid);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_article where id=?1",nativeQuery = true)
    public int deleteArticle(int aid);

    @Query(value = "select * from tb_article order by post_date desc ",nativeQuery = true)
    public List<Integer> getSortLast();

    @Query(value = "SELECT * FROM ((SELECT a.id,title,post_date,content,avatar,username,comment_num,likes_num from tb_article a,tb_user u,tb_statistics s WHERE a.user_id = u.id  and a.id = s.aid)as i) WHERE i.title like concat('%',?1,'%') OR i.username like concat('%',?1,'%')",nativeQuery = true)
    public ArrayList getSearchContent(String keyword);


    @Query(value = "SELECT * from ((select  a.id,title,content,post_date,avatar,username,atype from tb_article a,tb_user u where user_id=u.id) as i) WHERE i.atype=?1",nativeQuery = true)
    public ArrayList getTypeArticle(String type);

    @Query(value = "select id from tb_article where user_id=?1")
    public List<Integer> findUserAid(int uid);
}
