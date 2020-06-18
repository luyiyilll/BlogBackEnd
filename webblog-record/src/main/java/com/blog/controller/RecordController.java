package com.blog.controller;

import com.blog.domin.Record;
import com.blog.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("recordList")
    @ResponseBody
    public List<Record> getRecordList(@RequestParam int uid){return recordService.findById(uid);}

    @PostMapping("timerecordList")
    @ResponseBody
    public ArrayList getRecordsByTime(){return recordService.getRecordsByTime();}

    @PostMapping("userrecordList")
    @ResponseBody
    public ArrayList getRecordsByuId(@RequestParam int uid){return recordService.getRecordsByuId(uid);}

    @PostMapping("update")
    @ResponseBody
    public String updateRecord(@RequestParam int recordId,@RequestParam String recordContent) throws Exception{
        if(recordService.updateRecord(recordId,recordContent)>0){
            return "修改成功";
        }else{
            throw new Exception("修改失败");
        }
    }

    @PostMapping("insert")
    @ResponseBody
    public int addRecord(int userId, String content){return recordService.addRecord(userId,content);}

    @PostMapping("delete")
    @ResponseBody
    public int deleteRecord(int recordId){return recordService.deleteRecord(recordId);}

}
