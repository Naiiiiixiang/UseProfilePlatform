package com.hahaha.userprofile.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.FileInfo;
import com.hahaha.userprofile.bean.TagCommonTask;
import com.hahaha.userprofile.mapper.TagCommonTaskMapper;
import com.hahaha.userprofile.service.FileInfoService;
import com.hahaha.userprofile.service.TagCommonTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 15:21
 * @Description: 服务实现类
 **/
@Service
@DS("mysql")
public class TagCommonTaskServiceImpl extends ServiceImpl<TagCommonTaskMapper, TagCommonTask> implements TagCommonTaskService {

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public TagCommonTask getTagCommonTaskWithJarFile(Long id) {
        TagCommonTask tagCommonTask = getById(id);
        if (tagCommonTask != null) {
            FileInfo fileInfo = fileInfoService.getById(tagCommonTask.getTaskFileId());
            tagCommonTask.setFileInfo(fileInfo);
        }
        return tagCommonTask;
    }
}
