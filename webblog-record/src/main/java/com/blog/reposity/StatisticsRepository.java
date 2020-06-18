package com.blog.reposity;

import com.blog.domin.Statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {

    @Query(value = "select * from tb_statistics where aid=?1",nativeQuery = true)
    public List<Statistics> getLikeUser(int aid);

    @Query(value = "select aid from tb_statistics order by likes_num desc",nativeQuery = true)
    public List<Integer> getSortPost();

    @Query(value = "select aid from tb_statistics where comment_num=0",nativeQuery = true)
    public List<Integer> getSortZero();


    @Query(value = "select user_id from tb_statistics group by user_id order by MAX(comment_num)  desc limit 0,3",nativeQuery = true)
    public List<Integer> sortByComment();

    @Query(value = "select comment_num from tb_statistics where aid=?1",nativeQuery = true)
    public int getCommentNum(int aid);

    @Query(value = "select likes_num from tb_statistics where aid=?1",nativeQuery = true)
    public int getLikeNum(int aid);

    @Query(value = "select click_num from tb_statistics where aid=?1",nativeQuery = true)
    public int getClickNum(int aid);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_statistics(aid,click_num,comment_num,likes_num,user_id) values(?1,0,0,0,?2)",nativeQuery = true)
    public int insertRecord(int aid,int uid);

    @Transactional
    @Modifying
    @Query(value = "update tb_statistics set comment_num =?1 where aid=?2",nativeQuery = true)
    public int updateCommentNum(int commemt_num,int aid);

    @Transactional
    @Modifying
    @Query(value = "update tb_statistics set likes_num =?1 where aid=?2",nativeQuery = true)
    public int updateLikeNum(int likes_num,int aid);

    @Transactional
    @Modifying
    @Query(value = "update tb_statistics set click_num =?1 where aid=?2",nativeQuery = true)
    public int updateClickNum(int click_num,int aid);

    /*管理=系统*/
    @Transactional
    @Modifying
    @Query(value = "delete from tb_statistics where aid=?1",nativeQuery = true)
    public int deleteAS(int aid);
}
