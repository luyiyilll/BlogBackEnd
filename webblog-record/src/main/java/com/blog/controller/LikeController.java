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

    @PostMapping("userlist")
    @ResponseBody
    public List<User> getUserList(@RequestParam int aid){
        return likeService.getLikeUser(aid);
    }

    @PostMapping("delete")
    @ResponseBody
    public int deleteLike(@RequestParam int aid,@RequestParam int uid){
        return likeService.deleteLike(aid,uid);
    }

    @PostMapping("add")
    @ResponseBody
    public int addLike(@RequestParam int aid,@RequestParam int uid){
        return likeService.addLike(aid,uid);
    }

}
