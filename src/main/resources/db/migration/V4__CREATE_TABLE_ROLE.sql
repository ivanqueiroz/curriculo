CREATE TABLE ROLE
(
    ID   INTEGER NOT NULL,
    NOME VARCHAR(50),
    PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_ROLE INCREMENT BY 1 MINVALUE 1 START WITH 1;
