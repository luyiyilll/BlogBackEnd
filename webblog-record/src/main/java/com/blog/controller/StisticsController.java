package com.blog.controller;

import com.blog.domin.Statistics;
import com.blog.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("likeuser")
    public List<Statistics> getLikesUser(@RequestParam int aid){
        return statisticsService.getLikeUser(aid);
    }

    @PostMapping("updatecnum")
    @ResponseBody
    public String updateClickNum(int aid) throws Exception{
        if(statisticsService.updateClickNum(aid)>0){
            return "操作成功";
        }else{
            throw new Exception("操作失败");
        }
    }
}
