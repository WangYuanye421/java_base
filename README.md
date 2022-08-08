# tech-interview
> java技术面试知识点积累

## maven模块搭建问题
```xml
1. maven父项目定义 <finalName>cloud-demo</finalName>,导致子模块打包后名称错误,其他模块无法正常引入
正确设置: 
	<finalName>${artifactId}</finalName>

2. build标签引入错误,引入了org.springframework.boot中的maven-plugin
正确完整设置:
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-compiler-plugin</artifactId>
		<version>3.7.0</version>
		<configuration>
			<source>1.8</source>
			<target>1.8</target>
			<encoding>UTF-8</encoding>
		</configuration>
	</plugin>

3. spring-boot spring-cloud spring-cloud-alibaba版本问题
这三大依赖最好使用别人已经测试通过的版本,否则最开始需要解决大量版本问题
	<spring.boot.version>2.7.1</spring.boot.version>
	<spring.cloud.version>2021.0.1</spring.cloud.version>
	<spring.cloud.alibaba.version>2.2.7.RELEASE</spring.cloud.alibaba.version>

```

## 模块
- java-base
> java基础及JDK源码学习

- cloud-common-base
> 框架公共代码抽象练习

- cloud-rocket-mq
> 消息中间件学习

- cloud-provider-808*
> 生产者:模拟上游服务.用于测试服务注册发现,中心化配置,MQ,分布式事务等微服务中间件功能

- cloud-consumer-809*
> 消费者:模拟下游服务.用于测试服务注册发现,中心化配置,MQ,分布式事务等微服务中间件功能

### nacos集成
```xml
1. 依赖导入
	<dependency>
		<groupId>com.alibaba.cloud</groupId>
		<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
	</dependency>
	
	<!--spring-cloud-dependency 2020.X之后不再自动加载bootstrap.yml文件,需要手动添加依赖-->
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-bootstrap</artifactId>
	</dependency>

```
2. 配置nacos连接信息
```yml
spring:
	application:
		name: cloud-provider-8080 # nacos默认使用此项作为serviceName

	cloud:
		nacos:
			server-addr: localhost:8848
			discovery:
				group: DEMO # 服务调用需要在同一个组内,否则无法调通(解决方法未探究)
				namespace: public
```

3. 注解开启服务发现
	`@EnableDiscoveryClient` 

4. restTemplate注入问题
```java
// @Resource无法直接注入,通过创建bean的方式注入
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
```

### 分布式事务
> provider8080 `/order/save1` 创建订单,同时远程调用付款服务`/payment/save1`
> 两个服务的本地事务互不影响,调用付款服务成功后,订单创建发生异常,导致数据不一致

1. 首先这种同步远程调用的方式在分布式服务中本身就有问题,分布式中通常存在大量的链路较长的服务调用,
   如果使用这种同步等待的方式进行调用,首先并不能解决数据一致性问题,其次,对于调用链路的首端来说,等待时间是不可接受的,甚至服务超时.
   通常服务之间的RPC调用会通过MQ进行异步解耦,缩短请求的响应时间.
   
2. 本地事务之所以能够保证多个方法调用的事务性,是因为有事务管理器来进行协调,事务内的所有方法都成功则本次事务提交,否则回滚.
   根据这个思路,在分布式服务中,也可以在这些服务间添加一个事务的协调者,每个服务都向协调者汇报本地事务的状态,如果整个服务调用链路中所有的
   参与者都成功,则本地事务成功,否则回滚.
   假如ABC三个服务本地事务都成功,A在向协调者汇报本地事务状态时因为网络等原因无法汇报.那么整个链路ABC都会回滚,从而保证数据一致性.
   
### seata集成
[seata模型介绍](http://seata.io/zh-cn/docs/overview/what-is-seata.html)
1. AT模式

2. TCC模式

3. SAGA模式

4. XA模式


