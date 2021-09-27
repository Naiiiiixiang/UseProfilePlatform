package com.hahaha.userprofile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.UserGroup;
import com.hahaha.userprofile.mapper.UserGroupMapper;
import com.hahaha.userprofile.service.UserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:48
 * @Description: 服务实现类
 **/
@Service
@Slf4j
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements UserGroupService {
}
