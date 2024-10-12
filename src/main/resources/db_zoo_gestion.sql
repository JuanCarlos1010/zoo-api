CREATE DATABASE IF NOT EXISTS db_zoo_gestion;
USE db_zoo_gestion;

CREATE TABLE regiones (
	id_region INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE zonas (
	id_zona INT AUTO_INCREMENT PRIMARY KEY,
    id_region INT,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL,
    FOREIGN KEY (id_region) REFERENCES regiones(id_region)
);

CREATE TABLE animales (
	id_animal INT AUTO_INCREMENT PRIMARY KEY,
    id_zona INT,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    fecha_nacimiento DATETIME NOT NULL,
    genero CHAR(1) DEFAULT 'N',
    fecha_ingreso DATETIME NOT NULL,
    FOREIGN KEY (id_zona) REFERENCES zonas(id_zona)
);

CREATE TABLE alimentos (
	id_alimento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    unidad_medida VARCHAR(5) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE alimento_animal (
	id_alimento_animal INT AUTO_INCREMENT PRIMARY KEY,
    id_animal INT,
	id_alimento INT,
    porcion DOUBLE NOT NULL,
    fecha_consumo DATETIME NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES animales(id_animal),
	FOREIGN KEY (id_alimento) REFERENCES alimentos(id_alimento)
);


CREATE TABLE proveedores (
	id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(100)
);

-- ALTER TABLE proveedores DROP COLUMN cod_pais_telefono;

CREATE TABLE proveedor_alimento (
	id_proveedor_alimento INT AUTO_INCREMENT PRIMARY KEY,
	id_proveedor INT,
    id_alimento INT,
    fecha_suministro DATETIME NOT NULL,
    cantidad DOUBLE NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor),
	FOREIGN KEY (id_alimento) REFERENCES alimentos(id_alimento)
);

CREATE TABLE empleados (
	id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    puesto VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(100)
);

-- ALTER TABLE Empleados DROP COLUMN cod_pais_telefono;

CREATE TABLE tareas (
	id_tarea INT AUTO_INCREMENT PRIMARY KEY,
	id_empleado INT,
	descripcion VARCHAR(100),
	FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

CREATE TABLE zona_empleado (
	id_zona_empleado INT AUTO_INCREMENT PRIMARY KEY,
	id_zona INT,
	id_empleado INT,
	fecha_asignacion DATETIME NOT NULL,
	FOREIGN KEY (id_zona) REFERENCES zonas(id_zona),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado)
);

SELECT *FROM regiones;
SELECT *FROM zonas;
SELECT *FROM proveedores;

select
zonas.nombre,
zonas.tipo,
zonas.capacidad,

regiones.nombre
from zonas inner join regiones on (zonas.id_region = regiones.id_region);
