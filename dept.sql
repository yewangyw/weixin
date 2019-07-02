/*
Navicat MySQL Data Transfer

Source Server         : DEMO01
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bj1901

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-02 21:30:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(4) NOT NULL,
  `dname` varchar(20) NOT NULL,
  `loc` varchar(30) NOT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '研发部', '深圳');
INSERT INTO `dept` VALUES ('2', '外勤部', '华美居');
INSERT INTO `dept` VALUES ('3', '宣传部', '深圳');
INSERT INTO `dept` VALUES ('4', 'ad', '艾欧尼亚');
INSERT INTO `dept` VALUES ('5', '后勤部', '广东');
INSERT INTO `dept` VALUES ('7', '运营部1', '深圳');
INSERT INTO `dept` VALUES ('8', '运维部', '深圳');
INSERT INTO `dept` VALUES ('9', '运维部', '深圳');
INSERT INTO `dept` VALUES ('10', '测试', 'bj1901');
INSERT INTO `dept` VALUES ('1122', '人力资源部', '广州');
INSERT INTO `dept` VALUES ('1127', '运营部', '深圳');
