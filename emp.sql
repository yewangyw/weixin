/*
Navicat MySQL Data Transfer

Source Server         : DEMO01
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bj1901

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-02 21:30:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empno` int(8) NOT NULL AUTO_INCREMENT,
  `ename` varchar(20) NOT NULL,
  `job` varchar(30) NOT NULL,
  `mgr` int(8) DEFAULT NULL,
  `hiredate` date NOT NULL,
  `sal` double(8,0) NOT NULL,
  `deptno` int(8) NOT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', '1', '吃', '1', '2019-05-27', '30000', '1');
INSERT INTO `emp` VALUES ('2', 'b', '喝', '1', '2019-05-16', '30000', '1');
INSERT INTO `emp` VALUES ('3', 'c', 'a', '1', '2019-05-14', '1111', '1');
INSERT INTO `emp` VALUES ('4', 'd', 's', '3', '2019-04-30', '111', '3');
INSERT INTO `emp` VALUES ('5', 'e', 'q', '2', '2019-05-14', '123333', '1');
INSERT INTO `emp` VALUES ('6', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('7', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('8', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('9', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('10', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('11', 'z', 'd', '2', '2019-05-16', '1112', '2');
INSERT INTO `emp` VALUES ('13', '3', 'rwrwer', '2', '2019-05-28', '1112', '7');
INSERT INTO `emp` VALUES ('14', '2', 'erer', '2', '2019-05-28', '1112', '7');
INSERT INTO `emp` VALUES ('17', '1', '哈哈哈', '1', '2019-05-27', '1', '8');
INSERT INTO `emp` VALUES ('22', 'as', '1', '1', '2019-05-07', '2', '5');
INSERT INTO `emp` VALUES ('23', 'ap', 'qwer', '1', '2019-04-29', '111111', '5');
INSERT INTO `emp` VALUES ('24', 'app', 'aqq', '0', '2019-06-04', '1222222', '9999');
