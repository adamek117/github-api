A Spring Boot application that provides a REST API to list **non-fork** repositories for a given GitHub user.  
For each repository, the API returns:

- Repository name
- Owner login
- A list of branches with:
  - Branch name
  - Last commit SHA

## 🛠 Tech Stack

- Java 21
- Spring Boot (MVC)
- Maven
- JUnit & Spring Boot Test (for integration testing)
- Lombok


## 🚀 Running the application

Make sure you have Java and Maven installed.

```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
