package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/5/30 08:21
 **/
public class CustomThreadPool02 {
    public static void main(String[] args) {
        // 执行拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1),new ThreadFactory(){
            AtomicInteger integer = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "mxsm-"+integer.getAndIncrement());
            }
        });
        for(int i = 0; i < 3; ++i){
            final int b = i;
            executorService.execute(() -> {
                for (;;){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName()+ b +" 当前时间："+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("主线程结束");
    }
}
