create database if not exists music;

use music;
drop table user;
drop table music;
drop table comments;
drop table likes;

CREATE TABLE `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `contact_no` varchar(12) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE (`contact_no`)
);

CREATE TABLE `music` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `location` text DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE KEY `user_id_name_type` (`user_id`,`name`,`type`)
);

CREATE TABLE `comments` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `data` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `music_id` bigint(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3)
);

CREATE TABLE `likes` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `music_id` bigint(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `updated_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  UNIQUE (user_id, music_id)
);


CREATE TABLE `response_code_translator` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `resp_code` varchar(200) NOT NULL,
  `resp_constant` varchar(200) NOT NULL
);

