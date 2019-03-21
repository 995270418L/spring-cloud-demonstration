/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 18/03/2019 14:48:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_order
-- ----------------------------
DROP TABLE IF EXISTS `demo_order`;
CREATE TABLE `demo_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `payment_type` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_order
-- ----------------------------
INSERT INTO `demo_order` VALUES (1, 1, 1, 0, '2019-03-18 14:08:22', '2019-03-18 14:08:25');

-- ----------------------------
-- Table structure for demo_stock
-- ----------------------------
DROP TABLE IF EXISTS `demo_stock`;
CREATE TABLE `demo_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(255) NULL DEFAULT NULL,
  `total_num` int(11) NULL DEFAULT NULL,
  `left_num` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_stock
-- ----------------------------
INSERT INTO `demo_stock` VALUES (1, 1, 100000, 100000, '2019-03-18 14:08:42', '2019-03-18 14:08:45');

SET FOREIGN_KEY_CHECKS = 1;
