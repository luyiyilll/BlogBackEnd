package com.blog.controller;


import com.blog.domin.User;
import com.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestParam String username,@RequestParam String password,@RequestParam String avatar)  throws Exception{
        System.out.println(username);
        System.out.println(password);
        System.out.println(avatar);
        if(userService.checkUserName(username)!=null){
            throw new Exception("用户已存在");

        }else {
            int res = userService.registerUser(username,password,avatar);
            if(res>0){
                return "注册成功";
            }else{
                return "注册失败";
            }
        }

    }

    @PostMapping("/login")
    public User loginUser(String username,String password) throws Exception{
        User user=userService.login(username,password);
        if (user!=null){
            return user;
        }else{
            throw new Exception("用户不存在");
        }
    }

    @PostMapping("/update")
    public String updateUserInfo(int id,String school,String college,String sex,String introduce) throws Exception{
        System.out.println(id);
        System.out.println(sex);
        System.out.println(introduce);


        String str = "";
        if (userService.findById(id) != null) {
            int num1 = userService.updateUserInfo(id,school,college,sex, introduce);

            if (num1 > 0) {
                str = "编辑成功";
            } else {

                throw new Exception("编辑失败");
            }
        } else {
            throw new Exception("该用户不存在");
        }
        return str;
    }

    @PostMapping("/avatar")
    public String updateAvatar(String avatar,int id) throws Exception {
        int num=userService.updateUserAvatar(avatar,id);
        if (num >0){
            return "修改成功";
        }else{
            throw new Exception("修改失败");
        }
    }

    @PostMapping("password")
    public String updatePassword(String password ,int id) throws Exception{
        int num =userService.updatePassword(password, id);
        if (num>0){
            return "修改成功";
        }else{
            throw new Exception("修改失败");
        }
    }

    @PostMapping("info")
    public ArrayList getUserInfo(String username) {
         User user=userService.getUserInfo(username);
        ArrayList info=new ArrayList();
        info.add(user.getId());
        info.add(user.getUsername());
        info.add(user.getAvatar());
        info.add(user.getIs_admin());
        return info;
    }

    @PostMapping("infoid")
    public ArrayList getUserInfoById(int uid) {
        User user=userService.getUserInfoById(uid);
        ArrayList info=new ArrayList();
        info.add(user.getId());
        info.add(user.getUsername());
        info.add(user.getAvatar());
        return info;
    }

    @GetMapping("three")
    public ArrayList getTopThree(){
        return userService.getTopThree();
    }

    @GetMapping("activeuser")
    public ArrayList getActiveUser(){
        return userService.getActiveUser();
    }


    /*管理系统*/
    @GetMapping("allusers")
    public List getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("deleteuser")
    @ResponseBody
    public String deleteUser(@RequestParam int uid,@RequestParam String username) throws Exception{
        if(userService.deleteUser(uid,username)>0){
            return "删除成功";
        }else{
            throw new Exception("删除失败");
        }

    }

    @PostMapping("infobyadmin")
    public Optional<User> getUserInfoByAdmin(int id){
        return userService.getUserInfoByAdmin(id);
    }

    @PostMapping("adminupdateuser")
    public void adminUpdateUser(int id,String password,String avatar,String sex,String school,String edu,String introduce){
         userService.adminUpdateUser(id,password,avatar,sex,school,edu,introduce);
    }
}
