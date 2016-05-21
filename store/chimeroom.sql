# CHIMEROOM - Conference Database Schema

CREATE TABLE `rooms` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` CHAR NOT NULL,
	`capacity` TINYINT UNSIGNED NOT NULL,
	`location` CHAR NOT NULL,
	`status` enum('active', 'under-repair') COLLATE utf8_bin NOT NULL DEFAULT 'active',
	`level` TINYINT UNSIGNED DEFAULT NULL,
	`facilities` text COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `index_rq1` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `employees` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(64) NOT NULL,
	`email` varchar(32) NOT NULL,
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
	`purpose` text COLLATE utf8_bin DEFAULT NULL,
	`num_participants` TINYINT UNSIGNED NOT NULL,
	`details` text COLLATE utf8_bin DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `index_bq1` (`start_date`, `end_date`, `room_id`),
	KEY `index_bq2` (`start_date`, `end_date`, `status`),
	FOREIGN KEY (`room_id`) REFERENCES rooms(`id`),
	FOREIGN KEY (`emp_id`) REFERENCES employees(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

