-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2020 at 07:40 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

-- drop database netdb

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `netdb`
--
CREATE DATABASE IF NOT EXISTS `netdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `netdb`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `uid` char(12) NOT NULL,
  `u_pwd` varchar(30) NOT NULL,
  `time_remaining` int(11) NOT NULL DEFAULT 0,
  `u_state` tinyint(1) NOT NULL DEFAULT 0,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`uid`, `u_pwd`, `time_remaining`, `u_state`, `start_date`, `end_date`) VALUES
('1', '1', 26, 0, '2020-06-29', '2020-06-29'),
('2', '2', 71, 0, '2020-07-04', '2020-07-04'),
('3', '3', 75, 0, '2020-06-25', '2020-06-25'),
('4', '4', 87, 0, '2020-06-25', '2020-06-25'),
('5', '5', 77, 0, '2020-06-25', '2020-06-25'),
('6', '6', 30, 0, NULL, NULL),
('7', '7', 0, 0, '2020-07-04', '2020-07-04'),
('8', '', 10, 0, NULL, NULL),
('khach', 'admin', 161084, 0, '2020-07-08', '2020-07-08'),
('nhan', 'nhan', 106, 0, '2020-07-08', '2020-07-08'),
('nhannguyen', '1', 1000, 0, NULL, NULL),
('nhatpro', 'nhatpro', 195, 0, NULL, NULL),
('thuan', 'thuan', 0, 0, '2020-07-08', '2020-07-08'),
('thuannt', 'thuannt', 24, 0, '2020-07-04', '2020-07-04'),
('thuantruong', 'thuantruong', 100, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pc`
--

CREATE TABLE `pc` (
  `pc_id` int(12) NOT NULL,
  `uid` char(12) DEFAULT NULL,
  `pc_state` tinyint(1) NOT NULL DEFAULT 0,
  `using_time` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pc`
--

INSERT INTO `pc` (`pc_id`, `uid`, `pc_state`, `using_time`) VALUES
(0, NULL, 0, 0),
(1, NULL, 0, 0),
(2, NULL, 0, 0),
(3, NULL, 0, 0),
(4, NULL, 0, 0),
(5, NULL, 0, 0),
(6, NULL, 0, 0),
(7, NULL, 0, 0),
(8, NULL, 0, 0),
(9, NULL, 0, 0),
(10, NULL, 0, 0),
(11, NULL, 0, 0),
(12, NULL, 0, 0),
(13, NULL, 0, 0),
(14, NULL, 0, 0),
(15, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `stastical`
--

CREATE TABLE `stastical` (
  `date` date NOT NULL,
  `income` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stastical`
--

INSERT INTO `stastical` (`date`, `income`) VALUES
('2020-06-07', 123214),
('2020-06-08', 123400),
('2020-06-09', 120000),
('2020-06-10', 1243330),
('2020-06-11', 20000),
('2020-06-12', 234000),
('2020-06-13', 1231110),
('2020-06-14', 200),
('2020-06-15', 32500),
('2020-06-16', 45000),
('2020-06-17', 32000),
('2020-06-25', 46400),
('2020-06-26', 200),
('2020-06-27', 7311),
('2020-06-29', 40100),
('2020-07-04', 100200),
('2020-07-08', 14100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`uid`) USING BTREE;

--
-- Indexes for table `pc`
--
ALTER TABLE `pc`
  ADD PRIMARY KEY (`pc_id`),
  ADD KEY `PC_FK_1` (`uid`);

--
-- Indexes for table `stastical`
--
ALTER TABLE `stastical`
  ADD PRIMARY KEY (`date`);
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
