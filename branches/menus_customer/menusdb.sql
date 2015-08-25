-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.15 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-03-06 20:40:53
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for bookingdb
DROP DATABASE IF EXISTS `menusdb`;
CREATE DATABASE IF NOT EXISTS `menusdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `menusdb`;


-- Dumping structure for table bookingdb.booking
DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `id` bigint(20) NOT NULL,
  `beds` int(11) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `creditCard` varchar(16) NOT NULL,
  `creditCardExpiryMonth` int(11) NOT NULL,
  `creditCardExpiryYear` int(11) NOT NULL,
  `creditCardName` varchar(70) NOT NULL,
  `smoking` tinyint(1) NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  `user_username` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6713A03951897512` (`hotel_id`),
  KEY `FK6713A0396E4A3BD` (`user_username`),
  CONSTRAINT `FK6713A0396E4A3BD` FOREIGN KEY (`user_username`) REFERENCES `customer` (`username`),
  CONSTRAINT `FK6713A03951897512` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookingdb.booking: ~0 rows (approximately)
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;


-- Dumping structure for table bookingdb.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `username` varchar(15) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookingdb.customer: ~2 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
REPLACE INTO `customer` (`username`, `name`, `password`) VALUES
	('demo', 'Demo User', 'demo'),
	('gavin', 'Gavin King', 'foobar');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- Dumping structure for table bookingdb.hibernate_sequence
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookingdb.hibernate_sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;


-- Dumping structure for table bookingdb.hotel
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `id` bigint(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(40) NOT NULL,
  `country` varchar(40) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(6,2) DEFAULT NULL,
  `state` varchar(10) NOT NULL,
  `zip` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookingdb.hotel: ~20 rows (approximately)
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
REPLACE INTO `hotel` (`id`, `address`, `city`, `country`, `name`, `price`, `state`, `zip`) VALUES
	(1, 'Tower Place, Buckhead', 'Atlanta', 'USA', 'Marriott Courtyard', 120.00, 'GA', '30305'),
	(2, 'Tower Place, Buckhead', 'Atlanta', 'USA', 'Doubletree', 180.00, 'GA', '30305'),
	(3, 'Union Square, Manhattan', 'NY', 'USA', 'W Hotel', 450.00, 'NY', '10011'),
	(4, 'Lexington Ave, Manhattan', 'NY', 'USA', 'W Hotel', 450.00, 'NY', '10011'),
	(5, '1315 16th Street NW', 'Washington', 'USA', 'Hotel Rouge', 250.00, 'DC', '20036'),
	(6, '70 Park Avenue', 'NY', 'USA', '70 Park Avenue Hotel', 300.00, 'NY', '10011'),
	(8, '1395 Brickell Ave', 'Miami', 'USA', 'Conrad Miami', 300.00, 'FL', '33131'),
	(9, '2106 N Clairemont Ave', 'Eau Claire', 'USA', 'Sea Horse Inn', 80.00, 'WI', '54703'),
	(10, '1151 W Macarthur Ave', 'Eau Claire', 'USA', 'Super 8 Eau Claire Campus Area', 90.00, 'WI', '54701'),
	(11, '55 Fourth Street', 'San Francisco', 'USA', 'Marriott Downtown', 160.00, 'CA', '94103'),
	(12, 'Passeig del Taulat 262-264', 'Barcelona', 'Spain', 'Hilton Diagonal Mar', 200.00, 'Catalunya', '08019'),
	(13, 'Independence Park', 'Tel Aviv', 'Israel', 'Hilton Tel Aviv', 210.00, '', '63405'),
	(14, 'Takeshiba Pier', 'Tokyo', 'Japan', 'InterContinental Tokyo Bay', 240.00, '', '105'),
	(15, ' Esplanade L閛pold-Robert 2', 'Neuchatel', 'Switzerland', 'Hotel Beaulac', 130.00, '', '2000'),
	(16, 'William & George Streets', 'Brisbane', 'Australia', 'Conrad Treasury Place', 140.00, 'QLD', '4001'),
	(17, '1228 Sherbrooke St', 'West Montreal', 'Canada', 'Ritz Carlton', 230.00, 'Quebec', 'H3G1H6'),
	(18, 'Peachtree Rd, Buckhead', 'Atlanta', 'USA', 'Ritz Carlton', 460.00, 'GA', '30326'),
	(19, '68 Market Street', 'Sydney', 'Australia', 'Swissotel', 220.00, 'NSW', '2000'),
	(20, 'Albany Street', 'Regents Park London', 'Great Britain', 'Meli�White House', 250.00, '', 'NW13UP'),
	(21, '171 West Randolph Street', 'Chicago', 'USA', 'Hotel Allegro', 210.00, 'IL', '60601');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
