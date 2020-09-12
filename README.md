# Shrimp Farm App Challenge

### Reference Documentation (API)

##### Technology:

* JDK 11
* Kotlin 1.3
* Maven 3
* Spring Boot 2.3
* Spring Data JPA 2.3
* PostgreSQL 11.2
* JUnit 5

##### Guidelines:

* Code against interfaces, not implementations
* Favoring object composition over class inheritance
* Favoring immutability
* Runtime Exceptions
* Use of Spring Framework Inversion of Control and Dependency Injection  

##### Features:

* REST Api
* CRUD on farms and ponds
* Calculate total size of farm
* Swagger Api documentation

##### Compile and Build:

* Install JDK 11
* Install Maven 3
* Install PostgreSQL 11
* Download code from [GitHub](https://github.com/migueleinsz4/shrimp-farm-app) as zip file
* Unzip the zip file, you get the shrimp-farm-app-master directory
* Change to the shrimp-farm-app-master directory 
* Execute the command: mvn package
* The shrimp-farm-app-master/target directory is generated

##### Run:

* Create the database:  
    CREATE DATABASE "cargill_shrimp_farm_db" WITH
    OWNER=postgres
    TEMPLATE=template0
    ENCODING=UTF8
    LC_COLLATE="en_US.UTF-8"
    LC_CTYPE="en_US.UTF-8";      
* Change to the shrimp-farm-app-master/target directory
* To run the server, execute the command with the correct password(for postgres user) and port(of database server):  
java -jar shrimp-farm-app-1.0.0.jar --spring.datasource.password=pgs.,019! --spring.datasource.url=jdbc:postgresql://localhost:5433/cargill_shrimp_farm_db
* The default base URL for API is:  
  http://localhost:8081/cargill-shrimp-farm/api/v1.0    
* To read (and test) the Api docs:  
http://localhost:8081/cargill-shrimp-farm/swagger-ui.html