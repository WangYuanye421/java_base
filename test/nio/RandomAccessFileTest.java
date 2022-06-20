package nio;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/19 17:43
 **/
public class RandomAccessFileTest {

    /**
     * 使用channel双向读写以及直接字节缓冲区,实现文件读写
     */
    @Test
    public void rwTest(){
        // 获取文件
        File file = new File("/Users/mars/Desktop/writer.txt");
        try(
                // 构建通道
                RandomAccessFile raf = new RandomAccessFile(file,"rw");
                FileChannel fileChannel = raf.getChannel();
        ) {
            // 使用直接字节缓冲区,将文件映射到虚拟内存,提高IO读写效率
            MappedByteBuffer mapBuffer = fileChannel.map(MapMode.READ_WRITE, 0, file.length());

            // 设置channel的游标位置,指定buffer从channel的哪里开始写
            fileChannel.position(file.length());
            // buffer 往channel中写数据
            fileChannel.write(mapBuffer);

        }catch (Exception e){

        }
    }


}
