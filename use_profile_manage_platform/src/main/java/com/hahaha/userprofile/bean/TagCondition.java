package com.hahaha.userprofile.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 14:38
 * @Description:
 **/
@Data
public class TagCondition {
    String tagCode;
    String tagName;
    String operatorName;
    String operator;
    List<String> tagValues;
    List<String> tagCodePath;
}
