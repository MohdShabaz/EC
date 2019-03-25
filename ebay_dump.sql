-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--

-- Host: localhost    Database: ebayv1
-- ------------------------------------------------------
-- Server version	8.0.12
--
-- Table structure for table `buyer_table`
--

SET NAMES utf8;

DROP TABLE IF EXISTS `buyer_table`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Table structure for table `item_seller`
--

DROP TABLE IF EXISTS `item_seller`;
CREATE TABLE `item_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `address` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`,`seller_id`,`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Table structure for table `item_table`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name`(`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `sub_category`;
CREATE TABLE `sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `sub_category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
    UNIQUE KEY `sub_category_name`(`category_id`,`sub_category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



DROP TABLE IF EXISTS `item_table`;
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
  `dummy_1` varchar(40) DEFAULT NULL,
  `dummy_2` varchar(40) DEFAULT NULL,
  `dummy_3` varchar(40) DEFAULT NULL,
  `dummy_4` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `barcode` (`barcode`),
    foreign key(`category`) references category(`id`) ON DELETE CASCADE,    
    foreign key(`sub_category`) references sub_category(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

  DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


--
-- Table structure for table `label_table`
--

DROP TABLE IF EXISTS `label_table`;
CREATE TABLE `label_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `label` varchar(40) NOT NULL,
  `value` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`,`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Table structure for table `seller_table`
--

DROP TABLE IF EXISTS `seller_table`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `label_table`;
CREATE TABLE `label_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `label` varchar(40) NOT NULL,
  `value` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`,`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `shipping_address` varchar(140) DEFAULT NULL,
  `status` varchar(40) NOT NULL,
  `order_date` timestamp,
  `total_amount` int(11) NOT NULL,
  `payment_type` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
   FOREIGN KEY (`item_id`)  REFERENCES item_table(`item_id`) ON DELETE CASCADE ,
     FOREIGN KEY (`seller_id`)  REFERENCES seller_table(`seller_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

Insert into category (category_name) values ("Electronics");
Insert into category (category_name) values ("Fashion");
Insert into category (category_name) values ("Health & Beauty");
Insert into category (category_name) values ("Motors");
Insert into category (category_name) values ("Collectibles");
Insert into category (category_name) values ("Sports");
Insert into category (category_name) values ("Home & Garden");
Insert into category (category_name) values ("Under Rs100/-");
Insert into category (category_name) values ("Others");


Insert into sub_category (category_id,sub_category_name) values (1,"Cell phones & Accessories");
Insert into sub_category (category_id,sub_category_name) values (1,"Smart Watches");
Insert into sub_category (category_id,sub_category_name) values (1,"Video Games & Accessories");
Insert into sub_category (category_id,sub_category_name) values (1,"Computers & Tablets");
Insert into sub_category (category_id,sub_category_name) values (1,"Camera Drones");
Insert into sub_category (category_id,sub_category_name) values (1,"Digital Cameras & Photo");
Insert into sub_category (category_id,sub_category_name) values (1,"Others");

Insert into sub_category (category_id,sub_category_name) values (2,"Womens Clothing");
Insert into sub_category (category_id,sub_category_name) values (2,"Mens Clothing");
Insert into sub_category (category_id,sub_category_name) values (2,"Mens Shoes");
Insert into sub_category (category_id,sub_category_name) values (2,"Fashion Jewelry");
Insert into sub_category (category_id,sub_category_name) values (2,"Womens Hand Bags");
Insert into sub_category (category_id,sub_category_name) values (2,"Kids Clothing");
Insert into sub_category (category_id,sub_category_name) values (2,"Others");


Insert into sub_category (category_id,sub_category_name) values (3,"Makeup");
Insert into sub_category (category_id,sub_category_name) values (3,"Health Care");
Insert into sub_category (category_id,sub_category_name) values (3,"Fragrances");
Insert into sub_category (category_id,sub_category_name) values (3,"Nail Care,Manicure & pedicure");
Insert into sub_category (category_id,sub_category_name) values (3,"Hair Care & Styling");
Insert into sub_category (category_id,sub_category_name) values (3,"Skin Care");
Insert into sub_category (category_id,sub_category_name) values (3,"Shaving & Hair Removal");
Insert into sub_category (category_id,sub_category_name) values (3,"Bath & Body");
Insert into sub_category (category_id,sub_category_name) values (3,"Others");


Insert into sub_category (category_id,sub_category_name) values (4,"Car & Truck parts");
Insert into sub_category (category_id,sub_category_name) values (4,"Motorcycle Parts");
Insert into sub_category (category_id,sub_category_name) values (4,"ATV Parts");
Insert into sub_category (category_id,sub_category_name) values (4,"Scooter Parts");
Insert into sub_category (category_id,sub_category_name) values (4,"Motorcycle Apparel");
Insert into sub_category (category_id,sub_category_name) values (4,"Performance & Racing Parts");
Insert into sub_category (category_id,sub_category_name) values (4,"Others");



Insert into sub_category (category_id,sub_category_name) values (5,"Stamps");
Insert into sub_category (category_id,sub_category_name) values (5,"Postcards");
Insert into sub_category (category_id,sub_category_name) values (5,"Autographed Memorabilia");
Insert into sub_category (category_id,sub_category_name) values (5,"Sports Memorabilia");
Insert into sub_category (category_id,sub_category_name) values (5,"Comics");
Insert into sub_category (category_id,sub_category_name) values (5,"Toy Vehicles");
Insert into sub_category (category_id,sub_category_name) values (5,"Arts");
Insert into sub_category (category_id,sub_category_name) values (5,"Antiques");
Insert into sub_category (category_id,sub_category_name) values (5,"Others");


Insert into sub_category (category_id,sub_category_name) values (6,"Cycling");
Insert into sub_category (category_id,sub_category_name) values (6,"Outdoor Sports");
Insert into sub_category (category_id,sub_category_name) values (6,"Hunting");
Insert into sub_category (category_id,sub_category_name) values (6,"Fishing");
Insert into sub_category (category_id,sub_category_name) values (6,"Fitness , Running & Yoga");
Insert into sub_category (category_id,sub_category_name) values (6,"Swimming");
Insert into sub_category (category_id,sub_category_name) values (6,"Others");

Insert into sub_category (category_id,sub_category_name) values (7,"Tools & Workshop Equipment");
Insert into sub_category (category_id,sub_category_name) values (7,"Yard , Garden & Outdoor Living");
Insert into sub_category (category_id,sub_category_name) values (7,"Home  Improvement");
Insert into sub_category (category_id,sub_category_name) values (7,"Baby");
Insert into sub_category (category_id,sub_category_name) values (7,"Kitchen, Dining & Bar");
Insert into sub_category (category_id,sub_category_name) values (7,"Lamps, Lighting & Ceiling Fans");
Insert into sub_category (category_id,sub_category_name) values (7,"Others");
<<<<<<< HEAD

=======
>>>>>>> fbcf187200a3e278fbe3cf0db1e379bb295fd033
