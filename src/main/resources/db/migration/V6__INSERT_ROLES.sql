INSERT INTO ROLE (id, nome)
VALUES (nextval('SEQ_ROLE'), 'LEITURA_ESCRITA');
INSERT INTO ROLE (id, nome)
VALUES (nextval('SEQ_ROLE'), 'LEITURA');
INSERT INTO USUARIO
VALUES (nextval('SEQ_USUARIO'), 'USUÁRIO DE SISTEMA', 'ADMIN', '$2a$12$54SkSua571qKUqTEomR9ke4T/kW7YIdku/.49sJlqYuu9hIxq/w.m');
INSERT INTO USUARIO_ROLE (ID, USUARIO_ID, ROLE_ID)
VALUES (nextval('SEQ_USUARIO_ROLE'), 1, 2);
INSERT INTO USUARIO_ROLE (ID, USUARIO_ID, ROLE_ID)
VALUES (nextval('SEQ_USUARIO_ROLE'), 2, 1);
