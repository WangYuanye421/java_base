package org.wyy.tech.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.wyy.tech.entity.biz.Employee;

import javax.annotation.Resource;

/**
 * @author Wyy
 **/
@Controller
@RequestMapping("emp")
public class EmpController {
    @Value("${provider.url}")
    private String providerUrl;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("getEmp")
    public @ResponseBody Employee getEmp(){
        Employee emp = restTemplate.getForObject(providerUrl, Employee.class);
        return emp;
    }
}
