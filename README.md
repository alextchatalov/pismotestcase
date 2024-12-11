# **Pismo Tech Interview Test Case**

## **About this Project**

This project is a test case for a technical interview at Pismo. It provides an implementation using modern Java and Spring ecosystem tools to build a structured and reliable application.

### **Technologies Used**
- **Java**: Version 17
- **Spring Boot**: For creating the application
- **Spring Data JPA**: For ORM and database interaction
- **JUnit**: For unit testing
- **Maven**: For dependency management and build automation
- **PostgreSQL**: As the database
- **Docker Compose**: For containerized deployment

---

## **How to Run It**

To run this project successfully, follow these steps:

### **Step 1: Initialize Docker Compose**
1. Make sure you have Docker and Docker Compose installed on your machine.
2. Open a terminal in the root folder of the project.
3. Run the following command to initialize Docker Compose:

   ```bash
   docker compose up
   ```

   This will start the required services, including the PostgreSQL database.

### **Step 2: Compile the project**
Compile the project, executing this command:

``
  mvn clean install -DskipTests
``
### **Step 3: Build the Docker Image**
Once Docker Compose is running:

1. Use Docker to build the application's Docker image. Run the following command:

   ```bash
   docker build -t pismo-tech-test-case .
   ```

2. Note: Make sure you're in the root directory of the project (where the `Dockerfile` is located).

### **Step 4: Run the Built Container**

After building the Docker image, run the container with:

```bash
   docker run -p 8080:8080 --network=docker_db-network -e DATABASE_HOST=db:5432 pismo-tech-test-case
```

This will start the application and map it to port `8080` on your local machine.

## 5. Session: Test API Endpoints

You can use the following `curl` commands to test the API endpoints:

Note: **When executing each command, the account ID is used in the next endpoint execution.**
### 1. Create an Account
```bash
account_id=$(curl --silent --location 'localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "document_number": "211412412444"
}' | jq -r '.account_id')

echo "Account ID: $account_id"
```
This will create a new account with the specified `document_number`. Replace `"211412412444"` with your desired value.

---

### 2. Get Account Information
```bash
curl --location "localhost:8080/accounts/$account_id"
```
---

### 3. Create a Transaction
```bash
curl --location 'localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data "{
    \"account_id\": \"$account_id\",
    \"operation_type_id\": 4,
    \"amount\": 555.34
}"
```
Replace `account_id` with the actual account ID, `operation_type_id` with the desired operation type (e.g., `4`), and `amount` with the transaction value (e.g., `555.34`) to create a new transaction.
---

## Notes

- By default, the application uses the `application.yml` file configuration, so the connection to the PostgreSQL database works without manual intervention if the Docker Compose is initialized properly.
- Customize configurations (ports, database credentials, etc.) in the application properties file

## Final Observation About the Project

During this project, I did not focus on creating all the test scenarios due to time constraints. Instead, I implemented basic unit tests to cover essential functionality. There are, however, many other types of tests that could be added, such as integration tests using tools like **Testcontainers** to integrate with the database.

Another observation is regarding the `operation_type`. I believe this is a domain table and could benefit from caching, especially since it is accessed every time a new transaction is created. However, due to time constraints, I was not able to implement this feature.

There is room to further enhance this project by addressing these points in the future.
