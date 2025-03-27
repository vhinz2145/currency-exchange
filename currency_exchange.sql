-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2025 at 11:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `currency_exchange`
--

-- --------------------------------------------------------

--
-- Table structure for table `exchange_rates`
--

CREATE TABLE `exchange_rates` (
  `id` bigint(20) NOT NULL,
  `cut_off_date` date NOT NULL,
  `exchange_rate` decimal(38,2) NOT NULL,
  `source_currency` varchar(255) NOT NULL,
  `target_currency` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `exchange_rates`
--

INSERT INTO `exchange_rates` (`id`, `cut_off_date`, `exchange_rate`, `source_currency`, `target_currency`) VALUES
(1, '2024-03-19', 0.91, 'USD', 'EUR'),
(2, '2024-03-19', 57.28, 'USD', 'PHP'),
(3, '2024-03-19', 1.09, 'EUR', 'USD'),
(4, '2024-03-19', 62.65, 'EUR', 'PHP'),
(5, '2024-03-19', 0.02, 'PHP', 'USD'),
(6, '2024-03-19', 0.02, 'PHP', 'EUR'),
(7, '2025-03-19', 1.20, 'USD', 'EUR'),
(14, '2025-03-23', 1.09, 'USD', 'EUR'),
(15, '2025-03-10', 1.09, 'USD', 'PHP'),
(18, '2025-03-25', 0.92, 'USD', 'EUR'),
(26, '2025-03-08', 0.93, 'USD', 'EUR'),
(27, '2025-03-09', 0.93, 'USD', 'EUR'),
(28, '2025-03-09', 57.35, 'USD', 'PHP'),
(29, '2025-03-03', 0.02, 'PHP', 'USD'),
(32, '2025-03-15', 0.02, 'PHP', 'USD'),
(34, '2025-03-16', 0.02, 'PHP', 'USD'),
(35, '2025-03-16', 0.02, 'PHP', 'EUR'),
(37, '2025-03-16', 2.62, 'PHP', 'JPY'),
(38, '2025-03-16', 0.07, 'PHP', 'SAR'),
(39, '2025-03-16', 3.75, 'USD', 'SAR'),
(40, '2025-03-12', 3.75, 'USD', 'SAR'),
(42, '2025-03-12', 0.07, 'PHP', 'SAR'),
(43, '2025-05-12', 0.07, 'PHP', 'SAR'),
(44, '2024-05-12', 0.07, 'PHP', 'SAR'),
(45, '2024-04-12', 0.07, 'PHP', 'SAR'),
(46, '2023-04-12', 0.07, 'PHP', 'SAR'),
(47, '2023-04-19', 0.07, 'PHP', 'SAR'),
(48, '2023-04-19', 2.62, 'PHP', 'JPY'),
(49, '2023-04-19', 150.36, 'USD', 'JPY'),
(50, '2024-04-19', 150.36, 'USD', 'JPY'),
(51, '2025-04-19', 150.36, 'USD', 'JPY'),
(53, '2025-03-19', 57.35, 'USD', 'PHP'),
(59, '2025-03-25', 57.35, 'USD', 'PHP'),
(61, '2025-04-17', 150.36, 'USD', 'JPY'),
(62, '2025-02-17', 150.36, 'USD', 'JPY'),
(65, '2025-02-17', 7.26, 'USD', 'CNY'),
(84, '2025-02-20', 7.26, 'USD', 'CNY'),
(85, '2025-02-21', 7.26, 'USD', 'CNY'),
(86, '2025-02-25', 7.26, 'USD', 'CNY'),
(87, '2025-02-25', 0.05, 'JPY', 'CNY'),
(88, '2025-02-25', 0.38, 'JPY', 'PHP'),
(89, '2025-02-25', 0.01, 'JPY', 'EUR'),
(90, '2025-02-25', 0.01, 'JPY', 'USD'),
(91, '2025-02-25', 0.14, 'CNY', 'USD'),
(92, '2025-02-25', 0.13, 'CNY', 'EUR'),
(93, '2025-02-25', 0.52, 'CNY', 'SAR'),
(95, '2025-02-26', 0.52, 'CNY', 'SAR'),
(96, '2025-02-27', 0.52, 'CNY', 'SAR'),
(97, '2025-03-27', 0.52, 'CNY', 'SAR'),
(98, '2025-03-27', 20.66, 'CNY', 'JPY'),
(99, '2025-01-27', 0.02, 'PHP', 'USD'),
(100, '2025-01-01', 0.02, 'PHP', 'USD'),
(101, '2025-01-01', 2.62, 'PHP', 'JPY'),
(104, '2025-01-02', 2.62, 'PHP', 'JPY'),
(106, '2025-01-02', 0.07, 'PHP', 'SAR'),
(111, '2025-01-03', 0.02, 'PHP', 'USD'),
(115, '2025-01-04', 0.02, 'PHP', 'USD'),
(117, '2025-01-05', 0.02, 'PHP', 'USD'),
(121, '2025-01-06', 0.02, 'PHP', 'USD'),
(130, '2025-01-07', 0.02, 'PHP', 'USD'),
(131, '2025-01-09', 0.02, 'PHP', 'USD'),
(135, '2025-01-10', 0.02, 'PHP', 'USD'),
(138, '2025-01-11', 0.02, 'PHP', 'USD'),
(142, '2025-01-12', 0.02, 'PHP', 'USD'),
(150, '2025-01-16', 0.02, 'PHP', 'USD'),
(162, '2025-03-19', 0.02, 'PHP', 'USD'),
(163, '2025-03-19', 2.61, 'PHP', 'JPY'),
(164, '2025-03-19', 162.02, 'EUR', 'JPY'),
(165, '2025-03-19', 39.98, 'SAR', 'JPY'),
(172, '2025-03-20', 39.98, 'SAR', 'JPY'),
(175, '2025-03-21', 39.98, 'SAR', 'JPY'),
(177, '2025-03-21', 0.03, 'JPY', 'SAR'),
(178, '2025-03-21', 4.05, 'EUR', 'SAR'),
(179, '2025-03-27', 4.05, 'EUR', 'SAR'),
(181, '2025-03-28', 4.05, 'EUR', 'SAR'),
(184, '2025-03-24', 4.05, 'EUR', 'SAR'),
(193, '2025-03-26', 4.05, 'EUR', 'SAR'),
(195, '2025-03-26', 3.75, 'USD', 'SAR'),
(196, '2025-03-26', 149.94, 'USD', 'JPY'),
(201, '2025-03-26', 7.26, 'USD', 'CNY'),
(202, '2025-03-26', 24.00, 'USD', 'CUP'),
(203, '2025-03-26', 2.40, 'CUP', 'PHP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exchange_rates`
--
ALTER TABLE `exchange_rates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKifrgejrvnadfjr4bbp3frqm0b` (`cut_off_date`,`source_currency`,`target_currency`),
  ADD UNIQUE KEY `UK1yj0uh15pqb6hit15oje6l072` (`cut_off_date`,`source_currency`,`target_currency`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exchange_rates`
--
ALTER TABLE `exchange_rates`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=205;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
