CREATE TABLE IF NOT EXISTS `profile` (
    `id` bigint NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`)
);
INSERT INTO profile values (1, "COORDENADOR_EXTENSAO");
INSERT INTO profile values (2, "COORDENADOR_PESQUISA");
INSERT INTO profile values (3, "COORDENADOR_INOVACAO");
INSERT INTO profile values (4, "ADMINISTRADOR");