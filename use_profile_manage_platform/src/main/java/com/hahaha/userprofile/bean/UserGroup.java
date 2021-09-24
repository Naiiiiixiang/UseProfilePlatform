package com.hahaha.userprofile.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 15:05
 * @Description:
 **/
@Data
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)  //声明主键  主键默认的生成方式 Auto= 数据库的auto_increment
    private Long id;

    private String userGroupName;

    private String conditionJsonStr;

    @TableField(exist = false)
    private List<TagCondition> tagConditions;

    private String conditionComment;


    private Long userGroupNum;

    private String updateType;

    private String userGroupComment;

    private Date updateTime;


    private Date createTime;
    @TableField(exist = false)
    private String busiDate;

    public String conditionJsonToComment() {
        StringBuilder comment = new StringBuilder();
        for (TagCondition tagCondition : tagConditions) {
            comment.append(tagCondition.tagName).append(" ").append(tagCondition.operatorName).append(" ").append(StringUtils.join(tagCondition.getTagValues(), ",")).append(" ;\n");
        }
        return comment.toString();
    }
}
