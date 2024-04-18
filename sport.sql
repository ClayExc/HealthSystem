/*
 Navicat Premium Data Transfer

 Source Server         : sport
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : sport

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 30/06/2023 19:29:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_disease
-- ----------------------------
DROP TABLE IF EXISTS `t_disease`;
CREATE TABLE `t_disease`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '疾病id',
  `disease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病名字',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disease
-- ----------------------------
INSERT INTO `t_disease` VALUES (1, 'l性糖尿病', NULL);
INSERT INTO `t_disease` VALUES (2, '冠心病', NULL);
INSERT INTO `t_disease` VALUES (3, '高血压', NULL);
INSERT INTO `t_disease` VALUES (4, '退行性骨关节炎', NULL);
INSERT INTO `t_disease` VALUES (5, '骨质疏松症', NULL);
INSERT INTO `t_disease` VALUES (6, '急性肝炎', NULL);
INSERT INTO `t_disease` VALUES (7, '细菌性痢疾', NULL);
INSERT INTO `t_disease` VALUES (8, '急性扁桃体炎', NULL);
INSERT INTO `t_disease` VALUES (9, '肺炎', NULL);
INSERT INTO `t_disease` VALUES (10, '血友病', NULL);
INSERT INTO `t_disease` VALUES (11, '白血病', NULL);
INSERT INTO `t_disease` VALUES (12, '血小板减少性紫癜', NULL);
INSERT INTO `t_disease` VALUES (13, '支气管扩张咯血', NULL);
INSERT INTO `t_disease` VALUES (14, '消化道出血', NULL);
INSERT INTO `t_disease` VALUES (15, '病毒性心肌炎', NULL);
INSERT INTO `t_disease` VALUES (16, '急性病毒性肝炎', NULL);
INSERT INTO `t_disease` VALUES (17, '肺结核病的急性期', NULL);
INSERT INTO `t_disease` VALUES (18, '心肌梗塞', NULL);
INSERT INTO `t_disease` VALUES (19, '视网膜病变', NULL);
INSERT INTO `t_disease` VALUES (20, '充血性心脏衰竭', NULL);
INSERT INTO `t_disease` VALUES (21, '不稳定的心绞痛', NULL);
INSERT INTO `t_disease` VALUES (22, '急性心肌炎或心包炎', NULL);
INSERT INTO `t_disease` VALUES (23, '严重的主动脉狭窄', NULL);
INSERT INTO `t_disease` VALUES (24, '人工起搏等心血管疾病', NULL);

-- ----------------------------
-- Table structure for t_disease_recipe
-- ----------------------------
DROP TABLE IF EXISTS `t_disease_recipe`;
CREATE TABLE `t_disease_recipe`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '疾病id',
  `recipe_id` int(0) NOT NULL COMMENT '处方id',
  `dis_1` int(0) NULL DEFAULT NULL COMMENT '1、l性糖尿病',
  `dis_2` int(0) NULL DEFAULT NULL COMMENT '2、冠心病',
  `dis_3` int(0) NULL DEFAULT NULL COMMENT '\r\n3、高血压',
  `dis_4` int(0) NULL DEFAULT NULL COMMENT '4、退行性骨关节炎',
  `dis_5` int(0) NULL DEFAULT NULL COMMENT '5、骨质疏松症',
  `dis_6` int(0) NULL DEFAULT NULL COMMENT '6、急性肝炎',
  `dis_7` int(0) NULL DEFAULT NULL COMMENT '7、细菌性痢疾',
  `dis_8` int(0) NULL DEFAULT NULL COMMENT '8、急性扁桃体炎',
  `dis_9` int(0) NULL DEFAULT NULL COMMENT '9、肺炎',
  `dis_10` int(0) NULL DEFAULT NULL COMMENT '10、血友病',
  `dis_11` int(0) NULL DEFAULT NULL COMMENT '11、白血病',
  `dis_12` int(0) NULL DEFAULT NULL COMMENT '12、血小板减少性紫癜',
  `dis_13` int(0) NULL DEFAULT NULL COMMENT '13、支气管扩张咯血',
  `dis_14` int(0) NULL DEFAULT NULL COMMENT '14、消化道出血',
  `dis_15` int(0) NULL DEFAULT NULL COMMENT '15、病毒性心肌炎',
  `dis_16` int(0) NOT NULL COMMENT '16、急性病毒性肝炎',
  `dis_17` int(0) NULL DEFAULT NULL COMMENT '17、肺结核病的急性期',
  `dis_18` int(0) NULL DEFAULT NULL COMMENT '18、心肌梗塞',
  `dis_19` int(0) NULL DEFAULT NULL COMMENT '19、视网膜病变',
  `dis_20` int(0) NULL DEFAULT NULL COMMENT '20、充血性心脏衰竭',
  `dis_21` int(0) NULL DEFAULT NULL COMMENT '21、不稳定的心绞痛',
  `dis_22` int(0) NULL DEFAULT NULL COMMENT '22、急性心肌炎或心包炎',
  `dis_23` int(0) NULL DEFAULT NULL COMMENT '23、严重的主动脉狭窄',
  `dis_24` int(0) NULL DEFAULT NULL COMMENT '24、人工起搏等心血管疾病',
  PRIMARY KEY (`id`, `dis_16`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disease_recipe
-- ----------------------------

-- ----------------------------
-- Table structure for t_disease_user
-- ----------------------------
DROP TABLE IF EXISTS `t_disease_user`;
CREATE TABLE `t_disease_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '疾病id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `dis_1` int(0) NULL DEFAULT NULL COMMENT '1、l性糖尿病',
  `dis_2` int(0) NULL DEFAULT NULL COMMENT '2、冠心病',
  `dis_3` int(0) NULL DEFAULT NULL COMMENT '\r\n3、高血压',
  `dis_4` int(0) NULL DEFAULT NULL COMMENT '4、退行性骨关节炎',
  `dis_5` int(0) NULL DEFAULT NULL COMMENT '5、骨质疏松症',
  `dis_6` int(0) NULL DEFAULT NULL COMMENT '6、急性肝炎',
  `dis_7` int(0) NULL DEFAULT NULL COMMENT '7、细菌性痢疾',
  `dis_8` int(0) NULL DEFAULT NULL COMMENT '8、急性扁桃体炎',
  `dis_9` int(0) NULL DEFAULT NULL COMMENT '9、肺炎',
  `dis_10` int(0) NULL DEFAULT NULL COMMENT '10、血友病',
  `dis_11` int(0) NULL DEFAULT NULL COMMENT '11、白血病',
  `dis_12` int(0) NULL DEFAULT NULL COMMENT '12、血小板减少性紫癜',
  `dis_13` int(0) NULL DEFAULT NULL COMMENT '13、支气管扩张咯血',
  `dis_14` int(0) NULL DEFAULT NULL COMMENT '14、消化道出血',
  `dis_15` int(0) NULL DEFAULT NULL COMMENT '15、病毒性心肌炎',
  `dis_16` int(0) NULL DEFAULT NULL COMMENT '16、急性病毒性肝炎',
  `dis_17` int(0) NULL DEFAULT NULL COMMENT '17、肺结核病的急性期',
  `dis_18` int(0) NULL DEFAULT NULL COMMENT '18、心肌梗塞',
  `dis_19` int(0) NULL DEFAULT NULL COMMENT '19、视网膜病变',
  `dis_20` int(0) NULL DEFAULT NULL COMMENT '20、充血性心脏衰竭',
  `dis_21` int(0) NULL DEFAULT NULL COMMENT '21、不稳定的心绞痛',
  `dis_22` int(0) NULL DEFAULT NULL COMMENT '22、急性心肌炎或心包炎',
  `dis_23` int(0) NULL DEFAULT NULL COMMENT '23、严重的主动脉狭窄',
  `dis_24` int(0) NULL DEFAULT NULL COMMENT '24、人工起搏等心血管疾病',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disease_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_fatigue_degree
-- ----------------------------
DROP TABLE IF EXISTS `t_fatigue_degree`;
CREATE TABLE `t_fatigue_degree`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '运动疲劳度等级',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fatigue_degree
-- ----------------------------

-- ----------------------------
-- Table structure for t_individuation_recipe
-- ----------------------------
DROP TABLE IF EXISTS `t_individuation_recipe`;
CREATE TABLE `t_individuation_recipe`  (
  `id` int(0) NOT NULL COMMENT '个性化处方id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处方名称',
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `standard_id` int(0) NOT NULL COMMENT '标准处方id',
  `sports_effect_id` int(0) NULL DEFAULT NULL COMMENT '运动效果id',
  `sports_type_id` int(0) NULL DEFAULT NULL COMMENT '运动类型id',
  `sports_intensity_id` int(0) NULL DEFAULT NULL COMMENT '运动强度id',
  `sports_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动时间，区间用~进行间隔',
  `sports_frequency` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动频率，以周为单位，区间用~进行间隔',
  `sports_must_equipment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动所需器材',
  `sports_fatigue` int(0) NULL DEFAULT NULL COMMENT '运动疲劳度等级id',
  `run_speed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跑步速度，区间用户~进行间隔，单位min/公里',
  `subject_feel_id` int(0) NULL DEFAULT NULL COMMENT '主观运动感觉id',
  `taboo_disease_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禁忌疾病id',
  `methods_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法介绍',
  `review_cycle` int(0) NULL DEFAULT NULL COMMENT '评测周期',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技巧视频地址',
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注意事项',
  `recipe_num` int(0) NULL DEFAULT NULL COMMENT '处方生成次数记录,反应当前是第几次生成的处方',
  `recipe_time` date NULL DEFAULT NULL COMMENT '处方生成的日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_individuation_recipe
-- ----------------------------

-- ----------------------------
-- Table structure for t_sport_ability
-- ----------------------------
DROP TABLE IF EXISTS `t_sport_ability`;
CREATE TABLE `t_sport_ability`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '运动能力id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `yangwoqizuo` int(0) NULL DEFAULT NULL COMMENT '一分钟仰卧起做个数',
  `fuwocheng` int(0) NULL DEFAULT NULL COMMENT '一分钟俯卧撑个数',
  `taijieceshi` float NULL DEFAULT NULL COMMENT '台阶测试评分',
  `fenzhongpao` float NULL DEFAULT NULL COMMENT '12分钟跑评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sport_ability
-- ----------------------------
INSERT INTO `t_sport_ability` VALUES (10001, 10001, 51, 39, 161, NULL);

-- ----------------------------
-- Table structure for t_sport_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_sport_equipment`;
CREATE TABLE `t_sport_equipment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `equipment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sport_equipment
-- ----------------------------
INSERT INTO `t_sport_equipment` VALUES (1, '哑铃');
INSERT INTO `t_sport_equipment` VALUES (2, '杠铃');
INSERT INTO `t_sport_equipment` VALUES (3, '颈帽');
INSERT INTO `t_sport_equipment` VALUES (4, '壶铃');
INSERT INTO `t_sport_equipment` VALUES (5, '沙袋');
INSERT INTO `t_sport_equipment` VALUES (6, '橡皮带');
INSERT INTO `t_sport_equipment` VALUES (7, '网球');
INSERT INTO `t_sport_equipment` VALUES (8, '弹力球');
INSERT INTO `t_sport_equipment` VALUES (9, '跳绳');

-- ----------------------------
-- Table structure for t_sport_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sport_type`;
CREATE TABLE `t_sport_type`  (
  `id` int(0) NOT NULL COMMENT '运动类型id',
  `sport_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动类型',
  `sport_types_id` int(0) NULL DEFAULT NULL COMMENT '运动种类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sport_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_sport_types
-- ----------------------------
DROP TABLE IF EXISTS `t_sport_types`;
CREATE TABLE `t_sport_types`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '运动种类id',
  `sport_types_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动种类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sport_types
-- ----------------------------
INSERT INTO `t_sport_types` VALUES (1, '跑步');
INSERT INTO `t_sport_types` VALUES (2, '游泳');
INSERT INTO `t_sport_types` VALUES (3, '乒乓球');
INSERT INTO `t_sport_types` VALUES (4, '羽毛球');
INSERT INTO `t_sport_types` VALUES (5, '篮球');
INSERT INTO `t_sport_types` VALUES (6, '足球');
INSERT INTO `t_sport_types` VALUES (7, '爬楼梯');
INSERT INTO `t_sport_types` VALUES (8, '骑行');

-- ----------------------------
-- Table structure for t_sports_effect
-- ----------------------------
DROP TABLE IF EXISTS `t_sports_effect`;
CREATE TABLE `t_sports_effect`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sports_effect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sports_effect
-- ----------------------------
INSERT INTO `t_sports_effect` VALUES (1, '减脂瘦身');
INSERT INTO `t_sports_effect` VALUES (2, '增肌');
INSERT INTO `t_sports_effect` VALUES (3, '增强心肺能力');
INSERT INTO `t_sports_effect` VALUES (4, '强身健体');
INSERT INTO `t_sports_effect` VALUES (5, '增强有氧耐力');
INSERT INTO `t_sports_effect` VALUES (6, '增强无氧耐力');
INSERT INTO `t_sports_effect` VALUES (7, '缓解亚健康');

-- ----------------------------
-- Table structure for t_sports_intensity
-- ----------------------------
DROP TABLE IF EXISTS `t_sports_intensity`;
CREATE TABLE `t_sports_intensity`  (
  `id` int(0) NOT NULL COMMENT '运动强度id',
  `heart_rate_min` int(0) NULL DEFAULT NULL COMMENT '最小心率(最大心率百分比进行刻画）',
  `heart_rate_max` int(0) NULL DEFAULT NULL COMMENT '最大心率(最大心率百分比进行刻画）',
  `fuzhong_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负重范围',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '每组的运动次数范围',
  `group_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需要运动的组数范围',
  `intensity_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '强度刻画类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sports_intensity
-- ----------------------------

-- ----------------------------
-- Table structure for t_sports_intensity_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_sports_intensity_standard`;
CREATE TABLE `t_sports_intensity_standard`  (
  `id` int(0) NOT NULL COMMENT '运动强度id(标准处方)',
  `heart_rate_min` int(0) NULL DEFAULT NULL COMMENT '最小心率(最大心率百分比进行刻画）',
  `heart_rate_max` int(0) NULL DEFAULT NULL COMMENT '最大心率(最大心率百分比进行刻画）',
  `fuzhong_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负重范围',
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '每组的运动次数范围',
  `group_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需要运动的组数范围',
  `intensity_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '强度刻画类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sports_intensity_standard
-- ----------------------------

-- ----------------------------
-- Table structure for t_standard_recipe
-- ----------------------------
DROP TABLE IF EXISTS `t_standard_recipe`;
CREATE TABLE `t_standard_recipe`  (
  `id` int(0) NOT NULL COMMENT '标准处方id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处方名称',
  `sports_effect_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动效果id',
  `sports_type_id` int(0) NULL DEFAULT NULL COMMENT '运动类型id',
  `sports_intensity_id` int(0) NULL DEFAULT NULL COMMENT '运动强度id',
  `sports_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动时间，区间用~进行间隔',
  `sports_frequency` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动频率，以周为单位，区间用~进行间隔',
  `sports_must_equipment_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动所需器材id',
  `sports_fatigue` int(0) NULL DEFAULT NULL COMMENT '运动疲劳度等级',
  `taboo_disease_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禁忌疾病id',
  `methods_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法介绍',
  `review_cycle` int(0) NULL DEFAULT NULL COMMENT '评测周期',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技巧视频地址',
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注意事项',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_standard_recipe
-- ----------------------------

-- ----------------------------
-- Table structure for t_subhealthy
-- ----------------------------
DROP TABLE IF EXISTS `t_subhealthy`;
CREATE TABLE `t_subhealthy`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '亚健康id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `q_1` int(0) NULL DEFAULT NULL COMMENT '1、我感觉到疲乏',
  `q_2` int(0) NULL DEFAULT NULL COMMENT '2、我的疲乏在休息后不能缓解',
  `q_3` int(0) NULL DEFAULT NULL COMMENT '\r\n3、我感到头脑昏沉',
  `q_4` int(0) NULL DEFAULT NULL COMMENT '4、我颈背肌肉酸痛',
  `q_5` int(0) NULL DEFAULT NULL COMMENT '5、我感到眼睛干涩酸胀',
  `q_6` int(0) NULL DEFAULT NULL COMMENT '6、我感到咽干',
  `q_7` int(0) NULL DEFAULT NULL COMMENT '7、我感到记住事情很困难',
  `q_8` int(0) NULL DEFAULT NULL COMMENT '8、我的食欲很差',
  `q_9` int(0) NULL DEFAULT NULL COMMENT '9、我感到腹满腹胀',
  `q_10` int(0) NULL DEFAULT NULL COMMENT '10、我很容易出汗',
  `q_11` int(0) NULL DEFAULT NULL COMMENT '11、我睡着后经常出汗',
  `q_12` int(0) NULL DEFAULT NULL COMMENT '12、我感到手脚心发热',
  `q_13` int(0) NULL DEFAULT NULL COMMENT '13、我感到心慌',
  `q_14` int(0) NULL DEFAULT NULL COMMENT '14、我感到胸闷',
  `q_15` int(0) NULL DEFAULT NULL COMMENT '15、我感到身体有疼痛',
  `q_16` int(0) NULL DEFAULT NULL COMMENT '16、我的睡眠质量很差',
  `q_17` int(0) NULL DEFAULT NULL COMMENT '17、我每天都难以入睡',
  `q_18` int(0) NULL DEFAULT NULL COMMENT '18、我睡觉时蒙多，经常从噩梦中惊醒',
  `q_19` int(0) NULL DEFAULT NULL COMMENT '19、我思考问题时注意力不容易集中',
  `q_20` int(0) NULL DEFAULT NULL COMMENT '20、我感觉心情烦躁',
  `q_21` int(0) NULL DEFAULT NULL COMMENT '21、我对什么事情都不感兴趣',
  `q_22` int(0) NULL DEFAULT NULL COMMENT '22、我的情绪非常不好，什么事都不能使我高兴',
  `q_23` int(0) NULL DEFAULT NULL COMMENT '23、我经常对身边的人发脾气',
  `q_24` int(0) NULL DEFAULT NULL COMMENT '24、我总觉得似乎有不幸事情要降临',
  `q_25` int(0) NULL DEFAULT NULL COMMENT '25、我没有充沛的精力去应付日常生活',
  `q_26` int(0) NULL DEFAULT NULL COMMENT '26、我感觉到压力很大',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subhealthy
-- ----------------------------
INSERT INTO `t_subhealthy` VALUES (1, 10001, 3, 3, 3, 2, 3, 3, 5, 5, 4, 3, 4, 3, 4, 3, 2, 5, 4, 4, 2, 4, 4, 3, 4, 4, 3, 5);

-- ----------------------------
-- Table structure for t_subject_feel
-- ----------------------------
DROP TABLE IF EXISTS `t_subject_feel`;
CREATE TABLE `t_subject_feel`  (
  `id` int(0) NOT NULL COMMENT '主观运动感觉id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heart_rate_up` int(0) NULL DEFAULT NULL,
  `heart_rate_down` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subject_feel
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_birthday` date NULL DEFAULT NULL COMMENT '生日',
  `user_sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `user_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_bmi` float NULL DEFAULT NULL,
  `user_disease` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病id，用“，”对id进行分割',
  `user_optimal_rate` int(0) NULL DEFAULT NULL COMMENT '安静时心率',
  `user_is_manager` int(0) NULL DEFAULT NULL COMMENT '是否是管理员',
  `user_image` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `user_delcar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宣言',
  `user_weight` float NULL DEFAULT NULL COMMENT '身高',
  `user_height` float NULL DEFAULT NULL COMMENT '体重',
  `user_waist` float NULL DEFAULT NULL COMMENT '腰围',
  `user_chest` float NULL DEFAULT NULL COMMENT '胸围',
  `user_hipline` float NULL DEFAULT NULL COMMENT '臀围',
  `user_subhealthy` int(0) NULL DEFAULT NULL COMMENT '亚健康id',
  `user_sport_ability` int(0) NULL DEFAULT NULL COMMENT '运动能力id',
  `user_sport_equipment_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拥有的运动器材id，用“，”对id进行分割',
  `user_sport_like` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '喜欢的运动种类id，用“，”对id进行分割',
  `user_sport_objective1` int(0) NULL DEFAULT NULL COMMENT '用户选择的运动目标',
  `user_sport_objective2` int(0) NULL DEFAULT NULL COMMENT '系统确定的运动目标',
  `user_objective_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动目标描述',
  `user_recipe_num` int(0) NULL DEFAULT NULL COMMENT '第几次生成的处方',
  `user_recipe_status` int(0) NULL DEFAULT NULL COMMENT '1为存在运动处方，0为因患有疾病无法生成运动处方',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (10001, 'wkz', '1995-07-05', '男', '18866655544', '123', NULL, '3', 76, 1, NULL, '加油，让自己动起来', 62, 175, 75, 76, 96, 1, 10001, '1,2,3', '1,2,3', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_recipe
-- ----------------------------
DROP TABLE IF EXISTS `t_user_recipe`;
CREATE TABLE `t_user_recipe`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户处方参数id',
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `sport_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动方式',
  `sport_strength` int(0) NULL DEFAULT NULL COMMENT '运动强度等级',
  `min_strength_type` int(0) NULL DEFAULT NULL COMMENT '运动强度隶属小等级',
  `sport_time` int(0) NULL DEFAULT NULL COMMENT '运动时间等级',
  `min_time_type` int(0) NULL DEFAULT NULL COMMENT '运动时间隶属小等级',
  `sport_frequency` int(0) NULL DEFAULT NULL COMMENT '运动频率等级',
  `strength_show_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运动强度刻画方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_recipe
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_tizhi
-- ----------------------------
DROP TABLE IF EXISTS `t_user_tizhi`;
CREATE TABLE `t_user_tizhi`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '体质评测id',
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `heart_lung_ability` int(0) NULL DEFAULT NULL COMMENT '心肺能力等级（很差，较差，一般，良好，优秀）',
  `muscular_endurance` int(0) NULL DEFAULT NULL COMMENT '肌肉耐力等级(很差，较差，一般，良好，优秀）',
  `physique` int(0) NULL DEFAULT NULL COMMENT '体格等级（很差，较弱，正常，良好，健壮）',
  `within_fat` int(0) NULL DEFAULT NULL COMMENT '内脂等级（低，中，高）',
  `quality_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质量指数',
  `subhealt_num` int(0) NULL DEFAULT NULL COMMENT '亚健康评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_tizhi
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
