package com.blog.reposity;

import com.blog.domin.Like;
import com.blog.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserReposity extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into tb_user(username,password,avatar,is_admin) values(?1,?2,?3,?4)",nativeQuery = true)
    public int insertUser(String username,String password,String avatar,int is_admin);

    @Query("select username from tb_user where username=?1")
    public String checkName(String username);


    @Query(value = "select u from tb_user u where u.username=?1 and u.password = ?2")
    public User loginUser(String name,String password);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set school=?1,college=?2,sex=?3,introduce=?4 where id=?5",nativeQuery = true)
    public int updateUserInfo(String school,String college,String sex, String introduce,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set avatar=?1 where id=?2")
    public int updateUserAvatar(String avatar,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set password=?1 where id=?2")
    public int updatePassword(String password,int id);

    @Query(value = "select * from tb_user where id=?1",nativeQuery = true)
    public User findByUserId(int id);

    @Query(value = "select * from tb_user where username=?1",nativeQuery = true)
    public User findByName(String name);

    @Query(value = "select * from tb_user where username=?1",nativeQuery = true)
    public User getUserInfo(String username);

    @Query(value = "select * from tb_user where id=?1",nativeQuery = true)
    public User getUserInfoById(int uid);


    /*管理系统*/
    @Query(value = "select * from tb_user ",nativeQuery = true)
    public List getAllUsers();

    @Transactional
    @Modifying
    @Query(value = "delete from tb_user where id=?1",nativeQuery = true)
    public int deleteUser(int uid);


    @Transactional
    @Modifying
    @Query(value = "update tb_user set username=?1 where id=?2",nativeQuery = true)
    public int updateUsername(String username,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set sex=?1 where id=?2")
    public int updateSex(String sex,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set school=?1 where id=?2")
    public int updateSchool(String school,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set college=?1 where id=?2")
    public int updateEdu(String edu,int id);

    @Transactional
    @Modifying
    @Query(value = "update tb_user set introduce=?1 where id=?2")
    public int updateIntrouce(String introduce,int id);
}
