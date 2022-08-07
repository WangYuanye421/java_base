# tech-interview
> java技术面试知识点积累


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
## 模块问题
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
