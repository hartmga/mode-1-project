
-- Create default users

-- admin user with password admin_pwd
INSERT INTO users (username, password, enabled) VALUES ('admin_user', '$2y$12$qGmNzsLdVfOKetd3XP.cCuh1abtBtWYM88Sc1RBN3CC2rwAgLKfAW', true);
INSERT INTO authorities (role) values ('ROLE_ADMIN');
INSERT INTO user_authorities values(1,1);


-- user with password user_pwd
INSERT INTO users (username, password, enabled) values ('user', '$2y$12$h185Q2GaS2wt40GQ13qf9Oshd27UGAiZyL5/qMGdqCbQJbOofvqea', true);
INSERT INTO authorities (role) values ('ROLE_USER');
INSERT INTO user_authorities values(2,2);


-- Add initial products

INSERT INTO products (name, brand, madein, price, quantity) 
VALUES ('pencil', 'Ticonderoga', 'somewhere', 0.99, 20);

INSERT INTO products (name, brand, madein, price, quantity) 
VALUES ('eraser', 'Pentel', 'somewhere else', 0.25, 4);

INSERT INTO products (name, brand, madein, price, quantity) 
VALUES ('pen', 'Pilot', 'a place', 1.25, 12);

