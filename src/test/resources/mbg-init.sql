-- delete database first
drop database if exists `mbg` ;

-- create a new database
CREATE DATABASE `mbg` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use `mbg`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mbg_table
-- ----------------------------
DROP TABLE IF EXISTS `mbg_table`;
CREATE TABLE `mbg_table` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `int_value` int(11) NOT NULL,
  `long_value` bigint(20) unsigned DEFAULT '0',
  `string_value` varchar(16) DEFAULT '',
  `text_value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `blob_table`;
CREATE TABLE `blob_table` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hash` varchar(256) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='blob测试表';


DROP TABLE IF EXISTS `multi_key_table`;
CREATE TABLE `multi_key_table` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `int_value` int NOT NULL,
  `long_value` bigint unsigned DEFAULT '0',
  `string_value` varchar(16) DEFAULT '',
  `text_value` text,
  PRIMARY KEY (`id`,`int_value`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;

