package nio;

import org.junit.Test;

import java.nio.CharBuffer;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/19 03:19
 **/
public class BufferCopyAndSplit {

    /**
     * 缓冲区复制
     */
    @Test
    public void bufferCopy(){
        CharBuffer buf = CharBuffer.allocate(16);
        buf.put("hello");
        System.out.println("buf.position = "+buf.position());

        // 缓冲区复制
        CharBuffer buf2 = buf.duplicate();
        System.out.println("buf2.position="+buf2.position()+",buf2.limit="+buf2.limit()+",buf2.capacity="+buf2.capacity());

        buf2.flip();
        System.out.println("buf2="+buf2);

        buf2.clear();
        buf2.put("what's up");
        System.out.println("翻转前...buf2.position="+buf2.position()+",buf2.limit="+buf2.limit()+",buf2.capacity="+buf2.capacity());
        buf2.flip();
        System.out.println("put新值后,buf2="+buf2);// put新值后,buf2=what's up
        System.out.println("翻转后...buf2.position="+buf2.position()+",buf2.limit="+buf2.limit()+",buf2.capacity="+buf2.capacity());

        System.out.println("翻转前...buf.position="+buf.position()+",buf.limit="+buf.limit()+",buf.capacity="+buf.capacity());
        buf.flip();// 之前放的是hello,position为5,flip之后limit为5
        System.out.println("buf= "+buf);// buf= what'
        System.out.println("翻转后...buf.position="+buf.position()+",buf.limit="+buf.limit()+",buf.capacity="+buf.capacity());
    }

    @Test
    public void slice(){
        CharBuffer buf = CharBuffer.allocate(16);
        buf.put("你好中国"); // position 4
        System.out.println("buf.position="+buf.position()+",buf.limit="+buf.limit()+",buf.capacity="+buf.capacity());
        // 复制一个缓冲区
        CharBuffer buf2 = buf.slice();
        System.out.println("buf2.position="+buf2.position()+",buf2.limit="+buf2.limit()+",buf2.capacity="+buf2.capacity());

        buf.flip();
        System.out.println("buf = "+buf);
        System.out.println("buf.position="+buf.position()+",buf.limit="+buf.limit()+",buf.capacity="+buf.capacity());

        buf2.flip();
        System.out.println("buf2 = "+buf2);
        /**
         * buf.position=4,buf.limit=16,buf.capacity=16
         * buf2.position=0,buf2.limit=12,buf2.capacity=12
         * buf = 你好中国
         * buf.position=0,buf.limit=4,buf.capacity=16
         * buf2 =
         */

    }
}
