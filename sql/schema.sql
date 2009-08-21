SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `SartoriWLC` DEFAULT CHARACTER SET latin1 ;
USE `SartoriWLC`;

-- -----------------------------------------------------
-- Table `label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `label` (
 `id_label` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
 `label_name` VARCHAR(20) NULL DEFAULT NULL ,
 `label_value` VARCHAR(45) NULL DEFAULT NULL ,
 PRIMARY KEY (`id_label`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `comparison`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `comparison` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `comp_uri` TEXT NULL DEFAULT NULL ,
 `definition_date` DATETIME NULL DEFAULT NULL ,
 `comp_desc` TEXT NULL DEFAULT NULL ,
 PRIMARY KEY (`id_comparison`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `comparison_label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `comparison_label` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_label` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_comparison`, `id_label`) ,
 INDEX `comp_label_FKIndex1` (`id_comparison` ASC) ,
 INDEX `comp_label_FKIndex2` (`id_label` ASC) ,
 CONSTRAINT `comp_label_FKlabel`
   FOREIGN KEY (`id_label` )
   REFERENCES `label` (`id_label` ),
 CONSTRAINT `comp_label_FKcomp`
   FOREIGN KEY (`id_comparison` )
   REFERENCES `comparison` (`id_comparison` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `person` (
 `id_person` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
 `first_name` VARCHAR(20) NULL DEFAULT NULL ,
 `last_name` VARCHAR(20) NULL DEFAULT NULL ,
 `citizenship` VARCHAR(20) NULL DEFAULT NULL ,
 `title` VARCHAR(20) NULL DEFAULT NULL ,
 `email` VARCHAR(80) NULL DEFAULT NULL ,
 `person_uri` TEXT NULL DEFAULT NULL ,
 PRIMARY KEY (`id_person`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `group` (
 `id_group` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
 `group_name` VARCHAR(50) NULL DEFAULT NULL ,
 `group_uri` VARCHAR(200) NULL DEFAULT NULL ,
 PRIMARY KEY (`id_group`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `group_label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `group_label` (
 `id_group` INT(10) UNSIGNED NOT NULL ,
 `id_label` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_group`, `id_label`) ,
 INDEX `group_label_FKIndex1` (`id_group` ASC) ,
 INDEX `group_label_FKIndex2` (`id_label` ASC) ,
 CONSTRAINT `group_label_FKlabel`
   FOREIGN KEY (`id_label` )
   REFERENCES `label` (`id_label` ),
 CONSTRAINT `group_label_FKgroup`
   FOREIGN KEY (`id_group` )
   REFERENCES `group` (`id_group` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `group_membership`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `group_membership` (
 `id_group` INT(10) UNSIGNED NOT NULL ,
 `id_person` INT(10) UNSIGNED NOT NULL ,
 `start_date` DATE NULL DEFAULT NULL ,
 `end_date` DATE NULL DEFAULT NULL ,
 PRIMARY KEY (`id_group`, `id_person`) ,
 INDEX `group_membership_FKIndex1` (`id_group` ASC) ,
 INDEX `group_membership_FKIndex2` (`id_person` ASC) ,
 CONSTRAINT `group_membership_FKgroup`
   FOREIGN KEY (`id_group` )
   REFERENCES `group` (`id_group` ),
 CONSTRAINT `group_membership_FKuser`
   FOREIGN KEY (`id_person` )
   REFERENCES `person` (`id_person` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `metric`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `metric` (
 `id_metric` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
 `metric_name` VARCHAR(20) NULL DEFAULT NULL ,
 `metric_desc` TEXT NULL DEFAULT NULL ,
 `metric_type` VARCHAR(20) NULL DEFAULT NULL ,
 `metric_uri` VARCHAR(255) NULL DEFAULT NULL ,
 PRIMARY KEY (`id_metric`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `metric_label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `metric_label` (
 `id_metric` INT(10) UNSIGNED NOT NULL ,
 `id_label` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_metric`, `id_label`) ,
 INDEX `metric_label_FKIndex1` (`id_metric` ASC) ,
 INDEX `metric_label_FKIndex2` (`id_label` ASC) ,
 CONSTRAINT `metric_label_FKlabel`
   FOREIGN KEY (`id_label` )
   REFERENCES `label` (`id_label` ),
 CONSTRAINT `metric_label_FKmetric`
   FOREIGN KEY (`id_metric` )
   REFERENCES `metric` (`id_metric` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `person_label`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `person_label` (
 `id_user` INT(10) UNSIGNED NOT NULL ,
 `id_label` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_user`, `id_label`) ,
 INDEX `user_label_FKIndex1` (`id_user` ASC) ,
 INDEX `user_label_FKIndex2` (`id_label` ASC) ,
 CONSTRAINT `user_label_FKlabel`
   FOREIGN KEY (`id_label` )
   REFERENCES `label` (`id_label` ),
 CONSTRAINT `user_label_FKuser`
   FOREIGN KEY (`id_user` )
   REFERENCES `person` (`id_person` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `group_comparison_result`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `group_comparison_result` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_metric` INT(10) UNSIGNED NOT NULL ,
 `computation_date` DATETIME NOT NULL ,
 `id_group` INT(10) UNSIGNED NOT NULL ,
 `is_latest` TINYINT(1) NOT NULL ,
 `result` VARCHAR(45) NULL ,
 PRIMARY KEY (`id_comparison`, `id_group`, `id_metric`, `computation_date`) ,
 INDEX `group_comp_result_FKmetric` (`id_metric` ASC) ,
 INDEX `group_comp_result_FKgroup` (`id_group`) ,
 CONSTRAINT `group_comp_result_FKmetric`
   FOREIGN KEY (`id_metric` )
   REFERENCES `metric` (`id_metric` ),
 CONSTRAINT `group_comp_result_FKgroup`
   FOREIGN KEY (`id_group`)
   REFERENCES `group` (`id_group`),

 CONSTRAINT `group_comp_result_FKcomp`

   FOREIGN KEY (`id_comparison`)

   REFERENCES `comparison` (`id_comparison`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `person_comparison_result`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `person_comparison_result` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_metric` INT(10) UNSIGNED NOT NULL ,
 `computation_date` DATETIME NOT NULL ,
 `id_person` INT(10) UNSIGNED NOT NULL ,
 `is_latest` TINYINT(1) NOT NULL ,
 `result` VARCHAR(45) NULL ,
 PRIMARY KEY (`id_comparison`, `id_person`, `id_metric`, `computation_date`) ,
 INDEX `person_comp_result_FKmetric` (`id_metric` ASC) ,
 INDEX `person_comp_result_FKperson` (`id_person`) ,
 CONSTRAINT `person_comp_result_FKmetric`
   FOREIGN KEY (`id_metric` )
   REFERENCES `metric` (`id_metric` ),
 CONSTRAINT `person_comp_result_FKperson`
   FOREIGN KEY (`id_person`)
   REFERENCES `person` (`id_person`),
CONSTRAINT `person_comp_result_FKcomp`

   FOREIGN KEY (`id_comparison`)

   REFERENCES `comparison` (`id_comparison`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `compared_group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `compared_group` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_group` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_comparison`, `id_group`) ,
 INDEX `compared_group_FKcomparison` (`id_comparison`) ,
 CONSTRAINT `compared_group_FKgroup`
   FOREIGN KEY (`id_group`)
   REFERENCES `group` (`id_group`),
 CONSTRAINT `compared_group_FKcomparison`
   FOREIGN KEY (`id_comparison`)
   REFERENCES `comparison` (`id_comparison`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `compared_metric`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `compared_metric` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_metric` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_comparison`, `id_metric`) ,
 INDEX `compared_metric_FKcomparison` (`id_comparison`) ,
 CONSTRAINT `compared_metric_FKmetric`
   FOREIGN KEY (`id_metric`)
   REFERENCES `metric` (`id_metric`),
 CONSTRAINT `compared_metric_FKcomparison`
   FOREIGN KEY (`id_comparison` )
   REFERENCES `comparison` (`id_comparison` )
	 ON DELETE NO ACTION
   ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compared_person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `compared_person` (
 `id_comparison` INT(10) UNSIGNED NOT NULL ,
 `id_person` INT(10) UNSIGNED NOT NULL ,
 PRIMARY KEY (`id_comparison`, `id_person`) ,
 INDEX `compared_person_FKcomparison` (`id_comparison`) ,
 CONSTRAINT `compared_person_FKperson`
   FOREIGN KEY (`id_person`)
   REFERENCES `person` (`id_person`),
 CONSTRAINT `compared_person_FKcomparison`
   FOREIGN KEY (`id_comparison` )
   REFERENCES `comparison` (`id_comparison` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;
