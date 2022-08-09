package org.wyy.tech.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.wyy.tech.entity.BizOrder;
import org.wyy.tech.mapper.BizOrderMapper;

import javax.annotation.Resource;

/**
 * @author Wyy
 **/
@Service
public class BizOrderService {
	@Resource
	private BizOrderMapper mapper;
	@Resource
	RestTemplate restTemplate;

	@Value("${payment.save1}")
	private String paymentUrl1;

	@Value("${payment.save2}")
	private String paymentUrl2;

	@Transactional(rollbackFor = Exception.class)
	public void insert(BizOrder order){
		mapper.insert(order);
		// 远程调用
		String msg = restTemplate.postForObject(paymentUrl1, order, String.class);
		throw new RuntimeException("业务异常");
	}

	@GlobalTransactional(name = "seata-demo", timeoutMills = 30000, rollbackFor = Exception.class)
	public void insert2(BizOrder order){
		mapper.insert(order);
		// 远程调用
		String msg = restTemplate.postForObject(paymentUrl2, order, String.class);
		System.out.println(1/0);
	}
}
