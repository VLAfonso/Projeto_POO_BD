CREATE DATABASE IF NOT EXISTS projeto_bd_poo;
USE projeto_bd_poo;

-- Tabela dos setores
CREATE TABLE IF NOT EXISTS Setor (
  ID INT NOT NULL,
  Nome VARCHAR(45),
  PRIMARY KEY (id)
);

-- Tabela dos funcionários
CREATE TABLE IF NOT EXISTS Funcionario (
  Registro INT NOT NULL,
  Nome VARCHAR(45),
  CPF VARCHAR(45),
  DataNascimento VARCHAR(45),
  Telefone VARCHAR(45),
  Email VARCHAR(45),
  Gerente_Registro INT,
  Setor_ID INT,
  PRIMARY KEY (Registro),
  CONSTRAINT fk_Funcionário_Gerente
    FOREIGN KEY (Gerente_Registro)
    REFERENCES Gerente (Registro)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT fk_Funcionário_Setor1
    FOREIGN KEY (Setor_ID)
    REFERENCES Setor (ID)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);
  
-- Tabela dos gerentes
CREATE TABLE IF NOT EXISTS Gerente (
  Registro INT NOT NULL,
  Nome VARCHAR(45),
  CPF VARCHAR(45),
  DataNascimento VARCHAR(45),
  Telefone VARCHAR(45),
  Email VARCHAR(45),
  Setor_ID INT,
  PRIMARY KEY (Registro),
  CONSTRAINT fk_Gerente_Setor1
    FOREIGN KEY (Setor_ID)
    REFERENCES Setor (ID)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- Tabela dos fornecedores
CREATE TABLE IF NOT EXISTS Fornecedor (
  ID INT NOT NULL,
  Nome VARCHAR(45),
  Telefone VARCHAR(45),
  Email VARCHAR(45),
  Empresa VARCHAR(45),
  Gerente_Registro INT,
  PRIMARY KEY (ID),
  CONSTRAINT fk_Fornecedor_Gerente1
    FOREIGN KEY (Gerente_Registro)
    REFERENCES Gerente (Registro)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- Tabela dos produtos
CREATE TABLE IF NOT EXISTS Produto (
  CodigoInterno INT NOT NULL,
  Nome VARCHAR(45),
  CodigoDeBarras VARCHAR(45),
  Preco FLOAT,
  Estoque INT,
  Setor_ID INT,
  PRIMARY KEY (CodigoInterno),
  CONSTRAINT fk_Produto_Setor1
    FOREIGN KEY (Setor_ID)
    REFERENCES Setor (ID)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- Tabela de relação entre fornecedores e produtos
CREATE TABLE IF NOT EXISTS Fornecedor_Produto (
  ID INT NOT NULL,
  Fornecedor_ID INT,
  Produto_CodigoInterno INT,
  PRIMARY KEY (ID),
  CONSTRAINT fk_Fornecedor_Produto_Fornecedor1
    FOREIGN KEY (Fornecedor_ID)
    REFERENCES Fornecedor (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Fornecedor_Produto_Produto1
    FOREIGN KEY (Produto_CodigoInterno)
    REFERENCES Produto (CodigoInterno)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);