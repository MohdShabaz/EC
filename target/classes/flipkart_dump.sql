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

DROP TABLE IF EXISTS `item_table`;
CREATE TABLE `item_table` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `price` float(10,2) NOT NULL,
  `discount` float(3,2) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `pic_location` varchar(60) DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `sub_category` varchar(40) DEFAULT NULL,
  `barcode` varchar(10) NOT NULL,
  `dummy_1` varchar(40) DEFAULT NULL,
  `dummy_2` varchar(40) DEFAULT NULL,
  `dummy_3` varchar(40) DEFAULT NULL,
  `dummy_4` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `barcode` (`barcode`)
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

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `sub_category`;
CREATE TABLE `sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `sub_category_name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
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
  `buyer_id` int(11) NOT NULL,
  `shipping_address` varchar(140) DEFAULT NULL,
  `status` varchar(40) NOT NULL,
  `order_date` timestamp,
  `total_amount` int(11) NOT NULL,
  `payment_type` tinyint(4) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
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

INSERT INTO item_table(description, price, discount, name, pic_location, category, sub_category, barcode, dummy_1, dummy_2, dummy_3, dummy_4) VALUES
('Flagship killer smartphone of 2019', '34999.99', '0.10', 'OnePlus 6T', 'images/one_plus_6t', 'Mobile Phones', 'Android Smartphones', '1804289383', NULL, NULL, NULL, NULL),
('Simplicity. Glorified. Go Apple', '74999.00', '0.07', 'iPhone X', 'images/iphone_x', 'Mobile Phones', 'iOS Smartphones', '1714636915', NULL, NULL, NULL, NULL),
('FCAA certified whey protein isolate. No amino spiking!', '5500.00', '0.50', 'BPI Sports ISO HD', 'images/bpi_iso_hd', 'Health Supplements', 'Whey Protein', '1681692777', NULL, NULL, NULL, NULL),
('Industry-standard for IFBB Pros. MuscleTech supreme', '4400.00', '0.60', 'MuscleTech NitroTech Whey', 'images/muscle_tech_nitro', 'Health Supplements', 'Whey Protein', '1957747793', NULL, NULL, NULL, NULL),
('Gaming evolved! Dell G7 with state-of-the art specs', '134999.99', '0.35', 'Dell G7', 'images/dell_g7', 'Laptops', 'Gaming Laptops', '1649760492', NULL, NULL, NULL, NULL),
('Thinnest and fastest ultrabook of 2019', '93999.99', '0.25', 'ASUS Vivobook', 'images/asus_vivo', 'Laptops', 'Ultrabooks', '2596516649', NULL, NULL, NULL, NULL),
('Business standard for 30 years. ThinkPad', '112500.00', '0.20', 'ThinkPad T480S', 'images/thinkpad_t480s', 'Laptops', 'Business Laptops', '3719885386', NULL, NULL, NULL, NULL),
('Fast, safe and reliable cooking of eggs!', '1200.00', '0.15', 'Vixen Egg Boiler', 'images/vixen_egg_boiler', 'Cooking Equipment', 'Electric Equipment', '1189641421', NULL, NULL, NULL, NULL),
('5 litre capacity electric kettle', '899.99', '0', 'Pigeon Kettle', 'images/pigeon_kettle', 'Cooking Equipment', 'Electric Equipment', '1846930886', NULL, NULL, NULL, NULL),
('Cereal bowl (Pack of 4)', '250.00', '0', 'MCA Cereal bowl', 'images/cereal_bowl', 'Cooking Equipment', 'Utensils', '2424238335', NULL, NULL, NULL, NULL);

INSERT INTO item_seller(item_id, seller_id, quantity, address) VALUES
(1, 1, 400, 1),
(2, 1, 200, 1),
(3, 2, 50, 1),
(4, 2, 70, 1),
(5, 3, 95, 1),
(6, 3, 89, 1),
(7, 3, 103, 1),
(8, 4, 1065, 1),
(9, 4, 1000, 1),
(10, 4, 2056, 1);

INSERT INTO label_table(item_id, label, value) VALUES
(1, 'color', 'red'),
(2, 'color', 'blue'),
(3, 'color', 'green'),
(4, 'color', 'red'),
(5, 'color', 'red'),
(6, 'color', 'blue'),
(7, 'color', 'orange'),
(8, 'color', 'yellow'),
(9, 'color', 'violet'),
(10, 'color', 'purple');

INSERT INTO category(category_name) VALUES
('Mobile Phones'),
('Health Supplements'),
('Laptops'),
('Cooking Equipment');

INSERT INTO sub_category(category_id, sub_category_name) VALUES
('1', 'Android Smartphones'),
('1', 'iOS Smartphones'),
('2', 'Whey Protein'),
('3', 'Gaming Laptops'),
('3', 'Ultrabooks'),
('3', 'Business Laptops'),
('4', 'Electric Equipment'),
('4', 'Utensils');

INSERT INTO shopping_cart(buyer_id, item_id, quantity) VALUES
('1', '2', '2'),
('2', '3', '1'),
('3', '4', '4'),
('4', '5', '1'),
('5', '7', '1'),
('6', '6', '1'),
('7', '8', '1'),
('8', '10', '12'),
('9', '1', '1'),
('10', '9', '1');

INSERT INTO order_details(order_id, item_id, buyer_id, shipping_address, status, order_date, total_amount, payment_type,quantity) VALUES
('192339818', '2', '1', '25-K Frank Anne Street, Michigan', 'Dispatched', '2019-02-11', '74999.00', 1,'1'),
('241151251', '3', '2', '1-A, Royal Legion society, Detroit', 'Preparing for Dispatch', '2019-02-15', '5500.00', 1,'1'),
('987174122', '4', '3', '90th Wall Street Hub, Manhattan', 'Preparing for Dispatch', '2019-02-14', '17600.00', 2,'4'),
('137747189', '5', '4', '12FA, Silicon Valley, California', 'Shipped', '2019-02-09', '134999.99', 2,'1'),
('481204124', '7', '5', '4th House of Commons, Bangalore', 'Out for Delivery', '2019-02-05', '112500.00', 1,'1'),
('747748124', '6', '6', 'Flying Palace Apartments, Hyderabad', 'Delivered', '2019-02-04', '93999.99',2, '1'),
('912728491', '8', '7', '12th Vinewood road, Lad Venturas', 'Delivered', '2019-02-04', '1200.00', 2,'1'),
('871241235', '10', '8', 'Maddogg club house, Vinewood', 'Dispatched', '2019-02-14', '10800.00', 1,'12'),
('651412310', '1', '9', 'Saharan oasis apartments, Las Vegas', 'Shipped', '2019-02-11', '34999.99',1, '1'),
('252330102', '9', '10','Grove Street, Home, San Fierro', 'Shipped', '2019-02-10', '899.99',1, '1');
