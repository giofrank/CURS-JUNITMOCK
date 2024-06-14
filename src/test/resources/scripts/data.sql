
--LUGAR
INSERT INTO venta_entradas.lugar
( nombre, direccion, capacidad, estado)
VALUES('Estadio Monumental', ' avenida Javier Prado 7596 del distrito de Ate', 80000, '1');

INSERT INTO venta_entradas.lugar
( nombre, direccion, capacidad, estado)
VALUES('Estadio Nacional', 'Vía Expresa Paseo de la República con los jirones Sebastián Barranca y Madre de Dios, en el límite de los distritos de Lima', 43000, '1');

INSERT INTO venta_entradas.lugar
( nombre, direccion, capacidad, estado)
VALUES('Estadio Olímpico de San Marcos', ' Ciudad Universitaria - UNMSM, Jr. Francisco Moreyra y Riglos 637, Lima 15081', 32000, '1');

--TIPO ENTRADA
INSERT INTO venta_entradas.tipo_entrada
( nombre, precio_base, estado)
VALUES('GENERAL', 20, '1');
INSERT INTO venta_entradas.tipo_entrada
( nombre, precio_base, estado)
VALUES('ORIENTE', 100, '1');
INSERT INTO venta_entradas.tipo_entrada
( nombre, precio_base, estado)
VALUES('OCCIDENTE', 120, '1');

--ASIENTOS
INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'F', 10, 1, '1');

INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'F', 11, 1, '1');

INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'F', 12, 1, '1');

INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'G', 10, 2, '1');

INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'G', 11, 2, '1');

INSERT INTO venta_entradas.asiento
( fila, numero, lugar_id, estado)
VALUES( 'G', 12, 2, '1');

--EVENTO


INSERT INTO venta_entradas.evento
( nombre, descripcion, fecha, hora, lugar_id, estado)
VALUES( 'UNIVERSITARIO VS ADT TARMA','Evento por la 1ra fecha del clausura peruano', '2024-06-20', '13:40:00',  1, '1');

INSERT INTO venta_entradas.evento
( nombre, descripcion, fecha, hora, lugar_id, estado)
VALUES( 'UNIVERSITARIO VS CUSCO FC','Evento por la 3ra fecha del clausura peruano', '2024-06-27', '15:30:00',  1, '1');

INSERT INTO venta_entradas.evento
( nombre, descripcion, fecha, hora, lugar_id, estado)
VALUES( 'SPORTING CRISTAL VS VALLEJO','Evento por la 1ra fecha del clausura peruano', '2024-06-21', '19:30:00',  1, '1');


--CLIENTE

INSERT INTO venta_entradas.cliente
( tipo_documento, documento, nombre, apellido, email, telefono, direccion, estado)
VALUES('01', '71775164', 'ENRIQUE', 'SALCEDO ROJAS', 'ENRIQUE@gmail.com', '987654321', 'Av. Los proceres Lima', '1');

INSERT INTO venta_entradas.cliente
( tipo_documento, documento, nombre, apellido, email, telefono, direccion, estado)
VALUES('01', '71775165', 'JOSE', 'PEREZ ROJAS', 'JOSE@gmail.com', '989654321', 'Av. Los Candamo Lima', '1');

INSERT INTO venta_entradas.cliente
( tipo_documento, documento, nombre, apellido, email, telefono, direccion, estado)
VALUES('01', '71775166', 'JORGE', 'LOPEZ ROJAS', 'JORGE@gmail.com', '985654321', 'Av. Arequipa Lima', '1');

INSERT INTO venta_entradas.cliente
( tipo_documento, documento, nombre, apellido, email, telefono, direccion, estado)
VALUES('01', '71775167', 'MANRIQUE', 'ARANA ROJAS', 'MANRIQUE@gmail.com', '984654321', 'Av. Iquitos Lima', '1');