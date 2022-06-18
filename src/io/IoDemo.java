package io;

import java.io.*;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote io学习
 * @date : 2022/6/9 18:12
 **/
public class IoDemo {
    public static void main(String[] args) {
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
}
