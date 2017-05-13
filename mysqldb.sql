-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 14, 2017 at 12:29 AM
-- Server version: 5.7.18-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysqldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Kurzus`
--

CREATE TABLE `Kurzus` (
  `kurzus_id` int(10) UNSIGNED NOT NULL,
  `nev` varchar(255) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL,
  `leiras` varchar(8191) CHARACTER SET latin2 COLLATE latin2_hungarian_ci NOT NULL,
  `max_jelentkezok` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `indulas_datum` date NOT NULL,
  `letrehozva_datum` date NOT NULL,
  `letrehozo_user_id` int(10) UNSIGNED NOT NULL,
  `allapot` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Kurzus`
--

INSERT INTO `Kurzus` (`kurzus_id`, `nev`, `leiras`, `max_jelentkezok`, `indulas_datum`, `letrehozva_datum`, `letrehozo_user_id`, `allapot`) VALUES
(1, 'HTML5 Kurzu<b>&#39;&#34;;s</b>', 'Egy remek kis HTML5 kurzus mindenféle új technológiákkal', 5, '2017-05-13', '2017-05-12', 1, 0),
(2, 'Java Kurzus', 'Ismerd meg a Java programozás alapjait!', 11, '2017-05-13', '2017-05-12', 1, 1),
(3, 'C&#43;&#43; Kurzus', 'Egy 3 hetes C&#43;&#43; alapozó kurzus!', 6, '2017-05-13', '2017-05-13', 1, 0),
(4, 'XSS Prevention', '<b><i>XSS megelozése</i></b>', 3, '2017-05-13', '2017-05-13', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `KurzusJelentkezok`
--

CREATE TABLE `KurzusJelentkezok` (
  `kurzus_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `jelentkezes_datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `KurzusJelentkezok`
--

INSERT INTO `KurzusJelentkezok` (`kurzus_id`, `user_id`, `jelentkezes_datum`) VALUES
(1, 2, '2017-05-14'),
(1, 4, '2017-05-14'),
(3, 3, '2017-05-14'),
(3, 4, '2017-05-14'),
(4, 2, '2017-05-14'),
(4, 3, '2017-05-13'),
(4, 5, '2017-05-14');

-- --------------------------------------------------------

--
-- Table structure for table `UserRoles`
--

CREATE TABLE `UserRoles` (
  `user_id` int(11) UNSIGNED NOT NULL,
  `role` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UserRoles`
--

INSERT INTO `UserRoles` (`user_id`, `role`) VALUES
(1, 'ADMIN'),
(1, 'USER'),
(2, 'USER'),
(3, 'USER'),
(4, 'USER'),
(5, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `user_id` int(11) UNSIGNED NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`user_id`, `username`, `passwd`) VALUES
(1, 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg='),
(2, 'user1', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps='),
(3, 'user2', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps='),
(4, 'user3', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps='),
(5, 'user4', 'BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Kurzus`
--
ALTER TABLE `Kurzus`
  ADD PRIMARY KEY (`kurzus_id`);

--
-- Indexes for table `KurzusJelentkezok`
--
ALTER TABLE `KurzusJelentkezok`
  ADD PRIMARY KEY (`kurzus_id`,`user_id`),
  ADD KEY `kurzus_id` (`kurzus_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `UserRoles`
--
ALTER TABLE `UserRoles`
  ADD PRIMARY KEY (`user_id`,`role`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Kurzus`
--
ALTER TABLE `Kurzus`
  MODIFY `kurzus_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `KurzusJelentkezok`
--
ALTER TABLE `KurzusJelentkezok`
  ADD CONSTRAINT `FKgx5xyyx55eoxx93c99v0nfj0t` FOREIGN KEY (`kurzus_id`) REFERENCES `Kurzus` (`kurzus_id`),
  ADD CONSTRAINT `FKlbn8tqtmr9i1rxhw5dldxx9p3` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`),
  ADD CONSTRAINT `Kurzus_FK` FOREIGN KEY (`kurzus_id`) REFERENCES `Kurzus` (`kurzus_id`),
  ADD CONSTRAINT `User_FK` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
