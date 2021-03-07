
create database message_system
use message_system

CREATE TABLE user (
	id int(11) AUTO_INCREMENT,
	`username` VARCHAR(128) CHARACTER SET utf8 NOT NULL,
	`password` VARCHAR(128) CHARACTER SET utf8 NOT NULL,
	nickname VARCHAR(256) CHARACTER SET utf8mb4 NOT NULL,
	email VARCHAR(128) CHARACTER SET utf8 NOT NULL,
	memo VARCHAR(1024) CHARACTER SET utf8mb4,
	location VARCHAR(128) CHARACTER SET utf8,
	create_time DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE user_token (
	user_id int(11) NOT NULL,
	token varchar(1024) CHARACTER SET utf8 NOT NULL,
	expire_time DATETIME NOT NULL,
	PRIMARY KEY (`token`)
);