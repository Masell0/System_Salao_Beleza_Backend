CREATE TABLE `agendamentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `nome_cliente` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `status` INT NOT NULL,
  `data_agendamento` DATETIME NOT NULL,
  `valor_final` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `USUARIO_FK_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `USUARIO_FK`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`));
    
    CREATE TABLE `agendamentos_servicos` (
  `id_agendamento` INT NOT NULL,
  `id_servicos` INT NOT NULL,
  PRIMARY KEY (`id_agendamento`, `id_servicos`),
  INDEX `AGENDAMENTO_SERVICOS_FK_SERVICOS_idx` (`id_servicos` ASC) VISIBLE,
  CONSTRAINT `AGENDAMENTO_SERVICOS_FK_AGENDAMENTO`
    FOREIGN KEY (`id_agendamento`)
    REFERENCES `agendamentos` (`id`),
  CONSTRAINT `AGENDAMENTO_SERVICOS_FK_SERVICOS`
    FOREIGN KEY (`id_servicos`)
    REFERENCES `servicos` (`id`));

