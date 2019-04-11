-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: flipkart
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
  UNIQUE (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `sub_category`;
CREATE TABLE `sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `sub_category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`sub_category_name`)
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




INSERT INTO buyer_table(name, dob, mobile, address_1, address_2, email, password) VALUES
('John','1998-01-09','9367535629', '25-K Frank Anne Street, Michigan', NULL, 'johnfkennedy@gmail.com', 'johnwick3'),
('Smith','1997-07-17','9127093606', '1-A, Royal Legion society, Detroit', NULL, 'willissmith@rocketmail.com', 'tokyopunchout'),
('Wong','1995-11-06','9261879202', '90th Wall Street Hub, Manhattan', NULL, "liuwong@outlook.com", 'chaugoldman'),
('Zelaya','1989-01-09','9375922897', '12FA, Silicon Valley, California', NULL, "zellala@gmail.com", 'zelly1234'),
('Ramesh','1990-01-09','9361293194', '4th House of Commons, Bangalore', NULL, "rameshbhau@yahoo.com", 'bhaibh67a'),
('Suresh','1993-02-19','9784503610', 'Flying Palace Apartments, Hyderabad', NULL, "sureshiya@gmail.com", 'thegreatestking67'),
('Pankaj','1987-01-15','9632061554', '12th Vinewood road, Lad Venturas', NULL, "pank.1278@yahoomail.com", 'pnkjsthr98'),
('Alicia','1991-03-17','9765693745', 'Maddogg club house, Vinewood', NULL, "patricia.ali@hotmail.com", 'carljohnsontemp'),
('Trevor','1981-05-07','9254744307', 'Saharan oasis apartments, Las Vegas', NULL, 'itsmetrevor@google.com', 'patriciamissya'),
('Franklin','1998-04-08','9868843149', 'Grove Street, Home, San Fierro', NULL, 'franklin.nig@rocketmail.com', 'drivin12lambo');


INSERT INTO seller_table(name, mobile, email, password, address_1, address_2) VALUES
('Cloudtail Electronics','9367765691', 'info@cloudtail.com', 'phones4you', '345th Warehouse, Mumbai', NULL),
('ProteinXpress','9471621231', 'proteinxpress@proteinxpress.info', 'auth@prtnxpr', 'Scythe Protein Company, Vadodara', NULL),
('Computer World','9211823178', 'query@computerworld.in', 'auth4cw2019', 'Electronic City, Bangalore', NULL),
('Home4You','9875619898', 'h4u@gmail.com', 'beautifyh0mes', '12-C, Secret Society, Surat', NULL);


