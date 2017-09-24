-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 24, 2017 at 11:57 
-- Server version: 10.1.9-MariaDB
-- PHP Version: 7.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imdsocmed`
--
CREATE DATABASE IF NOT EXISTS `imdsocmed` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `imdsocmed`;

-- --------------------------------------------------------

--
-- Table structure for table `friendship`
--

CREATE TABLE `friendship` (
  `id` int(11) NOT NULL,
  `user_one` varchar(60) NOT NULL,
  `user_two` varchar(60) NOT NULL,
  `status` int(11) NOT NULL,
  `action_by_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friendship`
--

INSERT INTO `friendship` (`id`, `user_one`, `user_two`, `status`, `action_by_user`) VALUES
(1, 'elko.s.eppasa@gmail.com', 'john@example.com', 2, 1),
(2, 'john@example.com', 'andy@example.com', 2, 1),
(3, 'john@example.com', 'common@example.com', 2, 2),
(4, 'andy@example.com', 'common@example.com', 2, 1),
(5, 'andy@example.com', 'elko.s.eppasa@gmail.com', 2, 1),
(6, 'common@example.com', 'elko.s.eppasa@gmail.com', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `newsfeed`
--

CREATE TABLE `newsfeed` (
  `id` int(11) NOT NULL,
  `sender` varchar(60) NOT NULL,
  `text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `newsfeed`
--

INSERT INTO `newsfeed` (`id`, `sender`, `text`) VALUES
(1, 'john@example.com', 'Hello World! kate@example.com'),
(2, 'john@example.com', 'Hello World! kate@example.com elko@gmail.com'),
(14, 'john@example.com', 'Hello World! kate@example.com nur@example.com'),
(15, 'john@example.com', 'Hello World! kate@example.com runail@example.com');

-- --------------------------------------------------------

--
-- Table structure for table `subscribe`
--

CREATE TABLE `subscribe` (
  `requestor` varchar(60) NOT NULL,
  `target` varchar(60) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscribe`
--

INSERT INTO `subscribe` (`requestor`, `target`, `status`) VALUES
('andy@example.com', 'john@example.com', 0),
('lisa@example.com', 'john@example.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `name`) VALUES
('andy@exampe.com', 'andy'),
('common@example.com', 'common'),
('elko.s.eppasa@gmail.com', 'elko'),
('john@example.com', 'john'),
('kate@example.com', 'kate'),
('lisa@example.com', 'lisa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friendship`
--
ALTER TABLE `friendship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `newsfeed`
--
ALTER TABLE `newsfeed`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscribe`
--
ALTER TABLE `subscribe`
  ADD PRIMARY KEY (`requestor`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `friendship`
--
ALTER TABLE `friendship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `newsfeed`
--
ALTER TABLE `newsfeed`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
