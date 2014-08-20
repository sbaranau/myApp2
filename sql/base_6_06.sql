-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.95-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4075
-- Date/time:                    2012-06-04 22:45:07
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for mytestwork
CREATE DATABASE IF NOT EXISTS `mytestwork` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mytestwork`;


-- Dumping structure for table mytestwork.adv
CREATE TABLE IF NOT EXISTS `adv` (
  `idapps` int(11) NOT NULL auto_increment,
  `date` varchar(50) default NULL,
  `title` varchar(45) default NULL,
  `views` int(11) default NULL,
  `text` longtext,
  `members_idmembers` int(11) unsigned NOT NULL,
  PRIMARY KEY  (`idapps`,`members_idmembers`),
  KEY `FK_adv` (`members_idmembers`),
  CONSTRAINT `FK_adv` FOREIGN KEY (`members_idmembers`) REFERENCES `login` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table mytestwork.login
CREATE TABLE IF NOT EXISTS `login` (
  `login_id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY  (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table mytestwork.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(10) unsigned NOT NULL auto_increment,
  `login_id` int(10) unsigned NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY  (`role_id`),
  KEY `FK_roles` (`login_id`),
  CONSTRAINT `FK_role` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
