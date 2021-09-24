package com.hahaha.userprofile.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 14:36
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitEvent {
    Long taskProcessId;

    Long taskId;

    String appName;

    String jarFilePath;

    String mainClass;

    String master;

    String deployMode;

    Map<String, String> sparkArgs;

    Map<String, String> sparkConf;

    List<String> appArgs;
}
