# ************************************************************
# Antares - SQL Client
# Version 0.7.19
# 
# https://antares-sql.app/
# https://github.com/antares-sql/antares
# 
# Host: 127.0.0.1 (MySQL Community Server - GPL 8.3.0)
# Database: paint-service
# Generation time: 2024-02-21T13:28:32+06:30
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table artist
# ------------------------------------------------------------

DROP TABLE IF EXISTS `artist`;

CREATE TABLE `artist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;

INSERT INTO `artist` (`id`, `created_at`, `name`, `updated_at`) VALUES
	(1, "2024-02-17 01:02:50.000000", "artist one", "2024-02-17 01:02:50.000000");

/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `created_at`, `name`) VALUES
	(1, "2024-02-21 12:56:37.958000", "category one");

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table paint
# ------------------------------------------------------------

DROP TABLE IF EXISTS `paint`;

CREATE TABLE `paint` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` varbinary(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `artist_id` bigint DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh21lk8ov0f8g1wrg9tvg4pu1f` (`artist_id`),
  KEY `FK61c7y6cosptl4cecicwx41dvg` (`category_id`),
  CONSTRAINT `FK61c7y6cosptl4cecicwx41dvg` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKh21lk8ov0f8g1wrg9tvg4pu1f` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `paint` WRITE;
/*!40000 ALTER TABLE `paint` DISABLE KEYS */;

INSERT INTO `paint` (`id`, `created_at`, `description`, `price`, `title`, `updated_at`, `artist_id`, `image_path`, `category_id`) VALUES
	(19, "2024-02-21 12:58:41.420000", "description two", "{\"type\":\"Buffer\",\"data\":[172,237,0,5,115,114,0,17,106,97,118,97,46,108,97,110,103,46,73,110,116,101,103,101,114,18,226,160,164,247,129,135,56,2,0,1,73,0,5,118,97,108,117,101,120,114,0,16,106,97,118,97,46,108,97,110,103,46,78,117,109,98,101,114,134,172,149,29,11,148,224,139,2,0,0,120,112,0,0,11,184]}", "paint two", "2024-02-21 12:58:41.420000", 1, "http://localhost:8080/api/upload/1708496921420_manchester-united-football-club-manchester-united-logo-wallpaper-preview.jpg", 1);

/*!40000 ALTER TABLE `paint` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `created_at`, `email`, `phone`, `updated_at`, `username`) VALUES
	(1, "2024-02-17 01:03:04.573000", "userone@gmail.com", "09987654321", "2024-02-17 01:03:04.573000", "user one");

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



# Dump of table voucher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher`;

CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `total_price` varbinary(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4h57plnf4easro9xialxph4yy` (`user_id`),
  CONSTRAINT `FK4h57plnf4easro9xialxph4yy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





# Dump of table voucher_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher_item`;

CREATE TABLE `voucher_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` varbinary(255) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `paint_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtc8ulr5vlr1x2bcpqwet4jp8u` (`order_id`),
  KEY `FK95y9t3go2sg6vrxmaf6no8fiy` (`paint_id`),
  CONSTRAINT `FK95y9t3go2sg6vrxmaf6no8fiy` FOREIGN KEY (`paint_id`) REFERENCES `paint` (`id`),
  CONSTRAINT `FKtc8ulr5vlr1x2bcpqwet4jp8u` FOREIGN KEY (`order_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





# Dump of views
# ------------------------------------------------------------

# Creating temporary tables to overcome VIEW dependency errors


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

# Dump completed on 2024-02-21T13:28:32+06:30
