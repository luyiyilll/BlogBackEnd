package com.blog.controller;

import com.blog.domin.Comment;
import com.blog.reposity.CommentRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;


    @PostMapping("articlecomment")
    @ResponseBody
    public List<Comment> findArticleComment(@RequestParam int aid){
        return commentService.findArticleComment(aid);
    }

    @PostMapping("insert")
    @ResponseBody
    public String insertComment(@RequestParam int aid,@RequestParam String content,@RequestParam String username,@RequestParam String avatar) throws Exception{
        if(commentService.insertComment(aid,content,username,avatar)>0){
            return "评论成功";
        }else{
            throw new Exception("评论失败");
        }
    }

    @PostMapping("update")
    @ResponseBody
    public String updateComment(@RequestParam int cid,@RequestParam String content) throws Exception{
        if(commentService.updateComment(cid,content)>0){
            return "修改成功";
        }else{
            throw new Exception("修改失败");
        }
    }

    @PostMapping("delete")
    @ResponseBody
    public String deleteComment(@RequestParam int cid) throws Exception{
        if(commentService.deleteComment(cid)>0){
            return "删除成功";
        }else{
            throw new Exception("删除失败");
        }

    }

    @GetMapping("allacomment")
    public ArrayList getAllArticleC(){
        return commentService.getAllArticleC();
    }

}
