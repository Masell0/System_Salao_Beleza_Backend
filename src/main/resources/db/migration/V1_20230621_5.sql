INSERT INTO `usuario` (`email`, `password`, `nome`) VALUES ('admin@tcc.com', '123456', 'ADMIN ADMIN');

INSERT INTO `servicos` (`nome`, `valor`) VALUES ('UNHA', '50');
INSERT INTO `servicos` (`nome`, `valor`) VALUES ('CABELO', '50');
INSERT INTO `servicos` (`nome`, `valor`) VALUES ('SKIN CARE', '50');

INSERT INTO `perfil` (`nome`) VALUES ('ADMIN');
INSERT INTO `perfil` (`nome`) VALUES ('SUPERVISOR');
INSERT INTO `perfil` (`nome`) VALUES ('TRABALHADOR');
INSERT INTO `perfil` (`nome`) VALUES ('CLIENTE');

INSERT INTO `usuario_servicos` (`id_usuario`, `id_servicos`) VALUES ('1', '1');
INSERT INTO `usuario_servicos` (`id_usuario`, `id_servicos`) VALUES ('1', '2');

INSERT INTO `usuario_perfil` (`id_usuario`, `id_perfil`) VALUES ('1', '1');
