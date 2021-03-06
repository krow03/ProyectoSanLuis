-- MySQL Script generated by MySQL Workbench
-- Sat May  9 17:45:52 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SanluisDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SanluisDB` ;

-- -----------------------------------------------------
-- Schema SanluisDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SanluisDB` DEFAULT CHARACTER SET utf8 ;
USE `SanluisDB` ;

-- -----------------------------------------------------
-- Table `SanluisDB`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Roles` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Roles` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Aulas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Aulas` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Aulas` (
  `idAulas` INT NOT NULL AUTO_INCREMENT,
  `rangoIps` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  `capacidad` INT NULL,
  PRIMARY KEY (`idAulas`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Proveedores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Proveedores` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Proveedores` (
  `idProveedores` INT NOT NULL AUTO_INCREMENT,
  `cif` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `telef` VARCHAR(45) NULL,
  `pass` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`idProveedores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Stock` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Stock` (
  `idStock` INT NOT NULL AUTO_INCREMENT,
  `unidadesStock` INT NULL,
  `limiteInferior` INT NULL,
  `descripcion` VARCHAR(45) NULL,
  `precio` FLOAT NULL,
  `Proveedores_idProveedores` INT NOT NULL,
  PRIMARY KEY (`idStock`),
  INDEX `fk_Stock_Proveedores1_idx` (`Proveedores_idProveedores` ASC),
  CONSTRAINT `fk_Stock_Proveedores1`
    FOREIGN KEY (`Proveedores_idProveedores`)
    REFERENCES `SanluisDB`.`Proveedores` (`idProveedores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Equipos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Equipos` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Equipos` (
  `idEquipos` INT NOT NULL AUTO_INCREMENT,
  `Aulas_idAulas` INT NULL,
  `nombre` VARCHAR(45) NULL,
  `discoDuro` INT NULL,
  `ram` INT NULL,
  `ipEquipo` VARCHAR(45) NULL,
  `Stock_idStock` INT NULL,
  PRIMARY KEY (`idEquipos`),
  INDEX `fk_Equipos_Aulas1_idx` (`Aulas_idAulas` ASC),
  INDEX `fk_Equipos_Stock1_idx` (`Stock_idStock` ASC),
  CONSTRAINT `fk_Equipos_Aulas1`
    FOREIGN KEY (`Aulas_idAulas`)
    REFERENCES `SanluisDB`.`Aulas` (`idAulas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipos_Stock1`
    FOREIGN KEY (`Stock_idStock`)
    REFERENCES `SanluisDB`.`Stock` (`idStock`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Componentes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Componentes` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Componentes` (
  `idComponentes` INT NOT NULL AUTO_INCREMENT,
  `Equipos_idEquipos` INT NULL,
  `Stock_idStock` INT NULL,
  `descripcion` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `codLicencia` VARCHAR(45) NULL,
  `tipoHardware` VARCHAR(45) NULL,
  `marca` VARCHAR(45) NULL,
  `peso` DOUBLE NULL,
  PRIMARY KEY (`idComponentes`),
  INDEX `fk_Componentes_Equipos1_idx` (`Equipos_idEquipos` ASC),
  INDEX `fk_Componentes_Stock1_idx` (`Stock_idStock` ASC),
  CONSTRAINT `fk_Componentes_Equipos1`
    FOREIGN KEY (`Equipos_idEquipos`)
    REFERENCES `SanluisDB`.`Equipos` (`idEquipos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componentes_Stock1`
    FOREIGN KEY (`Stock_idStock`)
    REFERENCES `SanluisDB`.`Stock` (`idStock`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Usuarios` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Usuarios` (
  `idUsuarios` VARCHAR(45) NOT NULL,
  `Roles_idRol` INT NOT NULL,
  `Equipos_idEquipos` INT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(256) NOT NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsuarios`),
  INDEX `fk_Usuarios_Roles_idx` (`Roles_idRol` ASC),
  INDEX `fk_Usuarios_Equipos1_idx` (`Equipos_idEquipos` ASC),
  CONSTRAINT `fk_Usuarios_Roles`
    FOREIGN KEY (`Roles_idRol`)
    REFERENCES `SanluisDB`.`Roles` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Equipos1`
    FOREIGN KEY (`Equipos_idEquipos`)
    REFERENCES `SanluisDB`.`Equipos` (`idEquipos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`SolInci`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`SolInci` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`SolInci` (
  `idSolInci` INT NOT NULL AUTO_INCREMENT,
  `Realizada` VARCHAR(45) NOT NULL,
  `Asignada` VARCHAR(45) NULL,
  `Equipos_idEquipos` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `fechaSol` DATETIME NULL,
  `fechaFin` DATETIME NULL,
  `estado` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  `prioridad` INT NULL,
  `Componentes_idComponentes` INT NULL,
  PRIMARY KEY (`idSolInci`),
  INDEX `fk_SolInci_Usuarios1_idx` (`Realizada` ASC),
  INDEX `fk_SolInci_Usuarios2_idx` (`Asignada` ASC),
  INDEX `fk_SolInci_Equipos1_idx` (`Equipos_idEquipos` ASC),
  INDEX `fk_SolInci_Componentes1_idx` (`Componentes_idComponentes` ASC),
  CONSTRAINT `fk_SolInci_Usuarios1`
    FOREIGN KEY (`Realizada`)
    REFERENCES `SanluisDB`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SolInci_Usuarios2`
    FOREIGN KEY (`Asignada`)
    REFERENCES `SanluisDB`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SolInci_Equipos1`
    FOREIGN KEY (`Equipos_idEquipos`)
    REFERENCES `SanluisDB`.`Equipos` (`idEquipos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SolInci_Componentes1`
    FOREIGN KEY (`Componentes_idComponentes`)
    REFERENCES `SanluisDB`.`Componentes` (`idComponentes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`EstadosCompra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`EstadosCompra` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`EstadosCompra` (
  `idEstadosCompra` INT NOT NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idEstadosCompra`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`compras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`compras` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`compras` (
  `idcompras` INT NOT NULL AUTO_INCREMENT,
  `Proveedores_idProveedores` INT NOT NULL,
  `fechaCompra` DATETIME NULL,
  `precioTotal` DOUBLE NOT NULL,
  `idEstados` INT NOT NULL,
  PRIMARY KEY (`idcompras`),
  INDEX `fk_compras_Proveedores1_idx` (`Proveedores_idProveedores` ASC),
  INDEX `fk_compras_EstadosCompra1_idx` (`idEstados` ASC),
  CONSTRAINT `fk_compras_Proveedores1`
    FOREIGN KEY (`Proveedores_idProveedores`)
    REFERENCES `SanluisDB`.`Proveedores` (`idProveedores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compras_EstadosCompra1`
    FOREIGN KEY (`idEstados`)
    REFERENCES `SanluisDB`.`EstadosCompra` (`idEstadosCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Linea_compras`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Linea_compras` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Linea_compras` (
  `Stock_idStock` INT NOT NULL,
  `compras_idcompras` INT NOT NULL,
  `unidades` INT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`Stock_idStock`, `compras_idcompras`),
  INDEX `fk_Stock_has_compras_compras1_idx` (`compras_idcompras` ASC),
  INDEX `fk_Stock_has_compras_Stock1_idx` (`Stock_idStock` ASC),
  CONSTRAINT `fk_Stock_has_compras_Stock1`
    FOREIGN KEY (`Stock_idStock`)
    REFERENCES `SanluisDB`.`Stock` (`idStock`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_has_compras_compras1`
    FOREIGN KEY (`compras_idcompras`)
    REFERENCES `SanluisDB`.`compras` (`idcompras`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SanluisDB`.`Requisitos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SanluisDB`.`Requisitos` ;

CREATE TABLE IF NOT EXISTS `SanluisDB`.`Requisitos` (
  `idRequisitos` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `clave` VARCHAR(45) NULL,
  `Componentes_idComponentes` INT NOT NULL,
  PRIMARY KEY (`idRequisitos`),
  INDEX `fk_Requisitos_Componentes1_idx` (`Componentes_idComponentes` ASC),
  CONSTRAINT `fk_Requisitos_Componentes1`
    FOREIGN KEY (`Componentes_idComponentes`)
    REFERENCES `SanluisDB`.`Componentes` (`idComponentes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Roles` (`idRol`, `nombre`) VALUES (1, 'usuario');
INSERT INTO `SanluisDB`.`Roles` (`idRol`, `nombre`) VALUES (2, 'tecnico');
INSERT INTO `SanluisDB`.`Roles` (`idRol`, `nombre`) VALUES (3, 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Aulas`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Aulas` (`idAulas`, `rangoIps`, `nombre`, `descripcion`, `capacidad`) VALUES (1, '0-0-0', 'aula 1', 'administracion', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Proveedores`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Proveedores` (`idProveedores`, `cif`, `nombre`, `email`, `direccion`, `telef`, `pass`) VALUES (1, 'aaa', 'aaaa', 'aa@aa.com', 'aaaa', 'aaa', '555a9f1c4f2744ff8136e5ea15f49fdaec87dbc9f64f46445805f0872ce50254');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Stock` (`idStock`, `unidadesStock`, `limiteInferior`, `descripcion`, `precio`, `Proveedores_idProveedores`) VALUES (1, 1, 2, 'stockEquipos', 900.00, 1);
INSERT INTO `SanluisDB`.`Stock` (`idStock`, `unidadesStock`, `limiteInferior`, `descripcion`, `precio`, `Proveedores_idProveedores`) VALUES (2, 1, 1, 'stockLicenciasPhotoshop', 50.00, 1);
INSERT INTO `SanluisDB`.`Stock` (`idStock`, `unidadesStock`, `limiteInferior`, `descripcion`, `precio`, `Proveedores_idProveedores`) VALUES (3, 30, 5, 'Windows 10', 100.00, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Equipos`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Equipos` (`idEquipos`, `Aulas_idAulas`, `nombre`, `discoDuro`, `ram`, `ipEquipo`, `Stock_idStock`) VALUES (1, 1, 'equipo 1', 1, 1, '0-0', NULL);
INSERT INTO `SanluisDB`.`Equipos` (`idEquipos`, `Aulas_idAulas`, `nombre`, `discoDuro`, `ram`, `ipEquipo`, `Stock_idStock`) VALUES (2, NULL, 'equipo 2', 1, 1, '0-1', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Componentes`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Componentes` (`idComponentes`, `Equipos_idEquipos`, `Stock_idStock`, `descripcion`, `tipo`, `codLicencia`, `tipoHardware`, `marca`, `peso`) VALUES (1, NULL, 2, 'licenciaPhotoshop', 'soft', 'a001', NULL, 'x', 0);
INSERT INTO `SanluisDB`.`Componentes` (`idComponentes`, `Equipos_idEquipos`, `Stock_idStock`, `descripcion`, `tipo`, `codLicencia`, `tipoHardware`, `marca`, `peso`) VALUES (2, 1, 3, 'Windows 10', 'soft', 'a002', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Usuarios`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Usuarios` (`idUsuarios`, `Roles_idRol`, `Equipos_idEquipos`, `userName`, `email`, `pass`, `direccion`, `telefono`, `nombre`, `apellidos`) VALUES ('1', 3, NULL, 'admin', 'admin@admin.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'aaa', '919129839', 'admin', 'admin admin');
INSERT INTO `SanluisDB`.`Usuarios` (`idUsuarios`, `Roles_idRol`, `Equipos_idEquipos`, `userName`, `email`, `pass`, `direccion`, `telefono`, `nombre`, `apellidos`) VALUES ('2', 2, NULL, 'tecnico', 'tecnico@tecnico.com', 'b94570653d8c8d6622b664da72dd0f3cb13987bd006bf8a402d479ddcc02a2bb', 'bbbb', '029735973', 'tecnico', 'tecnico tecnico');
INSERT INTO `SanluisDB`.`Usuarios` (`idUsuarios`, `Roles_idRol`, `Equipos_idEquipos`, `userName`, `email`, `pass`, `direccion`, `telefono`, `nombre`, `apellidos`) VALUES ('3', 1, 1, 'usuario', 'usuario@usuario.com', '4f47c8605a23255480d3a30728526d69b70893e9c50f77da52689008035a10fc', 'cccc', '826489372', 'usuario', 'usuario usuario');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`SolInci`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`SolInci` (`idSolInci`, `Realizada`, `Asignada`, `Equipos_idEquipos`, `tipo`, `fechaSol`, `fechaFin`, `estado`, `descripcion`, `prioridad`, `Componentes_idComponentes`) VALUES (1, '1', '1', 1, 'sol', '2005-05-10', '2010-01-01', 'atendida', 'licencia photoshop', 2, 1);
INSERT INTO `SanluisDB`.`SolInci` (`idSolInci`, `Realizada`, `Asignada`, `Equipos_idEquipos`, `tipo`, `fechaSol`, `fechaFin`, `estado`, `descripcion`, `prioridad`, `Componentes_idComponentes`) VALUES (2, '1', '1', 1, 'inci', '2019-02-03', '2020-01-01', 'proceso', 'fallo sistema', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`EstadosCompra`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`EstadosCompra` (`idEstadosCompra`, `estado`) VALUES (1, 'aceptada');
INSERT INTO `SanluisDB`.`EstadosCompra` (`idEstadosCompra`, `estado`) VALUES (2, 'denegada');
INSERT INTO `SanluisDB`.`EstadosCompra` (`idEstadosCompra`, `estado`) VALUES (3, 'pendiente');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`compras`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`compras` (`idcompras`, `Proveedores_idProveedores`, `fechaCompra`, `precioTotal`, `idEstados`) VALUES (1, 1, '2019-06-07', 260, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Linea_compras`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Linea_compras` (`Stock_idStock`, `compras_idcompras`, `unidades`, `precio`) VALUES (2, 1, 3, 20);
INSERT INTO `SanluisDB`.`Linea_compras` (`Stock_idStock`, `compras_idcompras`, `unidades`, `precio`) VALUES (1, 1, 1, 200);

COMMIT;


-- -----------------------------------------------------
-- Data for table `SanluisDB`.`Requisitos`
-- -----------------------------------------------------
START TRANSACTION;
USE `SanluisDB`;
INSERT INTO `SanluisDB`.`Requisitos` (`idRequisitos`, `descripcion`, `clave`, `Componentes_idComponentes`) VALUES (1, 'requiere windows 10', 'windows10', 2);

COMMIT;

