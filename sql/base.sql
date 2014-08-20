-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.95-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4075
-- Date/time:                    2012-06-18 06:06:54
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for mytestwork
CREATE DATABASE IF NOT EXISTS `mytestwork` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mytestwork`;

CREATE TABLE adv
(
  idapps INT NOT NULL AUTO_INCREMENT,
  date VARCHAR(50),
  title VARCHAR(45),
  views INT,
  text LONGTEXT,
  activity VARCHAR(4) NOT NULL,
  picture LONGTEXT,
  members_idmembers INT UNSIGNED NOT NULL,
  PRIMARY KEY (idapps, members_idmembers)
);
CREATE TABLE login
(
  login_id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL,
  email VARCHAR(50) NOT NULL,
  avatar LONGTEXT,
  picture LONGBLOB
);
CREATE TABLE role
(
  role_id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  login_id INT UNSIGNED NOT NULL,
  role VARCHAR(45) NOT NULL
);
CREATE TABLE userfile
(
  idfile INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  filename VARCHAR(45) NOT NULL,
  filetype VARCHAR(45) DEFAULT 'null',
  file LONGBLOB,
  adv_id INT NOT NULL
);
ALTER TABLE adv ADD FOREIGN KEY (members_idmembers) REFERENCES login (login_id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX FK_adv ON adv (members_idmembers);
ALTER TABLE role ADD FOREIGN KEY (login_id) REFERENCES login (login_id) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX FK_roles ON role (login_id);
ALTER TABLE userfile ADD FOREIGN KEY (adv_id) REFERENCES adv (idapps) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX FK_file_adv_idx ON userfile (adv_id);
