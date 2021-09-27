package com.hahaha.userprofile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:48
 * @Description:
 **/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.hahaha.userprofile.mapper")
public class UserProfileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileManagerApplication.class, args);
    }
}
