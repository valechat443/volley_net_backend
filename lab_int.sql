-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1


-- Creato il: Gen 04, 2024 alle 15:32



-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lab_int`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `bet`
--

CREATE TABLE `bet` (
  `id_bet` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `bet`
--

INSERT INTO `bet` (`id_bet`, `id_user`, `id_game`, `id_team`) VALUES
(2, 3, 148524, 737),
(3, 3, 148463, 741),
(4, 3, 148464, 747);

-- --------------------------------------------------------

--
-- Struttura della tabella `country`
--

CREATE TABLE `country` (
  `id_country` int(55) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(2) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `country`
--

INSERT INTO `country` (`id_country`, `name`, `code`, `flag`) VALUES
(25, 'Italy', 'IT', 'https://media-4.api-sports.io/flags/it.svg');

-- --------------------------------------------------------

--
-- Struttura della tabella `game`
--

CREATE TABLE `game` (
  `id_game` int(11) NOT NULL,
  `id_league` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `timezone` varchar(3) NOT NULL,
  `status` varchar(50) NOT NULL,
  `home_odds` double DEFAULT NULL,
  `away_odds` double DEFAULT NULL,
  `week` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `game`
--

INSERT INTO `game` (`id_game`, `id_league`, `date`, `time`, `timezone`, `status`, `home_odds`, `away_odds`, `week`) VALUES
(148403, 97, '2023-10-22', '16:00:00', 'UTC', 'Finished', NULL, NULL, 1),
(148404, 97, '2023-10-22', '13:45:00', 'UTC', 'Finished', NULL, NULL, 1),
(148405, 97, '2023-10-22', '18:00:00', 'UTC', 'Finished', NULL, NULL, 1),
(148406, 97, '2023-10-22', '16:00:00', 'UTC', 'Finished', NULL, NULL, 1),
(148407, 97, '2023-10-22', '14:00:00', 'UTC', 'Finished', NULL, NULL, 1),
(148408, 97, '2023-10-22', '16:00:00', 'UTC', 'Finished', NULL, NULL, 1),
(148409, 97, '2023-10-29', '20:00:00', 'UTC', 'Finished', NULL, NULL, 2),
(148410, 97, '2023-10-29', '15:30:00', 'UTC', 'Finished', NULL, NULL, 2),
(148411, 97, '2023-10-29', '18:30:00', 'UTC', 'Finished', NULL, NULL, 2),
(148412, 97, '2023-10-29', '14:00:00', 'UTC', 'Finished', NULL, NULL, 2),
(148413, 97, '2023-10-28', '18:30:00', 'UTC', 'Finished', NULL, NULL, 2),
(148414, 97, '2023-10-28', '16:00:00', 'UTC', 'Finished', NULL, NULL, 2),
(148415, 97, '2023-11-05', '15:00:00', 'UTC', 'Finished', NULL, NULL, 3),
(148416, 97, '2023-11-05', '18:30:00', 'UTC', 'Finished', NULL, NULL, 3),
(148417, 97, '2023-11-05', '16:00:00', 'UTC', 'Finished', NULL, NULL, 3),
(148418, 97, '2023-11-05', '17:00:00', 'UTC', 'Finished', NULL, NULL, 3),
(148419, 97, '2023-11-05', '17:30:00', 'UTC', 'Finished', NULL, NULL, 3),
(148420, 97, '2023-11-04', '17:00:00', 'UTC', 'Finished', NULL, NULL, 3),
(148421, 97, '2023-11-12', '15:00:00', 'UTC', 'Finished', NULL, NULL, 4),
(148422, 97, '2023-11-12', '17:00:00', 'UTC', 'Finished', NULL, NULL, 4),
(148423, 97, '2023-11-11', '19:30:00', 'UTC', 'Finished', NULL, NULL, 4),
(148424, 97, '2023-11-12', '16:00:00', 'UTC', 'Finished', NULL, NULL, 4),
(148425, 97, '2023-11-12', '17:00:00', 'UTC', 'Finished', NULL, NULL, 4),
(148426, 97, '2023-11-12', '19:00:00', 'UTC', 'Finished', NULL, NULL, 4),
(148427, 97, '2023-11-15', '19:30:00', 'UTC', 'Finished', NULL, NULL, 5),
(148428, 97, '2023-11-15', '19:30:00', 'UTC', 'Finished', NULL, NULL, 5),
(148429, 97, '2023-11-15', '19:30:00', 'UTC', 'Finished', NULL, NULL, 5),
(148430, 97, '2023-11-15', '19:30:00', 'UTC', 'Finished', NULL, NULL, 5),
(148431, 97, '2023-11-15', '17:00:00', 'UTC', 'Finished', NULL, NULL, 5),
(148432, 97, '2023-11-08', '19:30:00', 'UTC', 'Finished', NULL, NULL, 5),
(148433, 97, '2023-11-18', '17:00:00', 'UTC', 'Finished', NULL, NULL, 6),
(148434, 97, '2023-11-19', '17:00:00', 'UTC', 'Finished', NULL, NULL, 6),
(148435, 97, '2023-11-19', '16:30:00', 'UTC', 'Finished', NULL, NULL, 6),
(148436, 97, '2023-11-19', '15:00:00', 'UTC', 'Finished', NULL, NULL, 6),
(148437, 97, '2023-11-19', '17:00:00', 'UTC', 'Finished', NULL, NULL, 6),
(148438, 97, '2023-11-19', '17:00:00', 'UTC', 'Finished', NULL, NULL, 6),
(148439, 97, '2023-11-26', '19:30:00', 'UTC', 'Finished', NULL, NULL, 7),
(148440, 97, '2023-11-26', '17:00:00', 'UTC', 'Finished', NULL, NULL, 7),
(148441, 97, '2023-11-26', '17:00:00', 'UTC', 'Finished', NULL, NULL, 7),
(148442, 97, '2023-11-26', '19:00:00', 'UTC', 'Finished', NULL, NULL, 7),
(148443, 97, '2023-11-26', '17:00:00', 'UTC', 'Finished', NULL, NULL, 7),
(148444, 97, '2023-11-25', '17:00:00', 'UTC', 'Finished', NULL, NULL, 7),
(148445, 97, '2023-12-03', '17:00:00', 'UTC', 'Finished', NULL, NULL, 8),
(148446, 97, '2023-12-04', '19:00:00', 'UTC', 'Finished', NULL, NULL, 8),
(148447, 97, '2023-12-03', '17:00:00', 'UTC', 'Finished', NULL, NULL, 8),
(148448, 97, '2023-12-02', '19:30:00', 'UTC', 'Finished', NULL, NULL, 8),
(148449, 97, '2023-12-03', '19:30:00', 'UTC', 'Finished', NULL, NULL, 8),
(148450, 97, '2023-12-02', '17:00:00', 'UTC', 'Finished', NULL, NULL, 8),
(148451, 97, '2023-12-08', '19:30:00', 'UTC', 'Finished', NULL, NULL, 9),
(148452, 97, '2023-12-10', '19:30:00', 'UTC', 'Finished', NULL, NULL, 9),
(148453, 97, '2023-12-10', '17:00:00', 'UTC', 'Finished', NULL, NULL, 9),
(148454, 97, '2023-11-22', '19:30:00', 'UTC', 'Finished', NULL, NULL, 9),
(148455, 97, '2023-12-08', '17:00:00', 'UTC', 'Finished', NULL, NULL, 9),
(148456, 97, '2023-12-10', '15:30:00', 'UTC', 'Finished', NULL, NULL, 9),
(148457, 97, '2023-12-17', '15:00:00', 'UTC', 'Finished', NULL, NULL, 10),
(148458, 97, '2023-12-17', '14:45:00', 'UTC', 'Finished', NULL, NULL, 10),
(148459, 97, '2023-12-17', '17:00:00', 'UTC', 'Finished', NULL, NULL, 10),
(148460, 97, '2023-12-17', '17:00:00', 'UTC', 'Finished', NULL, NULL, 10),
(148461, 97, '2023-12-17', '15:30:00', 'UTC', 'Finished', NULL, NULL, 10),
(148462, 97, '2023-12-16', '17:00:00', 'UTC', 'Finished', NULL, NULL, 10),
(148463, 97, '2023-12-26', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148464, 97, '2023-12-26', '14:30:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148465, 97, '2023-12-26', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148466, 97, '2023-12-26', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148467, 97, '2023-12-26', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148468, 97, '2023-12-26', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 11),
(148469, 97, '2023-12-30', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148470, 97, '2023-12-29', '18:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148471, 97, '2023-12-30', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148472, 97, '2023-12-30', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148473, 97, '2023-12-30', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148474, 97, '2023-12-30', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 12),
(148475, 97, '2024-01-06', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148476, 97, '2024-01-07', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148477, 97, '2024-01-07', '17:15:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148478, 97, '2024-01-06', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148479, 97, '2024-01-07', '16:00:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148480, 97, '2024-01-07', '19:00:00', 'UTC', 'Not Started', NULL, NULL, 13),
(148481, 97, '2024-01-14', '16:00:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148482, 97, '2024-01-14', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148483, 97, '2024-01-14', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148484, 97, '2024-01-14', '16:45:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148485, 97, '2024-01-14', '16:00:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148486, 97, '2024-01-14', '15:00:00', 'UTC', 'Not Started', NULL, NULL, 14),
(148487, 97, '2024-01-21', '15:00:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148488, 97, '2024-01-21', '15:30:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148489, 97, '2024-01-21', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148490, 97, '2024-01-20', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148491, 97, '2024-01-21', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148492, 97, '2024-01-21', '18:00:00', 'UTC', 'Not Started', NULL, NULL, 15),
(148493, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148494, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148495, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148496, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148497, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148498, 97, '2024-01-24', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 16),
(148499, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148500, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148501, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148502, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148503, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148504, 97, '2024-02-04', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 17),
(148505, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148506, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148507, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148508, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148509, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148510, 97, '2024-02-11', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 18),
(148511, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148512, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148513, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148514, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148515, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148516, 97, '2024-02-14', '19:30:00', 'UTC', 'Not Started', NULL, NULL, 19),
(148517, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148518, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148519, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148520, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148521, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148522, 97, '2024-02-18', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 20),
(148523, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148524, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148525, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148526, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148527, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148528, 97, '2024-02-25', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 21),
(148529, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22),
(148530, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22),
(148531, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22),
(148532, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22),
(148533, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22),
(148534, 97, '2024-03-03', '17:00:00', 'UTC', 'Not Started', NULL, NULL, 22);

-- --------------------------------------------------------

--
-- Struttura della tabella `group`
--

CREATE TABLE `group` (
  `id_group` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `group`
--

INSERT INTO `group` (`id_group`, `group_name`) VALUES
(0, 'Regular Season'),
(1, 'Group 1'),
(2, 'Group 2');

-- --------------------------------------------------------

--
-- Struttura della tabella `league`
--

CREATE TABLE `league` (
  `id_league` int(11) NOT NULL,
  `id_country` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `league`
--

INSERT INTO `league` (`id_league`, `id_country`, `name`, `type`, `logo`, `start_date`, `end_date`) VALUES
(88, 25, 'Serie A2', 'League', 'https://media-4.api-sports.io/volley/leagues/88.png', '2023-10-14', '2024-03-24'),
(89, 25, 'Serie A1 Women', 'League', 'https://media-4.api-sports.io/volley/leagues/89.png', '2023-10-07', '2024-03-24'),
(90, 25, 'Serie A2 Women', 'League', 'https://media-4.api-sports.io/volley/leagues/90.png', '2023-10-08', '2024-01-21'),
(91, 25, 'Super Cup', 'Cup', 'https://media-4.api-sports.io/volley/leagues/91.png', '2023-10-31', '2023-11-01'),
(92, 25, 'Coppa Italia A1 Women', 'Cup', 'https://media-4.api-sports.io/volley/leagues/92.png', '2023-01-24', '2023-01-29'),
(93, 25, 'Coppa Italia A1', 'Cup', 'https://media.api-sports.io/volley/leagues/93.png', '2024-01-03', '2024-01-04'),
(94, 25, 'Coppa Italia A2 Women', 'Cup', 'https://media-4.api-sports.io/volley/leagues/94.png', '2024-01-10', '2024-01-10'),
(95, 25, 'Super Cup Women', 'Cup', 'https://media-4.api-sports.io/volley/leagues/95.png', '2023-10-28', '2023-10-28'),
(97, 25, 'SuperLega', 'League', 'https://media-4.api-sports.io/volley/leagues/97.png', '2023-10-22', '2024-03-03');

-- --------------------------------------------------------

--
-- Struttura della tabella `period`
--

CREATE TABLE `period` (
  `id_period` int(11) NOT NULL,
  `id_score` int(11) NOT NULL,
  `points` int(2) DEFAULT NULL,
  `period_number` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `period`
--

INSERT INTO `period` (`id_period`, `id_score`, `points`, `period_number`) VALUES
(3, 1, 25, '1'),
(4, 1, 12, '2'),
(5, 1, 21, '3'),
(6, 1, 26, '4'),
(7, 1, 15, '5'),
(8, 2, 19, '1'),
(9, 2, 25, '2'),
(10, 2, 25, '3'),
(11, 2, 24, '4'),
(12, 2, 12, '5');

-- --------------------------------------------------------

--
-- Struttura della tabella `prefered_team`
--

CREATE TABLE `prefered_team` (
  `id_prefered_team` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `score`
--

CREATE TABLE `score` (
  `id_score` int(11) NOT NULL,
  `id_team` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `home` tinyint(1) NOT NULL DEFAULT 0,
  `sets` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `score`
--

INSERT INTO `score` (`id_score`, `id_team`, `id_game`, `home`, `sets`) VALUES
(1, 738, 148404, 1, 3),
(2, 742, 148404, 0, 2),
(3, 751, 148407, 1, 1),
(4, 747, 148407, 0, 3),
(5, 741, 148403, 1, 2),
(6, 740, 148403, 0, 3),
(7, 745, 148406, 1, 3),
(8, 744, 148406, 0, 0),
(9, 737, 148408, 1, 0),
(10, 743, 148408, 0, 3),
(12, 747, 148414, 1, 2),
(13, 738, 148414, 0, 3),
(14, 740, 148413, 1, 3),
(15, 751, 148413, 0, 2),
(16, 744, 148412, 1, 0),
(17, 737, 148412, 0, 3),
(18, 742, 148410, 1, 0),
(19, 745, 148410, 0, 3),
(20, 743, 148411, 1, 0),
(23, 741, 148420, 1, 3),
(24, 742, 148420, 0, 0),
(25, 745, 148415, 1, 3),
(27, 751, 148417, 1, 2),
(28, 744, 148417, 0, 3),
(29, 738, 148418, 1, 0),
(30, 743, 148418, 0, 3),
(31, 737, 148419, 1, 3),
(33, 747, 148416, 1, 0),
(34, 740, 148416, 0, 3),
(35, 747, 148432, 1, 1),
(36, 743, 148432, 0, 3),
(37, 740, 148423, 1, 3),
(38, 745, 148423, 0, 1),
(39, 744, 148421, 1, 3),
(40, 741, 148421, 0, 2),
(41, 743, 148424, 1, 3),
(42, 751, 148424, 0, 0),
(44, 742, 148425, 1, 3),
(45, 737, 148425, 0, 0),
(47, 751, 148431, 1, 1),
(49, 737, 148427, 1, 3),
(51, 742, 148428, 1, 1),
(52, 740, 148428, 0, 3),
(53, 738, 148429, 1, 3),
(54, 744, 148429, 0, 1),
(55, 745, 148430, 1, 3),
(56, 741, 148430, 0, 1),
(57, 744, 148433, 1, 3),
(58, 747, 148433, 0, 1),
(59, 738, 148436, 1, 0),
(60, 737, 148436, 0, 3),
(62, 740, 148434, 1, 3),
(63, 743, 148434, 0, 1),
(64, 741, 148437, 1, 3),
(65, 751, 148437, 0, 2),
(68, 741, 148444, 1, 1),
(69, 738, 148444, 0, 3),
(70, 740, 148440, 1, 3),
(72, 747, 148441, 1, 0),
(74, 737, 148443, 1, 3),
(75, 751, 148443, 0, 2),
(76, 742, 148442, 1, 3),
(77, 744, 148442, 0, 0),
(78, 743, 148439, 1, 3),
(79, 745, 148439, 0, 2),
(80, 751, 148450, 1, 2),
(81, 738, 148450, 0, 3),
(83, 737, 148445, 1, 0),
(84, 740, 148445, 0, 3),
(85, 744, 148447, 1, 3),
(87, 745, 148449, 1, 3),
(88, 747, 148449, 0, 0),
(89, 743, 148446, 1, 0),
(90, 742, 148446, 0, 3),
(91, 741, 148455, 1, 1),
(92, 737, 148455, 0, 3),
(93, 751, 148451, 1, 1),
(94, 745, 148451, 0, 3),
(96, 738, 148453, 1, 3),
(97, 740, 148453, 0, 1),
(98, 747, 148452, 1, 3),
(99, 742, 148452, 0, 2),
(101, 740, 148458, 1, 3),
(103, 745, 148457, 1, 2),
(104, 737, 148457, 0, 3),
(105, 742, 148461, 1, 3),
(106, 751, 148461, 0, 2),
(107, 747, 148459, 1, 3),
(108, 741, 148459, 0, 0),
(109, 743, 148460, 1, 3),
(110, 744, 148460, 0, 0),
(111, 737, 148464, 1, NULL),
(112, 747, 148464, 0, NULL),
(113, 741, 148463, 1, NULL),
(114, 743, 148463, 0, NULL),
(115, 738, 148465, 1, NULL),
(116, 745, 148465, 0, NULL),
(117, 744, 148466, 1, NULL),
(118, 740, 148466, 0, NULL),
(120, 751, 148468, 1, NULL),
(122, 747, 148470, 1, NULL),
(123, 751, 148470, 0, NULL),
(124, 740, 148469, 1, NULL),
(125, 741, 148469, 0, NULL),
(127, 744, 148472, 1, NULL),
(128, 745, 148472, 0, NULL),
(129, 743, 148473, 1, NULL),
(130, 737, 148473, 0, NULL),
(131, 742, 148474, 1, NULL),
(132, 738, 148474, 0, NULL),
(133, 737, 148475, 1, NULL),
(134, 744, 148475, 0, NULL),
(135, 745, 148478, 1, NULL),
(136, 742, 148478, 0, NULL),
(137, 751, 148479, 1, NULL),
(138, 740, 148479, 0, NULL),
(139, 738, 148476, 1, NULL),
(140, 747, 148476, 0, NULL),
(142, 741, 148480, 1, NULL),
(145, 744, 148481, 1, NULL),
(146, 751, 148481, 0, NULL),
(147, 742, 148485, 1, NULL),
(148, 741, 148485, 0, NULL),
(149, 743, 148484, 1, NULL),
(150, 738, 148484, 0, NULL),
(152, 740, 148483, 1, NULL),
(153, 747, 148483, 0, NULL),
(154, 738, 148490, 1, NULL),
(156, 745, 148487, 1, NULL),
(157, 740, 148487, 0, NULL),
(158, 751, 148488, 1, NULL),
(159, 743, 148488, 0, NULL),
(160, 747, 148489, 1, NULL),
(162, 737, 148491, 1, NULL),
(163, 742, 148491, 0, NULL),
(164, 741, 148492, 1, NULL),
(165, 744, 148492, 0, NULL),
(167, 741, 148494, 1, NULL),
(168, 745, 148494, 0, NULL),
(169, 743, 148495, 1, NULL),
(170, 747, 148495, 0, NULL),
(171, 744, 148496, 1, NULL),
(172, 738, 148496, 0, NULL),
(174, 740, 148498, 1, NULL),
(175, 742, 148498, 0, NULL),
(176, 745, 148499, 1, NULL),
(178, 747, 148500, 1, NULL),
(179, 744, 148500, 0, NULL),
(180, 751, 148501, 1, NULL),
(181, 741, 148501, 0, NULL),
(182, 743, 148502, 1, NULL),
(183, 740, 148502, 0, NULL),
(184, 742, 148503, 1, NULL),
(186, 737, 148504, 1, NULL),
(187, 738, 148504, 0, NULL),
(189, 738, 148506, 1, NULL),
(190, 741, 148506, 0, NULL),
(191, 744, 148507, 1, NULL),
(192, 742, 148507, 0, NULL),
(194, 745, 148509, 1, NULL),
(195, 743, 148509, 0, NULL),
(196, 751, 148510, 1, NULL),
(197, 737, 148510, 0, NULL),
(199, 741, 148512, 1, NULL),
(201, 742, 148513, 1, NULL),
(202, 743, 148513, 0, NULL),
(203, 738, 148514, 1, NULL),
(204, 751, 148514, 0, NULL),
(205, 740, 148515, 1, NULL),
(206, 737, 148515, 0, NULL),
(207, 747, 148516, 1, NULL),
(208, 745, 148516, 0, NULL),
(209, 745, 148517, 1, NULL),
(210, 751, 148517, 0, NULL),
(211, 740, 148518, 1, NULL),
(212, 738, 148518, 0, NULL),
(213, 742, 148519, 1, NULL),
(214, 747, 148519, 0, NULL),
(215, 744, 148520, 1, NULL),
(217, 743, 148521, 1, NULL),
(219, 737, 148522, 1, NULL),
(220, 741, 148522, 0, NULL),
(221, 741, 148523, 1, NULL),
(222, 747, 148523, 0, NULL),
(223, 737, 148524, 1, NULL),
(224, 745, 148524, 0, NULL),
(225, 738, 148525, 1, NULL),
(227, 744, 148526, 1, NULL),
(228, 743, 148526, 0, NULL),
(230, 751, 148528, 1, NULL),
(231, 742, 148528, 0, NULL),
(233, 742, 148530, 1, NULL),
(235, 743, 148531, 1, NULL),
(236, 741, 148531, 0, NULL),
(237, 745, 148532, 1, NULL),
(238, 738, 148532, 0, NULL),
(239, 740, 148533, 1, NULL),
(240, 744, 148533, 0, NULL),
(241, 747, 148534, 1, NULL),
(242, 737, 148534, 0, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `season`
--

CREATE TABLE `season` (
  `id_season` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `season`
--

INSERT INTO `season` (`id_season`, `year`) VALUES
(2022, 2022),
(2023, 2023);

-- --------------------------------------------------------

--
-- Struttura della tabella `standing`
--

CREATE TABLE `standing` (
  `id_standing` int(11) NOT NULL,
  `id_team_season` int(11) NOT NULL,
  `id_group` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `form` varchar(5) DEFAULT NULL,
  `zona` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `standing`
--

INSERT INTO `standing` (`id_standing`, `id_team_season`, `id_group`, `position`, `points`, `form`, `zona`) VALUES
(15, 11, 0, 1, 31, 'WWWLW', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(16, 8, 0, 3, 27, 'WWLWW', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(17, 2, 0, 4, 22, 'WLWWL', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(18, 3, 0, 5, 21, 'WWWLW', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(19, 5, 0, 6, 20, 'LLWWL', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(20, 4, 0, 7, 17, 'LLWWW', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(21, 12, 0, 8, 17, 'WWWWL', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(22, 6, 0, 9, 10, 'LLLWL', 'SuperLega (Losers stage: )'),
(23, 1, 0, 10, 10, 'LWLLL', 'SuperLega (Losers stage: )'),
(24, 10, 0, 11, 9, 'LWLLL', 'SuperLega (Losers stage: )'),
(25, 13, 0, 2, 28, 'WLLWW', 'Promotion - SuperLega (Play Offs: Quarter-finals)'),
(26, 14, 0, 12, 4, 'LLLLL', 'Relegation - Serie A2');

-- --------------------------------------------------------

--
-- Struttura della tabella `statistic`
--

CREATE TABLE `statistic` (
  `id_statistic` int(11) NOT NULL,
  `id_team_season` int(11) NOT NULL,
  `played_home` int(3) NOT NULL DEFAULT 0,
  `played_away` int(3) NOT NULL DEFAULT 0,
  `wins_home` int(3) NOT NULL DEFAULT 0,
  `wins_away` int(3) NOT NULL DEFAULT 0,
  `losses_home` int(3) NOT NULL DEFAULT 0,
  `losses_away` int(3) NOT NULL DEFAULT 0,
  `draws_home` int(3) NOT NULL DEFAULT 0,
  `draws_away` int(3) NOT NULL DEFAULT 0,
  `for_goals_home` int(3) NOT NULL DEFAULT 0,
  `for_goals_away` int(3) NOT NULL DEFAULT 0,
  `against_goals_home` int(3) NOT NULL DEFAULT 0,
  `against_goals_away` int(3) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `statistic`
--

INSERT INTO `statistic` (`id_statistic`, `id_team_season`, `played_home`, `played_away`, `wins_home`, `wins_away`, `losses_home`, `losses_away`, `draws_home`, `draws_away`, `for_goals_home`, `for_goals_away`, `against_goals_home`, `against_goals_away`) VALUES
(1, 11, 6, 6, 6, 5, 0, 1, 0, 0, 18, 16, 5, 6),
(2, 8, 5, 7, 4, 4, 1, 3, 0, 0, 14, 17, 4, 11),
(3, 2, 6, 6, 3, 5, 3, 1, 0, 0, 11, 15, 13, 7),
(4, 3, 6, 6, 4, 3, 2, 3, 0, 0, 13, 13, 8, 12),
(5, 5, 6, 6, 3, 4, 3, 2, 0, 0, 10, 14, 11, 8),
(6, 4, 6, 6, 3, 4, 3, 2, 0, 0, 9, 13, 13, 13),
(7, 12, 7, 5, 3, 3, 4, 2, 0, 0, 12, 10, 15, 10),
(8, 6, 6, 6, 3, 1, 3, 5, 0, 0, 10, 4, 13, 17),
(9, 1, 6, 6, 3, 0, 3, 6, 0, 0, 13, 5, 12, 18),
(10, 10, 6, 6, 1, 0, 5, 6, 0, 0, 10, 9, 16, 18),
(11, 13, 6, 6, 5, 4, 1, 2, 0, 0, 17, 15, 7, 7),
(13, 14, 6, 6, 1, 0, 5, 6, 0, 0, 8, 2, 16, 18);

-- --------------------------------------------------------

--
-- Struttura della tabella `team`
--

CREATE TABLE `team` (
  `id_team` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `national` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `team`
--

INSERT INTO `team` (`id_team`, `name`, `logo`, `national`) VALUES
(688, 'BCC-NEP Castellana', 'https://media-4.api-sports.io/volley/teams/688.png', 0),
(689, 'Bergamo', 'https://media-4.api-sports.io/volley/teams/689.png', 0),
(690, 'Brescia', 'https://media-4.api-sports.io/volley/teams/690.png', 0),
(691, 'Cantu', 'https://media-4.api-sports.io/volley/teams/691.png', 0),
(692, 'Cuneo Volley', 'https://media-4.api-sports.io/volley/teams/692.png', 0),
(693, 'HRK Motta di Livenza', 'https://media-4.api-sports.io/volley/teams/693.png', 0),
(694, 'Lagonegro', 'https://media-4.api-sports.io/volley/teams/694.png', 0),
(695, 'Lupi Santa Croce', 'https://media-4.api-sports.io/volley/teams/695.png', 0),
(698, 'Porto Viro', 'https://media-4.api-sports.io/volley/teams/698.png', 0),
(699, 'Reggio Emilia', 'https://media-4.api-sports.io/volley/teams/699.png', 0),
(700, 'Siena', 'https://media-4.api-sports.io/volley/teams/700.png', 0),
(701, 'Bergamo W', 'https://media-4.api-sports.io/volley/teams/701.png', 0),
(702, 'Casalmaggiore W', 'https://media-4.api-sports.io/volley/teams/702.png', 0),
(703, 'Chieri &apos;76 W', 'https://media-4.api-sports.io/volley/teams/703.png', 0),
(704, 'Conegliano W', 'https://media-4.api-sports.io/volley/teams/704.png', 0),
(705, 'Cuneo W', 'https://media-4.api-sports.io/volley/teams/705.png', 0),
(706, 'Firenze W', 'https://media-4.api-sports.io/volley/teams/706.png', 0),
(708, 'Novara W', 'https://media-4.api-sports.io/volley/teams/708.png', 0),
(710, 'Roma W', 'https://media-4.api-sports.io/volley/teams/710.png', 0),
(711, 'Scandicci W', 'https://media-4.api-sports.io/volley/teams/711.png', 0),
(712, 'Trentino W', 'https://media-4.api-sports.io/volley/teams/712.png', 0),
(713, 'UYBA Busto Arsizio W', 'https://media-4.api-sports.io/volley/teams/713.png', 0),
(714, 'Vallefoglia W', 'https://media-4.api-sports.io/volley/teams/714.png', 0),
(715, 'Albese W', 'https://media-4.api-sports.io/volley/teams/715.png', 0),
(717, 'Anthea Vicenza W', 'https://media-4.api-sports.io/volley/teams/717.png', 0),
(718, 'Brescia W', 'https://media-4.api-sports.io/volley/teams/718.png', 0),
(719, 'Club Italia W', 'https://media-4.api-sports.io/volley/teams/719.png', 0),
(720, 'Futura Volley Giovani W', 'https://media-4.api-sports.io/volley/teams/720.png', 0),
(721, 'Macerata W', 'https://media-4.api-sports.io/volley/teams/721.png', 0),
(723, 'Marsala W', 'https://media-4.api-sports.io/volley/teams/723.png', 0),
(724, 'Martignacco W', 'https://media-4.api-sports.io/volley/teams/724.png', 0),
(725, 'Mondovi W', 'https://media-4.api-sports.io/volley/teams/725.png', 0),
(726, 'Montecchio W', 'https://media-4.api-sports.io/volley/teams/726.png', 0),
(727, 'Olbia W', 'https://media-4.api-sports.io/volley/teams/727.png', 0),
(730, 'Pinerolo W', 'https://media-4.api-sports.io/volley/teams/730.png', 0),
(732, 'Sant&apos;Elia W', 'https://media-4.api-sports.io/volley/teams/732.png', 0),
(733, 'Sassuolo W', 'https://media-4.api-sports.io/volley/teams/733.png', 0),
(735, 'Soverato W', 'https://media-4.api-sports.io/volley/teams/735.png', 0),
(736, 'Talmassons W', 'https://media-4.api-sports.io/volley/teams/736.png', 0),
(737, 'Lube Civitanova', 'https://media-4.api-sports.io/volley/teams/737.png', 0),
(738, 'Modena', 'https://media-4.api-sports.io/volley/teams/738.png', 0),
(739, 'Perugia', 'https://media.api-sports.io/volley/teams/739.png', 0),
(740, 'Trentino', 'https://media-4.api-sports.io/volley/teams/740.png', 0),
(741, 'Cisterna', 'https://media-4.api-sports.io/volley/teams/741.png', 0),
(742, 'Milano', 'https://media-4.api-sports.io/volley/teams/742.png', 0),
(743, 'Monza', 'https://media-4.api-sports.io/volley/teams/743.png', 0),
(744, 'Padova', 'https://media-4.api-sports.io/volley/teams/744.png', 0),
(745, 'Piacenza', 'https://media-4.api-sports.io/volley/teams/745.png', 0),
(746, 'Ravenna', 'https://media-4.api-sports.io/volley/teams/746.png', 0),
(747, 'Verona', 'https://media-4.api-sports.io/volley/teams/747.png', 0),
(748, 'Vibo Valentia', 'https://media-4.api-sports.io/volley/teams/748.png', 0),
(751, 'Taranto', 'https://media-4.api-sports.io/volley/teams/751.png', 0),
(1794, 'Grottazzolina', 'https://media-4.api-sports.io/volley/teams/1794.png', 0),
(1801, 'Pordenone', 'https://media-4.api-sports.io/volley/teams/1801.png', 0),
(1811, 'Montale W', 'https://media-4.api-sports.io/volley/teams/1811.png', 0),
(3517, 'Cremona W', 'https://media-4.api-sports.io/volley/teams/3517.png', 0),
(3518, 'Messina W', 'https://media-4.api-sports.io/volley/teams/3518.png', 0),
(3519, 'Offanengo W', 'https://media-4.api-sports.io/volley/teams/3519.png', 0),
(3520, 'Pallavolo Perugia W', 'https://media-4.api-sports.io/volley/teams/3520.png', 0),
(3521, 'Picco Lecco W', 'https://media-4.api-sports.io/volley/teams/3521.png', 0),
(3522, 'San Giovanni W', 'https://media-4.api-sports.io/volley/teams/3522.png', 0),
(3604, 'Vero Volley W', 'https://media-4.api-sports.io/volley/teams/3604.png', 0),
(3815, 'Saturnia Acicastello', 'https://media.api-sports.io/volley/teams/3815.png', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `team_list`
--

CREATE TABLE `team_list` (
  `id_team_list` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_team_season` int(11) NOT NULL,
  `id_group` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `team_season`
