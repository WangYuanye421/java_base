package org.wyy.tech.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 创建线程的几种方式
 *
 * @author Yuanye Wang
 * @date 2022/8/15 15:37
 **/
public class CreateThreadDemo {
	private static final Logger logger = LoggerFactory.getLogger(CreateThreadDemo.class);

	public static void main(String[] args) {
		// 方式1
		Thread t1 = new ThreadType();
		t1.setName("方式1");
		t1.start();

		// 方式2
		Thread t2 = new Thread(new ThreadType2());
		t2.setName("方式2");
		t2.start();

		// 方式3
		FutureTask<Void> task = new FutureTask<Void>(new ThreadType3());
		Thread t3 = new Thread(task);
		t3.setName("方式3");
		t3.start();

		// 方式4
		new Thread(() -> {
			logger.info("[创建线程的方式:] 通过实现Runnable接口,lambda写法");
		},"方式4").start();

		// 方式5
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10,
				15, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(64),
				r -> new Thread(()->{
					Thread.currentThread().setName("方式5");
					logger.info("[创建线程的方式:] 自定义线程工厂创建");
				}),
				new ThreadPoolExecutor.AbortPolicy());
		threadPoolExecutor.execute(new ThreadType());
		threadPoolExecutor.shutdown();
	}

}

class ThreadType extends Thread{
	private static final Logger logger = LoggerFactory.getLogger(ThreadType.class);
	@Override
	public void run() {
		logger.info("[创建线程的方式:] 通过继承Thread类");
	}
}

class ThreadType2 implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(ThreadType2.class);
	@Override
	public void run() {
		logger.info("[创建线程的方式:] 通过实现Runnable接口");
	}
}

class ThreadType3 implements Callable<Void> {
	private static final Logger logger = LoggerFactory.getLogger(ThreadType3.class);
	@Override
	public Void call() throws Exception {
		logger.info("[创建线程的方式:] 通过实现Callable接口");
		return null;
	}
}
