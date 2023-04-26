-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 18 Sty 2022, 22:27
-- Wersja serwera: 10.4.19-MariaDB
-- Wersja PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `projekt`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `logged_in_users`
--

CREATE TABLE `logged_in_users` (
  `sessionId` varchar(100) NOT NULL,
  `userId` int(11) NOT NULL,
  `lastUpdate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `fullName` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `status` int(1) NOT NULL,
  `date` datetime NOT NULL,
  `telefon` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `userName`, `fullName`, `email`, `passwd`, `status`, `date`, `telefon`) VALUES
(1, 'admin123', 'Administrator', 'admin@admin.admin', '$2y$10$9zBwVeuq5DNfugjn5gdd5emaElEUG06DO8Y4C.qV35/JPV8wHuR3O', 2, '2021-12-10 00:00:00', ''),
(2, 'user1234', 'Usseerrr', 'user@user.user', '$2y$10$7MHvfv/oAoVp3aHsR/.TW.0oZlO/BvT1N85SS5bMRgLsmwx135zN6', 1, '2021-12-10 00:00:00', ''),
(3, 'testtest', 'Tester', 'test@test.test', '$2y$10$GCJgbqgvnW.goDOh02aqjuu03AY.TIiTyickR3SNzavVrDNIGTusC', 1, '2021-12-10 00:00:00', ''),
(6, 'jas', 'jasKowalski', 'jas@kowalski1.pl', '$2y$10$3lnJXaZfkFrAy.WiTANpDu1wHyz29EUgvNgV0uhtSR4YD94wd06Da', 1, '2022-01-15 00:00:00', '123 123 123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zainteresowani`
--

CREATE TABLE `zainteresowani` (
  `id` int(11) NOT NULL,
  `imieinazwisko` varchar(50) DEFAULT NULL,
  `adres` varchar(60) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `zainteresowani`
--

INSERT INTO `zainteresowani` (`id`, `imieinazwisko`, `adres`, `telefon`, `email`, `data`) VALUES
(13, 'Jan Kowalski', 'x', '123 123 123', 'jan@kowalski.pl', '2022-01-17 20:45:39'),
(15, 'Bogdan Mazur', 'Czerniejów', '989232123', 'bogdan@mazur.pl', '2022-01-17 20:47:11');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia`
--

CREATE TABLE `zamowienia` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT 0,
  `typ` enum('Dieta indywidualna','Kanapki','Pełna oferta') NOT NULL DEFAULT 'Dieta indywidualna',
  `zestaw` enum('true','false') NOT NULL DEFAULT 'false',
  `liczbaOsob` int(3) NOT NULL,
  `pakiet` enum('basic','standard','premium') NOT NULL DEFAULT 'basic',
  `alergeny` set('nuts','gluten','eggs','milk','inne','brak') DEFAULT 'brak',
  `inneAlergeny` varchar(30) DEFAULT NULL,
  `data` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `zamowienia`
--

INSERT INTO `zamowienia` (`id`, `userId`, `status`, `typ`, `zestaw`, `liczbaOsob`, `pakiet`, `alergeny`, `inneAlergeny`, `data`) VALUES
(1, 1, 1, 'Dieta indywidualna', 'true', 10, 'basic', 'nuts', 's', '2022-01-17 19:45:50'),
(2, 1, 0, 'Kanapki', 'false', 222, 'premium', 'nuts,gluten,eggs,milk', '', '2022-01-17 19:45:50'),
(3, 1, 1, 'Pełna oferta', 'true', 35, 'standard', 'milk', 'sezam', '2022-01-17 19:45:50'),
(4, 2, 0, 'Kanapki', 'true', 33, 'standard', 'brak', '', '2022-01-17 19:45:50'),
(5, 2, 0, 'Kanapki', 'false', 15, 'basic', 'eggs', '', '2022-01-17 19:45:50'),
(6, 2, 1, 'Kanapki', 'false', 15, 'basic', 'eggs', '', '2022-01-17 19:46:17'),
(7, 2, 1, 'Kanapki', 'false', 15, 'basic', 'eggs', '', '2022-01-17 19:54:54'),
(8, 1, 0, 'Dieta indywidualna', 'false', 10, 'basic', 'brak', '', '2022-01-18 20:06:19'),
(9, 1, 0, 'Dieta indywidualna', 'false', 10, 'basic', 'brak', '', '2022-01-18 20:09:45');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `logged_in_users`
--
ALTER TABLE `logged_in_users`
  ADD PRIMARY KEY (`sessionId`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userName` (`userName`,`email`);

--
-- Indeksy dla tabeli `zainteresowani`
--
ALTER TABLE `zainteresowani`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `adres` (`adres`,`telefon`);

--
-- Indeksy dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `zainteresowani`
--
ALTER TABLE `zainteresowani`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
