package com.blog.controller;

import com.blog.domin.Like;
import com.blog.domin.User;
import com.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     *获取文章的点赞用户
     */
    @PostMapping("userlist")
    @ResponseBody
    public List<User> getUserList(@RequestParam int aid){
        return likeService.getLikeUser(aid);
    }

    /**
     *取消点赞
     */
    @PostMapping("delete")
    @ResponseBody
    public int deleteLike(@RequestParam int aid,@RequestParam int uid){
        return likeService.deleteLike(aid,uid);
    }

    /**
     *点赞
     */
    @PostMapping("add")
    @ResponseBody
    public int addLike(@RequestParam int aid,@RequestParam int uid){
        return likeService.addLike(aid,uid);
    }

}
