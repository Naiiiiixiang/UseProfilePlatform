package com.hahaha.userprofile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.FileInfo;
import com.hahaha.userprofile.bean.TagInfo;
import com.hahaha.userprofile.bean.TaskInfo;
import com.hahaha.userprofile.bean.TaskTagRule;
import com.hahaha.userprofile.constants.ConstCodes;
import com.hahaha.userprofile.mapper.TaskInfoMapper;
import com.hahaha.userprofile.service.FileInfoService;
import com.hahaha.userprofile.service.TagInfoService;
import com.hahaha.userprofile.service.TaskInfoService;
import com.hahaha.userprofile.service.TaskTagRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:31
 * @Description:
 **/
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {
    @Autowired
    TaskTagRuleService taskTagRuleService;

    @Autowired
    TagInfoService tagInfoService;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    @Transactional
    public void saveTaskInfoWithTag(TaskInfo taskInfo) {
        if (taskInfo.getId() != null) {
            TaskTagRule taskTagRuleForRm = new TaskTagRule();
            taskTagRuleForRm.setTaskId(taskInfo.getTagId());
            taskTagRuleService.remove(new QueryWrapper<TaskTagRule>().eq("task_id", taskInfo.getId()));
        }
        saveOrUpdate(taskInfo);
        List<TaskTagRule> taskTagRuleList = taskInfo.getTaskTagRuleList();
        if (taskTagRuleList != null && taskTagRuleList.size() > 0) {
            for (TaskTagRule taskTagRule : taskTagRuleList) {
                taskTagRule.setTaskId(taskInfo.getId());
            }
            taskTagRuleService.saveBatch(taskTagRuleList);
        }
        if (taskInfo.getTaskType().equals(ConstCodes.TASK_TYPE_TAG)) {
            TagInfo tagInfoForUpdate = new TagInfo();
            tagInfoForUpdate.setTagTaskId(taskInfo.getId());
            tagInfoForUpdate.setId(taskInfo.getTagId());
            tagInfoService.updateById(tagInfoForUpdate);
        }
    }

    @Override
    public TaskInfo getTaskInfoWithTag(Long taskId) {
        TaskInfo taskInfo = getById(taskId);
        if (taskInfo.getFileId() != null) {
            FileInfo fileInfo = fileInfoService.getById(taskInfo.getFileId());
            taskInfo.setFileName(fileInfo.getFileName());
            taskInfo.setFilePath(fileInfo.getFilePath());
        }
        List<TaskTagRule> taskTagRuleList = taskTagRuleService.list(new QueryWrapper<TaskTagRule>().eq("task_id", taskId));
        taskInfo.setTaskTagRuleList(taskTagRuleList);
        return taskInfo;
    }
}
