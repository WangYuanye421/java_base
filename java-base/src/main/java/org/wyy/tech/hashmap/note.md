# 常见面试题
- hashMap的数据结构
- 什么是链表和红黑树?
- 为什么要指定容量? 为什么是2的幂次方? 可以是其他数字吗?
- 默认容量时多少?
- HashMap扩容的触发条件是什么?
- 负载因子是多少? 为什么要设置为0.75?
- 扰动函数的大概实现?
- 解决hash碰撞的其他方式
- 描述put的流程
- 描述扩容的流程,扩容之后数据如何摆放?
- 扩容时其他线程put,HashMap是如何处理的 ?
- 1.7版本,HashMap的循环链表能说一下吗?
> 开始看concurrentHashMap


# 知识点梳理
map的设置容量A时,如果A不是2的幂次方,会通过tableSizeFor() 转为大于A的2的幂次方数,如:
A = 5,6,7  -> 8
A = 9,10,11,12,13,14,15 -> 16



# 位运算
https://blog.csdn.net/weixin_30420305/article/details/95026035


Java中基本类型数据占几个字节?

byte:1个字节 8位 -128~127

short ：2个字节 16位

int ：4个字节 32位

long：8个字节 64位

浮点型：

float：4个字节 32 位

double ：8个字节 64位

注：默认的是double类型，如3.14是double类型的，加后缀F（3.14F）则为float类型的。

char类型：

char：2个字节。

Boolean 类型

boolean: （true or false）（并未指明是多少字节  1字节  1位 4字节）

补充：BigInteger类实现了任意精度的整数运算，BigDecimal实现了任意精度的浮点数运算。



