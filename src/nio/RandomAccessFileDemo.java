package nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/19 18:57
 **/
public class RandomAccessFileDemo {
    // todo 程序卡死原因分析
    public static void main(String[] args) {
        // 获取文件
        File file = new File("/Users/mars/Desktop/pom.xml");
        try(
                // 构建通道
                RandomAccessFile raf = new RandomAccessFile(file,"rw");
                FileChannel fileChannel = raf.getChannel();
        ) {
            // 使用直接字节缓冲区,将文件映射到虚拟内存,提高IO读写效率
            MappedByteBuffer mapBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());

            // 设置channel的游标位置,指定buffer从channel的哪里开始写
            fileChannel.position(file.length());
            // buffer 往channel中写数据
            fileChannel.write(mapBuffer);

        }catch (Exception e){

        }
    }
}
