package com.hahaha.userprofile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hahaha.userprofile.bean.TaskProcess;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:23
 * @Description: 服务类
 **/
@Service
public interface TaskProcessService extends IService<TaskProcess> {
    void updateStatus(Long taskProcessId, String status);

    void updateStatus(Long taskProcessId, String yarnAppId, String status);

    void genTaskProcess(String taskDate);

    List<TaskProcess> getTodoTaskProcessList(String taskTime);
}
