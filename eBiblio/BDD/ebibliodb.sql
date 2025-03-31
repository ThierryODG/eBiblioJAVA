-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 31 mars 2025 à 11:42
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ebibliodb`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

CREATE TABLE `adherents` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `adresse` text DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  `emprunts_en_cours` int(11) DEFAULT 0,
  `est_retardataire` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `emprunts`
--

CREATE TABLE `emprunts` (
  `id` int(11) NOT NULL,
  `adherent_id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `date_emprunt` datetime NOT NULL DEFAULT current_timestamp(),
  `date_retour_prevue` datetime NOT NULL,
  `date_retour_effectif` datetime DEFAULT NULL,
  `statut` enum('En cours','Retourné','En retard') NOT NULL DEFAULT 'En cours'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déclencheurs `emprunts`
--
DELIMITER $$
CREATE TRIGGER `after_emprunt_insert` AFTER INSERT ON `emprunts` FOR EACH ROW BEGIN
    UPDATE StatistiqueLivre 
    SET nombre_emprunts = nombre_emprunts + 1 
    WHERE livre_id = NEW.livre_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

CREATE TABLE `livres` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) NOT NULL,
  `editeur` varchar(255) DEFAULT NULL,
  `date_edition` date DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `type` enum('Scientifique','Littéraire') NOT NULL,
  `exemplaires_disponibles` int(11) NOT NULL DEFAULT 1,
  `exemplaires_totaux` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `statistiquelivre`
--

CREATE TABLE `statistiquelivre` (
  `livre_id` int(11) NOT NULL,
  `nombre_emprunts` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `typeadherent`
--

CREATE TABLE `typeadherent` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `livres_max` int(11) NOT NULL,
  `duree_emprunt_jours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `typeadherent`
--

INSERT INTO `typeadherent` (`id`, `nom`, `livres_max`, `duree_emprunt_jours`) VALUES
(1, 'Étudiant', 2, 7),
(2, 'Enseignant', 4, 21),
(3, 'Visiteur', 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('Admin','Bibliothecaire','Adherent') NOT NULL,
  `adherent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `username`, `password_hash`, `role`, `adherent_id`) VALUES
(1, 'admin', 'admin', 'Admin', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `type_id` (`type_id`);

--
-- Index pour la table `emprunts`
--
ALTER TABLE `emprunts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `adherent_id` (`adherent_id`),
  ADD KEY `livre_id` (`livre_id`),
  ADD KEY `idx_date_retour_prevue` (`date_retour_prevue`);

--
-- Index pour la table `livres`
--
ALTER TABLE `livres`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Index pour la table `statistiquelivre`
--
ALTER TABLE `statistiquelivre`
  ADD PRIMARY KEY (`livre_id`);

--
-- Index pour la table `typeadherent`
--
ALTER TABLE `typeadherent`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `adherent_id` (`adherent_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adherents`
--
ALTER TABLE `adherents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `emprunts`
--
ALTER TABLE `emprunts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livres`
--
ALTER TABLE `livres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `typeadherent`
--
ALTER TABLE `typeadherent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD CONSTRAINT `adherents_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `typeadherent` (`id`);

--
-- Contraintes pour la table `emprunts`
--
ALTER TABLE `emprunts`
  ADD CONSTRAINT `emprunts_ibfk_1` FOREIGN KEY (`adherent_id`) REFERENCES `adherents` (`id`),
  ADD CONSTRAINT `emprunts_ibfk_2` FOREIGN KEY (`livre_id`) REFERENCES `livres` (`id`);

--
-- Contraintes pour la table `statistiquelivre`
--
ALTER TABLE `statistiquelivre`
  ADD CONSTRAINT `statistiquelivre_ibfk_1` FOREIGN KEY (`livre_id`) REFERENCES `livres` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `utilisateurs_ibfk_1` FOREIGN KEY (`adherent_id`) REFERENCES `adherents` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
