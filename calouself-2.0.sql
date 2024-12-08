-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2024 at 03:28 PM
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
-- Database: `calouself`
--

-- --------------------------------------------------------

--
-- Table structure for table `msitem`
--

CREATE TABLE `msitem` (
  `Item_id` int(11) NOT NULL,
  `Item_name` varchar(50) NOT NULL,
  `Item_size` varchar(50) NOT NULL,
  `Item_price` varchar(50) NOT NULL,
  `Item_category` varchar(50) NOT NULL,
  `Item_status` varchar(50) NOT NULL,
  `Item_wishlist` varchar(50) NOT NULL,
  `Item_offer_status` varchar(50) NOT NULL,
  `Item_reason` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msitem`
--

INSERT INTO `msitem` (`Item_id`, `Item_name`, `Item_size`, `Item_price`, `Item_category`, `Item_status`, `Item_wishlist`, `Item_offer_status`, `Item_reason`) VALUES
(1, 'Baju Gembel', 'Medium', '20000', 'Baju', 'Approved', '-', 'NULL', NULL),
(2, 'Celana Gembel', 'Medium', '50000', 'Celana', 'Approved', '-', 'NULL', NULL),
(3, 'Topi Gembel', 'Large', '10000', 'Aksesoris', 'Declined', '-', 'NULL', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `msoffer`
--

CREATE TABLE `msoffer` (
  `Offer_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  `Item_id` int(11) NOT NULL,
  `Seller_id` int(11) NOT NULL,
  `Offer_status` varchar(50) NOT NULL,
  `Offer_reason` varchar(100) DEFAULT NULL,
  `Offer_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msoffer`
--

INSERT INTO `msoffer` (`Offer_id`, `User_id`, `Item_id`, `Seller_id`, `Offer_status`, `Offer_reason`, `Offer_price`) VALUES
(1, 1, 1, 1, 'Declined', 'jelek lu', 500);

-- --------------------------------------------------------

--
-- Table structure for table `mstransaction`
--

CREATE TABLE `mstransaction` (
  `Transaction_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  `Item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mstransaction`
--

INSERT INTO `mstransaction` (`Transaction_id`, `User_id`, `Item_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `msuser`
--

CREATE TABLE `msuser` (
  `User_id` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Phone_number` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msuser`
--

INSERT INTO `msuser` (`User_id`, `Username`, `Password`, `Phone_number`, `Address`, `Role`) VALUES
(1, 'Admin', 'Admin', '0', 'Office', 'Admin'),
(2, 'Hanjit', 'cobain', 'cobain', 'cobain', 'Buyer'),
(3, 'hanjit', 'hanjit', 'hanjit', 'hanjit', 'hanjit');

-- --------------------------------------------------------

--
-- Table structure for table `mswishlist`
--

CREATE TABLE `mswishlist` (
  `Wishlist_id` int(11) NOT NULL,
  `Item_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `msitem`
--
ALTER TABLE `msitem`
  ADD PRIMARY KEY (`Item_id`);

--
-- Indexes for table `msoffer`
--
ALTER TABLE `msoffer`
  ADD PRIMARY KEY (`Offer_id`),
  ADD KEY `FK_offeritem` (`Item_id`),
  ADD KEY `FK_offerseller` (`User_id`);

--
-- Indexes for table `mstransaction`
--
ALTER TABLE `mstransaction`
  ADD PRIMARY KEY (`Transaction_id`),
  ADD KEY `User_id` (`User_id`),
  ADD KEY `Item_id` (`Item_id`);

--
-- Indexes for table `msuser`
--
ALTER TABLE `msuser`
  ADD PRIMARY KEY (`User_id`);

--
-- Indexes for table `mswishlist`
--
ALTER TABLE `mswishlist`
  ADD PRIMARY KEY (`Wishlist_id`),
  ADD KEY `Item_id` (`Item_id`),
  ADD KEY `User_id` (`User_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `msitem`
--
ALTER TABLE `msitem`
  MODIFY `Item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `msoffer`
--
ALTER TABLE `msoffer`
  MODIFY `Offer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mstransaction`
--
ALTER TABLE `mstransaction`
  MODIFY `Transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `msuser`
--
ALTER TABLE `msuser`
  MODIFY `User_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mswishlist`
--
ALTER TABLE `mswishlist`
  MODIFY `Wishlist_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `msoffer`
--
ALTER TABLE `msoffer`
  ADD CONSTRAINT `FK_offerbuyer` FOREIGN KEY (`User_id`) REFERENCES `msuser` (`User_id`),
  ADD CONSTRAINT `FK_offeritem` FOREIGN KEY (`Item_id`) REFERENCES `msitem` (`Item_id`),
  ADD CONSTRAINT `FK_offerseller` FOREIGN KEY (`User_id`) REFERENCES `msuser` (`User_id`);

--
-- Constraints for table `mstransaction`
--
ALTER TABLE `mstransaction`
  ADD CONSTRAINT `mstransaction_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `msuser` (`User_id`),
  ADD CONSTRAINT `mstransaction_ibfk_2` FOREIGN KEY (`Item_id`) REFERENCES `msitem` (`Item_id`);

--
-- Constraints for table `mswishlist`
--
ALTER TABLE `mswishlist`
  ADD CONSTRAINT `mswishlist_ibfk_1` FOREIGN KEY (`Item_id`) REFERENCES `msitem` (`Item_id`),
  ADD CONSTRAINT `mswishlist_ibfk_2` FOREIGN KEY (`User_id`) REFERENCES `msuser` (`User_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
