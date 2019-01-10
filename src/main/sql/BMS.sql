##alarm
CREATE TABLE `alarm` (
                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                       `borrow_id` int(11) NOT NULL DEFAULT '-1' COMMENT '借书记录id',
                       `alarm_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '报警时间',
                       `alarm_long` bigint(20) NOT NULL DEFAULT '0' COMMENT '报警时长，毫秒',
                       `alarm_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '报警类型（默认逾期）',
                       `alarm_reason` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '报警原因',
                       `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0废弃，1正常',
                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

###book
CREATE TABLE `book` (
                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                      `book_name` varchar(50) NOT NULL DEFAULT '' COMMENT '书名',
                      `book_auther` varchar(50) NOT NULL DEFAULT '' COMMENT '书作者',
                      `book_remark` varchar(300) NOT NULL DEFAULT '' COMMENT '书简介',
                      `book_page_size` int(11) NOT NULL DEFAULT '0' COMMENT '书页数',
                      `book_word_size` int(11) NOT NULL DEFAULT '0' COMMENT '书字数',
                      `book_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '书价格',
                      `book_publish` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '书出版社',
                      `book_publish_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '书出版时间',
                      `book_type` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '书籍种类',
                      `book_storage` int(11) NOT NULL DEFAULT '0' COMMENT '书库存',
                      `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态',
                      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='书籍表';

###borrow
CREATE TABLE `borrow` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '用户id',
                        `book_id` int(11) NOT NULL DEFAULT '-1' COMMENT '书id',
                        `borrow_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '借书时间',
                        `borrow_statue` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0已归还，1正常借出',
                        `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0废弃，1使用',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='借书记录';

###dishonest
CREATE TABLE `dishonest` (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '用户id',
                           `alarm_long` bigint(20) NOT NULL DEFAULT '0' COMMENT '逾期时间',
                           `alarm_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '报警时间',
                           `alarm_reason` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '报警原因',
                           `alarm_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '报警类型',
                           `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0废弃，1正常',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `borrow_statue` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0报警已经消失，1报警正常',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='不诚信用户';

###user
CREATE TABLE `user` (
                      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                      `user_id` varchar(10) NOT NULL DEFAULT '' COMMENT '用户编号',
                      `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
                      `user_pwd` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
                      `user_img` varchar(50) NOT NULL DEFAULT '' COMMENT '用户照片',
                      `user_real_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户姓名',
                      `user_birth` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户生日',
                      `user_email` varchar(20) NOT NULL DEFAULT '' COMMENT '用户邮箱',
                      `user_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '用户电话',
                      `role` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户角色（0普通用户，10管理员）',
                      `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态 0废弃，1使用',
                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';