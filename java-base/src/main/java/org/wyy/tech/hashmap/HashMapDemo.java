package org.wyy.tech.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Wang Yuanye
 * @version : v1.0
 * @description :
 * HashMap源码测试
 * @date : 2021/1/12 上午1:29
 **/
public class HashMapDemo {
    public static void main(String[] args) {
        // 指定容量与不指定容量的区别
        Map<Integer, Integer> map = new HashMap<>();


        // 元素put的流程
        map.put(1,1);
        map.put(2,2);
        System.out.println(map.size());
        // 扩容流程

        // 删除操作


    }
}
