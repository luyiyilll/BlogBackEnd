package com.blog.reposity;

import com.blog.domin.Like;
import com.blog.domin.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Integer> {
    @Query(value = "select * from tb_like_record where aid=?1",nativeQuery = true)
    public List<Like> getLikeUser(int aid);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_like_record where aid=?1 and user_id=?2",nativeQuery = true)
    public int deleteLike(int aid,int uid);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_like_record(aid,user_id) values(?1,?2)",nativeQuery = true)
    public int addLike(int aid,int uid);

    /*管理系统*/
    @Transactional
    @Modifying
    @Query(value = "delete from tb_like_record where aid=?1 or user_id=?2",nativeQuery = true)
    public int deleteAL(int aid,int uid);
}
