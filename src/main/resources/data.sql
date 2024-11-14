USE db_zoo_management;

INSERT INTO roles (name) VALUES ('Admin'), ('Manager');

INSERT INTO permissions (name, module_name) VALUES
    ('view_animals', 'Animals'),
    ('add_animals', 'Animals'),
    ('edit_animals', 'Animals'),
    ('delete_animals', 'Animals'),
    ('view_employees', 'Employees'),
    ('add_employees', 'Employees'),
    ('edit_employees', 'Employees'),
    ('delete_employees', 'Employees'),
    ('view_reports', 'Reports'),
    ('view_settings', 'Settings'),
    ('edit_settings', 'Settings'),
    ('view_users', 'Users'),
    ('add_users', 'Users'),
    ('edit_users', 'Users'),
    ('delete_users', 'Users'),
    ('view_permissions', 'Permissions');

# INSERT INTO role_permissions(role_id, permission_id) VALUES (2, 1), (2, 2),(2, 3);
INSERT INTO users(role_id, username, password) VALUES (2, 'testing', 'testing');


# select * from roles;
# select * from role_permissions;

#FIN
