-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 09-Abr-2022 às 13:37
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `estoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categorias`
--

INSERT INTO `categorias` (`id`, `nome_categoria`) VALUES
(1, 'Eletrodoméstico'),
(2, 'Tv e Áudio');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `cpf`, `endereco`, `bairro`, `cidade`, `uf`, `cep`, `telefone`, `email`) VALUES
(1, 'Maria José', '515.831.890-50', 'Rua Nilo Pecanha 13', 'Ingá', 'Niterói', 'RJ', '24210480', '(21)654321789', 'maria@gmail.com'),
(2, 'Malu', '069.057.300-68', 'Rua Passos da partia', 'Boa Viagem', 'Niterói', 'RJ', '24210240', '(21)988617661', 'malu@yahoo.com'),
(14, 'Leonardo Cruz da Costa', '906.235.927-20', 'Rua Presidente Domiciano', 'inga', 'Niterói', 'RJ', '24210271', '988925670', 'leo.cruz@yahoo.com.br');

-- --------------------------------------------------------

--
-- Estrutura da tabela `compras`
--

DROP TABLE IF EXISTS `compras`;
CREATE TABLE IF NOT EXISTS `compras` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade_compra` int(11) NOT NULL,
  `data_compra` date NOT NULL,
  `valor_compra` int(11) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_funcionario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fk_fornecedor` (`id_fornecedor`),
  KEY `id_fk_produto` (`id_produto`),
  KEY `id_fk_funcionario` (`id_funcionario`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `compras`
--

INSERT INTO `compras` (`id`, `quantidade_compra`, `data_compra`, `valor_compra`, `id_fornecedor`, `id_produto`, `id_funcionario`) VALUES
(15, 2, '2022-02-02', 1000, 1, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
CREATE TABLE IF NOT EXISTS `fornecedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(50) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`id`, `razao_social`, `cnpj`, `endereco`, `bairro`, `cidade`, `uf`, `cep`, `telefone`, `email`) VALUES
(1, 'LG', '34.051.454/0001-03', 'Rua Antonio Carlos 10', 'Vila Maria', 'São Paulo', 'RJ', '37511-000', '(11)123456789', 'lg@lg.com.br'),
(2, 'SANSUNG', '59.672.481/0001-24', 'Rua Alceu Wamosy', 'Vila Mariana', 'São Paulo', 'SP', '04105-040', '(11)987654321', 'sansung@sansung.com.br'),
(10, 'aaaa23', '11.111.111/1111-11', 'aaa', 'aa', 'aa', 'RJ', '111111', '(11)11111-1', '1@1.com'),
(13, 'aaaa', '11.111.111/11', 'aaa', 'aa', 'aa', 'RJ', '11111111', '(11)11111-1', '1@1.com'),
(14, '11111', '11111111111111', 'Rua Presidente Domiciano', 'inga', 'NiterÃ³i', 'RJ', '24210271', '988925670', 'leo.cruz@yahoo.com.br'),
(16, 'aaaa34', '11.111.111/11', '11111111', '6666', '11111111111', 'RJ', '11111111', '(11)1111-11', 'lala@lala.com1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
CREATE TABLE IF NOT EXISTS `funcionarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(10) NOT NULL,
  `papel` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `nome`, `cpf`, `senha`, `papel`) VALUES
(1, 'Leo', '249.252.810-38', '111', '0'),
(2, 'Carlos', '081.599.500-80', '111', '1'),
(3, 'Pedro', '167.740.300-41', '111', '2');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(100) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco_compra` decimal(10,2) NOT NULL,
  `preco_venda` decimal(10,2) NOT NULL,
  `quantidade_disponível` int(11) NOT NULL,
  `liberado_venda` varchar(1) NOT NULL DEFAULT 'S',
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fk_categoria` (`id_categoria`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome_produto`, `descricao`, `preco_compra`, `preco_venda`, `quantidade_disponível`, `liberado_venda`, `id_categoria`) VALUES
(1, 'Refrigerador Side by Side LG de 02 Portas Frost Free com 601 Litros', 'Refrigerador Side by Side LG de 02 Portas Frost Free com 601 Litros, Moist Balance Crisper™, Aço Escovado - GC-L247S', '111.00', '11655.01', 16, 'S', 1),
(2, 'Refrigerador Bottom Freezer Samsung Barosa de 02 Portas Frost Free', 'Refrigerador Bottom Freezer Samsung Barosa de 02 Portas Frost Free com 435 L e Painel Eletrônico Inox Look - RL4353RBASL', '3040.31', '5600.99', 4, 'S', 1),
(3, 'Samsung Smart TV Crystal UHD TU8000 4K 50', 'Samsung Smart TV Crystal UHD TU8000 4K 50\", Borda Infinita, Alexa built in, Controle Único, Visual Livre de Cabos', '12121.00', '2690.99', 16, 'N', 2),
(4, 'Samsung Smart TV QLED 4K Q70T 85\"', 'Samsung Smart TV QLED 4K Q70T 85\", Pontos Quânticos, HDR, Borda Infinita, Alexa built in, Modo Ambiente 3.0', '14900.90', '17999.99', 0, 'N', 2),
(5, 'lala', 'aaaa', '1111.00', '11111.00', 11, 'S', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

DROP TABLE IF EXISTS `vendas`;
CREATE TABLE IF NOT EXISTS `vendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade_venda` int(11) NOT NULL,
  `data_venda` date NOT NULL,
  `valor_venda` float NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_funcionario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fk_cliente` (`id_cliente`) USING BTREE,
  KEY `id_fk_funcionario` (`id_funcionario`) USING BTREE,
  KEY `id_fk_produto` (`id_produto`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`id`, `quantidade_venda`, `data_venda`, `valor_venda`, `id_cliente`, `id_produto`, `id_funcionario`) VALUES
(1, 1, '2021-02-02', 5600.99, 1, 2, 2),
(3, 1, '2021-04-07', 11655, 1, 1, 2),
(6, 1, '2021-04-09', 11655, 14, 1, 2);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `id_fk_fornecedor` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`),
  ADD CONSTRAINT `id_fk_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`),
  ADD CONSTRAINT `id_fk_produto` FOREIGN KEY (`id_produto`) REFERENCES `produtos` (`id`);

--
-- Limitadores para a tabela `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `produtos_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `id_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`),
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`id_produto`) REFERENCES `produtos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
