package collection;

import javafx.util.Pair;
import org.junit.Test;
import sun.misc.Version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote hashMap源码测试
 * @date : 2021/3/24 下午5:35
 **/
public class CollectionDemo {

    /**
     * 初始过程
     */
    @Test
    public void listTest() {

       ArrayList<String> a = new ArrayList<>();
    }
    @Test
    public void initWithParam() {
        HashMap<String, String> map = new HashMap<>(15);
        map.put("1","1");
    }

    /**
     * put的过程
     */
    @Test
    public void add(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","wyy");
    }

    /**
     * 扩容过程
     */
    @Test
    public void growth(){
        // 触发扩容的条件 : 待插入的元素数量 / 负载因子 > hashMap容量
        HashMap<String, String> map = new HashMap<>(8);
        map.put("name","wyy");
        map.put("name2","wyy2");
        map.put("name3","wyy3");
        map.put("name4","wyy4");
        map.put("name5","wyy5");
        map.put("name6","wyy6");
        System.out.println(map.size());

        map.forEach((k,v)->{
            // 快速失败
            map.put("name7","wyyy");
            System.out.println(k+"-"+v);
        });

    }

    @Test
    public void hashTableTest(){
        Hashtable<Integer,String > table = new Hashtable();
        System.out.println(table.put(1, "wyy"));
        System.out.println(table.put(1, "wyy2"));
    }

}
