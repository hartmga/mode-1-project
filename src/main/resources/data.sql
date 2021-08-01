
-- Create default users

-- admin user with password admin_pwd
INSERT INTO users (username, password, enabled) VALUES ('admin_user', '$2y$12$qGmNzsLdVfOKetd3XP.cCuh1abtBtWYM88Sc1RBN3CC2rwAgLKfAW', true);
INSERT INTO authorities values ('admin_user', 'ROLE_ADMIN');


-- user with password user_pwd
INSERT INTO users (username, password, enabled) values ('user', '$2y$12$h185Q2GaS2wt40GQ13qf9Oshd27UGAiZyL5/qMGdqCbQJbOofvqea', true);
INSERT INTO authorities values ('user', 'ROLE_USER');


-- Add initial products

INSERT INTO products (name, price, quantity) 
VALUES ('pencil', 0.99, 20);

INSERT INTO products (name, price, quantity) 
VALUES ('eraser', 0.25, 4);

INSERT INTO products (name, price, quantity) 
VALUES ('pen', 1.25, 12);

