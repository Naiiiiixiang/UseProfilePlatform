package com.hahaha.userprofile.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 14:39
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagInfo {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tagCode;

    private String tagName;

    private Long tagLevel;

    private Long parentTagId;

    @TableField(exist = false)
    private Long parentTagLevel;

    @TableField(exist = false)
    private String parentTagCode;

    @TableField(exist = false)
    private String parentTagName;

    private String tagValueType;


    private String tagType;

    private Long tagTaskId;

    private String tagComment;

    private Date createTime;
    @TableField(exist = false)
    private String taskStatus;
}
