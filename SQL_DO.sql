-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
drop database if exists mydb;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Formacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Formacion` (
  `id_Formacion` INT NOT NULL AUTO_INCREMENT,
  `nombre_curso` VARCHAR(45) NOT NULL,
  `num_horas` INT NOT NULL,
  PRIMARY KEY (`id_Formacion`))
ENGINE = InnoDB;

INSERT INTO formacion(nombre_curso, num_horas) VALUES ('Base de datos','300'),('Programacion','600'),('Aplicaciones Web','250'),('Sistemas Informaticos','275');

-- -----------------------------------------------------
-- Table `mydb`.`Formador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Formador` (
  `id_Formador` INT NOT NULL AUTO_INCREMENT,
  `nombre_profe` VARCHAR(45) NOT NULL,
  `apellido_profe` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Formador`))
ENGINE = InnoDB;
insert into formador (nombre_profe,apellido_profe) VALUES ('Gonzalo','Rodriguez'),('Pepito','Grillo'),('Juanjo','Orcasitas'),('Penelope','Moreno');


-- -----------------------------------------------------
-- Table `mydb`.`Alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Alumno` (
  `id_Alumno` INT NOT NULL auto_increment,
  `nombre_alumn` VARCHAR(45) NOT NULL,
  `apellido_alumn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Alumno`))
ENGINE = InnoDB;
insert into alumno(nombre_alumn, apellido_alumn) VALUES ('Oier','Gago'),('Denis','Funado'),('Sergio','Miota'),('Aitor','Quintana');


-- -----------------------------------------------------
-- Table `mydb`.`Turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Turno` (
  `id_turno` INT NOT NULL AUTO_INCREMENT,
  `horario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_turno`))
ENGINE = InnoDB;
insert into turno(horario) values ('Matutino'),('Vespertino');

-- -----------------------------------------------------
-- Table `mydb`.`Resena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Resena` (
  `id_Resena` INT NOT NULL AUTO_INCREMENT,
  `Descrip` TEXT(200) NOT NULL,
  `id_Alumno` INT NOT NULL,
  `id_Formacion` INT NOT NULL,
  PRIMARY KEY (`id_Resena`),
  INDEX `fk_Reseña_Alumno_idx` (`id_Alumno` ASC) VISIBLE,
  INDEX `fk_Reseña_Formacion1_idx` (`id_Formacion` ASC) VISIBLE,
  CONSTRAINT `fk_Reseña_Alumno`
    FOREIGN KEY (`id_Alumno`)
    REFERENCES `mydb`.`Alumno` (`id_Alumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reseña_Formacion1`
    FOREIGN KEY (`id_Formacion`)
    REFERENCES `mydb`.`Formacion` (`id_Formacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
Insert into resena(descrip,id_alumno,id_formacion) values ('Es un curso muy bueno','2','3'), ('Es un curso bastante malo','3','4'),
 ('No esta bien planteado el curso','1','3'), ('Es un castaña de curso','3','1');


-- -----------------------------------------------------
-- Table `mydb`.`Idioma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Idioma` (
  `id_Idioma` INT NOT NULL auto_increment,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_Idioma`))
ENGINE = InnoDB;
insert into idioma(descripcion) values ('Euskera'),('Castellano'),('Ingles');


-- -----------------------------------------------------
-- Table `mydb`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Curso` (
  `id_Curso` INT NOT NULL auto_increment,
  `fecha` DATE NOT NULL,
  `id_Formacion` INT NOT NULL,
  `id_turno` INT NOT NULL,
  `id_Formador` INT NOT NULL,
  `id_Idioma` INT NOT NULL,
  PRIMARY KEY (`id_Curso`),
  INDEX `fk_Curso_Formacion1_idx` (`id_Formacion` ASC) VISIBLE,
  INDEX `fk_Curso_Turno1_idx` (`id_turno` ASC) VISIBLE,
  INDEX `fk_Curso_Formador1_idx` (`id_Formador` ASC) VISIBLE,
  INDEX `fk_Curso_Idioma1_idx` (`id_Idioma` ASC) VISIBLE,
  CONSTRAINT `fk_Curso_Formacion1`
    FOREIGN KEY (`id_Formacion`)
    REFERENCES `mydb`.`Formacion` (`id_Formacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_Turno1`
    FOREIGN KEY (`id_turno`)
    REFERENCES `mydb`.`Turno` (`id_turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_Formador1`
    FOREIGN KEY (`id_Formador`)
    REFERENCES `mydb`.`Formador` (`id_Formador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_Idioma1`
    FOREIGN KEY (`id_Idioma`)
    REFERENCES `mydb`.`Idioma` (`id_Idioma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
insert into curso(fecha,id_formacion,id_turno,id_formador,id_idioma) values  ('2015-05-10','1','2','3','2'), (sysdate(),'1','2','3','2'),(sysdate(),'2','2','4','1'),(sysdate(),'3','1','2','3'),(sysdate(),'4','1','1','1');


-- -----------------------------------------------------
-- Table `mydb`.`Participantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Participantes` (
  `id_Participantes` INT NOT NULL AUTO_INCREMENT,
  `id_Alumno` INT NOT NULL,
  `id_Curso` INT NOT NULL,
  PRIMARY KEY (`id_Participantes`),
  INDEX `fk_Participantes_Alumno1_idx` (`id_Alumno` ASC) VISIBLE,
  INDEX `fk_Participantes_Curso1_idx` (`id_Curso` ASC) VISIBLE,
  CONSTRAINT `fk_Participantes_Alumno1`
    FOREIGN KEY (`id_Alumno`)
    REFERENCES `mydb`.`Alumno` (`id_Alumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Participantes_Curso1`
    FOREIGN KEY (`id_Curso`)
    REFERENCES `mydb`.`Curso` (`id_Curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
insert into participantes(id_alumno,id_curso) values ('1','2'),('1','3'),('1','4'),('2','1'),('3','1'),('3','2');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;