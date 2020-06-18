package com.blog.service;

import com.blog.domin.Like;
import com.blog.domin.User;
import com.blog.reposity.LikeRepository;
import com.blog.reposity.StatisticsRepository;
import com.blog.reposity.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserReposity userReposity;

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<User> getLikeUser(int aid){
        List<User> userList = new ArrayList<User>();
        if(likeRepository.getLikeUser(aid)!=null) {
            List<Like> likeList = likeRepository.getLikeUser(aid);
            for (Like item : likeList) {
                User user = userReposity.findByUserId(item.getUser_id());
                userList.add(user);
            }
        }
        return userList;
    }

    public int deleteLike(int aid,int uid){
        int like_num=statisticsRepository.getLikeNum(aid);
        int likes=like_num-1;
        statisticsRepository.updateLikeNum(likes,aid);
        return likeRepository.deleteLike(aid,uid);
    }

    public  int addLike(int aid,int uid){
        int like_num=statisticsRepository.getLikeNum(aid);
        int likes=like_num+1;
        statisticsRepository.updateLikeNum(likes,aid);
        return  likeRepository.addLike(aid,uid);
    }
}
