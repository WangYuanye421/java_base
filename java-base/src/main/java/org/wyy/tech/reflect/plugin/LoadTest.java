package org.wyy.tech.reflect.plugin;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote
 * @date : 2021/3/10 下午5:00
 **/
public class LoadTest {
    /**
     * 1. 读取外部文件Person.java
     * 2. 编译
     * 3. 需要自定义类加载器进行加载(文件位置不是jre或ext)
     * 4. 获取class对象,通过反射API调用Person方法
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String path = "/Users/mars/codeSpace/0bat/3source_code/1jdk/upload/SkinPlugin.java";
        Class<?> aClass = compileAndLoad(path);
        Class<?> superclass = aClass.getSuperclass();
        Class<PluginTemplate> pluginClass = PluginTemplate.class;
        // class对象比较
        if (pluginClass.equals(superclass)) {
            PluginTemplate o = (PluginTemplate) aClass.newInstance();
            o.setData("css data");
            o.handle();
        }
        System.out.println("--------------");
        Object o = aClass.newInstance();
        // 对象实例比较
        if (o instanceof PluginTemplate) {
            PluginTemplate plugin = (PluginTemplate) aClass.newInstance();
            plugin.setData("css data");
            plugin.handle();
        }
    }

    public static Class<?> compileAndLoad(String path) {
        String type = ".class";
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        // 存放编译信息
        int result = javaCompiler.run(null, null, null, path);
        if (result != 0) {
            throw new RuntimeException("编译失败");
        }
        Class resultClass = null;
        String[] split = path.split("\\.");
        String classLocation = split[0]+ type;
        // 自定义类加载器
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            resultClass = myClassLoader.findClass(classLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultClass;
    }


    /**
     * 自定义类加载器
     */
    static class MyClassLoader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Path path = null;
            byte[] bytes = null;
            try {
                path = Paths.get(name);
                bytes = Files.readAllBytes(path);
            } catch ( IOException e) {
                e.printStackTrace();
            }
            String[] split = name.split("/");
            String fileName = split[split.length - 1];
            String className = fileName.split("\\.")[0];
            // 调用父类
            return super.defineClass(className, bytes, 0, bytes.length);
        }
    }
}
