CREATE TABLE IF NOT EXISTS `perfil` (
    `id` bigint NOT NULL,
    `nome` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`nome`)
);
INSERT INTO perfil values (1, "COORDENADOR_EXTENSAO");
INSERT INTO perfil values (2, "COORDENADOR_PESQUISA");
INSERT INTO perfil values (3, "COORDENADOR_INOVACAO");
INSERT INTO perfil values (4, "ADMINISTRADOR");