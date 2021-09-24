package com.hahaha.userprofile.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 14:37
 * @Description:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TagCommonTask对象", description = "")
public class TagCommonTask {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long taskFileId;

    private String mainClass;

    private Date updateTime;

    @TableField(exist = false)
    private FileInfo fileInfo;
}
