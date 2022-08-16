# 并发

## CAS

## AQS

## 并发工具类
### CountdownLatch 倒计时
> 设置倒计时数,线程调用await等待,其他线程每次调用countdown()后倒计时减一,
> 当倒计时为0时,await的线程被唤醒. 示例demo中,加载系统后需要等待所有子模块加载完成后系统才算加载完成,
> 加载系统的线程启动后调用await等待,每个子模块线程启动完成则countdown一次,所有子模块启动完成,系统加载完成.
```java
/**
 * {@linkorg.wyy.tech.concurrent.CountdownLatchDemo}
 */
public class CountdownLatchDemo{
	void loadSystem(){
		
    }
	
	void loadModule(){
		
    }
	
}
```
> 源码解析: countdownLatch内部有一个sync同步器,该同步器继承AQS,所以countdownLatch其实也是使用AQS来构建锁的.
> AQS中的state可以看成是锁标记,0表示无线程持有,非0表示有线程持有,state可以大于0,所以AQS是可重入的.
> countdownLatch的构造函数传入的int直接设置给了state,即当前线程重入了几次,每次countdown()都会调用tryRelease释放锁,
> state减一,当state为0时,当前线程释放锁.
### CyclicBarrier 发令枪
> 
```java

```
### Semaphore 信号量
> 
>
```java

```
