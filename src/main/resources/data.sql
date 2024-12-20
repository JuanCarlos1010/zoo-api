USE db_zoo_management;
INSERT INTO roles(id, name, created_at) VALUES (1, 'Veterinario', now());
INSERT INTO roles(id, name, created_at) VALUES (2, 'Admin', now());

INSERT INTO regions(name, created_at) VALUES ('COSTA', now());
INSERT INTO regions(name, created_at) VALUES ('SIERRA', now());
INSERT INTO regions(name, created_at) VALUES ('SELVA', now());

INSERT INTO users(role_id, username, full_name, password, document_number, created_at)
VALUES (1, 'admin@zoo.com.pe', 'Zoo Application', '$2a$10$VQ2H/ISb7ltZviGHhrx9.umzhe/B5nx8.sWw1oWZLpQv3ev3OghtO', NULL, now());


INSERT INTO permissions(name, system_name, module_name)
VALUES ('View dashboard', 'view_dashboard', 'Dashboard'),
       ('View animals', 'view_animals', 'Animals'),
       ('Create animal', 'create_animals', 'Animals'),
       ('Edit animal', 'edit_animals', 'Animals'),
       ('Delete animal', 'delete_animals', 'Animals'),
       ('View food', 'view_foods', 'Foods'),
       ('Create food', 'create_foods', 'Foods'),
       ('Edit food', 'edit_foods', 'Foods'),
       ('Delete food', 'delete_foods', 'Foods'),
       ('View shopping', 'view_shopping', 'Shopping'),
       ('Create shopping', 'create_shopping', 'Shopping'),
       ('Edit shopping', 'edit_shopping', 'Shopping'),
       ('Delete shopping', 'delete_shopping', 'Shopping'),
       ('View zone', 'view_zones', 'Zones'),
       ('Create zones', 'create_zones', 'Zones'),
       ('Edit zone', 'edit_zones', 'Zones'),
       ('Delete zone', 'delete_zones', 'Zones'),
       ('View employee', 'view_employees', 'Employees'),
       ('Create employee', 'create_employees', 'Employees'),
       ('Edit employee', 'edit_employees', 'Employees'),
       ('Delete employee', 'delete_employees', 'Employees'),
       ('View regions', 'view_regions', 'Regions'),
       ('Create region', 'create_regions', 'Regions'),
       ('Edit region', 'edit_regions', 'Regions'),
       ('Delete region', 'delete_regions', 'Regions'),
       ('View user', 'view_users', 'Users'),
       ('Create user', 'create_users', 'Users'),
       ('Edit user', 'edit_users', 'Users'),
       ('Delete user', 'delete_users', 'Users'),
       ('Create role', 'create_roles', 'Roles'),
       ('Edit role', 'edit_roles', 'Roles'),
       ('Delete role', 'delete_roles', 'Roles'),
       ('View ticket', 'view_tickets', 'Tickets'),
       ('Create ticket', 'create_tickets', 'Tickets'),
       ('Edit ticket', 'edit_tickets', 'Tickets'),
       ('Delete ticket', 'delete_tickets', 'Tickets'),
       ('View supplier', 'view_suppliers', 'Suppliers'),
       ('Create supplier', 'create_suppliers', 'Suppliers'),
       ('Edit supplier', 'edit_suppliers', 'Suppliers'),
       ('Delete supplier', 'delete_suppliers', 'Suppliers'),
       ('View veterinarian', 'view_vet', 'Veterinarian');
