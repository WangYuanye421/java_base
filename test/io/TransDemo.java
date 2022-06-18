package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Wyy
 * @version v1.0
 * @apiNote
 * @date : 2022/6/14 16:36
 **/
public class TransDemo {

    // 乱码演示: 当文件使用utf-16le格式进行保存时,读取时结果将出现乱码
    @Test
    public void test1() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("/Users/mars/Desktop/input.txt"));
        int len = 0;
        char[] arr = new char[12];
        while ((len = reader.read(arr)) > 0) {
            System.out.print(new String(arr,0,len));
        }
        reader.close();
    }


    @Test
    public void test2() throws Exception{
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("/Users/mars/Desktop/input.txt"), StandardCharsets.UTF_16LE));
        int len = 0;
        char[] arr = new char[12];
        while ((len = reader.read(arr)) > 0) {
            System.out.print(new String(arr,0,len));
        }
        reader.close();
    }
}
