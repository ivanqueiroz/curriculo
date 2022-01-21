CREATE TABLE USUARIO
(
    ID    INTEGER NOT NULL,
    NOME  VARCHAR(400),
    LOGIN VARCHAR(100),
    SENHA TEXT,
    PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_USUARIO INCREMENT BY 1 MINVALUE 1 START WITH 1;