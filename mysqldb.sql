-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 14, 2017 at 05:17 PM
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
  `nev` varchar(255) CHARACTER SET ucs2 COLLATE ucs2_hungarian_ci NOT NULL,
  `leiras` varchar(8191) CHARACTER SET ucs2 COLLATE ucs2_hungarian_ci NOT NULL,
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
(1, 'HTML5 Kurzus', 'Tanuld meg a legújabb HTML5 webes technológiákat.', 5, '2017-05-14', '2017-05-12', 1, 0),
(2, 'Java kurzus', 'Ismerd meg a Java programozás alapjait.', 11, '2017-05-14', '2017-05-12', 1, 1),
(3, 'C&#43;&#43;', '3 hetes alapozó C&#43;&#43; kurzus.', 6, '2017-05-14', '2017-05-13', 1, 0),
(4, 'XSS Prevention', 'Hogyan elözzük meg az <i><b>XSS támadásokat?</b></i>', 3, '2017-05-14', '2017-05-13', 1, 0);

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
(3, 2, '2017-05-14'),
(3, 3, '2017-05-14'),
(3, 4, '2017-05-14'),
(4, 2, '2017-05-14'),
(4, 3, '2017-05-13'),
(4, 5, '2017-05-14');

-- --------------------------------------------------------

--
-- Table structure for table `KurzusTartalmak`
--

CREATE TABLE `KurzusTartalmak` (
  `kurzus_id` int(10) UNSIGNED NOT NULL,
  `tartalom_id` int(10) UNSIGNED NOT NULL,
  `cim` varchar(255) CHARACTER SET ucs2 COLLATE ucs2_hungarian_ci NOT NULL,
  `tartalom` varchar(8191) CHARACTER SET ucs2 COLLATE ucs2_hungarian_ci NOT NULL,
  `letrehozva` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `KurzusTartalmak`
--

INSERT INTO `KurzusTartalmak` (`kurzus_id`, `tartalom_id`, `cim`, `tartalom`, `letrehozva`) VALUES
(1, 1, '1. Bevezetés', 'A HTML5 bevezet jó néhány új elemet (címkét) és tulajdonságot, amelyek a modern weblapokon jellemz?en alkalmazott szerkezetekre kínálnak új megoldást. Néhány változtatás szemantikai jelleg?, például az általánosan használt &lt;div&gt; és a soron belüli részek formázását biztosító &lt;span&gt; helyett a &lt;nav&gt; (a weboldal navigációs területe) és a &lt;footer&gt; (lábléc). Más elemek új funkciók elérését biztosítják szabványosított felületen, mint az &lt;audio&gt; és a &lt;video&gt; elemek.', '2017-05-01'),
(1, 5, '2. Gyakorlat', 'Hozzon létre egy...', '2017-05-14'),
(2, 6, '1. Bevezetés', 'Ne használj Javát.', '2017-05-14'),
(4, 7, '1. Bevezetés', 'Cross-Site Scripting (XSS) attacks are a type of injection, in which malicious scripts are injected into otherwise benign and trusted web sites. XSS attacks occur when an attacker uses a web application to send malicious code, generally in the form of a browser side script, to a different end user. Flaws that allow these attacks to succeed are quite widespread and occur anywhere a web application uses input from a user within the output it generates without validating or encoding it.<br /><br />\n\nAn attacker can use XSS? to send a malicious script to an unsuspecting user. The end user’s browser has no way to know that the script should not be trusted, and will execute the script. Because it thinks the script came from a trusted source, the malicious script can access any cookies, session tokens, or other sensitive information retained by the browser and used with that site. These scripts can even rewrite the content of the HTML page. For more details on the different types of XSS flaws, see: Types of Cross-Site Scripting. ', '2017-05-14'),
(3, 10, '1. Bevezetes', 'C&#43;&#43; &gt; C#', '2017-05-14');

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
-- Indexes for table `KurzusTartalmak`
--
ALTER TABLE `KurzusTartalmak`
  ADD PRIMARY KEY (`tartalom_id`),
  ADD KEY `kurzus_id` (`kurzus_id`);

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
  MODIFY `kurzus_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `KurzusTartalmak`
--
ALTER TABLE `KurzusTartalmak`
  MODIFY `tartalom_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
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

--
-- Constraints for table `KurzusTartalmak`
--
ALTER TABLE `KurzusTartalmak`
  ADD CONSTRAINT `FKbwsr0my5w66p8bj31qtujst30` FOREIGN KEY (`kurzus_id`) REFERENCES `Kurzus` (`kurzus_id`),
  ADD CONSTRAINT `KurzusTart_FK` FOREIGN KEY (`kurzus_id`) REFERENCES `Kurzus` (`kurzus_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
