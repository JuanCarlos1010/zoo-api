CREATE DATABASE IF NOT EXISTS db_zoo_management;
USE db_zoo_management;

CREATE TABLE IF NOT EXISTS roles
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       varchar(70) UNIQUE NOT NULL,
    created_at TIMESTAMP          NOT NULL,
    updated_at TIMESTAMP          NULL,
    deleted_at TIMESTAMP          NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS permissions
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    system_name VARCHAR(40) UNIQUE NOT NULL,
    module_name VARCHAR(40)        NULL,
    description VARCHAR(50)        NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS role_permissions
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    role_id       INT,
    permission_id INT,
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NULL,
    deleted_at    TIMESTAMP NULL,
    CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    role_id         INT,
    username        VARCHAR(70) UNIQUE NOT NULL,
    full_name       VARCHAR(100)       NOT NULL,
    password        VARCHAR(255)       NOT NULL,
    document_number VARCHAR(20)        NULL,
    phone           VARCHAR(15)        NULL,
    address         VARCHAR(255)       NULL,
    created_at      TIMESTAMP          NOT NULL,
    updated_at      TIMESTAMP          NULL,
    deleted_at      TIMESTAMP          NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles (id),
    INDEX (username)
) AUTO_INCREMENT=122812;

CREATE TABLE IF NOT EXISTS tickets
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT,
    status         BOOLEAN DEFAULT FALSE,
    price          DOUBLE  DEFAULT 0.0,
    type           VARCHAR(40)  NULL,
    code           VARCHAR(20)  NULL,
    visit_date     TIMESTAMP    NOT NULL,
    visitor_name   VARCHAR(100) NULL,
    payment_method VARCHAR(50)  NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NULL,
    deleted_at     TIMESTAMP    NULL,
    CONSTRAINT fk_ticket_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS employees
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(80)         NOT NULL,
    email       VARCHAR(100) UNIQUE NULL,
    address     VARCHAR(255)        NULL,
    phone       VARCHAR(15)         NULL,
    position    VARCHAR(50)         NULL,
    documentNumber VARCHAR(50)         NULL,
    description VARCHAR(255)        NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at  TIMESTAMP           NULL,
    deleted_at  TIMESTAMP           NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS regions
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) UNIQUE NOT NULL,
    description VARCHAR(255) NULL,
    created_at TIMESTAMP           NOT NULL,
    updated_at TIMESTAMP           NULL,
    deleted_at TIMESTAMP           NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS zones
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    region_id  INT,
    name       VARCHAR(50)  NOT NULL,
    capacity   INT DEFAULT 0,
    type       VARCHAR(100) NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NULL,
    deleted_at TIMESTAMP    NULL,
    CONSTRAINT fk_zone_region FOREIGN KEY (region_id) REFERENCES regions (id),
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS animals
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    zone_id     INT          NOT NULL,
    name        VARCHAR(100) NOT NULL,
    birthdate   TIMESTAMP    NULL,
    entry_date  TIMESTAMP    NULL,
    gender      VARCHAR(20)  NULL,
    species     VARCHAR(50)  NULL,
    age         VARCHAR(20)  NULL,
    description VARCHAR(255) NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NULL,
    deleted_at  TIMESTAMP    NULL,
    CONSTRAINT fk_animal_zone FOREIGN KEY (zone_id) REFERENCES zones (id),
    INDEX (name)
) AUTO_INCREMENT=1562;

CREATE TABLE IF NOT EXISTS medicines
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    description     VARCHAR(255) NULL,
    expired_at      DATE NULL,
    manufacturer    VARCHAR(255) NULL,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP    NULL,
    deleted_at      TIMESTAMP    NULL
);

CREATE TABLE IF NOT EXISTS treatments
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    animal_id    INT          NULL,
    veterinarian_id  INT          NULL,
    diagnosis    VARCHAR(255) NULL,
    comments   VARCHAR(255) NULL,
    apply_date   TIMESTAMP    NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NULL,
    deleted_at   TIMESTAMP    NULL,
    CONSTRAINT fk_treatment_animal FOREIGN KEY (animal_id) REFERENCES animals (id),
    CONSTRAINT fk_treatment_veterinarian FOREIGN KEY (veterinarian_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS treatment_medicines
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    medicine_id  INT,
    treatment_id INT,
    dosage      VARCHAR(255) NOT NULL,
    comment     VARCHAR(255) NOT NULL,
    CONSTRAINT fk_treatment_medicine_medicine FOREIGN KEY (medicine_id) REFERENCES medicines (id),
    CONSTRAINT fk_treatment_medicine_treatment FOREIGN KEY (treatment_id) REFERENCES treatments (id)
);

CREATE TABLE IF NOT EXISTS foods
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    stock            DOUBLE DEFAULT 0.0,
    name             VARCHAR(255) NOT NULL,
    type             VARCHAR(50)  NULL,
    unit_measurement VARCHAR(15)  NULL,
    expired_at       DATE    NULL,
    created_at       TIMESTAMP    NOT NULL,
    updated_at       TIMESTAMP    NULL,
    deleted_at       TIMESTAMP    NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS suppliers
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(150) NOT NULL,
    email      VARCHAR(100) UNIQUE,
    phone      VARCHAR(15)  NULL,
    address    VARCHAR(255) NULL,
    documentNumber VARCHAR(50)         NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NULL,
    deleted_at TIMESTAMP    NULL,
    INDEX (name)
);

CREATE TABLE IF NOT EXISTS shopping
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    supplier_id INT,
    supply_date TIMESTAMP NOT NULL,
    total       DOUBLE DEFAULT 0.0,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NULL,
    deleted_at  TIMESTAMP NULL,
    CONSTRAINT fk_supplier_food_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);

CREATE TABLE IF NOT EXISTS shopping_items
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    food_id     INT,
    shopping_id INT,
    quantity    DOUBLE DEFAULT 0.0,
    price       DOUBLE DEFAULT 0.0,
    CONSTRAINT fk_shopping_item_food FOREIGN KEY (food_id) REFERENCES foods (id),
    CONSTRAINT fk_shopping_item_shopping FOREIGN KEY (shopping_id) REFERENCES shopping (id)
);

CREATE TABLE IF NOT EXISTS food_animals
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    food_id          INT,
    animal_id        INT,
    portion          DOUBLE DEFAULT 0.0,
    consumption_date TIMESTAMP NOT NULL,
    created_at       TIMESTAMP NOT NULL,
    updated_at       TIMESTAMP NULL,
    deleted_at       TIMESTAMP NULL,
    CONSTRAINT fk_food_animal_food FOREIGN KEY (food_id) REFERENCES foods (id),
    CONSTRAINT fk_food_animal_animal FOREIGN KEY (animal_id) REFERENCES animals (id)
);

CREATE TABLE IF NOT EXISTS zone_employees
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    zone_id         INT,
    employee_id     INT,
    assignment_date TIMESTAMP NULL,
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NULL,
    deleted_at      TIMESTAMP NULL,
    CONSTRAINT fk_zone_employee_zone FOREIGN KEY (zone_id) REFERENCES zones (id),
    CONSTRAINT fk_zone_employee_employee FOREIGN KEY (employee_id) REFERENCES employees (id)
);
