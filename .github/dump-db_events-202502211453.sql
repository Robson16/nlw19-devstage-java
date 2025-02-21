-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: db_events
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_event`
--

DROP TABLE IF EXISTS `tbl_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_event` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `pretty_name` varchar(50) NOT NULL,
  `location` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `pretty_name_UNIQUE` (`pretty_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event`
--

LOCK TABLES `tbl_event` WRITE;
/*!40000 ALTER TABLE `tbl_event` DISABLE KEYS */;
INSERT INTO `tbl_event` VALUES (5,'Conferência de Tecnologia','tech_conf','São Paulo',250,'2025-04-10','2025-04-12','09:00:00','18:00:00'),(6,'Workshop de Programação','workshop_program','Rio de Janeiro',150,'2025-05-15','2025-05-16','10:00:00','17:00:00'),(7,'Festa de Aniversário','birthday_party','Belo Horizonte',50,'2025-06-01','2025-06-01','15:00:00','23:00:00');
/*!40000 ALTER TABLE `tbl_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_subscription`
--

DROP TABLE IF EXISTS `tbl_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_subscription` (
  `subscription_number` int unsigned NOT NULL AUTO_INCREMENT,
  `subscribed_user_id` int unsigned NOT NULL,
  `indication_user_id` int unsigned DEFAULT NULL,
  `event_id` int NOT NULL,
  PRIMARY KEY (`subscription_number`),
  KEY `fk_tbl_subscription_tbl_user_idx` (`subscribed_user_id`),
  KEY `fk_tbl_subscription_tbl_user1_idx` (`indication_user_id`),
  KEY `fk_tbl_subscription_tbl_event1_idx` (`event_id`),
  CONSTRAINT `fk_tbl_subscription_tbl_event1` FOREIGN KEY (`event_id`) REFERENCES `tbl_event` (`event_id`),
  CONSTRAINT `fk_tbl_subscription_tbl_user` FOREIGN KEY (`subscribed_user_id`) REFERENCES `tbl_user` (`user_id`),
  CONSTRAINT `fk_tbl_subscription_tbl_user1` FOREIGN KEY (`indication_user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_subscription`
--

LOCK TABLES `tbl_subscription` WRITE;
/*!40000 ALTER TABLE `tbl_subscription` DISABLE KEYS */;
INSERT INTO `tbl_subscription` VALUES (81,1,NULL,5),(82,2,1,6),(83,3,NULL,7),(84,4,2,5),(85,5,3,6),(86,6,NULL,7),(87,7,6,5),(88,8,7,6),(89,9,NULL,7),(90,10,9,5),(91,11,10,6),(92,12,NULL,7),(93,13,12,5),(94,14,NULL,6),(95,15,14,7),(96,16,NULL,5),(97,17,16,6),(98,18,17,7),(99,19,NULL,5),(100,20,NULL,6);
/*!40000 ALTER TABLE `tbl_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'João Silva','joao.silva@email.com'),(2,'Maria Oliveira','maria.oliveira@email.com'),(3,'Carlos Pereira','carlos.pereira@email.com'),(4,'Ana Costa','ana.costa@email.com'),(5,'Lucas Santos','lucas.santos@email.com'),(6,'Patrícia Gomes','patricia.gomes@email.com'),(7,'Eduardo Alves','eduardo.alves@email.com'),(8,'Fernanda Souza','fernanda.souza@email.com'),(9,'Ricardo Lima','ricardo.lima@email.com'),(10,'Juliana Martins','juliana.martins@email.com'),(11,'André Ribeiro','andre.ribeiro@email.com'),(12,'Bruna Silva','bruna.silva@email.com'),(13,'Rafael Costa','rafael.costa@email.com'),(14,'Carla Menezes','carla.menezes@email.com'),(15,'Felipe Rocha','felipe.rocha@email.com'),(16,'Beatriz Pinto','beatriz.pinto@email.com'),(17,'Luís Ferreira','luis.ferreira@email.com'),(18,'Marcela Pereira','marcela.pereira@email.com'),(19,'Ricardo Carvalho','ricardo.carvalho@email.com'),(20,'Simone Almeida','simone.almeida@email.com');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_events'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 14:53:23
