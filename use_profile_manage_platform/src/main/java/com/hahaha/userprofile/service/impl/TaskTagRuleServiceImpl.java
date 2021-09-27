package com.hahaha.userprofile.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.TaskTagRule;
import com.hahaha.userprofile.mapper.TaskTagRuleMapper;
import com.hahaha.userprofile.service.TaskTagRuleService;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:47
 * @Description: 服务实现类
 **/
@Service
@DS("mysql")
public class TaskTagRuleServiceImpl extends ServiceImpl<TaskTagRuleMapper, TaskTagRule> implements TaskTagRuleService {
}
