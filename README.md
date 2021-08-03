# mode-1-project
The project for mode 1 of the HCL training program.  
The project is currently incomplete.  
The MySql url and passord must be set in application.properties before running the applicaiton. 
When the app starts, it will drop and create the 'authorities', 'users', and 'products' tables from the MySQL database. There will be two default users set in data.sql: a user with username "user" and passoword "user\_pwd" and an admin with username "admin\_user and password admin\_pwd".   
It is a Spring Boot MVC application in which users can view a table of products and prices and edit the quantities of the products.  
Users with the Admin role also are able to create, delete, and edit products.  
