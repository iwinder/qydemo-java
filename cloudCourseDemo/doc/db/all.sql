SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `test`;
CREATE TABLE  test  (
   id  varchar(255) NOT NULL,
   name varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '测试');
SET FOREIGN_KEY_CHECKS=1;