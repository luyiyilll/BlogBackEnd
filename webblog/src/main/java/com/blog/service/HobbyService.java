package com.blog.service;

import com.blog.reposity.HobbyReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HobbyService {

    @Autowired
    private HobbyReposity hobbyReposity;

    public int updateHobby(String[] hobbies,int id){
        int num=0;

        for (int j = 0; j < hobbies.length; j++) {
            num = hobbyReposity.updateHobby(hobbies[j], id);
            if (num <= 0) {
                num = 0;
                break;
            }
        }
        return num;

    }
}
