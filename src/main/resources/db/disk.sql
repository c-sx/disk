/*
 Navicat MySQL Data Transfer

 Source Server         : csx
 Source Server Type    : MySQL
 Source Server Version : 100316
 Source Host           : localhost:3306
 Source Schema         : disk

 Target Server Type    : MySQL
 Target Server Version : 100316
 File Encoding         : 65001

 Date: 25/03/2020 18:51:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid1` int(11) UNSIGNED NOT NULL,
  `uid2` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid1`(`uid1`) USING BTREE,
  INDEX `uid2`(`uid2`) USING BTREE,
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`uid1`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`uid2`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (1, 1, 2);
INSERT INTO `friends` VALUES (2, 2, 1);
INSERT INTO `friends` VALUES (3, 1, 3);
INSERT INTO `friends` VALUES (4, 3, 1);
INSERT INTO `friends` VALUES (5, 2, 3);
INSERT INTO `friends` VALUES (6, 3, 2);
INSERT INTO `friends` VALUES (9, 4, 5);
INSERT INTO `friends` VALUES (10, 5, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nameZh` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_user', '普通用户');
INSERT INTO `role` VALUES (2, 'ROLE_admin', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1,
  `locked` tinyint(1) NOT NULL DEFAULT 0,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_number` int(11) NULL DEFAULT NULL,
  `ip_address` int(32) UNSIGNED NULL DEFAULT NULL,
  `name_check` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` decimal(10, 2) NULL DEFAULT NULL,
  `user_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '$2a$10$B8ce5oVpal/eoBSvDgh3KOOTmWodeJcRZ49agfh5VZot36pX14YsK', 1, 0, 'aaa@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '李四', '$2a$10$9DXgmv4oeA5bQjb87oN7n.hgY/yfcCc/5mB3DPeEIDJLzvBXCWNZ.', 1, 0, 'bbb@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '王五', '$2a$10$LRs5oIbINhg1A.9rnP8ZJuEZVv/1cGieKhxosqE4lEJBQmJ5OKvvy', 1, 0, 'ccc@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '赵六', '$2a$10$L2F5zvh.JZFl/JAr8huwauG9KbmgsAI9RB3x2.wyOEikegouL2wUa', 1, 0, 'ddd@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '刘七', '$2a$10$etwqdOW2x.TZ2xaovAgEgum0p10hM6/lthYB1fV07YmJo13Oh9thW', 1, 0, 'eee@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, '陈八', '$2a$10$vlUf1TggccHWFWL3U3hOoe4iOteqQNKXAmrmkm6YamhO2ByPiyvdO', 1, 0, 'fff@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, 'd5c28', '$2a$10$dZ.OyY91tYAWpq1xSz6Yu.g1y3GfUqF.IxtNBznpL.sD67Twyp4Hm', 1, 0, 'd5c28@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, '29089', '$2a$10$Ey7zCGpDBLhPT7vih.j25.ESdX6Gyj1ZqV5rvmrujvr5IkCgDAykK', 1, 0, '29089@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, '23037', '$2a$10$QEHQAwbL4utB8hs5icDJ4OUneeKk4n6z4SrsaZ/Xs9kyo.TDz7kOu', 1, 0, '23037@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (10, '93bfb', '$2a$10$bwws3gQkM355faJ7mK6REu7sCrxumkX/SJ38t31Ju78I.8WPq2J5W', 1, 0, '93bfb@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (11, '7dbca', '$2a$10$V.xgAzF.oz5S73DsJ9skQ.oDZvX9xNs4eW08zvsQjKfew3lbDlEra', 1, 0, '7dbca@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (12, 'fd3dd', '$2a$10$FzVMTyhiGSh09sVBsA5te./PPeSi0n1GTRnLRmclMAmuMPTxmCCBe', 1, 0, 'fd3dd@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (13, '39ce9', '$2a$10$4qzdj6.UhZesdwQFuRG7oO4YgW35VCVBlegDqUuogkaEIoVW7ChS6', 1, 0, '39ce9@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (14, 'f2ab0', '$2a$10$iTNzvrjPtFNmhChE7QX2N.cP7gBBGsota6RyFAMOdgOUdgZ0.GtEK', 1, 0, 'f2ab0@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (15, '45994', '$2a$10$jCt9prI5.alAlMXYeLCvwO2S7F5FOaRg.Dgwy/nu8mGYrxxw645yW', 1, 0, '45994@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (16, 'e2b8a', '$2a$10$FVzGdPY7ywcQuEX9ppvss.TAAr5hUTyTglvN4wXl8pvZKExsibnvS', 1, 0, 'e2b8a@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (17, 'b644e', '$2a$10$jaiYVfSs.ZljJWp5LLSWq.zOk1lHVoy4kL1Pil4BGHT4Mess9MRIe', 1, 0, 'b644e@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (18, '9305c', '$2a$10$XM1sazgH6bGA5wq8DjTh6eVVWKbuq3pi5oJGm9xV9MH5PMp.8ChFS', 1, 0, '9305c@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (19, '9d84a', '$2a$10$W.Len74z4d/RSC7UAZ/APuWnKoGLQaP.T5YBnFg3h33UMrYTUR1p.', 1, 0, '9d84a@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (20, '79234', '$2a$10$aowTa4v.CGCbTcApcyxmIuSwyMemDV4L/wqXFBth.xAKO1/FbrCte', 1, 0, '79234@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (21, '2ca36', '$2a$10$E5T18oBgsL7ZOpepp1shN.bFewzVRz/FB2fIWP67Vc4GL90nusx5a', 1, 0, '2ca36@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (22, '612b5', '$2a$10$wHm20xXuidupl./lUL1NlenYSMc5opxFfKHgdexU3Jb5q5Q0J0Qj.', 1, 0, '612b5@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (23, 'df825', '$2a$10$Kp0zEqrgfqdS48zEWPh1juPQ1vsH7tHnuylLnzC6KiYmTGwTE80gK', 1, 0, 'df825@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (24, '94076', '$2a$10$EHF4/jB15NpD3PyGiV.lcOtzDTZ3N/bmx.RKZQLpfXPJYE1rGwyNu', 1, 0, '94076@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (25, '36fef', '$2a$10$yDKfwBCm1AV0cjDW/6GIqOfXDzr6X5xSpNV5aIgGSG/dsHdi9/PYK', 1, 0, '36fef@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (26, '7804c', '$2a$10$32MEpSEORyrw7zhx8WSCluDzpf7lir9AXPIT6brXgdxCbSmgmprau', 1, 0, '7804c@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (27, '5dd35', '$2a$10$a.qNYGVUyvapBqz9LEjM7OCxUM8ixx15BELHogojCtOAVkuZ2MI6K', 1, 0, '5dd35@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (28, '58c4a', '$2a$10$hGUObzZHwPO5BfxjUDJjAudBpQXZHeHPeHDmZzPTo8WFNPmQTcEYG', 1, 0, '58c4a@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (29, '51b18', '$2a$10$RuG9EC0mA99Tmnk4aEL9guL3SXZGNSc4PVTT.CkwJCMA3CEPdSqwO', 1, 0, '51b18@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (30, '02a82', '$2a$10$ivmj0Y2whyaN05KY2T3ZL.yfNIjuKk7viYnf1TVbp6/EAVhpfU6H2', 1, 0, '02a82@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (31, 'eced6', '$2a$10$dD/UgeCES4QfRBj3LMb8xOWGPMlsXpAzZObYxbqsxFkn1SouKlpQe', 1, 0, 'eced6@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (32, 'bb3ef', '$2a$10$IDwMH1kIl1QQ8tIdzdrCgu6xaeLRJUa7aQ1OchKQx1DQhWqAo0ytK', 1, 0, 'bb3ef@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (33, '63ed6', '$2a$10$fWivJ9qHxIhxcL23Kudmm.CnASsfyqFx09.ie.yDnlMxmhdlN2DAG', 1, 0, '63ed6@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (34, '936d0', '$2a$10$fWlRAhXO/pF1nD0h7q5DYepZcAcdvJEiC8ctpTnpiErZt0SAsl6Re', 1, 0, '936d0@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (35, '2872f', '$2a$10$r77LUZWW67iHdjx3V.nhquAsDJ8cPnJb5mxGyPPXPAaJ4mfToch92', 1, 0, '2872f@example.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (36, 'c0fab', '$2a$10$KRra3bS/rp7t61IK6DrhVuLPywmYNYl9O4NRuCEQtdpHFmXMHrYAK', 1, 0, 'c0fab@example.com', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user` int(11) UNSIGNED NULL DEFAULT NULL,
  `role` int(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user`) USING BTREE,
  INDEX `role`(`role`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 1);
INSERT INTO `user_role` VALUES (4, 3, 2);

SET FOREIGN_KEY_CHECKS = 1;
