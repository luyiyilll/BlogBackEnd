package com.blog.service;

import com.blog.domin.User;
import com.blog.reposity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserReposity userReposity;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private LikeRepository likeRepository;

    public int registerUser(String username,String password,String avatar){
        int num=userReposity.insertUser(username,password,avatar,0);
        if(num>0){
            return num;
        }
        return 0;
    }
    public String checkUserName(String username){
        if (userReposity.checkName(username)!=null){
            return username;
        }
        return null;
    }

    public Optional findById(int id) throws Exception{
      Optional user =  userReposity.findById(id);
      if (user!=null){
          return user;
      }else{
          throw new Exception("没有该用户");
      }
    }

    public User login(String username,String password){
        User user=userReposity.loginUser(username,password);
        if(user!=null){
            return user;
        }else{
            return null;
        }
    }

    public int updateUserInfo(int id,String school,String college, String sex, String introduce) throws Exception{
        int num1=userReposity.updateUserInfo(school,college,sex,introduce,id);
        if (num1>0){
            return num1;
        }else {
            throw new Exception("没有该用户");
        }
    }

    public int updateUserAvatar(String avatar,int id){
        if(userReposity.updateUserAvatar(avatar,id)>0){
            return  userReposity.updateUserAvatar(avatar,id);
        }else{
            return 0;
        }
    }

    public int updatePassword(String password,int id){
        if(userReposity.updatePassword(password,id)>0){
            return userReposity.updatePassword(password, id);
        }else{
            return 0;
        }
    }

    public User getUserInfo(String username){
        return userReposity.getUserInfo(username);
    }

    public User getUserInfoById(int uid){
        return userReposity.getUserInfoById(uid);
    }

    public ArrayList getTopThree(){
        List<Integer> list= statisticsRepository.sortByComment();
        ArrayList allInfo= new ArrayList();
        ListIterator listIterator=list.listIterator();
        while (listIterator.hasNext()){
            int i= (int) listIterator.next();
            User user = userReposity.getUserInfoById(i);
            ArrayList data=new ArrayList();
            data.add(user.getId());
            data.add(user.getUsername());
            data.add(user.getAvatar());
            data.add(user.getIntroduce());
            allInfo.add(data);
        }
        return allInfo;
    }
    public ArrayList getActiveUser(){
       List<String> username=commentRepository.getMostCommetn();
        ArrayList userList= new ArrayList();
        ListIterator listIterator=username.listIterator();
        System.out.println(username);
        while (listIterator.hasNext()){
            String u= (String) listIterator.next();
            ArrayList user =new ArrayList();
            ArrayList uList=new ArrayList();
            User uitem= userReposity.getUserInfo(u);

            uList.add(uitem.getId());
            uList.add(uitem.getUsername());
            uList.add(uitem.getAvatar());
            user.add(uList);
            userList.add(user);

        }
        return userList;
    }


    public ArrayList getRecentVisit(int id){
       List<Integer> ids=userReposity.getRecentVisitId(id);
       ArrayList userList= new ArrayList();
       ListIterator listIterator=ids.listIterator();
        while (listIterator.hasNext()){
            ArrayList info= new ArrayList();
            int uid= (int)listIterator.next();
            User user=userReposity.getUserInfoById(uid);
            info.add(user.getId());
            info.add(user.getUsername());
            info.add(user.getAvatar());
            info.add(user.getIntroduce());
            userList.add(info);
        }
        return userList;
    }

    public int addVisitedUser(int touserid,int userid){
        if(userReposity.selectVisitedUser(touserid,userid)!=0)
            return 0;
        else
            return userReposity.addVisitedUser(touserid,userid);
    }


    /*管理系统*/
    public List getAllUsers(){
        return userReposity.getAllUsers();
    }

    public int deleteUser(int uid,String username){
       List<Integer> allUserAid=articleRepository.findUserAid(uid);
       for (int aid :allUserAid){
           commentRepository.deleteAC(aid,username);
           statisticsRepository.deleteAS(aid);
           likeRepository.deleteAL(aid,uid);
           articleRepository.deleteById(aid);
       }
      return userReposity.deleteUser(uid);
    }

    public Optional<User> getUserInfoByAdmin(int id){
        return userReposity.findById(id);
    }

    public void adminUpdateUser(int id,String password,String avatar,String sex,String school,String edu,String introduce){
//        if(username!=null){
//            userReposity.updateUsername(username,id);
//        }
        if(password!=null){
            userReposity.updatePassword(password,id);
        }
        if(avatar!=null){
            userReposity.updateUserAvatar(avatar,id);
        }
        if(sex!=null){
            userReposity.updateSex(sex,id);
        }
        if(school!=null){
            userReposity.updateSchool(school,id);
        }
        if(edu!=null){
            userReposity.updateEdu(edu,id);
        }
        if(introduce!=null){
            userReposity.updateIntrouce(introduce,id);
        }
    }
}
