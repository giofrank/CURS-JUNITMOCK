DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS entrada;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS asiento;
DROP TABLE IF EXISTS tipo_entrada;
DROP TABLE IF EXISTS lugar;



-- Crear la tabla Lugar
CREATE TABLE lugar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    estado CHAR(1)
);

-- Crear la tabla Cliente
CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_documento VARCHAR(2) NOT NULL,
    documento VARCHAR(15) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(255),
    estado CHAR(1)
);

-- Crear la tabla Asiento
CREATE TABLE asiento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fila VARCHAR(10) NOT NULL,
    numero INT NOT NULL,
    lugar_id INT,
    estado CHAR(1),
    FOREIGN KEY (lugar_id) REFERENCES lugar(id)
);

-- Crear la tabla Evento
CREATE TABLE evento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    lugar_id INT,
    estado CHAR(1),
    FOREIGN KEY (lugar_id) REFERENCES lugar(id)
);

-- Crear la tabla TipoEntrada
CREATE TABLE tipo_entrada (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    precio_base DECIMAL(10,2) NOT NULL,
    estado CHAR(1)
);



-- Crear la tabla Entrada
CREATE TABLE entrada (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cuuid CHAR(36) NOT NULL,
    evento_id INT,
    cliente_id INT,
    tipo_entrada_id INT,
    asiento_id INT,
    fecha_compra DATETIME DEFAULT CURRENT_TIMESTAMP,
    precio_final DECIMAL(10,2) NOT NULL,
    estado CHAR(1),
    UNIQUE KEY entrada_unique (cuuid),
    FOREIGN KEY (evento_id) REFERENCES evento(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (tipo_entrada_id) REFERENCES tipo_entrada(id),
    FOREIGN KEY (asiento_id) REFERENCES asiento(id)
);
-- Crear la tabla Pago
CREATE TABLE pago (
    id INT PRIMARY KEY AUTO_INCREMENT,
    entrada_id INT,
    monto DECIMAL(10,2) NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    metodo_pago VARCHAR(50) NOT NULL,
    estado CHAR(1) DEFAULT '1',
    FOREIGN KEY (entrada_id) REFERENCES entrada(id)
);