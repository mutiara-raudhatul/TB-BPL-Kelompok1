-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2020 at 02:36 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `sku` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `stok` int(50) NOT NULL,
  `harga_beli` int(50) NOT NULL,
  `harga_jual` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`sku`, `nama`, `stok`, `harga_beli`, `harga_jual`) VALUES
('PRD-001', 'Chitato Potato Chips', 20, 110000, 120000),
('PRD-002', 'Lays Potato Chips', 15, 115000, 125000),
('PRD-003', 'Gulaku Premium', 10, 271000, 279000),
('PRD-004', 'Gulaku Tebu', 8, 243000, 255000),
('PRD-005', 'Le Mineral 600 ml', 25, 32000, 36000),
('PRD-006', 'Le Mineral 1500 ml', 30, 48000, 53000),
('PRD-007', 'Pop Mie', 15, 90000, 98000),
('PRD-008', 'Teh Pucuk Harum', 10, 52000, 58000),
('PRD-009', 'Larutan Kaleng Cap Badak', 6, 110000, 120000),
('PRD-010', 'Roma Kelapa', 5, 178000, 188000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `noresi` varchar(30) NOT NULL,
  `tanggal` date NOT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`noresi`, `tanggal`, `username`) VALUES
('RS2020001', '2020-01-14', 'kasir-001'),
('RS2020002', '2020-02-13', 'kasir-001'),
('RS2020003', '2020-02-26', 'kasir-001'),
('RS2020004', '2020-03-09', 'kasir-001'),
('RS2020005', '2020-03-11', 'kasir-001'),
('RS2020006', '2020-03-18', 'kasir-001'),
('RS2020007', '2020-03-18', 'kasir-001');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_detail`
--

CREATE TABLE `transaksi_detail` (
  `id` int(30) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `noresi` varchar(30) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `harga` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`id`, `sku`, `noresi`, `jumlah`, `harga`) VALUES
(1, 'PRD-001', 'RS2020001', 1, 120000),
(2, 'PRD-001', 'RS2020002', 2, 240000),
(3, 'PRD-005', 'RS2020003', 2, 72000),
(4, 'PRD-004', 'RS2020004', 1, 255000),
(5, 'PRD-007', 'RS2020005', 2, 196000),
(6, 'PRD-001', 'RS2020006', 3, 360000),
(7, 'PRD-001', 'RS2020007', 3, 360000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `login_terakhir` datetime DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `login_terakhir`, `email`, `password`) VALUES
('admin-app', NULL, 'admin-app@gmail.com', 'admin-app'),
('kasir-001', NULL, 'kasir-001@gmail.com', 'kasir-001');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`sku`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`noresi`),
  ADD KEY `transaksi_ibfk_1` (`username`);

--
-- Indexes for table `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sku` (`sku`),
  ADD KEY `noresi` (`noresi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD CONSTRAINT `transaksi_detail_ibfk_1` FOREIGN KEY (`sku`) REFERENCES `barang` (`sku`),
  ADD CONSTRAINT `transaksi_detail_ibfk_2` FOREIGN KEY (`noresi`) REFERENCES `transaksi` (`noresi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
