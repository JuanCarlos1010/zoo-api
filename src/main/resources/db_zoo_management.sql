CREATE DATABASE IF NOT EXISTS db_zoo_management;
USE db_zoo_management;

create table roles (
    role_id int         auto_increment primary key,
    name    varchar(70) unique not null,
    index (name)
);

create table permissions (
    permission_id int         auto_increment primary key,
    name          varchar(50) null,
    module_name   varchar(40) null,
    index (name)
);

create table role_permissions (
    id            bigint  auto_increment primary key,
    role_id       int not null,
    permission_id int not null,
    constraint fk_role_permission_role foreign key (role_id) references roles(role_id),
    constraint fk_role_permission_permission foreign key (permission_id) references permissions(permission_id)
);

create table users (
   user_id         int          auto_increment primary key,
   role_id         int          not null,
   username        varchar(70)  unique not null,
   password        varchar(255) null,
   document_number varchar(20)  null,
   phone           varchar(15)  null,
   address         varchar(255) null,
   index (username),
   constraint fk_users_role foreign key (role_id) references roles(role_id)
);

create table tickets (
     ticket_id      int          auto_increment primary key,
     user_id        int          not null,
     status         bit          not null,
     price          double       null,
     type           varchar(40)  null,
     visit_date     timestamp    not null,
     visitor_name   varchar(100) null,
     payment_method varchar(50)  null,
     constraint fk_tickets_users foreign key (user_id) references users(user_id)
);

create table employees (
    employee_id int          auto_increment primary key,
    name        varchar(80)  null,
    email       varchar(100) unique not null,
    address     varchar(255) null,
    phone       varchar(15)  null,
    position    varchar(50)  null,
    description varchar(255) null,
    index (name)
);

create table regions (
    region_id int          auto_increment primary key,
    name      varchar(100) unique not null,
    index (name)
);

create table zones (
    zone_id   int          auto_increment primary key,
    name      varchar(50)  null,
    region_id int          not null,
    capacity  int          not null,
    type      varchar(100) null,
    index (name),
    constraint fk_zones_regions foreign key (region_id) references regions(region_id)
);

create table animals (
    animal_id       int          auto_increment primary key,
    zone_id         int          not null,
    name            varchar(100) null,
    birthdate       datetime(6)  null,
    entry_date      timestamp    null,
    gender          varchar(20)  null,
    species         varchar(50)  null,
    age             int          null,
    description     varchar(255) null,
    index (name),
    constraint fk_animals_zones foreign key (zone_id) references zones(zone_id)
);

create table foods (
    food_id          int          auto_increment primary key,
    stock            int          not null,
    name             varchar(255) null,
    type             varchar(50)  null,
    unit_measurement varchar(15)  null,
    index (name)
);

create table suppliers (
    supplier_id int          auto_increment primary key,
    name        varchar(150) null,
    email       varchar(100) unique,
    address     varchar(255) null,
    phone       varchar(15)  null,
    index (name)
);

create table supplier_foods (
    id               bigint    auto_increment primary key,
    food_id          int       null,
    supplier_id      int       null,
    amount           int       not null,
    supply_date      timestamp not null,
    expiration_date  timestamp  null,
    constraint fk_supplier_foods_food foreign key (food_id) references foods(food_id),
    constraint fk_supplier_foods_supplier foreign key (supplier_id) references suppliers(supplier_id)
);

create table food_animals (
    id               int         auto_increment primary key,
    food_id          int         not null,
    animal_id        int         not null,
    portion          double      not null,
    consumption_date timestamp   null,
    constraint fk_food_animals_foods foreign key (food_id) references foods(food_id),
    constraint fk_food_animals_animals foreign key (animal_id) references animals(animal_id)
);

create table zone_employees (
    id               int       auto_increment primary key,
    zone_id          int       null,
    employee_id      int       null,
    assignment_date  timestamp null,
    constraint fk_zone_employees_zones foreign key (zone_id) references zones(zone_id),
    constraint fk_zone_employees_employees foreign key (employee_id) references employees(employee_id)
);