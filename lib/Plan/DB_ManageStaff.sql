
create database manage_staff_fx;
use manage_staff_fx;

-- Bảng liệt kê vị trí trong công ty  
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `salary` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- bảng liệt kê các phòng ban trong công ty
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- bảng danh sách nhân viên cùng các khóa liên quan 
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`staff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(45) NULL,
  `gender` INT NULL,
  `dob` DATE NULL,
  `phoneNumber` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `department_id` INT NULL,
  `position_id` INT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_staff_deparment_id_idx` (`department_id` ASC) VISIBLE,
  INDEX `FK_staff_position_id_idx` (`position_id` ASC) VISIBLE,
  CONSTRAINT `FK_staff_deparment_id`
    FOREIGN KEY (`department_id`)
    REFERENCES `manage_staff_fx`.`departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_staff_position_id`
    FOREIGN KEY (`position_id`)
    REFERENCES `manage_staff_fx`.`position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- bảng chấm công thông qua id nhân viên
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`timekeeping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `present` VARCHAR(45) NOT NULL,
  `absent` VARCHAR(45) NOT NULL,
  `staff_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_timekeeping_staff_id_idx` (`staff_id` ASC) VISIBLE,
  CONSTRAINT `FK_timekeeping_staff_id`
    FOREIGN KEY (`staff_id`)
    REFERENCES `manage_staff_fx`.`staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Quyền truy cập mỗi account
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`roll` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- bảng account mỗi nhân viên trong công ty qua id staff
CREATE TABLE IF NOT EXISTS `manage_staff_fx`.`account` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `staff_id` INT NOT NULL,
  `roll_id` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_account_staff_id_idx` (`staff_id` ASC) VISIBLE,
  INDEX `FK_account-roll_id_idx` (`roll_id` ASC) VISIBLE,
  CONSTRAINT `FK_account_staff_id`
    FOREIGN KEY (`staff_id`)
    REFERENCES `manage_staff_fx`.`staff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_account-roll_id`
    FOREIGN KEY (`roll_id`)
    REFERENCES `manage_staff_fx`.`roll` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
select * from roll;
select * from staff;
select * from account;
INSERT INTO roll (name)
VALUES ("USER");

