package org.wyy.tech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Wyy
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.wyy.tech.mapper"})
public class AppConsumer8090 {
    public static void main(String[] args){
        SpringApplication.run(AppConsumer8090.class, args);
    }
}
