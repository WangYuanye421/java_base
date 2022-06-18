package io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/9 23:10
 **/
public class IoDemo {

    // 字节流读入输出
    @Test
    public void test01(){
        InputStream in = null;
        OutputStream out = null;
        try {
            // 读取文件
            in = new FileInputStream("/Users/mars/Desktop/input.txt");
            // 写出到其他文件
            out = new FileOutputStream("/Users/mars/Desktop/output.txt");
            byte[] content = new byte[12];
            int len = 0;
            while ((len = in.read(content)) != -1) {
                out.write(content,0,len);
            }
        } catch (IOException e) {
        }
        finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 字符流读取输出
    @Test
    public void test02(){
        Reader reader = null;
        Writer writer = null;
        try {
            // 读取文件
            reader = new FileReader("/Users/mars/Desktop/input.txt");

            // 写出到其他文件
            writer = new FileWriter("/Users/mars/Desktop/writer.txt");
            // 使用数组
            char[] content = new char[120];
            int len = 0;
            while ((len=reader.read(content)) != -1) {
                System.out.println(Arrays.toString(content));
                writer.write(content,0,len);
            }
        } catch (IOException e) {
        }
        finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 直接读取文件
    @Test
    public void test3() throws Exception{
        FileReader reader = new FileReader("/Users/mars/Desktop/input.txt");
        char[] arr = new char[32];
        int len = 0;
        while ((len= reader.read(arr)) > 0) {
            System.out.println(new String(arr,0,len));
        }
        reader.close();
    }
}
