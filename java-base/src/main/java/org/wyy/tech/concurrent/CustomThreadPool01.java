package org.wyy.tech.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/5/25 06:28
 **/
public class CustomThreadPool01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            AtomicInteger integer = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-"+integer.getAndIncrement());
            }
        });
        executorService.execute(() -> {
            System.out.println(1);
            try {
                int i = 1/0;
                System.out.println(i);
            }catch (Exception e){
                System.out.println("异常了");
            }

        });

        // 上方异常不影响下列程序运行
        executorService.execute(() -> {
            for (;;){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+" 当前时间："+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程执行完成");
    }
}