--

CREATE TABLE `team_season` (
  `id_team_season` int(11) NOT NULL,
  `id_league` int(11) NOT NULL,
  `id_season` int(11) NOT NULL,
  `id_team` int(11) NOT NULL,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `team_season`
--

INSERT INTO `team_season` (`id_team_season`, `id_league`, `id_season`, `id_team`, `end_date`, `start_date`) VALUES
(1, 97, 2023, 741, '2024-03-03', '2023-10-22'),
(2, 97, 2023, 737, '2024-03-03', '2023-10-22'),
(3, 97, 2023, 742, '2024-03-03', '2023-10-22'),
(4, 97, 2023, 738, '2024-03-03', '2023-10-22'),
(5, 97, 2023, 743, '2024-03-03', '2023-10-22'),
(6, 97, 2023, 744, '2024-03-03', '2023-10-22'),
(8, 97, 2023, 745, '2024-03-03', '2023-10-22'),
(10, 97, 2023, 751, '2024-03-03', '2023-10-22'),
(11, 97, 2023, 740, '2024-03-03', '2023-10-22'),
(12, 97, 2023, 747, '2024-03-03', '2023-10-22'),
(13, 97, 2023, 739, '2024-03-03', '2023-10-22'),
(14, 97, 2023, 3815, '2024-03-03', '2023-10-22');

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT 0,
  `money` int(11) NOT NULL DEFAULT 0,
  `verified` bit(1) NOT NULL,
  `count_bet` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `mail`, `Username`, `Password`, `admin`, `money`, `verified`, `count_bet`) VALUES
