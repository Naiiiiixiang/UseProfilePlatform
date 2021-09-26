package com.hahaha.userprofile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hahaha.userprofile.bean.TagCommonTask;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:06
 * @Description: 服务类
 **/
@Service
public interface TagCommonTaskService extends IService<TagCommonTask> {
    TagCommonTask getTagCommonTaskWithJarFile(Long id);
}
