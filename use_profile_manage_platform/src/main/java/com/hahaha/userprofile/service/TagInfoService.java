package com.hahaha.userprofile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hahaha.userprofile.bean.TagInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:07
 * @Description: 服务类
 **/
@Service
public interface TagInfoService extends IService<TagInfo> {
    List<TagInfo> getTagInfoAllWithStatus();

    TagInfo getTagInfo(Long taskId);

    List<TagInfo> getTagValueList(String parentTagCode);

    Map<String, TagInfo> getTagInfoMapWithCode();
}
