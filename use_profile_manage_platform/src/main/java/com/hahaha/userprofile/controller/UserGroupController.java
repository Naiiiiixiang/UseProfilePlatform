package com.hahaha.userprofile.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hahaha.userprofile.bean.UserGroup;
import com.hahaha.userprofile.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:52
 * @Description:
 **/
@RestController
public class UserGroupController {
    @Autowired
    UserGroupService userGroupService;

    @RequestMapping("/user-group-list")
    @CrossOrigin
    public String getUserGroupList(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        int startNo = (pageNo - 1) * pageSize;
        int endNo = startNo + pageSize;

        QueryWrapper<UserGroup> queryWrapper = new QueryWrapper<>();
        int count = Math.toIntExact(userGroupService.count(queryWrapper));

        queryWrapper.orderByDesc("id").last(" limit " + startNo + "," + endNo);
        List<UserGroup> userGroupList = userGroupService.list(queryWrapper);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", userGroupList);
        jsonObject.put("total", count);

        return jsonObject.toJSONString();
    }
}
