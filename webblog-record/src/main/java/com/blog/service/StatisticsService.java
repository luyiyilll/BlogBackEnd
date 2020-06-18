package com.blog.service;

import com.blog.domin.Statistics;
import com.blog.reposity.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<Statistics> getLikeUser(int aid){
        return statisticsRepository.getLikeUser(aid);
    }


    public int insertRecord(int aid,int uid){
        return statisticsRepository.insertRecord(aid,uid);
    }

    public int updateClickNum(int aid){
        int click_num=statisticsRepository.getClickNum(aid);
        int num=click_num+1;
        return statisticsRepository.updateClickNum(num,aid);
    }

    /*管理系统*/

    public Statistics getArticleInfo(int aid){
        return  statisticsRepository.findById(aid).get();
    }
}
