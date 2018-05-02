/*
Navicat MySQL Data Transfer

Source Server         : ethanLocalMysql
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-04-28 14:54:09
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('etuser1', 'dGptBA8CLs5u4sCvIOvQZQ==', 'UaCMZvJGPkOY4UZCfMmeDA==', '2018-04-28 14:44:19');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'May', '1', 'singer');
INSERT INTO `person` VALUES ('2', 'ethan', '25', 'coder');
INSERT INTO `person` VALUES ('3', 'joe', '21', 'dancer');

-- ----------------------------
-- Table structure for recv_file
-- ----------------------------
DROP TABLE IF EXISTS `recv_file`;
CREATE TABLE `recv_file` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(100) NOT NULL,
  `filepath` varchar(200) NOT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0开始接收，1接收完毕',
  `recv_percent` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recv_file
-- ----------------------------
INSERT INTO `recv_file` VALUES ('1', 'hello.txt', 'F://recv', '0', '100', '2017-12-11 11:32:03', '2017-12-14 11:35:52', null);
INSERT INTO `recv_file` VALUES ('2', 'hello2.txt', 'F://recv', '0', '100', '2017-12-11 11:32:03', '2017-12-14 11:35:52', null);
INSERT INTO `recv_file` VALUES ('3', 'hello3.txt', 'F://recv', '0', '100', '2017-12-11 11:32:03', '2017-12-14 11:35:52', null);
INSERT INTO `recv_file` VALUES ('4', '4o28b0625501ad13015501ad2bfc0123.jpg', 'F:/etframework-recieved-files', '0', '100', '2017-12-14 11:35:52', '2017-12-14 11:35:52', null);
INSERT INTO `recv_file` VALUES ('5', '2012-02-24 22.31.52.jpg', 'F:/etframework-recieved-files/', '0', '0', '2018-01-24 10:20:12', '2018-01-24 10:20:12', null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `url` varchar(50) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'ROLE_USER', 'home', '/', null);
INSERT INTO `sys_permission` VALUES ('2', 'ROLE_ADMIN', 'ABel', '/admin', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '2', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$kLb6iZefEUra6bkrkl85yuEIhE1Iw4u4BcEG6E9DbTieABSw1wbJO');
INSERT INTO `sys_user` VALUES ('2', 'ethan', '$2a$10$JsRgmzEky6UYyADv6r63kO1BUQP7kSG8rqyRAHdTdWxSPsQ4MFisW');
INSERT INTO `sys_user` VALUES ('3', 'etuser1', '$2a$10$hWAlXIdOZfKftcTs0H0Bq.yayB2ko/CfKvOMM7dE/1iRpYODlHC72');
INSERT INTO `sys_user` VALUES ('4', 'etuser2', '$2a$10$iRZLUQIEcl0FsW8HFz1lBuLZTM6LkpUh6fc1OUz7NrPQIQwFUzzDW');
INSERT INTO `sys_user` VALUES ('5', 'etuser3', '$2a$10$1n2v942/.q3dJ.zFg9kU5.MGhQxs1JKQCVGR2Ql2PiEXcv53gIalO');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '3', '1');
INSERT INTO `sys_user_role` VALUES ('4', '4', '1');
INSERT INTO `sys_user_role` VALUES ('5', '4', '2');


