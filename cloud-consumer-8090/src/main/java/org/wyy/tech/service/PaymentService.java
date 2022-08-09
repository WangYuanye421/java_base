package org.wyy.tech.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wyy.tech.entity.BizPayment;
import org.wyy.tech.mapper.BizPaymentMapper;

import javax.annotation.Resource;

/**
 * @author Wyy
 **/
@Service
public class PaymentService {
	@Resource
	BizPaymentMapper mapper;

	@Transactional(rollbackFor = Exception.class)
	public void insert(BizPayment payment) {
		mapper.insert(payment);
	}

	@GlobalTransactional(name = "seata-demo", timeoutMills = 30000, rollbackFor = Exception.class)
	public void insert2(BizPayment payment) {
		mapper.insert(payment);
	}

}
