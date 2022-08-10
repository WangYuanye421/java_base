package org.wyy.tech.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wyy.tech.entity.BizOrder;
import org.wyy.tech.service.BizOrderService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 分布式事务
 * @author Wyy
 **/
@Controller
@RequestMapping("order")
public class ProviderController {
    @Resource
    BizOrderService orderService;

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
        orderService.insert(order);
        return "success";
    }


    /**
     * 分布式事务
     * @param request
     * @return
     */
    @PostMapping("save2")
    @ResponseBody
    public String save2(HttpServletRequest request){
        String param = request.getParameter("param");
        JSONObject jsonObject = JSONObject.parseObject(param);
        BizOrder order = new BizOrder();
        order.setOrderNo(jsonObject.getString("orderNo"));
        order.setProductName(jsonObject.getString("productName"));
        orderService.insert2(order);
        return "success";
    }
}
