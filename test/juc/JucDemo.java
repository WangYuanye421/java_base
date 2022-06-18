package juc;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote juc源码分析
 * @date : 2022/6/2 04:06
 **/
public class JucDemo {

    @Test
    public  void concurrentHashMap(){
        // 初始化
        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>(16);

        // put操作
        map.put(1,"wyy");
        map.put(2,"wyy2");

        // get操作
        System.out.println(map.get(1));

    }

    @Test
    public void AqsDemo(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
