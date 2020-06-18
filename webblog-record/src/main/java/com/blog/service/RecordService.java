package com.blog.service;

import com.blog.domin.Record;
import com.blog.reposity.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;
    public List<Record> findById(int uid){return recordRepository.findById(uid);}
    public ArrayList getRecordsByTime(){return recordRepository.getRecordsByTime();}
    public ArrayList getRecordsByuId(int uid){return recordRepository.getRecordsByuId(uid);}

    public int updateRecord(int recordId,String recordContent){return recordRepository.updateRecord(recordId,recordContent);}

    public int deleteRecord(int recordId){return recordRepository.deleteRecord(recordId);}

    public int addRecord(int userId, String content){return recordRepository.addRecord(userId,content);}
}
