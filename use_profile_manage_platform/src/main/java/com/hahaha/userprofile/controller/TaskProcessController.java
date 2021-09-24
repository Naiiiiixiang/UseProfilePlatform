package com.hahaha.userprofile.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hahaha.userprofile.bean.TaskProcess;
import com.hahaha.userprofile.bean.TaskStatusInfo;
import com.hahaha.userprofile.constants.ConstCodes;
import com.hahaha.userprofile.service.TaskProcessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:51
 * @Description:
 **/
@RestController
@Slf4j
public class TaskProcessController {
    @Autowired
    TaskProcessService taskProcessService;

    @PostMapping("/callback/task-status")
    @CrossOrigin
    public void callbackTaskState(@RequestBody TaskStatusInfo taskStatusInfo) {
        log.info("收到回调：" + taskStatusInfo);
        taskProcessService.updateStatus(taskStatusInfo.getTaskProcessId(), taskStatusInfo.getYarnAppId(), taskStatusInfo.getTaskExecStatus());
    }

    @GetMapping("/task-process-list")
    @CrossOrigin
    public String getTaskProcessList(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        int startNo = (pageNo - 1) * pageSize;
        int endNo = startNo + pageSize;
        int count = Math.toIntExact(taskProcessService.count());
        List<TaskProcess> taskProcessList = taskProcessService.list(new QueryWrapper<TaskProcess>().orderByDesc("create_time", "task_busi_date").orderByAsc("task_exec_level").last(" limit " + startNo + "," + endNo));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", taskProcessList);
        jsonObject.put("total", count);

        return jsonObject.toJSONString();
    }

    @RequestMapping("/task-gen")
    @CrossOrigin
    public void genTask(@RequestParam(value = "busidt", required = false) String busidt) {
        if (busidt == null || busidt.length() == 0) {
            busidt = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyyMMdd");
        }
        taskProcessService.genTaskProcess(busidt);
    }


    @RequestMapping("/task-process-retry/{id}")
    @CrossOrigin
    public void taskRetry(@PathVariable("id") Long taskProcessId) {
        taskProcessService.updateStatus(taskProcessId, ConstCodes.TASK_EXEC_STATUS_TODO);
    }

    @DeleteMapping("/task-process/{id}")
    @CrossOrigin
    public void taskDelete(@PathVariable("id") Long taskProcessId) {
        taskProcessService.removeById(taskProcessId);
    }
}