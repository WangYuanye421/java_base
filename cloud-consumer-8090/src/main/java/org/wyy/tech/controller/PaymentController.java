package org.wyy.tech.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wyy.tech.entity.BizPayment;
import org.wyy.tech.service.PaymentService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Wyy
 **/
@Controller
@RequestMapping("payment")
public class PaymentController {
    @Resource
    PaymentService service;

    @PostMapping("save1")
    @ResponseBody
    public String save1(@RequestBody String order){
        JSONObject jsonObject = JSONObject.parseObject(order);
        BizPayment payment = new BizPayment();
        payment.setOrderNo(jsonObject.getString("orderNo"));
        payment.setPayAmount(new BigDecimal(20));
        payment.setStatus("SUCCESS");
        service.insert(payment);
        return "payment success";
    }

    @PostMapping("save2")
    @ResponseBody
    public String save2(@RequestBody String order){
        JSONObject jsonObject = JSONObject.parseObject(order);
        BizPayment payment = new BizPayment();
        payment.setOrderNo(jsonObject.getString("orderNo"));
        payment.setPayAmount(new BigDecimal(20));
        payment.setStatus("SUCCESS");
        service.insert2(payment);
        return "payment success";
    }
}
