-- MySQL Script generated by MySQL Workbench
-- Thu Apr 29 22:01:05 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema myproduct
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `myproduct` ;

-- -----------------------------------------------------
-- Schema myproduct
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myproduct` DEFAULT CHARACTER SET utf8 ;
USE `myproduct` ;

-- -----------------------------------------------------
-- Table `myproduct`.`brands`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myproduct`.`brands` ;

CREATE TABLE IF NOT EXISTS `myproduct`.`brands` (
  `brandid` INT NOT NULL AUTO_INCREMENT,
  `brandname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`brandid`),
  UNIQUE INDEX `bandid_UNIQUE` (`brandid` ASC) VISIBLE,
  UNIQUE INDEX `bandname_UNIQUE` (`brandname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myproduct`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myproduct`.`products` ;

CREATE TABLE IF NOT EXISTS `myproduct`.`products` (
  `prodcode` INT NOT NULL AUTO_INCREMENT,
  `prodname` VARCHAR(45) NOT NULL,
  `proddescription` VARCHAR(1000) NOT NULL,
  `price` DOUBLE NOT NULL,
  `prodmanufactured` DATE NOT NULL,
  `bands_bandid` INT NOT NULL,
  PRIMARY KEY (`prodcode`, `bands_bandid`),
  UNIQUE INDEX `productCode_UNIQUE` (`prodcode` ASC) VISIBLE,
  UNIQUE INDEX `prodname_UNIQUE` (`prodname` ASC) VISIBLE,
  INDEX `fk_products_bands1_idx` (`bands_bandid` ASC) VISIBLE,
  CONSTRAINT `fk_products_bands1`
    FOREIGN KEY (`bands_bandid`)
    REFERENCES `myproduct`.`brands` (`brandid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myproduct`.`colors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myproduct`.`colors` ;

CREATE TABLE IF NOT EXISTS `myproduct`.`colors` (
  `colorid` INT NOT NULL AUTO_INCREMENT,
  `colorname` VARCHAR(20) NOT NULL,
  `colorvalue` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`colorid`),
  UNIQUE INDEX `colorid_UNIQUE` (`colorid` ASC) VISIBLE,
  UNIQUE INDEX `colorname_UNIQUE` (`colorname` ASC) VISIBLE,
  UNIQUE INDEX `colorvalue_UNIQUE` (`colorvalue` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myproduct`.`productcolor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myproduct`.`productcolor` ;

CREATE TABLE IF NOT EXISTS `myproduct`.`productcolor` (
  `colorid` INT NOT NULL,
  `prodcode` INT NOT NULL,
  PRIMARY KEY (`colorid`, `prodcode`),
  INDEX `fk_colors_has_products_products1_idx` (`prodcode` ASC) VISIBLE,
  INDEX `fk_colors_has_products_colors_idx` (`colorid` ASC) VISIBLE,
  CONSTRAINT `fk_colors_has_products_colors`
    FOREIGN KEY (`colorid`)
    REFERENCES `myproduct`.`colors` (`colorid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_colors_has_products_products1`
    FOREIGN KEY (`prodcode`)
    REFERENCES `myproduct`.`products` (`prodcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
