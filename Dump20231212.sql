-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: cafe
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (17,'Uzumaki@gmail.com','Naruto','Uzumaki',5),(18,'Kirito@gmail.com','Kirigaya','Kirito',100),(19,'Stevenson@gmail.com','Steve','Stevenson',0),(20,'Johnson@gmail.com','John','Johnson',0),(21,'Jobs@gmail.com','Steve','Jobs',0),(22,'Vozniak@gmail.com','Steve','Vozniak',0),(23,'Menson@gmail.com','Mary','Menson',0),(24,'Dylan@gmail.com','Bob','Dylan',10),(25,'Mason@gmail.com','Mark','Mason',0),(26,'Mount@gmail.com','Mason','Mount',0),(27,'Firmino@gmail.com','Bobby','Firmino',0),(34,'Belllingham@gmail.com','Jude','Belllingham',50),(35,'Juice@gmail.com','Lemon','Juice',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `item_description` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `item_price` double DEFAULT NULL,
  `item_quantity` int DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Great black coffe 3','Espresso3',15,85),(2,'Stronger black coffe','Espresso22',15,95),(4,'Great black coffe 4','Espresso4',25,100),(6,'Cake1','Cake1',5,138),(12,'9','9',9,44),(13,'5','5',1,99),(14,'5','5',1.5,99);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_quantity`
--

DROP TABLE IF EXISTS `order_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_quantity` (
  `order_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `item_id` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`,`item_id`),
  CONSTRAINT `FKa9wmmcfi22m7hidys74gk28la` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_quantity`
--

LOCK TABLES `order_quantity` WRITE;
/*!40000 ALTER TABLE `order_quantity` DISABLE KEYS */;
INSERT INTO `order_quantity` VALUES (5,0,'1'),(5,1,'12'),(5,1,'13'),(5,1,'14'),(5,0,'2'),(5,0,'4'),(5,30,'6'),(6,0,'1'),(6,0,'11'),(6,0,'12'),(6,0,'13'),(6,0,'14'),(6,0,'2'),(6,0,'4'),(6,2,'6'),(6,0,'7'),(6,0,'8'),(74,5,'1'),(74,5,'2'),(74,5,'4'),(74,20,'6'),(74,1,'7'),(74,1,'8'),(74,1,'9'),(75,4,'1'),(75,1,'2'),(75,1,'4'),(75,22,'6'),(76,1,'1'),(76,1,'2'),(76,1,'4'),(76,1,'6'),(78,1,'1'),(78,1,'2'),(78,1,'4'),(78,1,'6'),(79,1,'1'),(79,1,'2'),(79,1,'4'),(79,1,'6'),(80,1,'1'),(80,1,'2'),(80,1,'4'),(80,4,'6'),(81,1,'1'),(81,1,'2'),(81,1,'4'),(81,1,'6'),(82,1,'1'),(82,1,'2'),(82,1,'4'),(82,1,'6'),(83,1,'1'),(83,1,'2'),(83,1,'4'),(83,2,'6'),(84,0,'1'),(84,0,'2'),(84,0,'4'),(84,5,'6'),(85,0,'1'),(85,0,'2'),(85,0,'4'),(85,0,'6'),(86,23,'1'),(86,10,'2'),(86,1,'4'),(86,1,'6'),(87,1,'1'),(87,1,'2'),(87,1,'4'),(87,1,'6'),(87,5,'7'),(88,0,'1'),(88,0,'2'),(88,0,'4'),(88,0,'6'),(88,0,'7'),(88,0,'8'),(88,5,'9'),(89,0,'1'),(89,0,'2'),(89,0,'4'),(89,0,'6'),(89,0,'7'),(89,0,'8'),(89,50,'9'),(90,1,'1'),(90,1,'2'),(90,1,'4'),(90,1,'6'),(90,1,'7'),(90,1,'8'),(90,1,'9'),(91,0,'1'),(91,0,'11'),(91,1,'12'),(91,0,'2'),(91,0,'4'),(91,0,'6'),(91,0,'7'),(91,0,'8'),(91,0,'9'),(92,1,'1'),(92,1,'11'),(92,1,'12'),(92,1,'2'),(92,1,'4'),(92,1,'6'),(92,1,'7'),(92,1,'8'),(93,1,'1'),(93,1,'11'),(93,1,'12'),(93,1,'13'),(93,1,'14'),(93,1,'2'),(93,1,'4'),(93,10,'6'),(93,1,'7'),(93,1,'8'),(94,1,'1'),(94,1,'11'),(94,1,'12'),(94,1,'13'),(94,1,'14'),(94,1,'2'),(94,1,'4'),(94,1,'6'),(94,1,'7'),(94,1,'8'),(95,1,'1'),(95,1,'11'),(95,1,'12'),(95,1,'13'),(95,1,'14'),(95,1,'2'),(95,1,'4'),(95,1,'6'),(95,1,'7'),(95,1,'8'),(97,1,'1'),(97,1,'11'),(97,1,'12'),(97,1,'13'),(97,1,'14'),(97,1,'2'),(97,1,'4'),(97,1,'6'),(97,1,'7'),(97,1,'8'),(98,1,'11'),(98,1,'12'),(98,1,'13'),(98,1,'14'),(98,1,'2'),(98,1,'6'),(98,1,'7'),(98,1,'8'),(100,1,'11'),(100,1,'12'),(100,1,'13'),(100,1,'14'),(100,1,'2'),(100,1,'6'),(100,1,'7'),(100,1,'8'),(101,1,'11'),(101,1,'12'),(101,1,'13'),(101,1,'14'),(101,1,'2'),(101,1,'6'),(101,1,'7'),(101,1,'8'),(102,1,'11'),(102,1,'12'),(102,1,'13'),(102,1,'14'),(102,1,'2'),(102,1,'6'),(102,1,'7'),(102,1,'8'),(103,1,'11'),(103,1,'12'),(103,1,'13'),(103,1,'14'),(103,1,'2'),(103,1,'6'),(103,1,'7'),(103,1,'8'),(110,20,'1'),(110,1,'11'),(110,1,'12'),(110,1,'13'),(110,1,'14'),(110,1,'2'),(110,1,'4'),(110,1,'6'),(110,1,'7'),(110,1,'8'),(111,1,'1'),(111,1,'11'),(111,1,'12'),(111,1,'13'),(111,5,'14'),(111,1,'2'),(111,1,'4'),(111,5,'6'),(111,1,'7'),(111,1,'8'),(112,20,'1'),(112,0,'11'),(112,0,'12'),(112,0,'13'),(112,0,'14'),(112,0,'2'),(112,0,'4'),(112,10,'6'),(112,0,'7'),(112,0,'8'),(113,30,'1'),(113,0,'11'),(113,0,'12'),(113,0,'13'),(113,0,'14'),(113,0,'2'),(113,0,'4'),(113,0,'6'),(113,0,'7'),(113,0,'8'),(114,0,'1'),(114,0,'11'),(114,0,'12'),(114,0,'13'),(114,0,'14'),(114,0,'2'),(114,0,'4'),(114,1,'6'),(114,0,'7'),(114,0,'8'),(115,5,'1'),(115,0,'11'),(115,0,'12'),(115,0,'13'),(115,0,'14'),(115,0,'2'),(115,0,'4'),(115,0,'6'),(115,0,'7'),(115,0,'8'),(117,0,'1'),(117,0,'11'),(117,0,'12'),(117,0,'13'),(117,0,'14'),(117,0,'2'),(117,0,'4'),(117,0,'6'),(117,0,'7'),(117,0,'8'),(119,0,'1'),(119,0,'11'),(119,0,'12'),(119,0,'13'),(119,0,'14'),(119,0,'2'),(119,0,'4'),(119,0,'6'),(119,0,'7'),(119,0,'8'),(129,0,'1'),(129,0,'11'),(129,0,'12'),(129,0,'13'),(129,0,'14'),(129,0,'2'),(129,0,'4'),(129,0,'6'),(129,0,'7'),(129,0,'8'),(130,0,'1'),(130,0,'11'),(130,0,'12'),(130,0,'13'),(130,0,'14'),(130,0,'2'),(130,0,'4'),(130,10,'6'),(130,0,'7'),(130,0,'8'),(131,0,'1'),(131,0,'11'),(131,0,'12'),(131,0,'13'),(131,0,'14'),(131,0,'2'),(131,0,'4'),(131,0,'6'),(131,0,'7'),(131,0,'8'),(132,5,'1'),(132,0,'12'),(132,0,'13'),(132,0,'14'),(132,0,'2'),(132,0,'4'),(132,0,'6'),(133,5,'1'),(133,0,'12'),(133,0,'13'),(133,0,'14'),(133,0,'2'),(133,5,'4'),(133,27,'6'),(134,5,'1'),(134,0,'12'),(134,0,'13'),(134,0,'14'),(134,0,'2'),(134,5,'4'),(134,0,'6'),(135,0,'1'),(135,0,'12'),(135,0,'13'),(135,0,'14'),(135,0,'2'),(135,0,'4'),(135,0,'6'),(136,5,'1'),(136,0,'12'),(136,0,'13'),(136,0,'14'),(136,5,'2'),(136,0,'4'),(136,0,'6'),(137,0,'1'),(137,20,'12'),(137,0,'13'),(137,0,'14'),(137,0,'2'),(137,0,'4'),(137,0,'6'),(139,0,'1'),(139,0,'12'),(139,0,'13'),(139,0,'14'),(139,0,'2'),(139,0,'4'),(139,5,'6');
/*!40000 ALTER TABLE `order_quantity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (5,'adminMain@gmail.com',161.5,'2023-12-11 13:02:56.978000','COMPLETED'),(6,'adminMain@gmail.com',198,'2023-12-10 21:13:03.751000','IN_PROGRESS'),(74,'adminMain@gmail.com',504,'2023-11-07 21:30:10.063000',NULL),(75,'milner@gmail.com',590,'2023-11-07 21:30:10.063000',NULL),(76,'milner@gmail.com',65,'2023-11-07 21:30:10.063000',NULL),(78,'milner@gmail.com',65,'2023-11-07 21:30:10.063000',NULL),(79,'milner@gmail.com',65,'2023-11-07 21:30:10.063000',NULL),(80,'adminMain@gmail.com',140,'2023-11-07 21:30:10.063000',NULL),(81,'milner@gmail.com',65,'2023-12-06 17:30:10.063000',NULL),(82,'admin@gmail.com',65,'2023-12-06 17:30:10.063000',NULL),(83,'admin@gmail.com',90,'2023-12-06 17:30:10.063000',NULL),(84,'admin1@gm',125,'2023-12-06 17:30:10.063000',NULL),(85,'adminMain@gmail.com',0,'2023-12-06 17:30:10.063000',NULL),(86,'adminMain@gmail.com',200,NULL,NULL),(87,'adminMain@gmail.com',75,NULL,NULL),(88,'adminMain@gmail.com',20,NULL,NULL),(89,'adminMain@gmail.com',250,NULL,NULL),(90,'adminMain@gmail.com',30,'2023-12-07 21:30:10.063000',NULL),(91,'adminMain@gmail.com',9,'2023-12-07 21:30:10.063000',NULL),(92,'test@gmail.com',39,'2023-12-07 21:30:10.063000',NULL),(93,'adminMain@gmail.com',266.5,'2023-12-07 21:30:10.063000',NULL),(94,'adminMain@gmail.com',43.5,'2023-12-07 19:02:03.655000',NULL),(95,'adminMain@gmail.com',43.5,'2023-12-07 19:13:49.397000','IN_PROGRESS'),(97,'adminMain@gmail.com',43.5,'2023-12-08 02:43:56.193000',NULL),(98,'adminMain@gmail.com',43.5,'2023-12-08 02:47:59.160000',NULL),(99,'adminMain@gmail.com',0,NULL,NULL),(100,'adminMain@gmail.com',43.5,'2023-12-09 21:08:21.752000',NULL),(101,'adminMain@gmail.com',43.5,'2023-12-09 21:10:12.878000',NULL),(102,'adminMain@gmail.com',43.5,'2023-12-09 21:11:13.643000',NULL),(103,'adminMain@gmail.com',43.5,'2023-12-09 21:37:01.316000',NULL),(104,'adminMain@gmail.com',0,NULL,NULL),(105,'adminMain@gmail.com',0,NULL,NULL),(106,'adminMain@gmail.com',0,NULL,NULL),(107,'adminMain@gmail.com',0,NULL,'COMPLETED'),(108,'adminMain@gmail.com',0,NULL,'COMPLETED'),(110,'adminMain@gmail.com',86.5,'2023-12-10 00:29:21.796000',NULL),(111,'adminMain@gmail.com',157.5,'2023-12-10 00:17:15.584000','IN_PROGRESS'),(112,'adminMain@gmail.com',590,'2023-12-10 00:36:15.301000',NULL),(113,'adminMain@gmail.com',0,'2023-12-10 00:44:35.761000',NULL),(114,'adminMain@gmail.com',99,'2023-12-10 00:45:21.286000',NULL),(115,'adminMain@gmail.com',75,'2023-12-10 00:49:25.855000',NULL),(117,'adminMain@gmail.com',0,'2023-12-10 21:13:09.812000','IN_PROGRESS'),(119,'adminMain@gmail.com',0,'2023-12-10 21:13:16.065000','IN_PROGRESS'),(129,'adminMain@gmail.com',0,'2023-12-10 22:09:21.832000',NULL),(130,'adminMain@gmail.com',990,'2023-12-10 22:13:34.093000','COMPLETED'),(131,'test@gmail.com',0,'2023-12-10 22:44:53.226000','IN_PROGRESS'),(132,'adminMain@gmail.com',0,'2023-12-10 22:59:08.688000',NULL),(133,'adminMain@gmail.com',2673,'2023-12-10 23:18:20.677000','IN_PROGRESS'),(134,'adminMain@gmail.com',75,'2023-12-10 23:19:06.226000',NULL),(135,'adminMain@gmail.com',0,'2023-12-10 23:25:45.982000',NULL),(136,'adminMain@gmail.com',150,'2023-12-10 23:32:23.771000',NULL),(137,'adminMain@gmail.com',180,'2023-12-11 13:02:21.710000','IN_PROGRESS'),(139,'adminMain@gmail.com',25,'2023-12-11 13:07:36.680000',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_ADMIN'),(7,'ROLE_ADMIN'),(8,'ROLE_USER'),(9,'ROLE_USER'),(10,'ROLE_USER'),(11,'ROLE_USER'),(12,'ROLE_USER'),(13,'ROLE_USER'),(14,'ROLE_USER'),(15,'ROLE_USER'),(16,'ROLE_ADMIN'),(17,'ROLE_USER'),(18,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kadabra@gmail.com','abra','kadabra','4444'),(2,'milner@gmail.com','James','Milner','$2a$10$d7..HrAXmW.ap1S2UJAgWuZXfnppPhFF98JYNSNRvsXxAet5tY7YS'),(3,'holubchyk@gmail.com','Dmytro','H','$2a$10$PyZbS0t9zFtMzyBAIhjNXe2Fv4NgnTdFamozyZUEvd4bEgjKQTQ.2'),(4,'admin@gmail.com','Dmytro','Admin','$2a$10$0iIX6AUyk1QCaT/9WI0.YeuBMSf5L40HJ3z1xo0RAYvnOY13lwTEa'),(5,'admin1@gm','admin1','ad','$2a$10$/yzxwTFc..bKjcEdTMMWouCirzxqeGMGqWYkjzGhS1N.7F9NUPFei'),(7,'adminMain@gmail.com','MainAdmin','MainAdmin','$2a$10$sl3Wx25L4.H4yarM2uRsHeIsdpajEUYAgfLpk/H4BHN8jsa8.TWVK'),(8,'admin%%@gmail.com','123','123','$2a$10$3c35yzj0Ul864SAo3kdz2Oxz5H4d5VGfkMw8BO.ddABJl3vDL6N6a'),(9,'1@gmail.com','1','1','$2a$10$/fs3gJd9.XdmGGfOBfwMA.x2S91PV0S7sTp18fyc.zL/Xd/2LK36K'),(10,'test@gmail.com','test','test','$2a$10$L/PXl0gG2kEWldwDXrt4W.gzYEehwDv/eAwEL.j85aXlgwAfVJpgi'),(11,'aaaa@gmail.com','aaaa','aaaa','$2a$10$UNHV8Jw8HpsbF8fVDY8e4uYnvMoYMYm2odrhqLtWwJeon4bZkNfTC'),(12,'t@gmail.com','t','t','$2a$10$j1f1abaUUvIbR4oKES0vguPJf7o9Q1Qp/ZNbiZfEKH9mrpfyLFCf2'),(13,'as@gmail.com','as','as','$2a$10$FVlSZz6Q.VF1.r3APGG85.QZgzCTSqUytfoIw6rOJOimgFCO2qcze'),(14,'bb2131@gmail.com','bb','bb','$2a$10$G0YzR9hwV2uJe/AwNUIwV.qHR5dRjhXw4zUXx3a8GIvJenTVHexdW'),(15,'rr@gmail.com','rr','rr','$2a$10$3cEtXFg/aiTMAKdoEI3psu2UvZ.xLnzT4RoQCvxubCF2KZXiLxg3W'),(16,'af@gmail.com','af','af','$2a$10$hpUiFnUZ98gn8GxSzTVfnusuP3H68JGwsmDDznY58ZiqsQL3fep5a'),(17,'new@gmail.com','new','new','$2a$10$S/TswHNKRodhguqHMnRwmuSqbS6e5ZrPNvqgOK.JIlyaSaqRp3GV2'),(18,'q@gmail.com','q','q','$2a$10$8xaLhdkFEj7EiwFmICViNuyzYtnCz8qA0CWaDgLukyVwh3yvZHp4e'),(19,'quser@gmail.com','quser','quser','$2a$10$RvvFoyC9XiLgwe1nbd6Q9OREEexQxqIAj4CbV751BKOzsVw9MRH5u'),(20,'asd@gmail.com','asd','asd','$2a$10$VMKaRyq6XquzcQ7HcYfpk.qCjONMUu2QVPNkppkPSARGUKskLshS.');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(7,6),(8,7),(9,8),(10,9),(11,10),(12,11),(13,12),(15,13),(16,14),(17,15),(18,16),(19,17),(20,18);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12  1:49:56
