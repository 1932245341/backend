/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : iyouyangshuo

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 20/04/2025 16:28:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货人',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '地址簿' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 6, '陈松桂', '0', '18378950813', '45', '广西壮族自治区', '4503', '桂林市', '450323', '灵川县', '桂电花江校区', '3', 1);
INSERT INTO `address` VALUES (5, 6, '', '1', '', '', '大苏打', '', '', '', '', '', '', 0);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '身份证号',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '管理员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (4, NULL, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车主键id',
  `user_id` int(0) DEFAULT NULL COMMENT '用户id',
  `specialty_id` int(0) DEFAULT NULL COMMENT '特产id',
  `address_id` int(0) DEFAULT NULL COMMENT '收货地址id',
  `number` int(0) DEFAULT NULL COMMENT '数量',
  `amount` decimal(10, 2) DEFAULT NULL COMMENT '总价',
  `create_time` datetime(0) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 6, 1, 1, 1, 1.10, '2025-04-10 15:03:36');
INSERT INTO `cart` VALUES (2, 2, 2, 3, NULL, NULL, NULL);
INSERT INTO `cart` VALUES (4, 0, 0, 0, 3, 312.30, NULL);
INSERT INTO `cart` VALUES (5, 6, 0, 0, 3, 312.30, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评价主键ID',
  `user_id` int(0) NOT NULL COMMENT '用户ID，关联评价者',
  `scenic_id` int(0) DEFAULT NULL COMMENT '关联景区ID（若为景区评价时使用）',
  `hotel_id` int(0) DEFAULT NULL COMMENT '关联民宿ID（若为民宿评价时使用）',
  `restaurant_id` int(0) DEFAULT NULL COMMENT '关联餐馆ID（若为餐馆评价时使用）',
  `rating` int(0) NOT NULL COMMENT '评分，0到5分之间',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '评价内容',
  `type` enum('景区','民宿','餐饮') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价类型，区分景区或民宿或餐饮',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '评价创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '套餐主键id',
  `restaurant_id` int(0) DEFAULT NULL COMMENT '所属餐馆',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '套餐名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单张图片url',
  `sale` int(0) DEFAULT NULL COMMENT '销量',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '价钱',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '介绍',
  `status` int(0) DEFAULT NULL COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1, 1, '321123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dish` VALUES (2, 1, '43', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for dish_book
-- ----------------------------
DROP TABLE IF EXISTS `dish_book`;
CREATE TABLE `dish_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '预约套餐主键id',
  `dish_id` int(0) NOT NULL COMMENT '关联的套餐id',
  `user_id` int(0) NOT NULL COMMENT '关联用户id',
  `date` date DEFAULT NULL COMMENT '预约的日期',
  `number` int(0) DEFAULT 1 COMMENT '门票数量',
  `total_price` decimal(10, 2) DEFAULT NULL COMMENT '总价格',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '民宿主键id',
  `marketer_id` int(0) DEFAULT NULL COMMENT '所属商家id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '民宿名称',
  `location` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '位置',
  `latitude` float(10, 6) DEFAULT NULL COMMENT '纬度',
  `longitude` float(10, 6) DEFAULT NULL COMMENT '经度',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面图片url',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签（三星舒适/五星豪华等）',
  `opening_year` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '开业年份',
  `fitment_year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '装修年份',
  `score` float DEFAULT NULL COMMENT '评分',
  `floor_num` int(0) DEFAULT NULL COMMENT '楼层数',
  `room_num` int(0) DEFAULT NULL COMMENT '房间数',
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '营业执照图片url',
  `status` int(0) DEFAULT NULL COMMENT '状态，0申请中，1已通过，2已拒绝',
  `minprice` decimal(10, 2) DEFAULT NULL COMMENT '该民宿最低价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, 7, '阳朔县朔园度假酒店（党校公寓）', '广西壮族自治区桂林市阳朔县阳朔镇阳朔县朔园度假酒店-贵宾楼', 24.778372, 110.488739, NULL, 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E9%98%B3%E6%9C%94%E5%8E%BF%E6%9C%94%E5%9B%AD%E5%BA%A6%E5%81%87%E9%85%92%E5%BA%97%EF%BC%88%E5%85%9A%E6%A0%A1%E5%85%AC%E5%AF%93%EF%BC%89.png', '三星/舒适', '22024', '2020', NULL, 4, 119, NULL, 1, 328.00);

