package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@EnableCaching//开启缓存(Cache+Redis)
//@EnableAsync//开启异步任务(多线程)
@EnableScheduling//开启定时任务
//@EnableEurekaClient//注册中心
//@EnableFeignClients//微服务之间调用1
@SpringBootApplication
//@MapperScan(basePackages = "com.users.modules.*.mapper")//*代表一个包，**代表多个包
public class UserApplication {

    /**
     * Spring Boot默认文件上传大小为2M，多文档上传中总是出现文件大小超出限度
     * https://blog.csdn.net/ccmedu/article/details/78485603
     * 限制上传文件大小
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大 50m 可以使用读取配置
        factory.setMaxFileSize("51200KB"); //51200KB
        /// 设置总上传数据总大小 50m
        factory.setMaxRequestSize("512000KB");//512000KB
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}