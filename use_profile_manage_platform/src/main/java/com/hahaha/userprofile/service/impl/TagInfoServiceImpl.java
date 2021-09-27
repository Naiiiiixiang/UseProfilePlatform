package com.hahaha.userprofile.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.TagInfo;
import com.hahaha.userprofile.mapper.TagInfoMapper;
import com.hahaha.userprofile.service.TagInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:30
 * @Description: 服务实现类
 **/
@Service
@DS("mysql")
public class TagInfoServiceImpl extends ServiceImpl<TagInfoMapper, TagInfo> implements TagInfoService {
    @Autowired
    TagInfoMapper tagInfoMapper;

    @Override
    public List<TagInfo> getTagInfoAllWithStatus() {
        return tagInfoMapper.getTagInfoAllWithStatus();
    }

    @Override
    public TagInfo getTagInfo(Long taskId) {
        TagInfo tagInfo = getById(taskId);
        if (tagInfo.getTagLevel() > 1L) {
            TagInfo parentTagInfo = getById(tagInfo.getParentTagId());
            tagInfo.setParentTagLevel(parentTagInfo.getTagLevel());
            tagInfo.setParentTagName(parentTagInfo.getTagName());
            tagInfo.setParentTagCode(parentTagInfo.getTagCode());
        }
        return tagInfo;
    }

    @Override
    public List<TagInfo> getTagValueList(String parentTagCode) {
        return tagInfoMapper.getTagValueList(parentTagCode);
    }

    @Override
    public Map<String, TagInfo> getTagInfoMapWithCode() {
        List<TagInfo> tagInfoList = list();
        Map<String, TagInfo> tagInfoMap = new HashMap<>();
        for (TagInfo tagInfo : tagInfoList) {
            tagInfoMap.put(tagInfo.getTagCode(), tagInfo);
        }
        return tagInfoMap;

    }
}
