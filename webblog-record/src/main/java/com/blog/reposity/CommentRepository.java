package com.blog.reposity;

import com.blog.domin.Article;
import com.blog.domin.Comment;
import com.blog.domin.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query(value = "select * from tb_comment where aid=?1 order by creat_time desc ",nativeQuery = true)
    public List<Comment> findArticleComment(int aid);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_comment(aid,content,creat_time,username,avatar) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    public int insertComment(int aid, String content, Date date, String username,String avatar);


    @Transactional
    @Modifying
    @Query(value = "update tb_comment set content=?1 where id=?2")
    public int updateCommetn(String content,int cid);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_comment where id=?1",nativeQuery = true)
    public int deleteComment(int cid);


    @Query(value = "select aid from tb_comment group by aid order by MAX(creat_time) desc",nativeQuery = true)
    public List<Integer> getSortActive();

    @Query(value = "SELECT username AS COUNT from tb_comment  GROUP BY username LIMIT 0,8",nativeQuery = true)
    public List<String> getMostCommetn();

    /*管理系统*/
    @Transactional
    @Modifying
    @Query(value = "delete from tb_comment where aid=?1 or username=?2",nativeQuery = true)
    public int deleteAC(int aid,String username);


    @Query(value = "delete from tb_comment where aid=?1",nativeQuery = true)
    public int deleteC(int aid);

}
