CREATE TABLE `usuario_perfil` (
  `id_usuario` INT NOT NULL,
  `id_perfil` INT NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_perfil`),
  INDEX `USUARIO_PERFIL_ID_PERFIL_idx` (`id_perfil` ASC),
  CONSTRAINT `USUARIO_PERFIL_ID_USUARIO`
    FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USUARIO_PERFIL_ID_PERFIL`
    FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);
