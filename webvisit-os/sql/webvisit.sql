/*
 Navicat Premium Data Transfer

 Source Server         : google
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : www.znzn.me:3306
 Source Schema         : webvisit

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/06/2019 21:09:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attence_annual
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_annual`;
CREATE TABLE `t_attence_annual`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  `expire_date` date NULL DEFAULT NULL COMMENT '上年年假到期日',
  `accumulate_to_next_year` tinyint(4) NULL DEFAULT NULL COMMENT '上一年假期是否累计到下一年',
  `probation_has` tinyint(4) NULL DEFAULT NULL COMMENT '试用期是否享受年假',
  `graduation_one_year_has` tinyint(4) NULL DEFAULT NULL COMMENT '毕业未满一年是否享受年假',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建人id',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改人id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_company`(`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '年假规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_annual
-- ----------------------------
INSERT INTO `t_attence_annual` VALUES (1, 1, '2019-03-27', 1, 1, 1, 1, '2019-04-13 10:31:08', 1, '2019-04-18 02:28:45', 1, NULL);
INSERT INTO `t_attence_annual` VALUES (3, 1, '2019-04-05', 1, 1, 1, 0, '2019-04-13 10:39:11', 1, '2019-04-13 10:58:43', 1, NULL);

-- ----------------------------
-- Table structure for t_attence_annual_step
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_annual_step`;
CREATE TABLE `t_attence_annual_step`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `annual_id` bigint(16) NULL DEFAULT NULL COMMENT '年假id',
  `more_than` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '工龄/司龄超过M年',
  `less_than` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '工龄/司龄不满N年',
  `vacation_days` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '可享受假期天数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_annual`(`annual_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '年假阶梯设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_annual_step
-- ----------------------------
INSERT INTO `t_attence_annual_step` VALUES (1, 1, 1, 100, 10);
INSERT INTO `t_attence_annual_step` VALUES (3, 3, 1, 2, 2);
INSERT INTO `t_attence_annual_step` VALUES (5, 3, 2, 3, 3);
INSERT INTO `t_attence_annual_step` VALUES (6, 3, 3, 8, 6);
INSERT INTO `t_attence_annual_step` VALUES (7, 1, 2, 3, 3);

-- ----------------------------
-- Table structure for t_attence_holiday_custom
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_holiday_custom`;
CREATE TABLE `t_attence_holiday_custom`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  `holiday_date` date NULL DEFAULT NULL COMMENT '日期',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型(0-新增，1-取消)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建人id',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改者id',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_company`(`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业自订节假日' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_holiday_custom
-- ----------------------------
INSERT INTO `t_attence_holiday_custom` VALUES (9, 1, '2018-12-31', 0, '2019-04-22 03:54:55', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_holiday_custom` VALUES (16, 1, '2019-05-03', 1, '2019-04-23 11:44:02', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_holiday_custom` VALUES (17, 1, '2019-02-04', 1, '2019-04-25 14:55:03', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_holiday_custom` VALUES (20, 1, '2019-02-02', 0, '2019-06-04 01:05:42', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_holiday_custom` VALUES (21, 1, '2019-06-10', 0, '2019-06-04 01:05:48', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_attence_holiday_default
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_holiday_default`;
CREATE TABLE `t_attence_holiday_default`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `holiday_date` date NULL DEFAULT NULL COMMENT '日期',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型(0-法定节假日，1-节假日调休班)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_holiday_default
-- ----------------------------
INSERT INTO `t_attence_holiday_default` VALUES (69, '2019-01-01', 0);
INSERT INTO `t_attence_holiday_default` VALUES (70, '2019-02-04', 0);
INSERT INTO `t_attence_holiday_default` VALUES (71, '2019-02-05', 0);
INSERT INTO `t_attence_holiday_default` VALUES (72, '2019-02-06', 0);
INSERT INTO `t_attence_holiday_default` VALUES (73, '2019-02-07', 0);
INSERT INTO `t_attence_holiday_default` VALUES (74, '2019-02-08', 0);
INSERT INTO `t_attence_holiday_default` VALUES (75, '2019-02-09', 0);
INSERT INTO `t_attence_holiday_default` VALUES (76, '2019-02-10', 0);
INSERT INTO `t_attence_holiday_default` VALUES (77, '2019-04-05', 0);
INSERT INTO `t_attence_holiday_default` VALUES (78, '2019-04-06', 0);
INSERT INTO `t_attence_holiday_default` VALUES (79, '2019-04-07', 0);
INSERT INTO `t_attence_holiday_default` VALUES (80, '2019-05-01', 0);
INSERT INTO `t_attence_holiday_default` VALUES (81, '2019-05-02', 0);
INSERT INTO `t_attence_holiday_default` VALUES (82, '2019-05-03', 0);
INSERT INTO `t_attence_holiday_default` VALUES (83, '2019-05-04', 0);
INSERT INTO `t_attence_holiday_default` VALUES (84, '2019-06-07', 0);
INSERT INTO `t_attence_holiday_default` VALUES (85, '2019-06-08', 0);
INSERT INTO `t_attence_holiday_default` VALUES (86, '2019-06-09', 0);
INSERT INTO `t_attence_holiday_default` VALUES (87, '2019-09-13', 0);
INSERT INTO `t_attence_holiday_default` VALUES (88, '2019-09-14', 0);
INSERT INTO `t_attence_holiday_default` VALUES (89, '2019-09-15', 0);
INSERT INTO `t_attence_holiday_default` VALUES (90, '2019-10-01', 0);
INSERT INTO `t_attence_holiday_default` VALUES (91, '2019-10-02', 0);
INSERT INTO `t_attence_holiday_default` VALUES (92, '2019-10-03', 0);
INSERT INTO `t_attence_holiday_default` VALUES (93, '2019-10-04', 0);
INSERT INTO `t_attence_holiday_default` VALUES (94, '2019-10-05', 0);
INSERT INTO `t_attence_holiday_default` VALUES (95, '2019-10-06', 0);
INSERT INTO `t_attence_holiday_default` VALUES (96, '2019-10-07', 0);
INSERT INTO `t_attence_holiday_default` VALUES (97, '2019-02-02', 1);
INSERT INTO `t_attence_holiday_default` VALUES (98, '2019-02-03', 1);
INSERT INTO `t_attence_holiday_default` VALUES (99, '2019-04-28', 1);
INSERT INTO `t_attence_holiday_default` VALUES (100, '2019-05-05', 1);
INSERT INTO `t_attence_holiday_default` VALUES (101, '2019-09-29', 1);
INSERT INTO `t_attence_holiday_default` VALUES (102, '2019-10-12', 1);

-- ----------------------------
-- Table structure for t_attence_holiday_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_holiday_detail`;
CREATE TABLE `t_attence_holiday_detail`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_id` bigint(16) NULL DEFAULT NULL COMMENT '员工id',
  `leave_type` bigint(16) NULL DEFAULT NULL COMMENT '假期类型（0请假1年假）',
  `leave_id` bigint(16) NULL DEFAULT NULL COMMENT '请假类型id',
  `this_year_leave_left` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '今年假期剩余',
  `last_year_leave_left` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '上年假期剩余',
  `vacation_number` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '已修天数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_emp`(`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '假期明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_holiday_detail
-- ----------------------------
INSERT INTO `t_attence_holiday_detail` VALUES (1, 1, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (2, 1, 0, 2, 0, 0, 2);
INSERT INTO `t_attence_holiday_detail` VALUES (3, 1, 2, 3, 0, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (4, 2, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (5, 3, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (6, 4, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (7, 5, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (8, 6, 0, 1, 5, 0, 0);
INSERT INTO `t_attence_holiday_detail` VALUES (9, 6, 0, 2, 0, 0, 3);
INSERT INTO `t_attence_holiday_detail` VALUES (10, 2, 0, 2, 1, 0, 3);
INSERT INTO `t_attence_holiday_detail` VALUES (11, 2, 0, 2, 1, 0, 3);

-- ----------------------------
-- Table structure for t_attence_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_leave`;
CREATE TABLE `t_attence_leave`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '企业id',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假类型名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '请假类型(0-默认，1-企业自增，2-企业自删)',
  `available_days` tinyint(4) NULL DEFAULT NULL COMMENT '可请假天数',
  `salary_percent` decimal(4, 2) NULL DEFAULT NULL COMMENT '薪水比例',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建人id',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改人id',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_company`(`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '假期管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_leave
-- ----------------------------
INSERT INTO `t_attence_leave` VALUES (1, 1, '病假', 1, 5, 0.70, '2019-04-13 11:37:10', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (2, 1, '事假', 1, 0, 0.00, '2019-04-13 11:38:41', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (3, 1, '婚假', 1, 0, 1.00, '2019-04-13 11:39:23', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (4, 1, '产假', 1, 0, 1.00, '2019-04-13 11:39:36', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (5, 1, '丧假', 2, 0, 0.00, '2019-04-15 18:28:54', 1, '2019-04-15 18:28:57', NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (6, 1, '哺乳假', 1, 0, 1.00, '2019-04-13 11:39:59', 1, NULL, NULL, NULL);
INSERT INTO `t_attence_leave` VALUES (7, 2, '测试', 1, 7, 0.70, '2019-04-15 18:21:20', 2, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_attence_punch_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_punch_detail`;
CREATE TABLE `t_attence_punch_detail`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_id` bigint(16) NULL DEFAULT NULL COMMENT '员工id',
  `punch_type` tinyint(4) NULL DEFAULT NULL COMMENT '考勤类型（0正常，1请假，2年假）',
  `leave_id` bigint(16) NULL DEFAULT NULL COMMENT '请假类型id',
  `punch_time` date NULL DEFAULT NULL COMMENT '考勤日期',
  `punch_in_time` datetime(0) NULL DEFAULT NULL COMMENT '签到时间',
  `punch_out_time` datetime(0) NULL DEFAULT NULL COMMENT '签退时间',
  `punch_in_status` tinyint(4) NULL DEFAULT NULL COMMENT '考勤签到状态(0-未签到，1-正常签到，2-迟到)',
  `punch_out_status` tinyint(4) NULL DEFAULT NULL COMMENT '考勤签退状态(0-未签退，1-正常签退，2-早退)',
  `punch_in_location_lon` decimal(10, 7) NULL DEFAULT NULL COMMENT '签到地点经度',
  `punch_in_location_lat` decimal(10, 7) NULL DEFAULT NULL COMMENT '签到地点纬度',
  `punch_out_location_lon` decimal(10, 7) NULL DEFAULT NULL COMMENT '考勤签退经度',
  `punch_out_location_lat` decimal(10, 7) NULL DEFAULT NULL COMMENT '考勤签退纬度',
  `punch_in_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到地点',
  `punch_out_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签退地点',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_emp`(`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1377 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考勤详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_punch_detail
-- ----------------------------
INSERT INTO `t_attence_punch_detail` VALUES (1034, 1, 0, NULL, '2019-01-01', '2019-01-01 08:32:08', '2019-01-01 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1035, 1, 0, NULL, '2019-01-02', '2019-01-02 08:32:08', '2019-01-02 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1036, 1, 0, NULL, '2019-01-03', '2019-01-03 08:32:08', '2019-01-03 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1037, 1, 0, NULL, '2019-01-04', '2019-01-04 08:32:08', '2019-01-04 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1038, 1, 0, NULL, '2019-01-05', '2019-01-05 08:32:08', '2019-01-05 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1039, 1, 0, NULL, '2019-01-06', '2019-01-06 08:32:08', '2019-01-06 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1040, 1, 0, NULL, '2019-01-07', '2019-01-07 08:32:08', '2019-01-07 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1041, 1, 0, NULL, '2019-01-08', '2019-01-08 08:32:08', '2019-01-08 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1042, 1, 0, NULL, '2019-01-09', '2019-01-09 08:32:08', '2019-01-09 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1043, 1, 0, NULL, '2019-01-10', '2019-01-10 08:32:08', '2019-01-10 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1044, 1, 0, NULL, '2019-01-11', '2019-01-11 08:32:08', '2019-01-11 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1045, 1, 0, NULL, '2019-01-12', '2019-01-12 08:32:08', '2019-01-12 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1046, 1, 0, NULL, '2019-01-13', '2019-01-13 08:32:08', '2019-01-13 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1047, 1, 0, NULL, '2019-01-14', '2019-01-14 08:32:08', '2019-01-14 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1048, 1, 0, NULL, '2019-01-15', '2019-01-15 08:32:08', '2019-01-15 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1049, 1, 0, NULL, '2019-01-16', '2019-01-16 08:32:08', '2019-01-16 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1050, 1, 0, NULL, '2019-01-17', '2019-01-17 08:32:08', '2019-01-17 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1051, 1, 0, NULL, '2019-01-18', '2019-01-18 08:32:08', '2019-01-18 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1052, 1, 0, NULL, '2019-01-19', '2019-01-19 08:32:08', '2019-01-19 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1053, 1, 0, NULL, '2019-01-20', '2019-01-20 08:32:08', '2019-01-20 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1054, 1, 0, NULL, '2019-01-21', '2019-01-21 08:32:08', '2019-01-21 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1055, 1, 0, NULL, '2019-01-22', '2019-01-22 08:32:08', '2019-01-22 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1056, 1, 0, NULL, '2019-01-23', '2019-01-23 08:32:08', '2019-01-23 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1057, 1, 0, NULL, '2019-01-24', '2019-01-24 08:32:08', '2019-01-24 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1058, 1, 0, NULL, '2019-01-25', '2019-01-25 08:32:08', '2019-01-25 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1059, 1, 0, NULL, '2019-01-26', '2019-01-26 08:32:08', '2019-01-26 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1060, 1, 0, NULL, '2019-01-27', '2019-01-27 08:32:08', '2019-01-27 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1061, 1, 0, NULL, '2019-01-28', '2019-01-28 08:32:08', '2019-01-28 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1062, 1, 0, NULL, '2019-01-29', '2019-01-29 08:32:08', '2019-01-29 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1063, 1, 0, NULL, '2019-01-30', '2019-01-30 08:32:08', '2019-01-30 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1064, 1, 0, NULL, '2019-02-01', '2019-02-01 08:32:08', '2019-02-01 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1065, 1, 0, NULL, '2019-02-02', '2019-02-02 08:32:08', '2019-02-02 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1066, 1, 0, NULL, '2019-02-03', '2019-02-03 08:32:08', '2019-02-03 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1067, 1, 0, NULL, '2019-02-04', '2019-02-04 08:32:08', '2019-02-04 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1068, 1, 0, NULL, '2019-02-05', '2019-02-05 08:32:08', '2019-02-05 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1069, 1, 0, NULL, '2019-02-06', '2019-02-06 08:32:08', '2019-02-06 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1070, 1, 0, NULL, '2019-02-07', '2019-02-07 08:32:08', '2019-02-07 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1071, 1, 0, NULL, '2019-02-08', '2019-02-08 08:32:08', '2019-02-08 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1072, 1, 0, NULL, '2019-02-09', '2019-02-09 08:32:08', '2019-02-09 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1073, 1, 0, NULL, '2019-02-10', '2019-02-10 08:32:08', '2019-02-10 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1074, 1, 0, NULL, '2019-02-11', '2019-02-11 08:32:08', '2019-02-11 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1075, 1, 0, NULL, '2019-02-12', '2019-02-12 08:32:08', '2019-02-12 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1076, 1, 0, NULL, '2019-02-13', '2019-02-13 08:32:08', '2019-02-13 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1077, 1, 0, NULL, '2019-02-14', '2019-02-14 08:32:08', '2019-02-14 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1078, 1, 0, NULL, '2019-02-15', '2019-02-15 08:32:08', '2019-02-15 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1079, 1, 0, NULL, '2019-02-16', '2019-02-16 08:32:08', '2019-02-16 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1080, 1, 0, NULL, '2019-02-17', '2019-02-17 08:32:08', '2019-02-17 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1081, 1, 0, NULL, '2019-02-18', '2019-02-18 08:32:08', '2019-02-18 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1082, 1, 0, NULL, '2019-02-19', '2019-02-19 08:32:08', '2019-02-19 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1083, 1, 0, NULL, '2019-02-20', '2019-02-20 08:32:08', '2019-02-20 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1084, 1, 0, NULL, '2019-02-21', '2019-02-21 08:32:08', '2019-02-21 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1085, 1, 0, NULL, '2019-02-22', '2019-02-22 08:32:08', '2019-02-22 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1086, 1, 0, NULL, '2019-02-23', '2019-02-23 08:32:08', '2019-02-23 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1087, 1, 0, NULL, '2019-02-24', '2019-02-24 08:32:08', '2019-02-24 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1088, 1, 0, NULL, '2019-02-25', '2019-02-25 08:32:08', '2019-02-25 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1089, 1, 0, NULL, '2019-02-26', '2019-02-26 08:32:08', '2019-02-26 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1090, 1, 0, NULL, '2019-02-27', '2019-02-27 08:32:08', '2019-02-27 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1091, 1, 0, NULL, '2019-02-28', '2019-02-28 08:32:08', '2019-02-28 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1092, 1, 0, NULL, '2019-03-01', '2019-03-01 08:32:08', '2019-03-01 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1093, 1, 0, NULL, '2019-03-02', '2019-03-02 08:32:08', '2019-03-02 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1094, 1, 0, NULL, '2019-03-01', '2019-03-01 08:32:08', '2019-03-01 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1095, 1, 0, NULL, '2019-03-02', '2019-03-02 08:32:08', '2019-03-02 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1096, 1, 0, NULL, '2019-03-03', '2019-03-03 08:32:08', '2019-03-03 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1097, 1, 0, NULL, '2019-03-04', '2019-03-04 08:32:08', '2019-03-04 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1098, 1, 0, NULL, '2019-03-05', '2019-03-05 08:32:08', '2019-03-05 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1099, 1, 0, NULL, '2019-03-06', '2019-03-06 08:32:08', '2019-03-06 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1100, 1, 0, NULL, '2019-03-07', '2019-03-07 08:32:08', '2019-03-07 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1101, 1, 0, NULL, '2019-03-08', '2019-03-08 08:32:08', '2019-03-08 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1102, 1, 0, NULL, '2019-03-09', '2019-03-09 08:32:08', '2019-03-09 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1103, 1, 0, NULL, '2019-03-10', '2019-03-10 08:32:08', '2019-03-10 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1104, 1, 0, NULL, '2019-03-11', '2019-03-11 08:32:08', '2019-03-11 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1105, 1, 0, NULL, '2019-03-12', '2019-03-12 08:32:08', '2019-03-12 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1106, 1, 0, NULL, '2019-03-13', '2019-03-13 08:32:08', '2019-03-13 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1107, 1, 0, NULL, '2019-03-14', '2019-03-14 08:32:08', '2019-03-14 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1108, 1, 0, NULL, '2019-03-15', '2019-03-15 08:32:08', '2019-03-15 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1109, 1, 0, NULL, '2019-03-16', '2019-03-16 08:32:08', '2019-03-16 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1110, 1, 0, NULL, '2019-03-17', '2019-03-17 08:32:08', '2019-03-17 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1111, 1, 0, NULL, '2019-03-18', '2019-03-18 08:32:08', '2019-03-18 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1112, 1, 0, NULL, '2019-03-19', '2019-03-19 08:32:08', '2019-03-19 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1113, 1, 0, NULL, '2019-03-20', '2019-03-20 08:32:08', '2019-03-20 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1114, 1, 0, NULL, '2019-03-21', '2019-03-21 08:32:08', '2019-03-21 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1115, 1, 0, NULL, '2019-03-22', '2019-03-22 08:32:08', '2019-03-22 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1116, 1, 0, NULL, '2019-03-23', '2019-03-23 08:32:08', '2019-03-23 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1117, 1, 0, NULL, '2019-03-24', '2019-03-24 08:32:08', '2019-03-24 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1118, 1, 0, NULL, '2019-03-25', '2019-03-25 08:32:08', '2019-03-25 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1119, 1, 0, NULL, '2019-03-26', '2019-03-26 08:32:08', '2019-03-26 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1120, 1, 0, NULL, '2019-03-27', '2019-03-27 08:32:08', '2019-03-27 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1121, 1, 0, NULL, '2019-03-28', '2019-03-28 08:32:08', '2019-03-28 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1122, 1, 0, NULL, '2019-03-29', '2019-03-29 08:32:08', '2019-03-29 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1123, 1, 0, NULL, '2019-03-30', '2019-03-30 08:32:08', '2019-03-30 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1124, 1, 0, NULL, '2019-04-01', '2019-04-01 08:32:08', '2019-04-01 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1125, 1, 0, NULL, '2019-04-02', '2019-04-02 08:32:08', '2019-04-02 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1126, 1, 0, NULL, '2019-04-03', '2019-04-03 08:32:08', '2019-04-03 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1127, 1, 0, NULL, '2019-04-04', '2019-04-04 08:32:08', '2019-04-04 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1128, 1, 0, NULL, '2019-04-05', '2019-04-05 08:32:08', '2019-04-05 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1129, 1, 0, NULL, '2019-04-06', '2019-04-06 08:32:08', '2019-04-06 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1130, 1, 0, NULL, '2019-04-07', '2019-04-07 08:32:08', '2019-04-07 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1131, 1, 0, NULL, '2019-04-08', '2019-04-08 08:32:08', '2019-04-08 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1132, 1, 0, NULL, '2019-04-09', '2019-04-09 08:32:08', '2019-04-09 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1133, 1, 0, NULL, '2019-04-10', '2019-04-10 08:32:08', '2019-04-10 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1134, 1, 0, NULL, '2019-04-11', '2019-04-11 08:32:08', '2019-04-11 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1135, 1, 0, NULL, '2019-04-12', '2019-04-12 08:32:08', '2019-04-12 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1136, 1, 0, NULL, '2019-04-13', '2019-04-13 08:32:08', '2019-04-13 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1137, 1, 0, NULL, '2019-04-14', '2019-04-14 08:32:08', '2019-04-14 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1138, 1, 0, NULL, '2019-04-15', '2019-04-15 08:32:08', '2019-04-15 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1139, 1, 0, NULL, '2019-04-16', '2019-04-16 08:32:08', '2019-04-16 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1140, 1, 0, NULL, '2019-04-17', '2019-04-17 08:32:08', '2019-04-17 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1141, 1, 0, NULL, '2019-04-18', '2019-04-18 08:32:08', '2019-04-18 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1142, 1, 0, NULL, '2019-04-19', '2019-04-19 08:32:08', '2019-04-19 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1143, 1, 0, NULL, '2019-04-20', '2019-04-20 08:32:08', '2019-04-20 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1144, 1, 0, NULL, '2019-04-21', '2019-04-21 08:32:08', '2019-04-21 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1145, 1, 0, NULL, '2019-04-22', '2019-04-22 08:32:08', '2019-04-22 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1146, 1, 0, NULL, '2019-04-23', '2019-04-23 08:32:08', '2019-04-23 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1147, 1, 0, NULL, '2019-04-24', '2019-04-24 08:32:08', '2019-04-24 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1148, 1, 0, NULL, '2019-04-25', '2019-04-25 08:32:08', '2019-04-25 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1149, 1, 0, NULL, '2019-04-26', '2019-04-26 08:32:08', '2019-04-26 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1150, 1, 0, NULL, '2019-04-27', '2019-04-27 08:32:08', '2019-04-27 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1151, 1, 0, NULL, '2019-04-28', '2019-04-28 08:32:08', '2019-04-28 18:44:09', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1163, 2, NULL, NULL, '2019-04-29', '2019-04-29 08:58:00', '2019-04-29 17:25:21', 1, 1, 121.1210000, 111.1110000, 121.1210000, 111.1110000, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1340, 2, NULL, NULL, '2019-04-30', NULL, '2019-04-30 16:58:58', NULL, 0, NULL, NULL, 118.1015940, 24.5052030, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1341, 1, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1342, 5, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1343, 6, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1344, 2, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1345, 3, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1346, 4, NULL, NULL, '2019-06-02', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1347, 1, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1348, 5, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1349, 6, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1350, 2, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1351, 3, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1352, 4, NULL, NULL, '2019-06-03', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1353, 1, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1354, 1, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1355, 5, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1356, 5, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1357, 6, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1358, 6, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1359, 2, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1360, 2, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1361, 3, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1362, 3, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1363, 4, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1364, 4, NULL, NULL, '2019-06-04', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1365, 1, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1366, 1, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1367, 5, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1368, 5, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1369, 6, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1370, 6, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1371, 2, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1372, 2, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1373, 3, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1374, 3, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1375, 4, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_punch_detail` VALUES (1376, 4, NULL, NULL, '2019-06-05', NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_attence_record
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_record`;
CREATE TABLE `t_attence_record`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_id` bigint(16) NULL DEFAULT NULL COMMENT '员工id',
  `check_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '刷卡时间',
  `check_type` tinyint(4) NULL DEFAULT NULL COMMENT '刷卡类型(0-进门，1-出门)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_emp`(`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考勤刷卡记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_attence_regulation
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_regulation`;
CREATE TABLE `t_attence_regulation`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `punch_in_start` time(0) NULL DEFAULT NULL COMMENT '签到开始',
  `punch_in_end` time(0) NULL DEFAULT NULL COMMENT '签到结束',
  `punch_out_start` time(0) NULL DEFAULT NULL COMMENT '签退开始',
  `punch_out_end` time(0) NULL DEFAULT NULL COMMENT '签退结束',
  `allow_late` time(0) NULL DEFAULT NULL COMMENT '允许迟到时间',
  `allow_leave_early` time(0) NULL DEFAULT NULL COMMENT '允许早退时间',
  `allow_location_offset` int(4) NULL DEFAULT NULL COMMENT '允许偏差范围（米）',
  `check_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考勤位置',
  `check_location_lon` decimal(10, 7) NULL DEFAULT NULL COMMENT '考勤位置经度',
  `check_location_lat` decimal(10, 7) NULL DEFAULT NULL COMMENT '考勤位置纬度',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型（0默认1自增）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建账号',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改账号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_company`(`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考勤规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_regulation
-- ----------------------------
INSERT INTO `t_attence_regulation` VALUES (1, 0, '国家标准', '08:30:00', '08:30:00', '17:30:00', '17:30:00', '00:30:00', '00:00:00', NULL, NULL, NULL, NULL, 0, '2019-04-13 09:55:55', NULL, NULL, NULL, NULL);
INSERT INTO `t_attence_regulation` VALUES (2, 1, '国家标准', '08:30:00', '08:30:00', '16:30:00', '17:30:00', '00:30:00', '00:00:00', 200, NULL, 118.1028980, 24.5065910, 0, '2019-04-13 09:56:06', NULL, '2019-06-04 02:45:31', NULL, NULL);
INSERT INTO `t_attence_regulation` VALUES (3, 1, 'test1', '21:28:47', '21:29:51', '21:28:56', '21:28:58', '21:29:00', '21:29:02', 200, NULL, 118.0980320, 24.5048790, NULL, '2019-04-14 02:43:12', 1, '2019-05-06 21:29:09', NULL, NULL);

-- ----------------------------
-- Table structure for t_attence_report
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_report`;
CREATE TABLE `t_attence_report`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_id` bigint(16) NULL DEFAULT NULL COMMENT '员工id',
  `punch_in_count` int(11) NULL DEFAULT NULL COMMENT '签到次数',
  `punch_in_late_count` int(11) NULL DEFAULT NULL COMMENT '迟到次数',
  `punch_in_miss_count` int(11) NULL DEFAULT NULL COMMENT '未签到次数',
  `punch_out_count` int(11) NULL DEFAULT NULL COMMENT '签退次数',
  `punch_out_early_count` int(11) NULL DEFAULT NULL COMMENT '早退次数',
  `punch_out_miss_count` int(11) NULL DEFAULT NULL COMMENT '未签退次数',
  `ask_leave_count` int(11) NULL DEFAULT NULL COMMENT '请假次数',
  `work_outside_count` int(11) NULL DEFAULT NULL COMMENT '外勤次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_emp`(`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出勤统计报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_report
-- ----------------------------
INSERT INTO `t_attence_report` VALUES (1, 1, 20, 2, 0, 22, 0, 0, 2, 0);
INSERT INTO `t_attence_report` VALUES (2, 2, 50, 1, 0, 48, 3, 0, 2, 1);
INSERT INTO `t_attence_report` VALUES (3, 3, 107, 4, 1, 108, 4, 0, 1, 0);
INSERT INTO `t_attence_report` VALUES (4, 4, 108, 4, 4, 108, 0, 8, 1, 0);
INSERT INTO `t_attence_report` VALUES (5, 5, 103, 0, 0, 100, 3, 0, 0, 4);
INSERT INTO `t_attence_report` VALUES (6, 6, 99, 0, 1, 97, 2, 1, 0, 6);

-- ----------------------------
-- Table structure for t_attence_workday
-- ----------------------------
DROP TABLE IF EXISTS `t_attence_workday`;
CREATE TABLE `t_attence_workday`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `regulation_id` bigint(16) NULL DEFAULT NULL COMMENT '考勤规则id',
  `week_day` tinyint(4) NULL DEFAULT NULL COMMENT '工作日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作日表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attence_workday
-- ----------------------------
INSERT INTO `t_attence_workday` VALUES (6, 3, 1);
INSERT INTO `t_attence_workday` VALUES (7, 3, 2);
INSERT INTO `t_attence_workday` VALUES (8, 3, 3);
INSERT INTO `t_attence_workday` VALUES (9, 3, 4);
INSERT INTO `t_attence_workday` VALUES (10, 3, 5);
INSERT INTO `t_attence_workday` VALUES (20, 2, 1);
INSERT INTO `t_attence_workday` VALUES (21, 2, 2);
INSERT INTO `t_attence_workday` VALUES (22, 2, 3);
INSERT INTO `t_attence_workday` VALUES (23, 2, 4);

-- ----------------------------
-- Table structure for t_company_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_company_dept`;
CREATE TABLE `t_company_dept`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  `attence_regulation_id` bigint(16) NULL DEFAULT NULL COMMENT '考勤规则id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company_dept
-- ----------------------------
INSERT INTO `t_company_dept` VALUES (1, 1, 2, '产品技术中心');
INSERT INTO `t_company_dept` VALUES (2, 1, 2, '人事管理');
INSERT INTO `t_company_dept` VALUES (3, 1, 2, '运维部门');

-- ----------------------------
-- Table structure for t_company_img
-- ----------------------------
DROP TABLE IF EXISTS `t_company_img`;
CREATE TABLE `t_company_img`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '企业id',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `img_detail` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片简介',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_company`(`company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_company_info
-- ----------------------------
DROP TABLE IF EXISTS `t_company_info`;
CREATE TABLE `t_company_info`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `scope_of_business` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `type` bigint(16) NULL DEFAULT NULL COMMENT '企业类型',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `login_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业登录页logo',
  `page_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主页右上角logo',
  `web_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器右上角logo',
  `regist_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '成立时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company_info
-- ----------------------------
INSERT INTO `t_company_info` VALUES (1, '神州优车集团', NULL, NULL, '福建省厦门市思明区', NULL, 'http://img.znzn.me/group1/M00/00/00/CowAB1y0hxWAdhduAAAjnSt3xRM023.jpg', 'http://img.znzn.me/group1/M00/00/00/CowAB1y4DbqATYm3AAMr6BO95Sg319.jpg', NULL, '2019-04-13 16:00:00', '2019-04-11 16:00:00');

-- ----------------------------
-- Table structure for t_company_product
-- ----------------------------
DROP TABLE IF EXISTS `t_company_product`;
CREATE TABLE `t_company_product`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '企业id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品简介',
  `product_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品链接',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建id',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_company`(`company_id`) USING BTREE,
  INDEX `index_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company_product
-- ----------------------------
INSERT INTO `t_company_product` VALUES (3, 1, 'QQ', '即时通讯软件', 'https://www.qq.com', '2019-04-18 09:57:41', 1, '2019-04-20 04:36:07', NULL, NULL);
INSERT INTO `t_company_product` VALUES (6, 1, 'WeChat', '即时通信软体', 'https://wx.qq.com/', '2019-04-20 05:02:23', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_company_product_img
-- ----------------------------
DROP TABLE IF EXISTS `t_company_product_img`;
CREATE TABLE `t_company_product_img`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(16) NULL DEFAULT NULL COMMENT '产品id',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `img_detail` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片介绍',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_product`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业产品图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company_product_img
-- ----------------------------
INSERT INTO `t_company_product_img` VALUES (1, 3, 'http://img.znzn.me/group1/M00/00/00/CowAB1y6AueAIa8iAAAjnSt3xRM962.jpg', '111', '2019-04-19 06:31:43');
INSERT INTO `t_company_product_img` VALUES (10, 3, 'http://img.znzn.me/group1/M00/00/00/CowAB1y6o8aAJ8wkAAWV5TrfSdQ137.jpg', '222', '2019-04-20 04:45:00');
INSERT INTO `t_company_product_img` VALUES (11, 3, 'http://img.znzn.me/group1/M00/00/01/CowAB1zQN8WAJw-rAAHRmxlFwbw748.png', 'adddd', '2019-05-06 21:34:23');

-- ----------------------------
-- Table structure for t_device_info
-- ----------------------------
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mac` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备识别号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '设备类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job`;
CREATE TABLE `t_schedule_job`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 1启动 2暂停',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除 0 否 1是',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` bigint(16) NULL DEFAULT NULL COMMENT '创建人id',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_id` bigint(16) NULL DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_job
-- ----------------------------
INSERT INTO `t_schedule_job` VALUES (1, '更新考勤详情', '0 0 1 * * ? ', 'updatePunchJob', 'execute', 1, 0, '2019-04-28 11:08:55', NULL, '2019-04-30 15:59:08', NULL);

-- ----------------------------
-- Table structure for t_security_info
-- ----------------------------
DROP TABLE IF EXISTS `t_security_info`;
CREATE TABLE `t_security_info`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operate_module` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作模块',
  `operate_event` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作事件',
  `operate_id` bigint(16) NULL DEFAULT NULL COMMENT '操作员id',
  `operate_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员名称',
  `operate_detail` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作详情',
  `operate_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_webvisit_log
-- ----------------------------
DROP TABLE IF EXISTS `t_webvisit_log`;
CREATE TABLE `t_webvisit_log`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `method` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入参',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_webvisit_log
-- ----------------------------
INSERT INTO `t_webvisit_log` VALUES (72, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-04-22 21:00:25', 1);
INSERT INTO `t_webvisit_log` VALUES (73, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-04-23 10:03:33', 1);
INSERT INTO `t_webvisit_log` VALUES (74, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-04-23 11:08:20', 1);
INSERT INTO `t_webvisit_log` VALUES (75, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-07\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:24:13', 1);
INSERT INTO `t_webvisit_log` VALUES (76, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-07\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:24:13', 1);
INSERT INTO `t_webvisit_log` VALUES (77, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-01-02\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:41:52', 1);
INSERT INTO `t_webvisit_log` VALUES (78, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-01-01\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:41:55', 1);
INSERT INTO `t_webvisit_log` VALUES (79, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-01\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:02', 1);
INSERT INTO `t_webvisit_log` VALUES (80, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-02\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:06', 1);
INSERT INTO `t_webvisit_log` VALUES (81, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-02\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:08', 1);
INSERT INTO `t_webvisit_log` VALUES (82, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-04-05\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:11', 1);
INSERT INTO `t_webvisit_log` VALUES (83, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-04-05\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:12', 1);
INSERT INTO `t_webvisit_log` VALUES (84, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-04-15\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:42:15', 1);
INSERT INTO `t_webvisit_log` VALUES (85, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-05-03\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 11:44:02', 1);
INSERT INTO `t_webvisit_log` VALUES (86, 'zhuzening', '更新年假规则', 'com.webvisit.controller.AttenceController.editAnnual', '{}', '0:0:0:0:0:0:0:1', '2019-04-23 14:43:32', 1);
INSERT INTO `t_webvisit_log` VALUES (87, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-05\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 14:49:00', 1);
INSERT INTO `t_webvisit_log` VALUES (88, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-06\"]}', '0:0:0:0:0:0:0:1', '2019-04-23 14:49:50', 1);
INSERT INTO `t_webvisit_log` VALUES (89, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-04-23 14:50:27', 1);
INSERT INTO `t_webvisit_log` VALUES (90, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-04\"]}', '0:0:0:0:0:0:0:1', '2019-04-25 14:55:04', 1);
INSERT INTO `t_webvisit_log` VALUES (91, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-05-06 21:29:27', 1);
INSERT INTO `t_webvisit_log` VALUES (92, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-08\"]}', '0:0:0:0:0:0:0:1', '2019-05-06 21:29:53', 1);
INSERT INTO `t_webvisit_log` VALUES (93, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-08\"]}', '0:0:0:0:0:0:0:1', '2019-05-06 21:29:59', 1);
INSERT INTO `t_webvisit_log` VALUES (94, 'zhuzening', '增加公司产品图片', 'com.webvisit.controller.YellowPageController.addCompanyProductImg', '{}', '0:0:0:0:0:0:0:1', '2019-05-06 21:34:24', 1);
INSERT INTO `t_webvisit_log` VALUES (95, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:00', 1);
INSERT INTO `t_webvisit_log` VALUES (96, 'zhuzening', '删除考勤规则', 'com.webvisit.controller.AttenceController.deleteRegulation', '{\"regulationId\":[\"4\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:16', 1);
INSERT INTO `t_webvisit_log` VALUES (97, 'zhuzening', '删除年假规则', 'com.webvisit.controller.AttenceController.deleteAnnual', '{\"annualId\":[\"4\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:33', 1);
INSERT INTO `t_webvisit_log` VALUES (98, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-01-01\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:36', 1);
INSERT INTO `t_webvisit_log` VALUES (99, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-01-01\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:38', 1);
INSERT INTO `t_webvisit_log` VALUES (100, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-02\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:40', 1);
INSERT INTO `t_webvisit_log` VALUES (101, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-02\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:42', 1);
INSERT INTO `t_webvisit_log` VALUES (102, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-06-10\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 01:05:48', 1);
INSERT INTO `t_webvisit_log` VALUES (103, 'zhuzening', '更新考勤规则', 'com.webvisit.controller.AttenceController.updateRegulation', '{}', '0:0:0:0:0:0:0:1', '2019-06-04 02:45:32', 1);
INSERT INTO `t_webvisit_log` VALUES (104, 'zhuzening', '更新工作日', 'com.webvisit.controller.AttenceController.editWorkday', '{}', '0:0:0:0:0:0:0:1', '2019-06-04 02:45:40', 1);
INSERT INTO `t_webvisit_log` VALUES (105, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-01-01\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:07', 1);
INSERT INTO `t_webvisit_log` VALUES (106, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-01-01\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:09', 1);
INSERT INTO `t_webvisit_log` VALUES (107, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-02-03\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:10', 1);
INSERT INTO `t_webvisit_log` VALUES (108, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-02-03\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:12', 1);
INSERT INTO `t_webvisit_log` VALUES (109, 'zhuzening', '新增节假日', 'com.webvisit.controller.AttenceController.addHoliday', '{\"date\":[\"2019-06-04\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:19', 1);
INSERT INTO `t_webvisit_log` VALUES (110, 'zhuzening', '取消节假日', 'com.webvisit.controller.AttenceController.cancelHoliday', '{\"date\":[\"2019-06-04\"]}', '0:0:0:0:0:0:0:1', '2019-06-04 02:46:25', 1);

-- ----------------------------
-- Table structure for t_webvisit_r_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_webvisit_r_user_role`;
CREATE TABLE `t_webvisit_r_user_role`  (
  `id` bigint(16) NOT NULL COMMENT '主键',
  `user_id` bigint(16) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(16) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_webvisit_role
-- ----------------------------
DROP TABLE IF EXISTS `t_webvisit_role`;
CREATE TABLE `t_webvisit_role`  (
  `id` bigint(16) NOT NULL COMMENT '主键',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_webvisit_user
-- ----------------------------
DROP TABLE IF EXISTS `t_webvisit_user`;
CREATE TABLE `t_webvisit_user`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `mobile` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `company_id` bigint(255) NULL DEFAULT NULL COMMENT '公司id',
  `dept_id` bigint(255) NULL DEFAULT NULL COMMENT '部门id',
  `employment_date` date NULL DEFAULT NULL COMMENT '入职日期',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '用户状态(0冻结1有效)',
  `salt` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `last_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '本次登录时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_account_id` bigint(16) NULL DEFAULT NULL COMMENT '创建人',
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modify_account_id` bigint(16) NULL DEFAULT NULL COMMENT '修改人id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`) USING BTREE,
  INDEX `index_mobile`(`mobile`) USING BTREE,
  INDEX `index_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_webvisit_user
-- ----------------------------
INSERT INTO `t_webvisit_user` VALUES (1, 'zhuzening', '朱泽宁', 0, NULL, NULL, '380a0cb7755cf12e98e27827f6589a95fa83c3ae1a414247', 1, 1, '2019-04-03', 1, NULL, '2019-04-13 09:53:30', '2019-04-13 09:53:30', NULL, NULL, '2019-04-13 09:53:30', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (2, 'test1', '测试1', 0, NULL, NULL, 'c44023e9df95933c27851a13174180e65f10624c17886b26', 1, 2, NULL, NULL, NULL, '2019-04-13 12:11:06', '2019-04-13 12:11:06', NULL, NULL, '2019-04-13 12:11:06', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (3, 'test2', '测试2', 1, NULL, NULL, '91cb7fd79a63a75592827b5bb4b194651a42e25d4d40178d', 1, 3, NULL, NULL, NULL, '2019-04-13 12:11:07', '2019-04-13 12:11:07', NULL, NULL, '2019-04-13 12:11:07', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (4, 'test3', '测试3', 1, NULL, NULL, 'e3db5992a08934d55879ce4393d80539d75e572a1736ba50', 1, 3, NULL, NULL, NULL, '2019-04-13 12:11:11', '2019-04-13 12:11:11', NULL, NULL, '2019-04-13 12:11:11', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (5, 'test4', '测试4', 0, NULL, NULL, 'd5114759935a307498e1c22457f07f052711055449081712', 1, 1, NULL, NULL, NULL, '2019-04-13 12:11:12', '2019-04-13 12:11:12', NULL, NULL, '2019-04-13 12:11:12', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (6, 'test5', '测试5', 0, NULL, NULL, 'f59f0f553886239a63312476986562a79f02b8520e265549', 1, 1, NULL, NULL, NULL, '2019-04-13 12:11:15', '2019-04-13 12:11:15', NULL, NULL, '2019-04-13 12:11:15', NULL, NULL);
INSERT INTO `t_webvisit_user` VALUES (7, 'test4', NULL, NULL, NULL, NULL, 'f8cf1f516f6a10d18d606c0e54874a67953911579b369093', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_webvisit_visitor
-- ----------------------------
DROP TABLE IF EXISTS `t_webvisit_visitor`;
CREATE TABLE `t_webvisit_visitor`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(16) NULL DEFAULT NULL COMMENT '公司id',
  `emp_id` bigint(16) NULL DEFAULT NULL COMMENT '邀请人id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `face_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸信息',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证信息',
  `business_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_webvisit_visitor
-- ----------------------------
INSERT INTO `t_webvisit_visitor` VALUES (1, 1, 1, '韦小宝', 'http://img.znzn.me/group1/M00/00/01/CowAB1zvq66AYBt7AAAdcUTUBeY198.jpg', 'http://img.znzn.me/group1/M00/00/01/CowAB1zvq3-AJp53AAEFOZFLcR8663.jpg', NULL);
INSERT INTO `t_webvisit_visitor` VALUES (4, 1, 2, '爱新觉罗·玄烨', 'http://img.znzn.me/group1/M00/00/01/CowAB1zz-paAdrMGAADUdOKfYFM568.jpg', 'http://img.znzn.me/group1/M00/00/01/CowAB1zz-lmADsQEAAB17hkdfcU86.jpeg', NULL);

SET FOREIGN_KEY_CHECKS = 1;
