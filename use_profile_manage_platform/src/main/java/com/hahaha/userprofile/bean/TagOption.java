package com.hahaha.userprofile.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 14:41
 * @Description:
 **/
@Data
public class TagOption {
    Long tagId;

    Long parentTagId;

    String label;

    String value;

    List<TagOption> children;

    public TagOption(TagInfo tagInfo) {
        this.tagId = tagInfo.getId();
        this.parentTagId = tagInfo.getParentTagId();
        this.label = tagInfo.getTagName();
        this.value = tagInfo.getTagCode();
//        this.children= new ArrayList<>();
    }
}
