CREATE DATABASE IF NOT EXISTS `blogdb`;
USE `blogdb`;

/** 博客文章表 **/
DROP TABLE IF EXISTS `blog_atc`;
CREATE TABLE `blog_atc` (
	`atc_id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL,
	`atc_title` varchar(30) NOT NULL,
	`atc_url` varchar(255) NOT NULL,
	`atc_type` varchar(10),
	`atc_time` varchar(14),
	`atc_view` int(10) DEFAULT 0,
	`atc_flag` char(1),
	PRIMARY KEY (`atc_id`,`user_id`)
);

/** 博客类型表 **/
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type` (
	`type_id` int(11) NOT NULL AUTO_INCREMENT,
	`type_name` varchar(30) NOT NULL,
	`type_desc` varchar(255) NOT NULL,
	PRIMARY KEY (`type_id`)
);

/** 用户表 **/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`account` varchar(20) NOT NULL,
	`name` varchar(10) NOT NULL,
	`password` varchar(255) NOT NULL,
	`role_id` char(1) DEFAULT '2',
	`sex` varchar(2),
	`email` varchar(40),
	`phone` varchar(20),
	`last_login_time` varchar(14),
	PRIMARY KEY (`user_id`)
);

/** 角色表 **/
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`role_id` int(11) NOT NULL AUTO_INCREMENT,
	`type_content` varchar(255),
	`type_limit` varchar(40),
	PRIMARY KEY (`role_id`)
);

/** 留言表 **/
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
	`msg_id` int(11) NOT NULL AUTO_INCREMENT,
	`atc_id` int(11) NOT NULL,
	`user_id` int(11) NOT NULL,
	`msg_time` varchar(14) NOT NULL,
	`msg_content` varchar(255),
	`msg_own` int(11) NOT NULL,
	`msg_type` char(1),
	`msg_return_flag` char(1),
	PRIMARY KEY (`msg_id`)
);


/** 用户好友表 **/
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
	`user_id` int(11) NOT NULL,
	`fri_id` int(11) NOT NULL,
	PRIMARY KEY (`user_id`,`fri_id`)
);


ALTER TABLE message
ADD constraint msg_user foreign key(user_id) references user(user_id);

ALTER TABLE message
ADD constraint msg_atc foreign key(atc_id) references blog_atc(atc_id);

ALTER TABLE user_friend
ADD constraint usr_fri foreign key(user_id) references user(user_id);

ALTER TABLE user_friend
ADD constraint usr_fri2 foreign key(fri_id) references user(user_id);