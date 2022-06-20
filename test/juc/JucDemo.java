package juc;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote juc源码分析
 * @date : 2022/6/2 04:06
 **/
public class JucDemo {

    /**
     * concurrentHashMap 基本使用
     *
     * @author wang.yuanye
     * @date 2022/6/18 08:16
     */
    @Test
    public  void concurrentHashMap(){
        // 调用该构造函数初始化,仅确定了扩容的大小,扩容尚未开始
        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>(16);

        // put操作
        map.put(1,"wyy");
        map.put(1,"wyy2");
        map.put(3,"wyy3");
        // get操作
        String ele = map.get(1);
        System.out.println(ele);

    }

    @Test
    public void AqsDemo(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
