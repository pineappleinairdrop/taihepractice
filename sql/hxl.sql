/*
 Navicat MySQL Data Transfer

 Source Server         : 172.30.100.21
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 172.30.100.21:3308
 Source Schema         : hxl

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 22/02/2019 17:46:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for redundancy_take
-- ----------------------------
DROP TABLE IF EXISTS `redundancy_take`;
CREATE TABLE `redundancy_take`  (
  `id` int(11) NOT NULL,
  `sid` int(11) NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL,
  `stu_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cou_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` smallint(6) NULL DEFAULT NULL,
  `tid` int(11) NULL DEFAULT NULL,
  `tea_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_date` date NULL DEFAULT NULL,
  `login_id` bigint(16) NOT NULL,
  `password` varchar(128) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `tuition` double(10, 2) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_uniq_idx`(`login_id`) USING BTREE COMMENT '学号索引，用于登录查询'
) ENGINE = InnoDB AUTO_INCREMENT = 1000001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for take
-- ----------------------------
DROP TABLE IF EXISTS `take`;
CREATE TABLE `take`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `score` smallint(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_fk`(`sid`) USING BTREE,
  INDEX `teach_course_fk`(`cid`) USING BTREE,
  CONSTRAINT `stu_fk` FOREIGN KEY (`sid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teach_course_fk` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10000001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teach
-- ----------------------------
DROP TABLE IF EXISTS `teach`;
CREATE TABLE `teach`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_fk`(`tid`) USING BTREE,
  INDEX `teacher_course_fk`(`cid`) USING BTREE,
  CONSTRAINT `teacher_course_fk` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_fk` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1000467 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for add_course
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_course`;
delimiter ;;
CREATE PROCEDURE `add_course`(IN n int)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE (i <= n) DO
      INSERT into course(name) VALUEs (CONCAT('课程',substring(MD5(RAND()), floor(RAND() * 26) + 1, 5)));
      set i = i + 1;
    END WHILE;
  END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for add_student
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_student`;
delimiter ;;
CREATE PROCEDURE `add_student`(IN n int)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE (i <= n) DO
      IF (i % 2 = 0)
      THEN
        INSERT into student (name, sex) VALUEs (CONCAT('学生',substring(MD5(RAND()), floor(RAND() * 26) + 1, 6)), '男');
      else
        INSERT into student (name, sex) VALUEs (CONCAT('学生',substring(MD5(RAND()), floor(RAND() * 26) + 1, 6)), '女');
      END IF;
      set i = i + 1;
    END WHILE;
  END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for add_teacher
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_teacher`;
delimiter ;;
CREATE PROCEDURE `add_teacher`(IN n int)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE (i <= n) DO
        INSERT into teacher (name) VALUEs (CONCAT('教师',substring(MD5(RAND()), floor(RAND() * 26) + 1, 5)));
      set i = i + 1;
    END WHILE;
  END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
