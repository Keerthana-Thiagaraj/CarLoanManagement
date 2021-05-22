# Car Loan Management 

This codebase was created to demonstrate the usage of Spring boot for building microservice-based architecture.All the Microservices are developed using spring boot. This spring boot application will be registered with the eureka discovery server.

### Tech Stack
    
| Technology type |  	Name 
| ------------- | ------------- 
| Programming language   | Java 8  
| Database               | Postgres   
| Application framework  | Spring Boot  
|  Build tool            |  Maven
| Testing framework      | Junit, Mockito
| Declarative REST client| Spring Cloud OpenFeign
| Service discovery      | Spring Cloud Netflix Eureka  
| Container Tool         | Docker   


### Modules
The project contains the following modules:
* **service-discovery-server** - A module that uses Spring Cloud Netflix Eureka as an embedded discovery server.
* **UserProfile** - It contains the user information and allows the user to modify/delete/list his/her details.
* **LoanProcessing** - It is responsible for sanctioning the loan to the user after checking the salary eligibility of the user. An individual or a company are allowed to obtain the car loan. Status of the loan can be APPROVED,REJECTED or PENDING based on the requirement. The module communicates with the UserProfile with the help of Feign client.  


### In Local  (non-docker env)

After cloning the repo,
build each of the services independently by using the below commands:
- `mvn clean install`
- `mvn clean package`

Once the build is successful, bring the following services(in the same order) by using `mvn spring-boot:run`

* service-discovery-service
* CarLoan-UserProfile 
* CarLoan-LoanProcessing 

Once the services are up, check the registered instances of CarLoan-UserProfile,CarLoan-LoanProcessing in  `localhost:8761` 


In Docker env
-

Build the docker images in each of the services as follows 
-  `docker build -t service-discovery:latest`
-  `docker build -t user-profile:latest`
-  `docker build -t loanprocessing:latest`

Once the build is successful, attach the created images to the container and bring the service up by using 

To attach the image to the container and bring the change password service up
- `docker-compose up`

To Test from postman
-

Once the services are up,test the endpoints from postman or any service testing tool

| Request |  	Payload 
| ------------- | ------------- 
| POST-http://localhost:8443/api/v1/user/                                | "{\"name\":\"keerthana\",\"salary\":\"50000\",\"email\":\"keerthana12@gmail.com\",\"contact\":9580387197}"
| GET-http://localhost:8443/api/v1/user/                                 |  NA
| GET-http://localhost:8443/api/v1/user/1                                |   
| PUT-http://localhost:8443/api/v1/user/updateUser/keerthana12@gmail.com | 
| POST-http://localhost:8443/api/v1/user/checkLoanEligibility/1          |   
| POST-http://localhost:8444/api/v1/loan/apply                           | "{\"userId\":1,\"amount\":500000}"   