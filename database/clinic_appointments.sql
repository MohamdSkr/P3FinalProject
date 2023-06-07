-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2023 at 11:22 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic_appointments`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `appointmentDate` date DEFAULT NULL,
  `appointmentDay` varchar(15) NOT NULL,
  `appointmentTime` varchar(15) NOT NULL,
  `status` enum('free','booked') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `appointmentDate`, `appointmentDay`, `appointmentTime`, `status`) VALUES
(1, '2023-06-01', 'THURSDAY', '09:00:00', 'booked'),
(2, '2023-06-06', 'TUESDAY', '12:00:00', 'booked'),
(4, '2023-06-08', 'THURSDAY', '12:00:00', 'booked'),
(5, '2023-06-09', 'FRIDAY', '10:00:00', 'booked'),
(6, '2023-07-07', 'FRIDAY', '01:30:00', 'booked'),
(7, '2023-07-01', 'SATURDAY', '10:00:00', 'free'),
(8, '2023-07-10', 'MONDAY', '01:30:00', 'booked'),
(9, '2023-07-12', 'WEDNESDAY', '12:40:00', 'free'),
(12, '2023-07-01', 'SATURDAY', '12:00:00', 'booked');

-- --------------------------------------------------------

--
-- Table structure for table `booked_appointment`
--

CREATE TABLE `booked_appointment` (
  `id` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `appointmentID` int(11) DEFAULT NULL,
  `status` enum('waiting','finished') DEFAULT NULL,
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booked_appointment`
--

INSERT INTO `booked_appointment` (`id`, `userID`, `appointmentID`, `status`, `comment`) VALUES
(1, 2, 2, 'finished', 'Edit comment for test it .'),
(2, 3, 1, 'finished', 'Again edit it . '),
(4, 2, 5, 'waiting', ''),
(5, 4, 6, 'finished', 'Eat fruits and sleep very well . '),
(6, 5, 8, 'finished', 'just take care and sleep well .'),
(7, 6, 12, 'finished', 'eat fruits and take care jamal.');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `gender` enum('male','female') DEFAULT NULL,
  `role` enum('admin','patient') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstName`, `lastName`, `age`, `email`, `phone`, `gender`, `role`) VALUES
(1, 'admin', '1', 'admin', 'admin', 0, 'admin@gmail.com', '123456', 'male', 'admin'),
(3, 'jana', '456', 'Jana', 'Ahmed', 25, 'jana@gmail.com', '123987', 'female', 'patient'),
(4, 'Mohamd', '012', 'Mohamd', 'Skr', 24, 'mohjamd@gmail.com', '123654789', 'male', 'patient'),
(5, 'Basem', 'basem', 'Basem', 'Fadi', 35, 'basem@gmail.com', '6543135', 'male', 'patient'),
(6, 'Jamal', '123', 'Jamal', 'Kamal', 52, 'jamal@gmail.com', '1563264', 'male', 'patient'),
(7, 'test', 'test', 'test', 'test', 22, 'test@gmail.com', '165161', 'male', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booked_appointment`
--
ALTER TABLE `booked_appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `booked_appointment`
--
ALTER TABLE `booked_appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
