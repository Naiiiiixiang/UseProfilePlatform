package com.hahaha.userprofile.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 15:04
 * @Description:
 **/
@Data
@AllArgsConstructor
public class TaskStatusInfo {
    Long taskProcessId;

    Long taskId;

    String yarnAppId;

    String taskExecStatus;
}
