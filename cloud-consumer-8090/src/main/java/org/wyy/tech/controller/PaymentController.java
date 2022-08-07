package org.wyy.tech.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wyy.tech.entity.BizPayment;
import org.wyy.tech.mapper.BizPaymentMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Wyy
 **/
@Controller
@RequestMapping("payment")
public class PaymentController {
    @Resource
    BizPaymentMapper mapper;

    @PostMapping("save1")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String save1(@RequestBody String order){
        JSONObject jsonObject = JSONObject.parseObject(order);
        BizPayment payment = new BizPayment();
        payment.setOrderNo(jsonObject.getString("orderNo"));
        payment.setPayAmount(new BigDecimal(20));
        payment.setStatus("SUCCESS");
        mapper.insert(payment);
        return "payment success";
    }

    @PostMapping("save2")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public String save2(String order){
        JSONObject jsonObject = JSONObject.parseObject(order);
        BizPayment payment = new BizPayment();
        payment.setOrderNo(jsonObject.getString("orderNo"));
        payment.setPayAmount(new BigDecimal(20));
        payment.setStatus("SUCCESS");
        mapper.insert(payment);
        return "payment success";
    }
}
