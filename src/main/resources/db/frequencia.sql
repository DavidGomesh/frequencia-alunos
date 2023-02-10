-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 10-Fev-2023 às 12:43
-- Versão do servidor: 10.4.20-MariaDB
-- versão do PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `frequencia`
--
CREATE DATABASE IF NOT EXISTS `frequencia` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `frequencia`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
  `id_aluno` int(11) NOT NULL,
  `cartao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `fk_curso` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `id_curso` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estagio`
--

DROP TABLE IF EXISTS `estagio`;
CREATE TABLE `estagio` (
  `id_estagio` int(11) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `fk_aluno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `horas_estagio`
--

DROP TABLE IF EXISTS `horas_estagio`;
CREATE TABLE `horas_estagio` (
  `id_horas_estagio` int(11) NOT NULL,
  `data_registro` date NOT NULL,
  `hora_fim` time DEFAULT NULL,
  `hora_inicio` time NOT NULL,
  `fk_estagio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `log_leitura`
--

DROP TABLE IF EXISTS `log_leitura`;
CREATE TABLE `log_leitura` (
  `id_log_leitura` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `data_hora` datetime NOT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  `micro` varchar(255) DEFAULT NULL,
  `modo_operacao` varchar(255) DEFAULT NULL,
  `pessoa` varchar(255) DEFAULT NULL,
  `tipo_micro` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `micro`
--

DROP TABLE IF EXISTS `micro`;
CREATE TABLE `micro` (
  `id_micro` int(11) NOT NULL,
  `modo_operacao` varchar(255) NOT NULL,
  `tipo_micro` varchar(255) NOT NULL,
  `fk_localizacao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sala`
--

DROP TABLE IF EXISTS `sala`;
CREATE TABLE `sala` (
  `id_sala` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id_aluno`),
  ADD UNIQUE KEY `UK_n5irycb6pt6hltt8j62pecmfq` (`cartao`),
  ADD KEY `FKhum2hb90hieiq635tdybkm888` (`fk_curso`);

--
-- Índices para tabela `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id_curso`);

--
-- Índices para tabela `estagio`
--
ALTER TABLE `estagio`
  ADD PRIMARY KEY (`id_estagio`),
  ADD KEY `FKdk1eq2cf1qb3wt3rvt7cn3a2a` (`fk_aluno`);

--
-- Índices para tabela `horas_estagio`
--
ALTER TABLE `horas_estagio`
  ADD PRIMARY KEY (`id_horas_estagio`),
  ADD KEY `FKpc19f599kcgltbat0myyx614s` (`fk_estagio`);

--
-- Índices para tabela `log_leitura`
--
ALTER TABLE `log_leitura`
  ADD PRIMARY KEY (`id_log_leitura`);

--
-- Índices para tabela `micro`
--
ALTER TABLE `micro`
  ADD PRIMARY KEY (`id_micro`),
  ADD KEY `FK8kuj25sojlpb3jwo3kf9jgxyu` (`fk_localizacao`);

--
-- Índices para tabela `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id_sala`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id_aluno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `curso`
--
ALTER TABLE `curso`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `estagio`
--
ALTER TABLE `estagio`
  MODIFY `id_estagio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `horas_estagio`
--
ALTER TABLE `horas_estagio`
  MODIFY `id_horas_estagio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `log_leitura`
--
ALTER TABLE `log_leitura`
  MODIFY `id_log_leitura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `micro`
--
ALTER TABLE `micro`
  MODIFY `id_micro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `sala`
--
ALTER TABLE `sala`
  MODIFY `id_sala` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `FKhum2hb90hieiq635tdybkm888` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`id_curso`);

--
-- Limitadores para a tabela `estagio`
--
ALTER TABLE `estagio`
  ADD CONSTRAINT `FKdk1eq2cf1qb3wt3rvt7cn3a2a` FOREIGN KEY (`fk_aluno`) REFERENCES `aluno` (`id_aluno`);

--
-- Limitadores para a tabela `horas_estagio`
--
ALTER TABLE `horas_estagio`
  ADD CONSTRAINT `FKpc19f599kcgltbat0myyx614s` FOREIGN KEY (`fk_estagio`) REFERENCES `estagio` (`id_estagio`);

--
-- Limitadores para a tabela `micro`
--
ALTER TABLE `micro`
  ADD CONSTRAINT `FK8kuj25sojlpb3jwo3kf9jgxyu` FOREIGN KEY (`fk_localizacao`) REFERENCES `sala` (`id_sala`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
