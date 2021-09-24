package com.hahaha.userprofile.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.hahaha.userprofile.bean.SubmitEvent;
import com.hahaha.userprofile.bean.TagCommonTask;
import com.hahaha.userprofile.bean.TaskInfo;
import com.hahaha.userprofile.bean.TaskProcess;
import com.hahaha.userprofile.constants.ConstCodes;
import com.hahaha.userprofile.service.TagCommonTaskService;
import com.hahaha.userprofile.service.TaskInfoService;
import com.hahaha.userprofile.service.TaskProcessService;
import com.hahaha.userprofile.service.TaskSubmitService;
import com.hahaha.userprofile.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:46
 * @Description:
 **/
@Slf4j
@Service
@DS("mysql")
public class TaskSubmitServiceImpl implements TaskSubmitService {
    @Autowired
    TaskInfoService taskInfoService;

    @Autowired
    TagCommonTaskService tagCommonTaskService;

    @Autowired
    TaskProcessService taskProcessService;

    @Value("${spark.rest.submitter.url}")
    String restSubmitterUrl;

    @Override
    @Async
    public void submitTask(TaskProcess taskProcess, boolean isRetry) {
        TaskInfo taskInfo = taskInfoService.getTaskInfoWithTag(Long.valueOf(taskProcess.getTaskId()));
        taskProcessService.updateStatus(taskProcess.getId(), ConstCodes.TASK_EXEC_STATUS_START);

        SubmitEvent submitEvent = new SubmitEvent();
        Map<String, Map<String, String>> taskArgsMap = checkArgsAndConf(taskInfo.getTaskArgs());
        submitEvent.setTaskProcessId(taskProcess.getId());
        submitEvent.setTaskId(taskProcess.getTaskId());
        submitEvent.setSparkArgs(taskArgsMap.get("args"));
        submitEvent.setSparkConf(taskArgsMap.get("conf"));
        submitEvent.setAppName(taskInfo.getTaskName() + "_" + taskProcess.getTaskBusiDate());
        submitEvent.setMaster("yarn");
        submitEvent.setDeployMode("cluster");
        if (taskInfo.getExecType().equals(ConstCodes.EXEC_TYPE_SQL)) {
            TagCommonTask tagCommonTask = tagCommonTaskService.getTagCommonTaskWithJarFile(ConstCodes.TAG_COMMON_TASK_ID);
            submitEvent.setJarFilePath(tagCommonTask.getFileInfo().getFilePath());
            submitEvent.setMainClass(tagCommonTask.getMainClass());
            submitEvent.setAppArgs(Arrays.asList(taskProcess.getTaskId().toString(), taskProcess.getTaskBusiDate()));
            this.submitToRestSubmitter(submitEvent);
        } else {
            submitEvent.setJarFilePath(taskInfo.getFilePath());
            submitEvent.setMainClass(taskInfo.getMainClass());
            submitEvent.setAppArgs(Arrays.asList(taskProcess.getTaskId().toString(), taskProcess.getTaskBusiDate()));
            this.submitToRestSubmitter(submitEvent);
        }
    }


    public Map<String, Map<String, String>> checkArgsAndConf(String taskArgs) {
        Map<String, Map<String, String>> taskArgsMap = new HashMap<>();
        Map<String, String> sparkArgsMap = new HashMap();
        Map<String, String> sparkConfMap = new HashMap();
        assert taskArgs != null;
        if (taskArgs.length() > 0) {
            String[] taskArgsArr = taskArgs.split("\\r\\n|\\n|\\r");
            for (int i = 0; i < taskArgsArr.length; i++) {
                String arg = taskArgsArr[i];
                String[] argsKV = arg.split("=");
                String argK = argsKV[0];
                String argV = argsKV[1].trim();
                if (argK.contains("master") || argK.contains("deploy")) {
                    continue;
                }
                if (argK.contains("--spark.")) {
                    argK = argK.replace("--", "").trim();
                    sparkConfMap.put(argK, argV);
                } else if (argK.contains("--conf ")) {
                    argK = argK.replace("--conf", "").trim();
                    sparkConfMap.put(argK, argV);
                } else {
                    argK = argK.trim();
                    sparkArgsMap.put(argK, argV);
                }
            }
        }

        taskArgsMap.put("args", sparkArgsMap);
        taskArgsMap.put("conf", sparkConfMap);
        return taskArgsMap;
    }


    public void submitToRestSubmitter(SubmitEvent submitEvent) {
        try {
            String jsonString = JSON.toJSONString(submitEvent);
            HttpUtil.post(restSubmitterUrl, jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("远程提交失败");
        }
    }
}
