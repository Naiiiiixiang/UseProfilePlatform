package com.hahaha.userprofile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 16:05
 * @Description: 服务类
 **/
public interface HdfsService {
    Long createFile(String path, MultipartFile file);
}
