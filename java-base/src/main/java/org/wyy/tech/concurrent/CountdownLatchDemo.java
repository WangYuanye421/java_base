package org.wyy.tech.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * countdownLatch 倒计时
 * @author Yuanye Wang
 * @date 2022/8/15 14:11
 **/
public class CountdownLatchDemo {
	private static final Logger logger = LoggerFactory.getLogger(CountdownLatchDemo.class);
	/**
	 * 定义倒计时为 4
	 */
	private final CountDownLatch LATCH = new CountDownLatch(4);
	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

	public void loadSystem(String systemName) {
		logger.info("{} 开始加载...", systemName);
		try {
			LATCH.await();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		logger.info("所有子模块加载完毕!");
		logger.info("{} 加载完毕...", systemName);
	}

	public void loadModule(String moduleName, int mins) {
		logger.info("开始加载{}模块...", moduleName);
		try {
			TimeUnit.SECONDS.sleep(mins);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("加载{}模块完成...", moduleName);
		LATCH.countDown();
	}

	public static void main(String[] args) {
		CountdownLatchDemo demo = new CountdownLatchDemo();
		// 系统虽然最先开始执行,但需要等待子模块加载完毕才能继续
		new Thread(() -> demo.loadSystem("IOT")).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(() -> demo.loadModule("网络检查", RANDOM.nextInt(5))).start();
		new Thread(() -> demo.loadModule("安全检查", RANDOM.nextInt(3))).start();
		new Thread(() -> demo.loadModule("系统监控", RANDOM.nextInt(4))).start();
		new Thread(() -> demo.loadModule("通信模块", RANDOM.nextInt(1))).start();
	}

}
