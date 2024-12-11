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

### **Step 5: Test Using Postman**

To test the application, you can use the provided requests in the Postman files. Follow these steps:

1. Locate the **Postman collection file** provided with this project.
2. Import the file into Postman (use **File > Import** in Postman).
3. Run the test requests against the API.

Make sure the application is running and all required services (e.g., PostgreSQL) are up and available through Docker Compose.

---

## Notes

- By default, the application uses the `application.yml` file configuration, so the connection to the PostgreSQL database works without manual intervention if the Docker Compose is initialized properly.
- Customize configurations (ports, database credentials, etc.) in the application properties file