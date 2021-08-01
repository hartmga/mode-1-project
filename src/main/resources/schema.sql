
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;

CREATE TABLE users(
	username varchar(45),
    password varchar(64) NOT NULL,
    enabled boolean DEFAULT TRUE,
    primary key(username)
);

CREATE TABLE authorities(
	username varchar(45),
    role varchar(45),
    foreign key(username) references users(username),
    primary key(username, role)
);

CREATE TABLE products(
	product_id integer auto_increment,
    name varchar(45),
    price double CHECK (price>=0),
    quantity integer CHECK (quantity>=0),
    primary key(product_id)
);


