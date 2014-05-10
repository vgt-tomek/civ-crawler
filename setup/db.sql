CREATE TABLE `users` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`login` VARCHAR(255) NOT NULL,
`password` CHAR(32) NOT NULL,
`salt` CHAR(32) NOT NULL,
`created_at` DATETIME NOT NULL,
UNIQUE(`login`)
) engine=innodb;

CREATE TABLE `user_tokens` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`user_id` INT UNSIGNED NOT NULL,
`token` CHAR(32) NOT NULL,
`ip` VARCHAR(100) NOT NULL,
`created_at` DATETIME NOT NULL,
CONSTRAINT `user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE
) engine=innodb;

CREATE TABLE `threads` (
`id` INT UNSIGNED NOT NULL PRIMARY KEY,
`title` VARCHAR(255) NOT NULL
)engine=innodb;

CREATE TABLE `authors` (
`id` INT UNSIGNED NOT NULL PRIMARY KEY,
`name` VARCHAR(255) NOT NULL
)engine=innodb;

CREATE TABLE `posts` (
`id` INT UNSIGNED NOT NULL PRIMARY KEY,
`thread_id` INT UNSIGNED NOT NULL,
`author_id` INT UNSIGNED NOT NULL,
`page` INT UNSIGNED NOT NULL,
`created_at` DATETIME NOT NULL,
CONSTRAINT `post_thread_id_fkey` FOREIGN KEY (`thread_id`) REFERENCES `threads` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `post_author_id_fkey` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
)engine=innodb;

CREATE TABLE `forum_read_markers` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`user_id` INT UNSIGNED NOT NULL,
`read_at` DATETIME NOT NULL,
`execution` ENUM("manual","automatic") NOT NULL,
CONSTRAINT `forum_read_marker_user_id_fkey` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
INDEX (`read_at`)
)engine=innodb;
