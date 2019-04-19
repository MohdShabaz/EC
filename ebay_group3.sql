-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: ebayv1
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.10.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_table`
--



DROP DATABASE IF EXISTS `ebayv1`;
CREATE DATABASE `ebayv1`;
use `ebayv1`;


DROP TABLE IF EXISTS `admin_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_table` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_table`
--

LOCK TABLES `admin_table` WRITE;
/*!40000 ALTER TABLE `admin_table` DISABLE KEYS */;
INSERT INTO `admin_table` VALUES (1,'Shane','9290966544','shaneroy@gmail.com','shane1234'),(2,'Suresh','9247891907','sureahmalla@gmail.com','suri@malla'),(3,'Umesh','9247937995','umeshkumar@gmail.com','umesh000'),(4,'Sahid','9848689072','sahidafridi@yahoomail.com','sahiSahi'),(5,'Benjamin','7981740761','benjaminhaas@outlook.com','benj9876'),(6,'James','8919835818','jameschristian@rocketmail.com','james007'),(7,'Chris','9642100251','chrishenry@google.com','chris11');
/*!40000 ALTER TABLE `admin_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_account_details`
--

DROP TABLE IF EXISTS `buyer_account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_account_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `account_number` int(12) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `buyer_id` (`buyer_id`),
  CONSTRAINT `buyer_account_details_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `buyer_table` (`buyer_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_account_details`
--

LOCK TABLES `buyer_account_details` WRITE;
/*!40000 ALTER TABLE `buyer_account_details` DISABLE KEYS */;
INSERT INTO `buyer_account_details` VALUES (1,1,123131316,1000000),(2,2,123131317,2000000),(3,3,123131318,3000000),(4,4,123131319,4000000),(5,5,123131320,5000000),(6,6,123131321,100000),(7,7,123131322,200000),(8,8,123131323,300000),(9,9,123131324,400000),(10,10,123131325,500000);
/*!40000 ALTER TABLE `buyer_account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_table`
--

DROP TABLE IF EXISTS `buyer_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_table` (
  `buyer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `dob` date NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `address_1` varchar(140) DEFAULT NULL,
  `address_2` varchar(140) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`buyer_id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_table`
--

LOCK TABLES `buyer_table` WRITE;
/*!40000 ALTER TABLE `buyer_table` DISABLE KEYS */;
INSERT INTO `buyer_table` VALUES (1,'John','1998-01-09','9367535629','25-K Frank Anne Street, Michigan',NULL,'johnfkennedy@gmail.com','johnwick3'),(2,'Smith','1997-07-17','9127093606','1-A, Royal Legion society, Detroit',NULL,'willissmith@rocketmail.com','tokyopunchout'),(3,'Wong','1995-11-06','9261879202','90th Wall Street Hub, Manhattan',NULL,'liuwong@outlook.com','chaugoldman'),(4,'Zelaya','1989-01-09','9375922897','12FA, Silicon Valley, California',NULL,'zellala@gmail.com','zelly1234'),(5,'Ramesh','1990-01-09','9361293194','4th House of Commons, Bangalore',NULL,'rameshbhau@yahoo.com','bhaibh67a'),(6,'Suresh','1993-02-19','9784503610','Flying Palace Apartments, Hyderabad',NULL,'sureshiya@gmail.com','thegreatestking67'),(7,'Pankaj','1987-01-15','9632061554','12th Vinewood road, Lad Venturas',NULL,'pank.1278@yahoomail.com','pnkjsthr98'),(8,'Alicia','1991-03-17','9765693745','Maddogg club house, Vinewood',NULL,'patricia.ali@hotmail.com','carljohnsontemp'),(9,'Trevor','1981-05-07','9254744307','Saharan oasis apartments, Las Vegas',NULL,'itsmetrevor@google.com','patriciamissya'),(10,'Franklin','1998-04-08','9868843149','Grove Street, Home, San Fierro',NULL,'franklin.nig@rocketmail.com','drivin12lambo');
/*!40000 ALTER TABLE `buyer_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (5,'Collectibles'),(1,'Electronics'),(2,'Fashion'),(3,'Health & Beauty'),(7,'Home & Garden'),(4,'Motors'),(9,'Others'),(6,'Sports'),(8,'Under Rs100/-');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ebay_account_details`
--

DROP TABLE IF EXISTS `ebay_account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ebay_account_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(40) NOT NULL,
  `account_number` int(11) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ebay_account_details`
--

LOCK TABLES `ebay_account_details` WRITE;
/*!40000 ALTER TABLE `ebay_account_details` DISABLE KEYS */;
INSERT INTO `ebay_account_details` VALUES (1,'ebay_account',346712124,500000);
/*!40000 ALTER TABLE `ebay_account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_seller`
--

DROP TABLE IF EXISTS `item_seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `address` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`,`seller_id`,`address`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_seller`
--

LOCK TABLES `item_seller` WRITE;
/*!40000 ALTER TABLE `item_seller` DISABLE KEYS */;
INSERT INTO `item_seller` VALUES (1,1,1,400,1),(2,2,1,200,1),(3,3,2,50,1),(4,4,2,70,1),(5,5,3,95,1),(6,6,3,89,1),(7,7,3,103,1),(8,8,4,1065,1),(9,9,4,1000,1),(10,10,4,2056,1),(11,11,4,2056,1),(12,12,3,2056,1),(13,13,3,2056,1),(14,14,3,2056,1),(15,15,3,2056,1),(16,16,1,2056,1),(17,17,1,2056,1),(18,18,4,2056,1),(19,19,4,2056,1),(20,20,4,2056,1),(21,21,4,2056,1),(22,22,3,100,1),(23,23,3,96,1),(24,24,3,120,1),(25,25,3,230,1),(26,26,3,125,1),(27,27,3,225,1),(28,28,3,310,1),(29,29,3,120,1),(30,30,3,129,1),(31,31,3,215,1),(32,32,3,130,1),(33,33,3,125,1),(34,34,3,120,1),(35,35,3,125,1),(36,36,3,125,1),(37,37,3,125,1),(38,38,3,640,1),(39,39,3,126,1),(40,40,3,125,1),(41,41,3,240,1),(42,42,3,129,1),(43,43,4,120,1),(44,44,4,125,1),(45,45,4,128,1);
/*!40000 ALTER TABLE `item_seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_table`
--

DROP TABLE IF EXISTS `item_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_table` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `price` float(10,2) NOT NULL,
  `discount` float(3,2) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `pic_location` varchar(60) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `sub_category` int(11) DEFAULT NULL,
  `barcode` varchar(10) NOT NULL,
  `total_stars` int(11) DEFAULT '0',
  `total_users_rated` int(11) DEFAULT '0',
  `dummy_1` varchar(40) DEFAULT NULL,
  `dummy_2` varchar(40) DEFAULT NULL,
  `dummy_3` varchar(40) DEFAULT NULL,
  `dummy_4` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `barcode` (`barcode`),
  KEY `category` (`category`),
  KEY `sub_category` (`sub_category`),
  CONSTRAINT `item_table_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE CASCADE,
  CONSTRAINT `item_table_ibfk_2` FOREIGN KEY (`sub_category`) REFERENCES `sub_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_table`
--

LOCK TABLES `item_table` WRITE;
/*!40000 ALTER TABLE `item_table` DISABLE KEYS */;
INSERT INTO `item_table` VALUES (1,'Flagship killer smartphone of 2019',34999.99,0.10,'OnePlus 6T','images/1804289383.png',1,1,'1804289383',0,0,NULL,NULL,NULL,NULL),(2,'Simplicity. Glorified. Go Apple',74999.00,0.07,'iPhone X','images/1714636915.png',1,1,'1714636915',0,0,NULL,NULL,NULL,NULL),(3,'FCAA certified whey protein isolate. No amino spiking!',5500.00,0.50,'BPI Sports ISO HD','images/1681692777.png',3,16,'1681692777',0,0,NULL,NULL,NULL,NULL),(4,'Industry-standard for IFBB Pros. MuscleTech supreme',4400.00,0.60,'MuscleTech NitroTech Whey','images/1957747793.png',3,16,'1957747793',0,0,NULL,NULL,NULL,NULL),(5,'Gaming evolved! Dell G7 with state-of-the art specs',134999.98,0.35,'Dell G7','images/1649760492.png',1,4,'1649760492',0,0,NULL,NULL,NULL,NULL),(6,'Thinnest and fastest ultrabook of 2019',93999.99,0.25,'ASUS Vivobook','images/2596516649.png',1,4,'2596516649',0,0,NULL,NULL,NULL,NULL),(7,'Business standard for 30 years. ThinkPad',112500.00,0.20,'ThinkPad T480S','images/3719885386.png',1,4,'3719885386',0,0,NULL,NULL,NULL,NULL),(8,'Fast, safe and reliable cooking of eggs!',1200.00,0.15,'Vixen Egg Boiler','images/1189641421.png',7,51,'1189641421',0,0,NULL,NULL,NULL,NULL),(9,'5 litre capacity electric kettle',899.99,0.25,'Pigeon Kettle','images/1846930886.png',7,51,'1846930886',0,0,NULL,NULL,NULL,NULL),(10,'Cereal bowl (Pack of 4)',250.00,0.10,'MCA Cereal bowl','images/2424238335.png',7,53,'2424238335',0,0,NULL,NULL,NULL,NULL),(11,'Clothes for Small Pet Puppys',895.17,0.25,'puppy CLothes','images/2836827938.png',7,52,'2836827938',0,0,NULL,NULL,NULL,NULL),(12,'HTC Bluetooth Smart Watch with Camera and SIM Slot ',650.00,0.05,'Smart Watch','images/2389548393.png',1,2,'2389548393',0,0,NULL,NULL,NULL,NULL),(13,'Red Dead Redemption 2 - PlayStation 4 New',4095.79,0.36,'Red Dead Redemption 2','images/2884383939.png',1,3,'2884383939',0,0,NULL,NULL,NULL,NULL),(14,'Sony Alpha a7 III Mirrorless Digital Camera Body',141175.91,0.17,'Sony Alpha Camera','images/2759273958.png',1,6,'2759273958',0,0,NULL,NULL,NULL,NULL),(15,'DJI Mavic Air Quadcopter Drone with Camera',6500.00,0.12,'Quadcaptor Drone','images/1837493738.png',1,5,'1837493738',0,0,NULL,NULL,NULL,NULL),(16,'Samsung Electronics UN43MU6300 43-Inch 4K Ultra HD Smart LED TV with 120 CMR',41355.80,0.43,'Samsung LED TV','images/1647382046.png',1,7,'1647382046',0,0,NULL,NULL,NULL,NULL),(17,'Family Matching Outfits Mother and Daughter Clothes dress Baby Girl stripe Shirt',785.30,0.17,'Matching Clothes','images/1567839764.png',2,8,'1567839764',0,0,NULL,NULL,NULL,NULL),(18,'DIESEL Mens T-Shirt MIREY Mohawk BROWN GOLD Casual Designer',6754.80,0.80,'DIESEL T-Shirt','images/3784910647.png',2,9,'3784910647',0,0,NULL,NULL,NULL,NULL),(19,'AIR JORDAN 13 RETRO ATMOSPHERE GREY SHOES FOR MEN',5514.80,0.05,'JORDAN SHOES','images/3789234568.png',2,10,'3789234568',0,0,NULL,NULL,NULL,NULL),(20,'Joyalukas 22K Gold Necklace',240161.00,0.20,'Gold NeckLace','images/2489765135.png',2,11,'2489765135',0,0,NULL,NULL,NULL,NULL),(21,'adidas Three Stripe Life T-Short for Kids',1380.00,0.50,'Adidas T-Shirt','images/2759486125.png',2,13,'2759486125',0,0,NULL,NULL,NULL,NULL),(22,'Realme 3 (Dynamic Black, 64 GB)  (4 GB RAM)',12999.00,0.15,'Realme-3','images/2341562784.png',1,1,'2341562784',0,0,NULL,NULL,NULL,NULL),(23,'Redmi Note 7 (Onyx Black, 64 GB)  (4 GB RAM) with (12 MP + 2 MP) dual-rear camera and the 13 MP front camera.',14999.00,0.20,'Redmi Note 7','images/2563784519.png',1,1,'2563784519',0,0,NULL,NULL,NULL,NULL),(24,'Redmi Note 7 Pro (Neptune Blue, 128 GB)  (6 GB RAM) with a (48 MP + 5 MP) dual-rear camera and a 13 MP front camera which let you click truly beautiful pictures',17999.00,0.05,'Redmi Note 7 Pro','images/2781540935.png',1,1,'2781540935',0,0,NULL,NULL,NULL,NULL),(25,'Vivo V15 Pro (Topaz Blue, 128 GB)  (6 GB RAM) Equipped with a triple rear camera (48 Million Quad Pixel Sensor (12 Million Effective Pixel) + 8 MP + 5 MP) and a 32 MP Pop-up Selfie camera.',32990.00,0.12,'Vivo V15 Pro','images/2891654703.png',1,1,'2891654703',0,0,NULL,NULL,NULL,NULL),(26,'Samsung Galaxy A30 (Red, 64 GB)  (4 GB RAM) with Dual Camera System, comprising the 16 MP Low Light Camera and 5 MP Ultra-wide Camera, lets you take rich and beautiful pictures.',18000.00,0.05,'Samsung Galaxy A30','images/2761453692.png',1,1,'2761453692',0,0,NULL,NULL,NULL,NULL),(27,'Samsung Galaxy S10e (Prism Black, 128 GB)  (6 GB RAM) with an Intelligent Camera that automatically optimizes its settings to give you picture-perfect photos.',59000.00,0.05,'Saamsung Galaxy S10e','images/3178264530.png',1,1,'3178264530',0,0,NULL,NULL,NULL,NULL),(28,'Redmi Note 6 Pro (Black, 64 GB)  (4 GB RAM) , A 20 MP + 2 MP dual front camera, a long-lasting battery and 15.9 cm (6.26-inch) screen.',15999.00,0.25,'Mi Redmi Note 6 Pro','images/3176243905.png',1,1,'3176243905',0,0,NULL,NULL,NULL,NULL),(29,'Asus ZenFone Max M2 (Black, 32 GB)  (3 GB RAM) ',12999.00,0.34,'Asus Zenfone Max M2','images/3925615746.png',1,1,'3925615746',0,0,NULL,NULL,NULL,NULL),(30,'Realme 2 Pro (Black Sea, 128 GB)  (8 GB RAM) withy 16 Mp front camera',18990.00,0.15,'Redmi 2 Pro','images/4251637286.png',1,1,'4251637286',0,0,NULL,NULL,NULL,NULL),(31,'Nokia 5.1 Plus (Black, 32 GB)  (3 GB RAM). The smartphone\'s 13 MP rear camera and 8 MP front camera serve as the backbone of its advanced photographic capabilities.',13199.00,0.24,'Nokia 5.1 plus','images/2103126798.png',1,1,'2103126798',0,0,NULL,NULL,NULL,NULL),(32,'Google Pixel 3 (Clearly White, 128 GB)  (4 GB RAM) . The Pixel 3 boasts a 2915 mAh battery that not only charges fast, but also lasts for almost all day.',80000.00,0.00,'Google Pixel 3','images/3145267389.png',1,1,'3145267389',0,0,NULL,NULL,NULL,NULL),(33,'POCO F1 by Xiaomi (Steel Blue, 128 GB)  (6 GB RAM). On the back, it features a 12MP + 5MP Dual Pixel AI dual camera setup. It sports a 20 MP high-res front camera and IR Face unlock.',24999.00,0.16,'Xiaomi POCO F1','images/3145279035.png',1,1,'3145279035',0,0,NULL,NULL,NULL,NULL),(34,'Acer Predator Helios 300 Core i5 8th Gen - (8 GB/1 TB HDD/128 GB SSD/Windows 10 Home/4 GB Graphics) PH315-51 / PH315-51-51V7 Gaming Laptop  (15.6 inch, Shale Black, 2.5 kg)',104999.00,0.40,'Acer Predator Helios 300','images/4123267504.png',1,4,'4123267504',0,0,NULL,NULL,NULL,NULL),(35,'Lenovo Ideapad 330 Core i5 8th Gen - (8 GB/1 TB HDD/DOS/2 GB Graphics) 330-15IKB Laptop  (15.6 inch, Onyx Black, 2.2 kg)',49146.00,0.18,'Lenovo Idepad','images/4162783945.png',1,4,'4162783945',0,0,NULL,NULL,NULL,NULL),(36,'HP 15q Core i5 8th Gen - (8 GB/1 TB HDD/Windows 10 Home) 15q-ds0010TU Laptop  (15.6 inch, Sparkling Black, 2.04 kg)',50190.00,0.10,'HP 15q Laptop','images/4156278397.png',1,4,'4156278397',0,0,NULL,NULL,NULL,NULL),(37,'Lenovo Ideapad 130 Core i5 8th Gen - (4 GB/1 TB HDD/Windows 10 Home) 130-14IKB Laptop  (14 inch, Black, 2 kg)',41330.00,0.15,'Lenovo Ideapad 130','images/4127890645.png',1,4,'4127890645',0,0,NULL,NULL,NULL,NULL),(38,'Dell Vostro 14 3000 Core i5 8th Gen - (8 GB/1 TB HDD/Ubuntu) 3478 Laptop  (14 inch, Black, 1.76 kg)',44645.00,0.12,'Dell Vostro 14','images/4126697428.png',1,4,'4126697428',0,0,NULL,NULL,NULL,NULL),(39,'Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A A1466  (13.3 inch, Silver, 1.35 kg)',84900.00,0.16,'Apple MacBook Air','images/2671874563.png',1,4,'2671874563',0,0,NULL,NULL,NULL,NULL),(40,'Acer Aspire 5 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home/2 GB Graphics) A515-51G Laptop  (15.6 inch, Steel Grey, 2 kg)',59999.00,0.23,'ACer Aspire','images/4123789645.png',1,4,'4123789645',0,0,NULL,NULL,NULL,NULL),(41,'Dell Inspiron 13 5000 Core i5 8th Gen - (8 GB/256 GB SSD/Windows 10 Home) 5370 Thin and Light Laptop  (13 inch, Platinum Silver, 1.4 kg, With MS Office)',67728.00,0.01,'Dell Inspiron','images/4123678450.png',1,4,'4123678450',0,0,NULL,NULL,NULL,NULL),(42,'Asus TUF Core i5 8th Gen - (8 GB/1 TB HDD/128 GB SSD/Windows 10 Home/4 GB Graphics) FX504GE-E4366T Gaming Laptop  (15.6 inch, Black Metal, 2.3 kg)',82990.00,0.19,'Asus TUF','images/5128956230.png',1,4,'5128956230',0,0,NULL,NULL,NULL,NULL),(43,'Solid Men Round or Crew White T-Shirt',1499.00,0.73,'WHite T-Shirt','images/412095478.png',2,9,'412095478',0,0,NULL,NULL,NULL,NULL),(44,'Slim Men Black Jeans',2199.00,0.58,'Black Jeens','images/2489415602.png',2,9,'2489415602',0,0,NULL,NULL,NULL,NULL),(45,'Solid Men Polo Neck Black, White T-Shirt',1299.00,0.56,'Neck Black White T-Shirt','images/4127869034.png',2,9,'4127869034',0,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `item_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label_table`
--

DROP TABLE IF EXISTS `label_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `label` varchar(40) NOT NULL,
  `attr` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`,`label`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label_table`
--

LOCK TABLES `label_table` WRITE;
/*!40000 ALTER TABLE `label_table` DISABLE KEYS */;
INSERT INTO `label_table` VALUES (1,1,'color','red'),(2,2,'color','blue'),(3,3,'color','green'),(4,4,'color','red'),(5,5,'color','red'),(6,6,'color','blue'),(7,7,'color','orange'),(8,8,'color','yellow'),(9,9,'color','violet'),(10,10,'color','purple'),(11,11,'color','Indigo'),(12,12,'color','Gold'),(13,13,'color','RED'),(14,14,'color','dark-orange'),(15,15,'color','lightyellow'),(16,16,'color','olive'),(17,17,'color','cyan'),(18,18,'color','aqua'),(19,19,'color','Grey'),(20,20,'color','lightblue'),(21,21,'color','magenta'),(22,1,'RAM','2GB'),(23,2,'RAM','2GB'),(24,3,'Weight','1KG'),(25,4,'Weight','2KG'),(26,5,'RAM','4GB'),(27,5,'Hard-Disk','1TB'),(28,6,'RAM','8GB'),(29,6,'Hard-Disk','1,5TB'),(30,7,'size(LengthxBreadth)','35cmx24cm'),(31,8,'Weight','2KG'),(32,9,'Capacity','5 Litre'),(33,10,'Offer','Pack of 4'),(34,11,'size','S'),(35,12,'Brand','HTC'),(36,13,'Game Size','64GB'),(37,14,'Camera Quality','24Mega Pixel'),(38,15,'Camera Quality','40Mega Pixel'),(39,15,'Weight','2KG'),(40,16,'size','43 inch'),(41,17,'sizes','XL,M'),(42,18,'size','XXL'),(43,19,'size','10inches'),(44,20,'Purity','22K'),(45,21,'size','XL'),(46,22,'RAM','4 GB'),(47,22,'Hard-Disk','64 GB'),(48,23,'RAM','4GB'),(49,23,'Hard-Disk','64GB'),(50,23,'Camera Quality','13 MP'),(51,24,'color','blue'),(52,24,'RAM','6GB'),(53,24,'Hard-Disk','128GB'),(54,24,'Camera Quality','13MP'),(55,25,'RAM','6GB'),(56,25,'Hard-Disk','128GB'),(57,25,'color','blue'),(58,25,'Camera Quality','32MP'),(59,26,'color','Red'),(60,26,'RAM','4GB'),(61,26,'Hard-Disk','64GB'),(62,26,'Camera Quality','16MP'),(63,27,'RAM','6GB'),(64,27,'Hard-Disk','128GB'),(65,27,'Camera Quality','16MP'),(66,28,'color','black'),(67,28,'RAM','4GB'),(68,28,'Hard-Disk','64GB'),(69,28,'Camera Quality','20MP'),(70,29,'color','black'),(71,29,'RAM','3GB'),(72,29,'Hard-Disk','32GB'),(73,30,'color','black'),(74,30,'RAM','8GB'),(75,30,'Hard-Disk','128GB'),(76,30,'Camera Quality','16MP'),(77,31,'color','black'),(78,31,'RAM','3GB'),(79,31,'Hard-Disk','32GB'),(80,31,'Camera Quality','13MP'),(81,32,'color','White'),(82,32,'RAM','4GB'),(83,32,'Hard-Disk','128GB'),(84,32,'Camera Quality','12.1'),(85,32,'Battery','2915mAh'),(86,33,'color','black'),(87,33,'RAM','6GB'),(88,33,'Hard-Disk','128GB'),(89,33,'Camera Quality','20MP'),(90,34,'color','black'),(91,34,'RAM','8GB'),(92,34,'Hard-Disk','1TB'),(93,34,'SDD','128GB'),(94,34,'Graphics','4GB'),(95,35,'color','black'),(96,35,'RAM','8GB'),(97,35,'Hard-Disk','1TB'),(98,35,'Graphics','2GB'),(99,35,'processor','core i5 8th gen'),(100,36,'color','Black'),(101,36,'RAM','8GB'),(102,36,'Hard-Disk','1TB'),(103,36,'weight','2.04KG'),(104,36,'processor','core i5 8th gen'),(105,37,'color','black'),(106,37,'RAM','4GB'),(107,37,'Hard-Disk','1TB'),(108,37,'processor','core i5 8th gen'),(109,38,'color','black'),(110,38,'size','14inch'),(111,38,'RAM','8GB'),(112,38,'Hard-Disk','1TB'),(113,38,'processor','core i6 8th gen'),(114,39,'color','silver'),(115,39,'RAM','8GB'),(116,39,'Hard-DIsk','128GB'),(117,39,'processor','core i5 8th gen'),(118,40,'RAM','8GB'),(119,40,'Hard-DIsk','1TB'),(120,40,'processor','core i5 7th gen'),(121,40,'Graphics','2GB'),(122,40,'color','Grey'),(123,41,'color','silver'),(124,41,'RAM','8GB'),(125,41,'Hard-Disk','256Gb'),(126,41,'processor','core i5 8th gen'),(127,42,'color','black'),(128,42,'RAM','8GB'),(129,42,'Hard-DIsk','1TB'),(130,42,'SDD','128GB'),(131,42,'Graphics','4GB'),(132,42,'processor','core i5 8th gen'),(133,43,'color','white'),(134,43,'SIze','XL'),(135,43,'Brand','Aelomart'),(136,44,'color','black'),(137,44,'Brand','Flying Machine'),(138,44,'size','L'),(139,45,'color','white'),(140,45,'Brand','Flying Machine');
/*!40000 ALTER TABLE `label_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `shipping_address` varchar(140) DEFAULT NULL,
  `status` varchar(40) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total_amount` int(11) NOT NULL,
  `payment_type` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  `rating` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item_table` (`item_id`) ON DELETE CASCADE,
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `seller_table` (`seller_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,192339818,2,1,1,'25-K Frank Anne Street, Michigan','Dispatched','2019-02-10 18:30:00',74999,1,1,0),(2,241151251,3,2,2,'1-A, Royal Legion society, Detroit','Shipped Items','2019-02-14 18:30:00',5500,1,1,0),(3,987174122,4,2,3,'90th Wall Street Hub, Manhattan','Preparing for Dispatch','2019-02-13 18:30:00',17600,2,4,0),(4,137747189,5,3,4,'12FA, Silicon Valley, California','Shipped','2019-02-08 18:30:00',135000,2,1,0),(5,481204124,7,3,5,'4th House of Commons, Bangalore','Out for Delivery','2019-02-04 18:30:00',112500,1,1,0),(6,747748124,6,2,6,'Flying Palace Apartments, Hyderabad','Delivered','2019-02-03 18:30:00',94000,2,1,0),(7,912728491,8,1,7,'12th Vinewood road, Lad Venturas','Delivered','2019-02-03 18:30:00',1200,2,1,0),(8,871241235,10,2,8,'Maddogg club house, Vinewood','Shipped Items','2019-02-13 18:30:00',10800,1,12,0),(9,651412310,1,1,9,'Saharan oasis apartments, Las Vegas','Shipped','2019-02-10 18:30:00',35000,1,1,0),(10,252330102,9,2,10,'Grove Street, Home, San Fierro','Shipped','2019-02-09 18:30:00',900,1,1,0);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_account_details`
--

DROP TABLE IF EXISTS `seller_account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_account_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) NOT NULL,
  `account_number` int(12) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `seller_account_details_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller_table` (`seller_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_account_details`
--

LOCK TABLES `seller_account_details` WRITE;
/*!40000 ALTER TABLE `seller_account_details` DISABLE KEYS */;
INSERT INTO `seller_account_details` VALUES (1,1,123131331,5000000),(2,2,123131332,4000000),(3,3,123131333,3000000),(4,4,123131334,2000000);
/*!40000 ALTER TABLE `seller_account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_table`
--

DROP TABLE IF EXISTS `seller_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_table` (
  `seller_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  `address_1` varchar(140) DEFAULT NULL,
  `address_2` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`seller_id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_table`
--

LOCK TABLES `seller_table` WRITE;
/*!40000 ALTER TABLE `seller_table` DISABLE KEYS */;
INSERT INTO `seller_table` VALUES (1,'Cloudtail Electronics','9367765691','info@cloudtail.com','phones4you','345th Warehouse, Mumbai',NULL),(2,'ProteinXpress','9471621231','proteinxpress@proteinxpress.info','auth@prtnxpr','Scythe Protein Company, Vadodara',NULL),(3,'Computer World','9211823178','query@computerworld.in','auth4cw2019','Electronic City, Bangalore',NULL),(4,'Home4You','9875619898','h4u@gmail.com','beautifyh0mes','12-C, Secret Society, Surat',NULL);
/*!40000 ALTER TABLE `seller_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (1,1,2,2),(2,2,3,1),(3,3,4,4),(4,4,5,1),(5,5,7,1),(6,6,6,1),(7,7,8,1),(8,8,10,12),(9,9,1,1),(10,10,9,1);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,1,2),(2,2,3),(3,3,4),(4,4,5),(5,5,7),(6,6,6),(7,7,8),(8,8,10),(9,9,1),(10,10,9);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;





--
-- Table structure for table `sub_category`
--

DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `sub_category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sub_category_name` (`category_id`,`sub_category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category`
--

LOCK TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category` DISABLE KEYS */;
INSERT INTO `sub_category` VALUES (5,1,'Camera Drones'),(1,1,'Cell phones & Accessories'),(4,1,'Computers & Tablets'),(6,1,'Digital Cameras & Photo'),(7,1,'Others'),(2,1,'Smart Watches'),(3,1,'Video Games & Accessories'),(11,2,'Fashion Jewelry'),(13,2,'Kids Clothing'),(9,2,'Mens Clothing'),(10,2,'Mens Shoes'),(14,2,'Others'),(8,2,'Womens Clothing'),(12,2,'Womens Hand Bags'),(22,3,'Bath & Body'),(17,3,'Fragrances'),(19,3,'Hair Care & Styling'),(16,3,'Health Care'),(15,3,'Makeup'),(18,3,'Nail Care,Manicure & pedicure'),(23,3,'Others'),(21,3,'Shaving & Hair Removal'),(20,3,'Skin Care'),(26,4,'ATV Parts'),(24,4,'Car & Truck parts'),(28,4,'Motorcycle Apparel'),(25,4,'Motorcycle Parts'),(30,4,'Others'),(29,4,'Performance & Racing Parts'),(27,4,'Scooter Parts'),(38,5,'Antiques'),(37,5,'Arts'),(33,5,'Autographed Memorabilia'),(35,5,'Comics'),(39,5,'Others'),(32,5,'Postcards'),(34,5,'Sports Memorabilia'),(31,5,'Stamps'),(36,5,'Toy Vehicles'),(40,6,'Cycling'),(43,6,'Fishing'),(44,6,'Fitness , Running & Yoga'),(42,6,'Hunting'),(46,6,'Others'),(41,6,'Outdoor Sports'),(45,6,'Swimming'),(50,7,'Baby'),(49,7,'Home  Improvement'),(51,7,'Kitchen, Dining & Bar'),(52,7,'Lamps, Lighting & Ceiling Fans'),(53,7,'Others'),(47,7,'Tools & Workshop Equipment'),(48,7,'Yard , Garden & Outdoor Living');
/*!40000 ALTER TABLE `sub_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12 16:18:33
