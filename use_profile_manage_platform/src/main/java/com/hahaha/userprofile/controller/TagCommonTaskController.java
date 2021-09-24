package com.hahaha.userprofile.controller;

import com.alibaba.fastjson.JSON;
import com.hahaha.userprofile.bean.TagCommonTask;
import com.hahaha.userprofile.service.impl.TagCommonTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 15:20
 * @Description:
 **/
@RestController
public class TagCommonTaskController {
    @Autowired
    TagCommonTaskServiceImpl tagCommonTaskServiceImpl;

    @PostMapping("/tagcommon")
    @CrossOrigin
    public String saveTagCommonTask(@RequestBody TagCommonTask tagCommonTask) {
        tagCommonTask.setUpdateTime(new Date());
        tagCommonTaskServiceImpl.saveOrUpdate(tagCommonTask);
        return "success";
    }

    @GetMapping("/tagcommon/{id}")
    @CrossOrigin
    public String getTagCommonTask(@PathVariable("id") Long id) {
        TagCommonTask tagCommonTask = tagCommonTaskServiceImpl.getTagCommonTaskWithJarFile(id);
        if (tagCommonTask == null) {
            tagCommonTask = new TagCommonTask();
            tagCommonTask.setId(1L);
        }
        return JSON.toJSONString(tagCommonTask);
    }
}
