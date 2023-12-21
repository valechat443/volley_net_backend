-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 21, 2023 alle 16:14
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

-- --------------------------------------------------------

--
-- Struttura della tabella `group`
--

CREATE TABLE `group` (
  `id_group` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `sets` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `season`
--

CREATE TABLE `season` (
  `id_season` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Struttura della tabella `team`
--

CREATE TABLE `team` (
  `id_team` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `national` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  MODIFY `id_bet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `country`
--
ALTER TABLE `country`
  MODIFY `id_country` int(55) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `group`
--
ALTER TABLE `group`
  MODIFY `id_group` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `league`
--
ALTER TABLE `league`
  MODIFY `id_league` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `period`
--
ALTER TABLE `period`
  MODIFY `id_period` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `prefered_team`
--
ALTER TABLE `prefered_team`
  MODIFY `id_prefered_team` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `score`
--
ALTER TABLE `score`
  MODIFY `id_score` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `season`
--
ALTER TABLE `season`
  MODIFY `id_season` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `standing`
--
ALTER TABLE `standing`
  MODIFY `id_standing` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `statistic`
--
ALTER TABLE `statistic`
  MODIFY `id_statistic` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `team`
--
ALTER TABLE `team`
  MODIFY `id_team` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `team_list`
--
ALTER TABLE `team_list`
  MODIFY `id_team_list` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `team_season`
--
ALTER TABLE `team_season`
  MODIFY `id_team_season` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

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
