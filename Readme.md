# Solution #

## 1. Description ##
This solution is designed to introduce two main entities:
- Phones
- Bookings

If you run this service locally, you can access the Swagger UI at http://localhost:8080/swagger-ui.html
where you will see two main endpoints:
- /api/v1/phones
- /api/v1/bookings
The main entity is the Phone. Access to the bookings is done through the Phone entity.

### 1.1. Architecture ###
The main architecture is based on the hexagonal architecture:
- Domain: contains the business logic and the domain objects
- Rest: contains the REST controllers
- Adapter: contains the adapters to the external world (database, external services, etc)
To connect domain logic with rest layer and the adapters interactors were implemented.

### 1.3. Technologies ###
- Java
- Spring Boot
- Spring Boot Data MongoDB
- Spring Boot Web
- Spring Cloud OpenFeign

### 1.4. Shortcuts ###
The solution is not production ready. It is missing:
- API Security
- Proper logging in JSON format
- Full-fledged monitoring (micrometer, prometheus, grafana, etc)
- Distributed lock on the modify operation
- Containerization and helm charts
- CI/CD pipeline
- Integration tests
- Unit tests

## 2. Trade-offs ##
As far as Fonoapi does not work in production, I decided to use a mock service for the phone data based on their API.
That's why side project was created in the folder ./fonoapi_mock
There you can find:
- Fake implementation of the FonoapiClient base on Java
- Integration tests for the FonoapiClient mock (with 100% coverage)
- Dockerfile to build the image
- Gradle build script to build the project
- Helm chart to deploy the service to the kubernetes cluster
- HOWTO instruction in the ./fonoapi_mock/README.md file

## 3. How to run ##
Right now the project can be run locally only. 
To run the project locally you need to:
- Assemble and deploy the fonoapi_mock service - see the ./fonoapi_mock/README.md file
- Run the following command in the root folder of the project:
```
./gradlew bootRun
```
- Open the Swagger UI at http://localhost:8080/swagger-ui.html

## 4. How to test ##
You can test the project manually using the Swagger UI at http://localhost:8080/swagger-ui.html
Here are the steps:
- Call "Get All Phones" endpoint to get the list of phones
- Pick one of the phones ID and call "Get Phone" endpoint to get the phone details (for some phones extended information would be available)
- Call "Create a Booking" endpoint to create a booking for the phone
- Call "Get Phone" endpoint to get the phone details and check that the booking is there
- Call "Cancel a Booking" endpoint to delete the booking
- Call "Get Phone" endpoint to get the phone details and check that the booking is not there

## Alternative solutions ##
- It is possible to put booking as a property inside the Phone entity. 
In that case the locking problem can be solved by using optimistic locking (@Version) provided be MongoDB.
    - Pros: no need to implement distributed locking
    - Cons: the booking is not a property of the phone, it is a separate entity.
- Not delete the booking, but mark it as cancelled.
    - Cons: definitely will require distributed locking
    - Pros: we can have a history of bookings for the phone, phone entity will not be affected by the booking history

So, as a conclusion, I would say that the solution where the booking is a separate entity is more flexible and scalable.
But it requires more efforts to implement it and the distributed locking is a must.
