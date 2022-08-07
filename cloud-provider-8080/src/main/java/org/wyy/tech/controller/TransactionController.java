package org.wyy.tech.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.wyy.tech.entity.BizOrder;
import org.wyy.tech.mapper.BizOrderMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 分布式事务
 * @author Wyy
 **/
@Controller
@RequestMapping("order")
public class TransactionController {
    @Resource
    private BizOrderMapper mapper;
    @Resource
    RestTemplate restTemplate;

    @Value("${payment.save1}")
    private String paymentUrl1;

    @Value("${payment.save2}")
    private String paymentUrl2;
    /**
     * 无分布式事务
     * @param request
     * @return
     */
    @PostMapping("save1")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String save1(HttpServletRequest request){
        String param = request.getParameter("param");
        JSONObject jsonObject = JSONObject.parseObject(param);
        BizOrder order = new BizOrder();
        order.setOrderNo(jsonObject.getString("orderNo"));
        order.setProductName(jsonObject.getString("productName"));
        mapper.insert(order);
        // 远程调用
        String msg = restTemplate.postForObject(paymentUrl1, param, String.class);
        if("payment success".equals(msg)) {
            return "success";
        }
        return "error";
    }


    /**
     * 无分布式事务
     * @param request
     * @return
     */
    @PostMapping("save2")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String save2(HttpServletRequest request){
        String param = request.getParameter("param");
        JSONObject jsonObject = JSONObject.parseObject(param);
        BizOrder order = new BizOrder();
        order.setOrderNo(jsonObject.getString("orderNo"));
        order.setProductName(jsonObject.getString("productName"));
        mapper.insert(order);


        return "success";
    }
}
