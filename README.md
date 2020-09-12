# Shrimp Farm App Challenge

### Reference Documentation (API)

##### Technology:

* JDK 11
* Kotlin 1.3
* Maven 3
* Spring Boot 2.3
* Spring Data JPA 2.3
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
* Download code from [GitHub](https://github.com/migueleinsz4/shrimp-farm-app) as zip file
* Unzip the zip file, you get the shrimp-farm-app-master directory
* Change to the shrimp-farm-app-master directory 
* Execute the command: mvn package
* The shrimp-farm-app-master/target directory is generated

##### Run:

* Change to the shrimp-farm-app-master/target directory
* To run the server, execute the command: java -jar shrimp-farm-app-1.0.0.jar --server.port=8081
* To read the Api docs: http://localhost:8081/cargill-shrimp-farm/swagger-ui.html