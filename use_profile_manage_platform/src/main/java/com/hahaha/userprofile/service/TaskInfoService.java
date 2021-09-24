package com.hahaha.userprofile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hahaha.userprofile.bean.TaskInfo;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:07
 * @Description: 服务类
 **/
public interface TaskInfoService extends IService<TaskInfo> {
    void saveTaskInfoWithTag(TaskInfo taskInfo);

    TaskInfo getTaskInfoWithTag(Long taskId);
}
