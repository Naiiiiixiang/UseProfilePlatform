package com.hahaha.userprofile.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hahaha.userprofile.bean.FileInfo;
import com.hahaha.userprofile.mapper.FileInfoMapper;
import com.hahaha.userprofile.service.FileInfoService;
import org.springframework.stereotype.Service;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 15:41
 * @Description: 服务实现类
 **/
@Service
@DS("mysql")
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
}
