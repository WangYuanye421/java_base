package org.wyy.tech.threadlocal;/**
 * @author Wyy
 **/

/**
 * threadlocal源码
 * @author Yuanye Wang
 * @date 2022/8/15 13:32
 **/
public class ThreadLocalDemo {
	public static void main(String[] args) {


		for (int i = 0; i < 3; i++) {
			Thread a = new Thread(new Runnable() {
				ThreadLocal<String> tl = new ThreadLocal<>();

				@Override
				public void run() {
					tl.set(Thread.currentThread().getName());
					System.out.println(tl.get());
				}
			});
			a.start();
		}
	}
}