-- ----------------------------
-- Table structure for marketer
-- ----------------------------
DROP TABLE IF EXISTS `marketer`;
CREATE TABLE `marketer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商家主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '商家名称',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `type` enum('餐饮','民宿') CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '类型，餐饮或民宿',
  `license` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '营业执照的图片url',
  `manager` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '经办人',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系地址',
  `email` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '电子邮箱',
  `status` int(0) DEFAULT 0 COMMENT '0提交中，1已接收，2已拒绝',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '商家信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketer
-- ----------------------------
INSERT INTO `marketer` VALUES (7, '测试test', '6', 'e10adc3949ba59abbe56e057f20f883e', '31241343', NULL, NULL, 'ffgvx', '经办人', '联系地址', '电子邮箱', 1);
INSERT INTO `marketer` VALUES (8, NULL, 'dfd', '2e0aca891f2a8aedf265edf533a6d9a8', '312', NULL, NULL, '312', 'dsd', NULL, NULL, 0);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单编号',
  `user_id` int(0) DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `num` int(0) DEFAULT NULL COMMENT '商品数量',
  `total` decimal(10, 2) DEFAULT NULL COMMENT '总价格',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `pay_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付编号',
  `pay_time` datetime(0) DEFAULT NULL COMMENT '支付时间',
  `status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型（门票、餐饮、住宿、特产）',
  `address_id` int(0) DEFAULT NULL COMMENT '地址id（特产需要）',
  `pay_type` enum('微信支付','支付宝支付') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信或支付宝',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for parking
-- ----------------------------
DROP TABLE IF EXISTS `parking`;
CREATE TABLE `parking`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '停车场主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '停车场昵称',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '位置',
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片url',
  `latitude` float(50, 6) DEFAULT NULL COMMENT '纬度',
  `longitude` float(50, 6) DEFAULT NULL COMMENT '经度',
  `status` int(0) DEFAULT 1 COMMENT '状态，1表示正常，0停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking
-- ----------------------------
INSERT INTO `parking` VALUES (1, '万景码头停车场', '广西壮族自治区桂林市阳朔县阳朔镇广成线十里画廊', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E4%B8%87%E6%99%AF%E7%A0%81%E5%A4%B4%E5%81%9C%E8%BD%A6%E5%9C%BA.jpg', 24.733662, 110.490822, 1);
INSERT INTO `parking` VALUES (2, '水厄底码头停车场', '广西壮族自治区桂林市阳朔县高\n田镇水厄底码头', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E6%B0%B4%E5%8E%84%E5%BA%95%E7%A0%81%E5%A4%B4%E5%81%9C%E8%BD%A6%E5%9C%BA.jpg', 24.742552, 110.471611, 1);
INSERT INTO `parking` VALUES (3, '金龙桥码头停车场', '广西壮族自治区桂林市阳朔县白沙镇099县道金龙桥码头', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E9%87%91%E9%BE%99%E6%A1%A5%E7%A0%81%E5%A4%B4%E5%81%9C%E8%BD%A6%E5%9C%BA.jpg', 24.816849, 110.394058, 1);

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '餐馆主键id',
  `marketer_id` int(0) DEFAULT NULL COMMENT '所属商家id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '餐馆名称',
  `location` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '位置',
  `latitude` float DEFAULT NULL COMMENT '纬度',
  `longitude` float DEFAULT NULL COMMENT '经度',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面图片url',
  `score` float DEFAULT NULL COMMENT '评分',
  `license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '营业执照图片url',
  `status` int(0) DEFAULT 0 COMMENT '状态，0申请中，1已通过,2已拒绝',
  `minprice` decimal(10, 2) DEFAULT NULL COMMENT '最低价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
