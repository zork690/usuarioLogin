DROP TABLE IF EXISTS USUARIO;

CREATE TABLE USUARIO (
  LOGIN VARCHAR2 (20 Byte) PRIMARY KEY NOT NULL,
  CLAVE VARCHAR2 (30 Byte) NOT NULL,
  NOMBRE VARCHAR2 (50 Byte) NOT NULL,
  CLIENTE FLOAT NOT NULL,
  FECHAMODIFICACION DATE DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO USUARIO (LOGIN, CLAVE, NOMBRE, CLIENTE) VALUES
  ('DKDKFDKLDF', 'skdkkdkd', 'skdskkd Industrialist', 1);
  
  INSERT INTO USUARIO (LOGIN, CLAVE, NOMBRE, CLIENTE) VALUES
  ('fff', 'pass', 'cesar', 1);



