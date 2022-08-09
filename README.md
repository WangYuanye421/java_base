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
> provider8080 `/order/save1` 创建订单,同时远程调用付款服务 consumer8090`/payment/save1`
> 两个服务的本地事务互不影响,调用付款服务成功后,订单创建发生异常,导致数据不一致

1. 首先这种同步远程调用的方式在分布式服务中本身就有问题,分布式中通常存在大量的链路较长的服务调用,
   如果使用这种同步等待的方式进行调用,首先并不能解决数据一致性问题,其次,对于调用链路的首端来说,等待时间是不可接受的,甚至服务超时.
   通常服务之间的RPC调用会通过MQ进行异步解耦,缩短请求的响应时间.
   
2. 本地事务之所以能够保证多个方法调用的事务性,是因为有事务管理器来进行协调,事务内的所有方法都成功则本次事务提交,否则回滚.
   根据这个思路,在分布式服务中,也可以在这些服务间添加一个事务的协调者,每个服务都向协调者汇报本地事务的状态,如果整个服务调用链路中所有的
   参与者都成功,则本地事务成功,否则回滚.
   假如ABC三个服务本地事务都成功,A在向协调者汇报本地事务状态时因为网络等原因无法汇报.那么整个链路ABC都会回滚,从而保证数据一致性.
   
3. 分布式事务解决方案-TCC

概念:
> Try-Confirm-Cancel,核心思想是：针对每个操作，都要注册一个与其对应的确认和补偿（撤销）操作，分为三个阶段：
> Try：这个阶段对各个服务的资源做检测以及对资源进行锁定或者预留；
> Confirm ：执行真正的业务操作，不作任何业务检查，只使用Try阶段预留的业务资源，Confirm操作要求具备幂等设计，Confirm失败后需要进行重试；
> Cancel：如果任何一个服务的业务方法执行出错，那么这里就需要进行补偿，即执行回滚操作，释放Try阶段预留的业务资源 ，Cancel操作要求具备幂等设计，
> Cancel失败后需要进行重试
 
优缺点:


4. 分布式事务解决方案-2PC
> 两阶段提交(微服务参与者的两阶段行为). 服务的事务分为两阶段,所有参与者由协调者进行协调
> 第一阶段: 提交本地事务,同时提交写数据和对应的回滚日志,向TC协调者上报本地事务结果
> 第二阶段-提交:  TC通知提交,返回成功结果给TC,异步删除回滚日志
> 第二阶段-回滚 TC通知回滚,使用回滚日志进行业务数据反向补偿,向TC上报回滚结果.达到数据的最终一致性
   
### seata集成
[seata模型介绍](http://seata.io/zh-cn/docs/overview/what-is-seata.html)
tc-事务协调者(全局) tm-事务管理器 rm-资源管理器
1. AT模式
> 依赖数据库本地ACID事务,使用两阶段提交模型

2. TCC模式
> 不依赖底层数据库的事务支持,需要自定义提交/回滚行为,所谓TCC就是把自定义的分支事务纳入到全局事务的管理中


#### Seata集成步骤
1. seata服务端下载,启动 (TC分布式事务协调者)
> file.conf配置seata存储方式为MySQL及数据库连接,配置事务组等信息
> registry.conf 配置注册中心连接信息

2. seata依赖导入
```xml
<!--seata 分布式事务-->
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-seata</artifactId>
		</dependency>
```
3. 微服务端seata配置

```yaml
spring:
   main:
      allow-circular-references: true # 允许循环依赖,否则SeataRestTemplateAutoConfiguration循环依赖
seata:
  enabled: true # 默认开启
  application-id: ${spring.application.name}
  tx-service-group: default_tx_group # 事务群组（可以每个应用独立取名，也可以使用相同的名字）
  client:
    rm-report-success-enable: true
    rm-table-meta-check-enable: false # 自动刷新缓存中的表结构（默认false）
    rm-report-retry-count: 5 # 一阶段结果上报TC重试次数（默认5）
    rm-async-commit-buffer-limit: 10000 # 异步提交缓存队列长度（默认10000）
    rm:
      lock:
        lock-retry-internal: 10 # 校验或占用全局锁重试间隔（默认10ms）
        lock-retry-times: 30 # 校验或占用全局锁重试次数（默认30）
        lock-retry-policy-branch-rollback-on-conflict: true # 分支事务与其它全局回滚事务冲突时锁策略（优先释放本地锁让回滚成功）
    tm-commit-retry-count: 3 # 一阶段全局提交结果上报TC重试次数（默认1次，建议大于1）
    tm-rollback-retry-count: 3 # 一阶段全局回滚结果上报TC重试次数（默认1次，建议大于1）
    undo:
      undo-data-validation: true # 二阶段回滚镜像校验（默认true开启）
      undo-log-serialization: jackson # undo序列化方式（默认jackson）
      undo-log-table: undo_log  # 自定义undo表名（默认undo_log）
    log:
      exception-rate: 100 # 日志异常输出概率（默认100）
    support:
      spring:
        datasource-autoproxy: true
  service:
    vgroup-mapping:
      default_tx_group: default # TC 集群（必须与seata-server保持一致）
    grouplist:
      default: 127.0.0.1:8091
    enable-degrade: false # 降级开关
    disable-global-transaction: false # 禁用全局事务（默认false）

  transport:
    type: TCP
    server: NIO
    heartbeat: true
    serialization: seata
    compressor: none
    enable-client-batch-send-request: true # 客户端事务消息请求是否批量合并发送（默认true）
    shutdown:
      wait: 3
    thread-factory:
      boss-thread-prefix: NettyBoss
      worker-thread-prefix: NettyServerNIOWorker
      server-executor-thread-prefix: NettyServerBizHandler
      share-boss-worker: false
      client-selector-thread-prefix: NettyClientSelector
      client-selector-thread-size: 1
      client-worker-thread-prefix: NettyClientWorkerThread

  registry:
    type: file  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  config:
    #    file:
    #      name: file.conf
    type: file  # file、nacos 、apollo、zk、consul、etcd3
```
4. 使用事务注解 `@GlobalTransactional`
```java
    @GlobalTransactional(name = "seata-demo", timeoutMills = 30000, rollbackFor = Exception.class)
	public void insert2(BizOrder order){
		mapper.insert(order);
		// 远程调用
		String msg = restTemplate.postForObject(paymentUrl2, order, String.class);
		throw new RuntimeException("业务异常");
	}
```

5. 分布式服务客户端所在数据库添加undo_log表
```sql
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
6. 测试
```shell
请求参数: param:{"orderNo":"33333","productName":"干脆面"}
`8080/order/save1` # 无事务执行
`8080/order/save2` # 有事务执行
```
### RocketMQ集成
