package com.blog.reposity;

import com.blog.domin.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    @Query(value = "select * from tb_record where user_id = ?1",nativeQuery = true)
    public List<Record> findById(int uid);

    @Query(value = "SELECT * FROM ((select u.id,content,create_time,username,avatar,r.rid from tb_record r,tb_user u where r.user_id=u.id) as i) order by create_time desc ",nativeQuery = true)
    public ArrayList getRecordsByTime();

    @Query(value = "SELECT * FROM ((select u.id,content,create_time,username,avatar,r.rid from tb_record r,tb_user u where r.user_id=u.id) as i) where i.id=?1 order by create_time desc ",nativeQuery = true)
    public ArrayList getRecordsByuId(int uid);

    @Transactional
    @Modifying
    @Query(value = "update tb_record set content=?2 where rid=?1")
    public int updateRecord(int recordId, String recordContent);

    @Transactional
    @Modifying
    @Query(value = "delete from tb_record where rid=?1",nativeQuery = true)
    public int deleteRecord(int recordId);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_record(user_id,content) values(?1,?2)",nativeQuery = true)
    public int addRecord(int userId, String content);
}
