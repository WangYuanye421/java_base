package reflect.demo1;

import reflect.demo1.Dog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote
 * @date : 2021/3/5 上午11:39
 **/
public class MainTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1. 通过对象实例调用
        /*Dog dog = new Dog();
        dog.bark();*/

        // 2. 通过反射调用
        /*Dog dog1 = Dog.class.newInstance();
        dog1.bark();*/

        Object dog = new Dog();
        Method bark = dog.getClass().getMethod("bark", null);
        bark.invoke(dog,null);
    }
}
