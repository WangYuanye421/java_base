package org.wyy.tech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/8/7 15:17
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.wyy.tech.mapper"})
public class AppProvider8080 {
    public static void main(String[] args){
        SpringApplication.run(AppProvider8080.class, args);
    }
}
