/*
Navicat MySQL Data Transfer

Source Server         : ssh
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : ssm-demo

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-09-22 11:02:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL COMMENT '密码',
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '曹操', '男', '上海', '55');
INSERT INTO `user` VALUES ('3', '曹丕', '男', '上海', '28');
INSERT INTO `user` VALUES ('4', '大乔', '男', '上海', '25');
INSERT INTO `user` VALUES ('6', '曹丕', '男', '上海', '282');
INSERT INTO `user` VALUES ('8', '诸葛亮', '男', '三国', '58');
INSERT INTO `user` VALUES ('9', '姜飞祥', '男', '上海', '24');
