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
SET FOREIGN_KEY_CHECKS=1;
