DROP DATABASE IF EXISTS tech_interview;
CREATE DATABASE tech_interview;
-- 订单表
DROP TABLE IF EXISTS `biz_order`;
CREATE TABLE biz_order
(
    id           bigint AUTO_INCREMENT
        PRIMARY KEY,
    order_no     varchar(64) NOT NULL COMMENT '订单编号',
    product_name varchar(64) NOT NULL COMMENT '商品名称',
    CONSTRAINT biz_order_order_no_uindex
        UNIQUE (order_no)
)
    COMMENT '订单表';
-- 付款表
DROP TABLE IF EXISTS `biz_payment`;
CREATE TABLE biz_payment
(
    id         bigint AUTO_INCREMENT
        PRIMARY KEY,
    order_no   varchar(64)                   NOT NULL COMMENT '订单编号',
    pay_amount decimal(10, 2)                NOT NULL COMMENT '付款金额',
    status     varchar(10) DEFAULT 'SUCCESS' NOT NULL COMMENT '付款状态'
)
    COMMENT '支付表';
-- seata回滚日志表
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

-- 用户 角色 权限
create table tech_interview.sys_user
(
    id       bigint auto_increment
        primary key,
    username varchar(64) not null comment '用户名',
    pwd      varchar(64) not null comment '密码',
    salt     varchar(64) not null comment '加密盐',
    constraint sys_user_username_uindex
        unique (username)
)
    comment '系统用户表';

create table tech_interview.sys_role
(
    id   bigint auto_increment
        primary key,
    name varchar(64) null comment '角色名称',
    constraint sys_role_name_uindex
        unique (name)
)
    comment '系统角色表';

create table tech_interview.sys_user_role
(
    id      bigint auto_increment
        primary key,
    user_id bigint            not null comment '用户id',
    role_id bigint            not null comment '角色id',
    status  tinyint default 1 not null comment '数据状态'
)
    comment '用户角色表';

create table tech_interview.sys_auth
(
    id         bigint auto_increment
        primary key,
    permission varchar(64)  not null comment '权限定义',
    url        varchar(255) null comment '资源路径'
)
    comment '权限表';

create table tech_interview.sys_role_auth
(
    id      bigint auto_increment
        primary key,
    role_id bigint not null comment '角色id',
    auth_id bigint not null comment '权限id'
)
    comment '角色权限表';




SET FOREIGN_KEY_CHECKS=1;
