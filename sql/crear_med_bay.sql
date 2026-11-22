CREATE DATABASE IF NOT EXISTS med_bay
    CHARACTER SET utf8mb4;

USE med_bay;

CREATE TABLE IF NOT EXISTS pacientes (
    id_paciente INT NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    apellido    VARCHAR(100) NOT NULL,
    documento   VARCHAR(30)  NOT NULL,
    edad        INT          NOT NULL,
    genero      VARCHAR(30),
    telefono    VARCHAR(30),
    correo      VARCHAR(150),
    direccion   VARCHAR(200),
    PRIMARY KEY (id_paciente),
    UNIQUE KEY uq_pacientes_documento (documento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;