(3, 'vale@mail.com', 'vale', '80424d5247f97d337c0538b7324c7acc2acd1bed2b2e59df466f9982ede23aef', 0, 0, b'0', 0),
(4, 'marco@mail.com', 'marco', '80424d5247f97d337c0538b7324c7acc2acd1bed2b2e59df466f9982ede23aef', 0, 0, b'0', 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `bet`
--
ALTER TABLE `bet`
  ADD PRIMARY KEY (`id_bet`),
  ADD KEY `id_game` (`id_game`),
  ADD KEY `id_team` (`id_team`),
  ADD KEY `id_user` (`id_user`);

--
-- Indici per le tabelle `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id_country`);

--
-- Indici per le tabelle `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`),
  ADD KEY `id_league` (`id_league`);

--
-- Indici per le tabelle `group`
--
ALTER TABLE `group`
  ADD PRIMARY KEY (`id_group`);

--
-- Indici per le tabelle `league`
--
ALTER TABLE `league`
  ADD PRIMARY KEY (`id_league`),
  ADD KEY `id_country` (`id_country`);

--
-- Indici per le tabelle `period`
--
ALTER TABLE `period`
  ADD PRIMARY KEY (`id_period`),
  ADD KEY `id_score` (`id_score`);

--
-- Indici per le tabelle `prefered_team`
--
ALTER TABLE `prefered_team`
  ADD PRIMARY KEY (`id_prefered_team`),
  ADD KEY `id_team` (`id_team`),
  ADD KEY `id_user` (`id_user`);

