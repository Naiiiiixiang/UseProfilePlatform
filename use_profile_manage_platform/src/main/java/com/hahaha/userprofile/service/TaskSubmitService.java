package com.hahaha.userprofile.service;

import com.hahaha.userprofile.bean.TaskProcess;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:23
 * @Description: 服务类
 **/
@Service
public interface TaskSubmitService {
    void submitTask(TaskProcess taskProcess, boolean isRetry);
}
