CREATE TABLE usuarios(
    id bigint NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE cursos(
       id BIGINT NOT NULL AUTO_INCREMENT,
       nombre VARCHAR(150) NOT NULL,
       categoria VARCHAR(50) NOT NULL,

       PRIMARY KEY(id)
);

CREATE TABLE topicos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estatus VARCHAR(50) NULL,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_topicos_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id)
);

CREATE TABLE respuestas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(1000) NOT NULL,
    topico_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);


