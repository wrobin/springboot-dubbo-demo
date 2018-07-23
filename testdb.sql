/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : testdb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-23 17:53:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for by_token
-- ----------------------------
DROP TABLE IF EXISTS `by_token`;
CREATE TABLE `by_token` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户表业务主键(一个用户不同的应用有不同的token)',
  `access_token` varchar(50) NOT NULL,
  `refresh_token` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `refresh_time` datetime DEFAULT NULL COMMENT '刷新时间',
  `system_code` varchar(50) NOT NULL COMMENT '系统编号',
  `client` varchar(50) DEFAULT NULL COMMENT '客户端来源',
  PRIMARY KEY (`id`),
  KEY `idx_acc_token` (`access_token`),
  KEY `idx_u_s_id` (`user_id`,`system_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of by_token
-- ----------------------------
INSERT INTO `by_token` VALUES ('52441188237574144', '111', '06e1afc3-8d13-49af-9b4d-a738fa8fc8fe', 'd22443ac-de27-4825-b757-95867985ff45', '2018-07-23 17:02:34', '2018-07-24 17:02:34', null, '1001', null);

-- ----------------------------
-- Table structure for by_user
-- ----------------------------
DROP TABLE IF EXISTS `by_user`;
CREATE TABLE `by_user` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `mobile_no` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `system_code` varchar(50) NOT NULL COMMENT '系统编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_u_s_id` (`system_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小程序端用户表';

-- ----------------------------
-- Records of by_user
-- ----------------------------
INSERT INTO `by_user` VALUES ('111', '18199999999', '308893d0275fce60dffce5da422ec5c4', '1001', '2018-07-23 16:05:46');
