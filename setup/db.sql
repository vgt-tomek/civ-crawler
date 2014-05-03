CREATE TABLE `users` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
`login` VARCHAR(255) NOT NULL,
`password` CHAR(32) NOT NULL,
`salt` CHAR(32) NOT NULL,
`created_at` DATETIME NOT NULL,
UNIQUE(`login`)
) engine=innodb;


