DROP TABLE USER;

CREATE TABLE user
(
	idx INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id INT UNSIGNED NOT NULL,
	PASSWORD VARCHAR(128) NOT NULL,
	NAME VARCHAR(10) NOT NULL,
	nickName VARCHAR(10) UNIQUE NOT NULL,
	createAt DATETIME NOT NULL DEFAULT now()
);

CREATE TABLE board
(
	id INT UNSIGNED NOT NULL PRIMARY key,
	NAME VARCHAR(40) NOT NULL 
);

CREATE TABLE article
(
	idx INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	boardId INT UNSIGNED NOT NULL UNIQUE,
	userId INT UNSIGNED NOT NULL,
	title VARCHAR(100) NOT NULL,
	content VARCHAR(10000) NOT NULL,
	writtenAt DATETIME NOT NULL DEFAULT NOW(),
	viewCount INT UNIQUE NOT NULL DEFAULT 0,
	likeCount INT UNIQUE NOT NULL DEFAULT 0,
	tag VARCHAR(20),
	CONSTRAINT FOREIGN KEY(boardId) REFERENCES board(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(userId) REFERENCES user(idx)
		ON UPDATE CASCADE
		ON DELETE CASCADE		
);

CREATE TABLE comment
(
	idx INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	userId INT UNSIGNED NOT NULL,
	content VARCHAR(150) NOT NULL,
	writtenAt datetime NOT NULL DEFAULT NOW(),
	CONSTRAINT FOREIGN KEY (userid) REFERENCES user(idx)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (idx) REFERENCES article(idx)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);






















