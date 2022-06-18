package io;

import java.io.FileReader;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote 转换流
 * @date : 2022/6/14 16:11
 **/
public class TransDemo {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("/Users/mars/Desktop/input.txt");
        char[] arr = new char[32];
        int len = 0;
        while ((len= reader.read(arr)) > 0) {
            System.out.println(new String(arr,0,len));
        }
        reader.close();
    }
}
