package org.wyy.tech.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/11 04:22
 **/
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        // 定义字符集
        Charset charset = StandardCharsets.UTF_8;
        // 创建字符集解码器
        CharsetDecoder decoder = charset.newDecoder();
        // 读取文件
        RandomAccessFile file = new RandomAccessFile("/Users/mars/Desktop/input.txt", "rw");
        // 获取文件通道
        FileChannel fileChannel = file.getChannel();
        // 定义buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        CharBuffer charBuffer = CharBuffer.allocate(128);
        byteBuffer.clear();
        charBuffer.clear();
        // 准备写入
        while (fileChannel.read(byteBuffer) >= 0){
            byteBuffer.flip();
            //数据从字节缓冲区写入字符缓冲区
            decoder.decode(byteBuffer,charBuffer,true);
            charBuffer.flip();
            char[] buff = new char[charBuffer.length()];
            charBuffer.get(buff,0,charBuffer.length());
            System.out.print(new String(buff) );
            charBuffer.clear();
            byteBuffer.clear();
        }
        file.close();
    }
}
