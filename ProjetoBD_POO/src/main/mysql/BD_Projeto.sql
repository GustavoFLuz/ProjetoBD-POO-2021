DROP DATABASE IF EXISTS projeto;
CREATE DATABASE projeto;
USE projeto;

CREATE TABLE usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cpf VARCHAR(45) NOT NULL
);

SELECT * FROM usuario