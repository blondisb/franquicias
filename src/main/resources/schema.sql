CREATE SCHEMA IF NOT EXISTS `franquiciasdb_v3` ;

CREATE TABLE IF NOT EXISTS `franquicias` (
  `idfranquicias` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idfranquicias`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `idfranquicias_UNIQUE` (`idfranquicias` ASC) VISIBLE);



  CREATE TABLE IF NOT EXISTS `sucursales` (
  `idsucursales` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `franquiciaid` int NOT NULL,
  PRIMARY KEY (`idsucursales`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `idsucursales_UNIQUE` (`idsucursales`),
  KEY `franquiciaid_fk_idx` (`franquiciaid`),
  CONSTRAINT `franquiciaid_fk`
    FOREIGN KEY (`franquiciaid`)
    REFERENCES `franquicias` (`idfranquicias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  );



CREATE TABLE IF NOT EXISTS `productos` (
  `idproductos` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idproductos`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `idproductos_UNIQUE` (`idproductos` ASC) VISIBLE);


CREATE TABLE IF NOT EXISTS`productos_por_sucursal` (
  `idproductos_por_sucursal` INT NOT NULL AUTO_INCREMENT,
  `idsuc` INT NOT NULL,
  `idprod` INT NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`idproductos_por_sucursal`),
  INDEX `idsucursal_idx` (`idsuc` ASC) VISIBLE,
  INDEX `idproductos_idx` (`idprod` ASC) VISIBLE,
  CONSTRAINT `idsuc_fk`
    FOREIGN KEY (`idsuc`)
    REFERENCES `sucursales` (`idsucursales`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idprod_fk`
    FOREIGN KEY (`idprod`)
    REFERENCES `productos` (`idproductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
