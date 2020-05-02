-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 02. Mai 2020 um 15:29
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `csv`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `artikel`
--

CREATE TABLE `artikel` (
  `artikelID` int(10) UNSIGNED NOT NULL,
  `palettenID` int(10) UNSIGNED NOT NULL,
  `artikelnummer` varchar(255) NOT NULL,
  `artikelbezeichnung` varchar(255) NOT NULL,
  `datum` date NOT NULL,
  `stat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `artikel`
--

INSERT INTO `artikel` (`artikelID`, `palettenID`, `artikelnummer`, `artikelbezeichnung`, `datum`, `stat`) VALUES
(21, 13, '10-100-123', 'Musteritem a', '2020-05-02', 'new'),
(22, 14, '10-100-124', 'Musteritem b', '2020-05-02', 'new');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `benutzer`
--

CREATE TABLE `benutzer` (
  `userID` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userpassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`userID`, `username`, `userpassword`) VALUES
(1, 'admin', 'geheim'),
(2, 'user', 'password');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lieferant`
--

CREATE TABLE `lieferant` (
  `lieferantenID` int(11) UNSIGNED NOT NULL,
  `lieferantennummer` varchar(255) NOT NULL,
  `lieferantenname` varchar(255) NOT NULL,
  `strasse` varchar(255) NOT NULL,
  `plz` varchar(5) NOT NULL,
  `ort` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `lieferant`
--

INSERT INTO `lieferant` (`lieferantenID`, `lieferantennummer`, `lieferantenname`, `strasse`, `plz`, `ort`) VALUES
(18, '123456', 'Max Mustermann GmbH & co. KG', 'Mustermann Str. 1', '12345', 'Musterstadt');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `palette`
--

CREATE TABLE `palette` (
  `palettenID` int(10) UNSIGNED NOT NULL,
  `lieferantenID` int(10) UNSIGNED NOT NULL,
  `palettennummer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `palette`
--

INSERT INTO `palette` (`palettenID`, `lieferantenID`, `palettennummer`) VALUES
(13, 18, 'Musterpalette1'),
(14, 18, 'Musterpalette2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `setting`
--

CREATE TABLE `setting` (
  `settingID` int(10) UNSIGNED NOT NULL,
  `artikelID` int(10) UNSIGNED NOT NULL,
  `settingtitle` varchar(255) NOT NULL,
  `settingvalue` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `setting`
--

INSERT INTO `setting` (`settingID`, `artikelID`, `settingtitle`, `settingvalue`) VALUES
(101, 21, 'test', 'a'),
(102, 21, 'test2', 'b'),
(103, 21, 'test3', 'c'),
(104, 22, 'test3', 'd'),
(105, 22, 'test4', 'ab');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `artikel`
--
ALTER TABLE `artikel`
  ADD PRIMARY KEY (`artikelID`),
  ADD KEY `palettenID` (`palettenID`);

--
-- Indizes für die Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  ADD PRIMARY KEY (`userID`);

--
-- Indizes für die Tabelle `lieferant`
--
ALTER TABLE `lieferant`
  ADD PRIMARY KEY (`lieferantenID`);

--
-- Indizes für die Tabelle `palette`
--
ALTER TABLE `palette`
  ADD PRIMARY KEY (`palettenID`),
  ADD KEY `lieferantenID` (`lieferantenID`);

--
-- Indizes für die Tabelle `setting`
--
ALTER TABLE `setting`
  ADD PRIMARY KEY (`settingID`),
  ADD KEY `artikelID` (`artikelID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `artikel`
--
ALTER TABLE `artikel`
  MODIFY `artikelID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT für Tabelle `benutzer`
--
ALTER TABLE `benutzer`
  MODIFY `userID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `lieferant`
--
ALTER TABLE `lieferant`
  MODIFY `lieferantenID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT für Tabelle `palette`
--
ALTER TABLE `palette`
  MODIFY `palettenID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT für Tabelle `setting`
--
ALTER TABLE `setting`
  MODIFY `settingID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `artikel`
--
ALTER TABLE `artikel`
  ADD CONSTRAINT `artikel_ibfk_1` FOREIGN KEY (`palettenID`) REFERENCES `palette` (`palettenID`);

--
-- Constraints der Tabelle `palette`
--
ALTER TABLE `palette`
  ADD CONSTRAINT `palette_ibfk_1` FOREIGN KEY (`lieferantenID`) REFERENCES `lieferant` (`lieferantenID`);

--
-- Constraints der Tabelle `setting`
--
ALTER TABLE `setting`
  ADD CONSTRAINT `setting_ibfk_1` FOREIGN KEY (`artikelID`) REFERENCES `artikel` (`artikelID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
