package com.hahaha.userprofile.task;

import com.hahaha.userprofile.bean.TaskProcess;
import com.hahaha.userprofile.service.TaskSubmitService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:55
 * @Description:
 **/
public class TaskSubmitter implements Runnable {
    TaskProcess taskProcess;

    @Autowired
    private TaskSubmitService taskSubmitService;

    public TaskSubmitter(TaskProcess taskProcess) {
        this.taskProcess = taskProcess;
    }

    public void submit() {
        taskSubmitService.submitTask(taskProcess, false);
    }

    @Override
    public void run() {
        submit();
    }
}
