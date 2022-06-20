package nio;

import org.junit.Test;

import java.nio.CharBuffer;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/18 23:22
 **/
public class NioDemo {

    /**
     * 基础api调用
     */
    @Test
    public void baseApi(){
        CharBuffer charBuffer = CharBuffer.allocate(12);
        System.out.println("初始position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // 初始position:0,容量capacity:12,limit:12

        // 7个字符,被占用索引0~6
        charBuffer.put("我没钱你恨我吗");
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // 初始position:7,容量capacity:12,limit:12

        // 切换模式
        charBuffer.flip();
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // position:0,容量capacity:12,limit:7

        // 读取数据
        System.out.println(charBuffer.get()); // 我
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // position:1,容量capacity:12,limit:7

        // 写入数据
        charBuffer.put("有"); // position再次后移1个单位
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // position:2,容量capacity:12,limit:7

        // 设置标记位,此时标记位是第3个元素,索引位2
        charBuffer.mark();

        // 读取后重置标记位
        System.out.println(charBuffer.get()); // 钱
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // position:3,容量capacity:12,limit:7
        charBuffer.reset();
        System.out.println(charBuffer.get()); // 钱
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());
        // position:3,容量capacity:12,limit:7

        // compact 压缩: 把position之后的数据[你恨我吗]覆盖到buffer的起始位置,position指向未读元素之后[吗]
        // 把position理解成"已读"标识,compact的功能是让所有内容已读,即指向移到后元素的后方.
        charBuffer.compact();
        System.out.println("position:"+charBuffer.position()+",容量capacity:"+charBuffer.capacity()+",limit:"+ charBuffer.limit());

        // 清空
        charBuffer.clear();
        System.out.println(charBuffer);// 你恨我吗恨我吗     
    }


}
