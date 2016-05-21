# CHIMEROOM - Conference Database Schema

CREATE TABLE `rooms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	`capacity` TINYINT UNSIGNED NOT NULL,
	`floor` VARCHAR(32) NOT NULL,
	`status` enum('active', 'under-repair') COLLATE utf8_bin NOT NULL DEFAULT 'active',
	`accesslevel` INT DEFAULT NULL,
	`facilities` TEXT COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `index_rq1` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `employees` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`email` VARCHAR(32) NOT NULL,
	`priority` TINYINT UNSIGNED,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `bookings` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`room_id` INT NOT NULL,
	`start_date` DATETIME NOT NULL,
	`end_date` DATETIME NOT NULL,
	`emp_id` INT NOT NULL,
	`status` enum('queued', 'success', 'rejected') COLLATE utf8_bin NOT NULL DEFAULT 'queued',
	`purpose` TEXT COLLATE utf8_bin DEFAULT NULL,
	`num_participants` TINYINT UNSIGNED NOT NULL,
	`details` TEXT COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `index_bq1` (`start_date`, `end_date`, `room_id`),
	KEY `index_bq2` (`start_date`, `end_date`, `status`),
	FOREIGN KEY (`room_id`) REFERENCES rooms(`id`),
	FOREIGN KEY (`emp_id`) REFERENCES employees(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

