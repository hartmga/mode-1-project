
DROP TABLE IF EXISTS user_authorities;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;

CREATE TABLE users(
	user_id bigint auto_increment,
	username varchar(45) unique,
    password varchar(64) NOT NULL,
    enabled boolean DEFAULT TRUE,
    primary key(user_id)
);

CREATE TABLE authorities(
	authority_id bigint auto_increment,
    role varchar(45) unique,
    primary key(authority_id)
);

CREATE TABLE user_authorities(
	user_id bigint,
    authority_id bigint,
    foreign key(user_id) references users(user_id),
    foreign key(authority_id) references authorities(authority_id),
    primary key(user_id, authority_id)
);

CREATE TABLE products(
	product_id bigint auto_increment,
    name varchar(45) NOT NULL,
    brand varchar(45),
    madein varchar(45),
    price double CHECK (price>=0) NOT NULL,
    quantity integer CHECK (quantity>=0) DEFAULT 0,
    primary key(product_id)
);


