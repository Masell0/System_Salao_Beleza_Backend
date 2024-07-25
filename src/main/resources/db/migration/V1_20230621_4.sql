CREATE TABLE `usuario_servicos` (
  `id_usuario` INT NOT NULL,
  `id_servicos` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_servicos`),
  INDEX `USUARIO_PERFIL_ID_SERVICOS_idx` (`id_servicos` ASC) VISIBLE,
  CONSTRAINT `USUARIO_SERVICOS_ID_SERVICOS`
    FOREIGN KEY (`id_servicos`)
    REFERENCES `servicos` (`id`),
  CONSTRAINT `USUARIO_SERVICOS_ID_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `usuario` (`id`));

