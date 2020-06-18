package com.blog.reposity;

import com.blog.domin.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface HobbyReposity extends JpaRepository<Hobby,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update tb_hobby set hobby_name=?1 where user_id=?2",nativeQuery = true)
    public int updateHobby(String hobby,int user_id);
}