INSERT INTO `restaurant` VALUES (1, 7, '一牛一螺广西牛八宝火锅（遇龙河店）', '阳朔县阳朔镇骥马村委朝阳村12号  一牛一螺  广西牛八宝火锅（遇龙河店）', 24.765, 24.765, NULL, 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E4%B8%80%E7%89%9B%E4%B8%80%E8%9E%BA%E5%B9%BF%E8%A5%BF%E7%89%9B%E5%85%AB%E5%AE%9D%E7%81%AB%E9%94%85%EF%BC%88%E9%81%87%E9%BE%99%E6%B2%B3%E5%BA%97%EF%BC%89.png', NULL, NULL, 1, 128.00);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '民宿套房id',
  `hotel_id` int(0) NOT NULL COMMENT '所属民宿id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '套房名称',
  `capacity` int(0) DEFAULT NULL COMMENT '最多入住人数',
  `floor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '所在楼层',
  `area` int(0) DEFAULT NULL COMMENT '占地面积（平方）',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '一天的价格',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '套房图片（单张）',
  `bed` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '大床（双床）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 7, '31', NULL, NULL, NULL, NULL, NULL, '双人床');
INSERT INTO `room` VALUES (3, 4, 'hh', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `room` VALUES (4, 7, 'asd', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for room_book
-- ----------------------------
DROP TABLE IF EXISTS `room_book`;
CREATE TABLE `room_book`  (
  `id` int(0) NOT NULL COMMENT '预约民宿主键id',
  `room_id` int(0) DEFAULT NULL COMMENT '关联的套房id',
  `user_id` int(0) DEFAULT NULL COMMENT '关联的用户id',
  `check_in` date DEFAULT NULL COMMENT '入住日期',
  `departure` date DEFAULT NULL COMMENT '离店日期',
  `total_price` decimal(10, 2) DEFAULT NULL COMMENT '总价，天数*一天单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '景区ID，主键',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '景区名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '简介',
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '开放时间',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '位置',
  `latitude` float(20, 6) DEFAULT NULL COMMENT '纬度',
  `longitude` float(20, 6) DEFAULT NULL COMMENT '经度',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面图片url',
  `score` float DEFAULT NULL COMMENT '评分',
  `minprice` decimal(10, 2) DEFAULT NULL COMMENT '最低门票价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (1, '印象•刘三姐', NULL, '19:00-23:00', '广西壮族自治区桂林市阳朔县阳朔镇印象·刘三姐', 24.766960, 110.508034, '0773-8818008', NULL, NULL, 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E5%8D%B0%E8%B1%A1%E5%88%98%E4%B8%89%E5%A7%90%E5%B0%81%E9%9D%A2.png', NULL, 118.00);
INSERT INTO `scenic` VALUES (2, '阳朔如意峰索道景区', NULL, '7:00-17:30', '广西壮族自治区桂林市阳朔县高田镇桂林阳朔如意峰索道景区售票处阳朔如意峰度假酒店', 24.679443, 110.471077, '0773-8776628', NULL, NULL, 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E9%98%B3%E6%9C%94%E5%A6%82%E6%84%8F%E5%B3%B0%E7%B4%A2%E9%81%93%E6%99%AF%E5%8C%BA%E5%B0%81%E9%9D%A2.png', NULL, 110.00);

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '特产主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '特产名称',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '价格',
  `sale` int(0) DEFAULT NULL COMMENT '销量',
  `type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型（食品、工艺品）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialty
-- ----------------------------
INSERT INTO `specialty` VALUES (1, '刘三姐玩偶', 0.01, NULL, '工艺品', '高10cm，宽25cm，重50g', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/aa3745be-2ded-45dd-bc46-d1814a7d539a.png');
INSERT INTO `specialty` VALUES (2, '桂林米粉', 9.90, NULL, '食品', '广西特产老盐街桂林米粉速食袋装干捞湿米线早餐礼盒装', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/f626fc2a-190c-45c9-a1a6-0b39fa7c2b57.png');
INSERT INTO `specialty` VALUES (3, '漓泉啤酒', 75.00, NULL, '食品', '桂林漓泉1998小度特酿加料500ml*12瓶装整箱广西漓江活水黄啤啤酒', 'https://iyouyangshuo.oss-cn-guangzhou.aliyuncs.com/%E6%BC%93%E6%B3%89%E5%95%A4%E9%85%92.png');

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '门票id',
  `scenic_id` int(0) DEFAULT NULL COMMENT '关联的景区id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '门票名称',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '门票单价',
  `label` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `sale` int(0) DEFAULT 0 COMMENT '销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (1, 1, '1号的景区门票1', NULL, NULL, 0);
INSERT INTO `ticket` VALUES (2, 1, '1号的景区门票2', NULL, NULL, 0);
INSERT INTO `ticket` VALUES (3, 2, '2号的景区门票1', NULL, NULL, 0);
INSERT INTO `ticket` VALUES (4, 1, 'fds', NULL, NULL, 0);

-- ----------------------------
-- Table structure for ticket_book
-- ----------------------------
DROP TABLE IF EXISTS `ticket_book`;
CREATE TABLE `ticket_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '预约门票主键id',
  `ticket_id` int(0) NOT NULL COMMENT '关联的门票id',
  `user_id` int(0) NOT NULL COMMENT '关联用户id',
  `date` date DEFAULT NULL COMMENT '预约的日期',
  `number` int(0) DEFAULT 1 COMMENT '门票数量',
  `total_price` decimal(10, 2) DEFAULT NULL COMMENT '总价格',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信小程序用户唯一标识',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `identity_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像url',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'o6rNa7O072uNrO9SR8D99Jwm2ec8', NULL, NULL, NULL, NULL, NULL, '2025-03-18 23:08:54');

-- ----------------------------
-- Event structure for update_hotel_score
-- ----------------------------
DROP EVENT IF EXISTS `update_hotel_score`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `update_hotel_score`
ON SCHEDULE
EVERY '1' HOUR STARTS '2025-04-08 15:41:01'
DO BEGIN
  UPDATE `hotel`
  SET `score` = ROUND((
    SELECT AVG(`rating`)
    FROM `comment`
    WHERE `hotel_id` = `hotel`.`id`
    AND `type` = '民宿'
  ), 1);
END
;;
delimiter ;

-- ----------------------------
-- Event structure for update_restaurant_score
-- ----------------------------
DROP EVENT IF EXISTS `update_restaurant_score`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `update_restaurant_score`
ON SCHEDULE
EVERY '1' HOUR STARTS '2025-04-08 15:25:36'
DO BEGIN
  UPDATE `restaurant`
  SET `score` = ROUND((
    SELECT AVG(`rating`)
    FROM `comment`
    WHERE `restaurant_id` = `restaurant`.`id`
    AND `review_type` = '餐饮'
  ), 1);
END
;;
delimiter ;

-- ----------------------------
-- Event structure for update_scenic_score
-- ----------------------------
DROP EVENT IF EXISTS `update_scenic_score`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `update_scenic_score`
ON SCHEDULE
EVERY '1' HOUR STARTS '2025-04-08 15:24:37'
DO BEGIN
  UPDATE `scenic`
  SET `score` = ROUND((
    SELECT AVG(`rating`)
    FROM `comment`
    WHERE `scenic_id` = `scenic`.`id`
    AND `type` = '景区'
  ), 1);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
