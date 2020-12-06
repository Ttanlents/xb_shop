/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : 65001

 Date: 06/12/2020 18:19:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名字',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址-区域',
  `address_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址-详细地址',
  `post_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, 'jackson', '广东省广州市天河区', '珠江新城南岳大厦9楼', '528244', '15625511657');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '猛男列表');
INSERT INTO `category` VALUES (2, '屌丝列表');
INSERT INTO `category` VALUES (3, '清新列表');
INSERT INTO `category` VALUES (4, '哒哒列表');
INSERT INTO `category` VALUES (5, '时尚列表');
INSERT INTO `category` VALUES (6, '时髦列表');
INSERT INTO `category` VALUES (7, '憨憨列表');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `receive_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `receive_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人的名字',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单提交时间',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单收货电话',
  `state` int(11) NULL DEFAULT NULL COMMENT '订单状态',
  `total` double NULL DEFAULT NULL COMMENT '订单总价格',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发起订单的用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive_address` int(11) NULL DEFAULT NULL COMMENT '收货地址',
  `count` int(11) NULL DEFAULT NULL COMMENT '该id商品购买数量',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `total_price` double NULL DEFAULT NULL COMMENT '数量 乘以 该商品单价=总价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '商品类别id',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `is_hot` int(11) NULL DEFAULT NULL COMMENT '是否热门',
  `price` double NULL DEFAULT NULL COMMENT '商品价钱',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '上架时间',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `brows_count` int(11) NULL DEFAULT NULL COMMENT '浏览次数',
  `image_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情图片以逗号隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 1, '长衣服', 0, NULL);
INSERT INTO `product` VALUES (2, 2, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img2.jpg', 1, 58, '2020-12-06 11:18:30', 2, '裤子', 0, NULL);
INSERT INTO `product` VALUES (3, 2, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 3, '短衣服', 0, NULL);
INSERT INTO `product` VALUES (4, 4, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 1, '大衣服', 0, NULL);
INSERT INTO `product` VALUES (5, 5, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 2, '小衣服', 0, NULL);
INSERT INTO `product` VALUES (6, 6, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 3, '中衣服', 0, NULL);
INSERT INTO `product` VALUES (7, 7, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 2, '夏天衣服', 0, NULL);
INSERT INTO `product` VALUES (8, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 1, '冬天衣服', 0, NULL);
INSERT INTO `product` VALUES (9, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 2, '秋天衣服', 0, NULL);
INSERT INTO `product` VALUES (10, 2, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 3, '风衣衣服', 0, NULL);
INSERT INTO `product` VALUES (11, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 2, '雨天衣服', 0, NULL);
INSERT INTO `product` VALUES (12, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 2, '春天衣服', 0, NULL);
INSERT INTO `product` VALUES (13, 1, '这是描述，这是描述，这是描述，这是描述', 'http://localhost:8080/images/img1.jpg', 1, 20, '2020-12-06 11:16:23', 1, '小孩子衣服', 0, NULL);
INSERT INTO `product` VALUES (14, 1, '这款小米手机非常好用，冬天还保暖', 'http://localhost:8080/images/big_2.jpg', 1, 998.85, '2020-12-06 16:22:53', 1, '小米手机', 0, 'http://localhost:8080/images/big_1.jpg,http://localhost:8080/images/big_2.jpg,http://localhost:8080/images/big_3.jpg,http://localhost:8080/images/big_4.jpg,http://localhost:8080/images/big_5.jpg,http://localhost:8080/images/big_6.jpg');

-- ----------------------------
-- Table structure for shop_car
-- ----------------------------
DROP TABLE IF EXISTS `shop_car`;
CREATE TABLE `shop_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` double(32, 2) NULL DEFAULT NULL COMMENT '价钱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shop_car
-- ----------------------------
INSERT INTO `shop_car` VALUES (1, 1, 14, 1, 998.85);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '一帆风顺店铺');
INSERT INTO `store` VALUES (2, '衣服店铺');
INSERT INTO `store` VALUES (3, '服饰帝爵');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` datetime(0) NULL DEFAULT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `qq_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wx_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wb_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, '825362171@qq.com', '余俊锋', 'admin', '15625511657', 'admin', '2020-12-06 09:30:00', '2020-12-06 18:15:37', NULL, NULL, '1', 24, NULL, NULL, NULL);
INSERT INTO `user` VALUES (14, NULL, '2317023081@qq.com', NULL, '123456', NULL, 'jackson', '2020-12-06 10:19:15', NULL, NULL, NULL, '\0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite`  (
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------
INSERT INTO `user_favorite` VALUES (1, 1);
INSERT INTO `user_favorite` VALUES (1, 2);
INSERT INTO `user_favorite` VALUES (1, 14);

SET FOREIGN_KEY_CHECKS = 1;