--
-- Indici per le tabelle `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id_score`),
  ADD KEY `id_team` (`id_team`),
  ADD KEY `id_game` (`id_game`);

--
-- Indici per le tabelle `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id_season`);

--
-- Indici per le tabelle `standing`
--
ALTER TABLE `standing`
  ADD PRIMARY KEY (`id_standing`),
  ADD KEY `id_group` (`id_group`),
  ADD KEY `id_team_season` (`id_team_season`);

--
-- Indici per le tabelle `statistic`
--
ALTER TABLE `statistic`
  ADD PRIMARY KEY (`id_statistic`),
  ADD KEY `id_team_season` (`id_team_season`);

--
-- Indici per le tabelle `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id_team`);

--
-- Indici per le tabelle `team_list`
--
ALTER TABLE `team_list`
  ADD PRIMARY KEY (`id_team_list`),
  ADD KEY `id_group` (`id_group`),
  ADD KEY `id_team_season` (`id_team_season`),
  ADD KEY `id_user` (`id_user`);

--
-- Indici per le tabelle `team_season`
--
ALTER TABLE `team_season`
  ADD PRIMARY KEY (`id_team_season`),
  ADD KEY `id_league` (`id_league`),
  ADD KEY `id_season` (`id_season`),
  ADD KEY `id_team` (`id_team`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `bet`
--
ALTER TABLE `bet`
  MODIFY `id_bet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `country`
--
ALTER TABLE `country`
  MODIFY `id_country` int(55) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT per la tabella `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148535;

--
-- AUTO_INCREMENT per la tabella `group`
--
ALTER TABLE `group`
  MODIFY `id_group` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `league`
--
ALTER TABLE `league`
  MODIFY `id_league` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT per la tabella `period`
--
ALTER TABLE `period`
  MODIFY `id_period` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT per la tabella `prefered_team`
--
ALTER TABLE `prefered_team`
  MODIFY `id_prefered_team` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `score`
--
ALTER TABLE `score`
  MODIFY `id_score` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=243;

--
-- AUTO_INCREMENT per la tabella `season`
--
ALTER TABLE `season`
  MODIFY `id_season` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2024;

--
-- AUTO_INCREMENT per la tabella `standing`
--
ALTER TABLE `standing`
  MODIFY `id_standing` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT per la tabella `statistic`
--
ALTER TABLE `statistic`
  MODIFY `id_statistic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT per la tabella `team`
--
ALTER TABLE `team`
  MODIFY `id_team` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3816;

--
-- AUTO_INCREMENT per la tabella `team_list`
--
ALTER TABLE `team_list`
  MODIFY `id_team_list` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `team_season`
--
ALTER TABLE `team_season`
  MODIFY `id_team_season` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `bet`
--
ALTER TABLE `bet`
  ADD CONSTRAINT `bet_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bet_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bet_ibkf_3` FOREIGN KEY (`id_team`) REFERENCES `team` (`id_team`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`id_league`) REFERENCES `league` (`id_league`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `league`
--
ALTER TABLE `league`
  ADD CONSTRAINT `league_ibfk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `period`
--
ALTER TABLE `period`
  ADD CONSTRAINT `period_ibfk_1` FOREIGN KEY (`id_score`) REFERENCES `score` (`id_score`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prefered_team`
--
ALTER TABLE `prefered_team`
  ADD CONSTRAINT `prefered_team_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prefered_team_ibfk_2` FOREIGN KEY (`id_team`) REFERENCES `team` (`id_team`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `score_ibfk_1` FOREIGN KEY (`id_team`) REFERENCES `team` (`id_team`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `score_ibfk_2` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `standing`
--
ALTER TABLE `standing`
  ADD CONSTRAINT `standing_ibfk_1` FOREIGN KEY (`id_group`) REFERENCES `group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `standing_ibfk_2` FOREIGN KEY (`id_team_season`) REFERENCES `team_season` (`id_team_season`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `statistic`
--
ALTER TABLE `statistic`
  ADD CONSTRAINT `statistic_ibfk_1` FOREIGN KEY (`id_team_season`) REFERENCES `team_season` (`id_team_season`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `team_list`
--
ALTER TABLE `team_list`
  ADD CONSTRAINT `team_list_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `team_list_ibfk_2` FOREIGN KEY (`id_team_season`) REFERENCES `team_season` (`id_team_season`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `team_list_ibfk_3` FOREIGN KEY (`id_group`) REFERENCES `group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `team_season`
--
ALTER TABLE `team_season`
  ADD CONSTRAINT `team_season_ibfk_1` FOREIGN KEY (`id_league`) REFERENCES `league` (`id_league`) ON UPDATE CASCADE,
  ADD CONSTRAINT `team_season_ibfk_2` FOREIGN KEY (`id_season`) REFERENCES `season` (`id_season`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `team_season_ibfk_3` FOREIGN KEY (`id_team`) REFERENCES `team` (`id_team`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
