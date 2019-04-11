-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: flipkart
-- Host: localhost    Database: ebayv1
-- ------------------------------------------------------
-- Server version 8.0.12
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
  `barcode` varchar(10) NOT NULL, `total_stars` int(11) DEFAULT 0,
  `total_users_rated` int(11) DEFAULT 0,
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
  `attr` varchar(40) NOT NULL,
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
  `attr` varchar(40) NOT NULL,
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
  `rating` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
   FOREIGN KEY (`item_id`)  REFERENCES item_table(`item_id`) ON DELETE CASCADE ,
     FOREIGN KEY (`seller_id`)  REFERENCES seller_table(`seller_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `buyer_account_details`;
CREATE TABLE `buyer_account_details`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `account_number` int(12) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`buyer_id`) REFERENCES buyer_table(`buyer_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `seller_account_details`;
CREATE TABLE `seller_account_details`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) NOT NULL,
  `account_number` int(12) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`seller_id`) REFERENCES seller_table(`seller_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `ebay_account_details`;
CREATE TABLE `ebay_account_details`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(40) NOT NULL,
  `account_number` int(11) NOT NULL,
  `current_balance` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

Insert into buyer_table(name, dob, mobile, address_1, address_2, email, password) VALUES
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



INSERT INTO admin_table(admin_id, name, mobile, email, password) VALUES
(1, 'Shane', '9290966544', 'shaneroy@gmail.com', 'shane1234'),
(2, 'Suresh', '9247891907', 'sureahmalla@gmail.com', 'suri@malla'),
(3, 'Umesh', '9247937995', 'umeshkumar@gmail.com', 'umesh000'),
(4, 'Sahid', '9848689072', 'sahidafridi@yahoomail.com', 'sahiSahi'),
(5, 'Benjamin', '7981740761', 'benjaminhaas@outlook.com', 'benj9876'),
(6, 'James', '8919835818', 'jameschristian@rocketmail.com', 'james007'),
(7, 'Chris', '9642100251', 'chrishenry@google.com', 'chris11');

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

INSERT INTO label_table(item_id, label, attr) VALUES
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



INSERT INTO item_table(description, price, discount, name, pic_location, category, sub_category, barcode, dummy_1, dummy_2, dummy_3, dummy_4) VALUES
('Flagship killer smartphone of 2019', '34999.99', '0.10', 'OnePlus 6T', 'images/1804289383.png', 1, 1, '1804289383', NULL, NULL, NULL, NULL),
('Simplicity. Glorified. Go Apple', '74999.00', '0.07', 'iPhone X', 'images/1714636915.png', 1, 1, '1714636915', NULL, NULL, NULL, NULL),
('FCAA certified whey protein isolate. No amino spiking!', '5500.00', '0.50', 'BPI Sports ISO HD', 'images/1681692777.png', 3, 16, '1681692777', NULL, NULL, NULL, NULL),
('Industry-standard for IFBB Pros. MuscleTech supreme', '4400.00', '0.60', 'MuscleTech NitroTech Whey', 'images/1957747793.png', 3, 16, '1957747793', NULL, NULL, NULL, NULL),
('Gaming evolved! Dell G7 with state-of-the art specs', '134999.99', '0.35', 'Dell G7', 'images/1649760492.png', 1, 4, '1649760492', NULL, NULL, NULL, NULL),
('Thinnest and fastest ultrabook of 2019', '93999.99', '0.25', 'ASUS Vivobook', 'images/2596516649.png', 1, 4, '2596516649', NULL, NULL, NULL, NULL),
('Business standard for 30 years. ThinkPad', '112500.00', '0.20', 'ThinkPad T480S', 'images/3719885386.png', 1, 4, '3719885386', NULL, NULL, NULL, NULL),
('Fast, safe and reliable cooking of eggs!', '1200.00', '0.15', 'Vixen Egg Boiler', 'images/1189641421.png', 7, 51, '1189641421', NULL, NULL, NULL, NULL),
('5 litre capacity electric kettle', '899.99', '0.25', 'Pigeon Kettle', 'images/1846930886.png', 7, 51, '1846930886', NULL, NULL, NULL, NULL),
('Cereal bowl (Pack of 4)', '250.00', '0.10', 'MCA Cereal bowl', 'images/2424238335.png', 7, 53, '2424238335', NULL, NULL, NULL, NULL),
('Clothes for Small Pet Puppys', '895.17', '0.25', 'puppy CLothes', 'images/2836827938.png', 7, 52, '2836827938', NULL, NULL, NULL, NULL),
('HTC Bluetooth Smart Watch with Camera and SIM Slot ', '650.00', '0.05', 'Smart Watch', 'images/2389548393.png', 1, 2, '2389548393', NULL, NULL, NULL, NULL),
('Red Dead Redemption 2 - PlayStation 4 New', '4095.79', '0.36', 'Red Dead Redemption 2', 'images/2884383939.png', 1, 3, '2884383939', NULL, NULL, NULL, NULL),
('Sony Alpha a7 III Mirrorless Digital Camera Body', '141175.90', '0.17', 'Sony Alpha Camera', 'images/2759273958.png', 1, 6, '2759273958', NULL, NULL, NULL, NULL),
('DJI Mavic Air Quadcopter Drone with Camera', '6500.00', '0.12', 'Quadcaptor Drone', 'images/1837493738.png', 1, 5, '1837493738', NULL, NULL, NULL, NULL),
('Samsung Electronics UN43MU6300 43-Inch 4K Ultra HD Smart LED TV with 120 CMR', '41355.80', '0.43', 'Samsung LED TV', 'images/1647382046.png', 1, 7, '1647382046', NULL, NULL, NULL, NULL),
('Family Matching Outfits Mother and Daughter Clothes dress Baby Girl stripe Shirt', '785.30', '0.17', 'Matching Clothes', 'images/1567839764.png', 2, 8, '1567839764', NULL, NULL, NULL, NULL),
('DIESEL Mens T-Shirt MIREY Mohawk BROWN GOLD Casual Designer', '6754.80', '0.80', 'DIESEL T-Shirt', 'images/3784910647.png', 2, 9, '3784910647', NULL, NULL, NULL, NULL),
('AIR JORDAN 13 RETRO ATMOSPHERE GREY SHOES FOR MEN', '5514.80', '0.05', 'JORDAN SHOES', 'images/3789234568.png', 2, 10, '3789234568', NULL, NULL, NULL, NULL),
('Joyalukas 22K Gold Necklace', '240161.00', '0.20', 'Gold NeckLace', 'images/2489765135.png', 2, 11, '2489765135', NULL, NULL, NULL, NULL),
('adidas Three Stripe Life T-Short for Kids', '1380.00', '0.50', 'Adidas T-Shirt', 'images/2759486125.png', 2, 13, '2759486125', NULL, NULL, NULL, NULL);




INSERT INTO order_details(order_id, item_id, seller_id, buyer_id, shipping_address, status, order_date, total_amount, payment_type,quantity) VALUES
(192339818, 2, 1, 1, '25-K Frank Anne Street, Michigan', 'Dispatched', '2019-02-11', 74999.00, 1,1),
(241151251, '3', 2, '2', '1-A, Royal Legion society, Detroit', 'Shipped Items', '2019-02-15', 5500.00, 1,1),
(987174122, '4', 2, '3', '90th Wall Street Hub, Manhattan', 'Preparing for Dispatch', '2019-02-14', 17600.00, 2,4),
(137747189, '5', 3, '4', '12FA, Silicon Valley, California', 'Shipped', '2019-02-09', 134999.99, 2,1),
(481204124, '7', 3, '5', '4th House of Commons, Bangalore', 'Out for Delivery', '2019-02-05', 112500.00, 1,1),
(747748124, '6', 2, '6', 'Flying Palace Apartments, Hyderabad', 'Delivered', '2019-02-04', 93999.99,2, 1),
(912728491, '8', 1, '7', '12th Vinewood road, Lad Venturas', 'Delivered', '2019-02-04', 1200.00, 2,1),
(871241235, '10', 2, '8', 'Maddogg club house, Vinewood', 'Shipped Items', '2019-02-14', 10800.00, 1,12),
(651412310, '1', 1, '9', 'Saharan oasis apartments, Las Vegas', 'Shipped', '2019-02-11', 34999.99,1, 1),
(252330102, '9', 2, '10','Grove Street, Home, San Fierro', 'Shipped', '2019-02-10', 899.99,1, 1);

INSERT INTO ebay_account_details(account_name, account_number, current_balance) VALUES
('ebay_account', 346712124, 500000);
