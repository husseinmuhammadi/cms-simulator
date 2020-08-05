# E-SHOP WORKSHOP
A workshop based on react as frontend and spring boot as backend

## DATABASE CONFIGURATION
### MARIADB
#### CREATE USER
````
$mysql -u root -p
CREATE DATABASE cms_test;
CREATE USER cms_test@localhost IDENTIFIED BY '123456';
SELECT user, host FROM mysql.user;
GRANT ALL PRIVILEGES ON cms_test.* TO cms_test@localhost;
SHOW GRANTS FOR tasnim@localhost;
````
#### TABLES
````
MariaDB [tasnim]> show columns from users;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| username | varchar(255) | NO   | PRI | NULL    |       |
| email    | varchar(255) | YES  |     | NULL    |       |
| password | varchar(255) | YES  |     | NULL    |       |
+----------+--------------+------+-----+---------+-------+
````

````
SHOW CREATE TABLE role;
````

## Spring Security with JWT
### Spring Security Entry Point
By default, the BasicAuthenticationEntryPoint provisioned by Spring Security returns 
a full page for a 401 Unauthorized response back to the client. This HTML representation 
of the error renders well in a browser, but it not well suited for other scenarios, 
such as a REST API where a json representation may be preferred.

# Spring Boot Multi-Module Project 
https://github.com/drahkrub/spring-boot-multi-module

# Debug 
````
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
````


# Error Handling (Web Services) 
https://dzone.com/articles/spring-rest-service-exception-handling-1
https://dzone.com/articles/global-exception-handling-with-controlleradvice
https://thepracticaldeveloper.com/2019/09/09/custom-error-handling-rest-controllers-spring-boot/
https://stormpath.com/blog/spring-mvc-rest-exception-handling-best-practices-part-1


# Error Handling (Web Application) 
https://www.baeldung.com/spring-boot-custom-error-page
https://www.journaldev.com/2651/spring-mvc-exception-handling-controlleradvice-exceptionhandler-handlerexceptionresolver
