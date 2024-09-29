# Progressa Spring

## Requirements

- **Java**: 17
- **Docker**

## Setup

### Java Installation

1. Install [SKDMAN!](https://sdkman.io/install).
2. Use the command line interface (CLI) to install Java:
    
   - List available versions:

     ``` bash
     sdk list java
     ```

   - Install Java 17 (recommended vendor: **Temurin**): 

     ``` bash
     sdk install java <java-version-17>
     ```
     
   - Verify the installation:

     ``` bash
     java --version
     ```

### Database Configuration with Docker

1. Navigate to the folder containing the progressa-mysql-compose.yml file:
    
    ``` bash
    cd docker
    ```

2. Start the MySQL container:

    ``` bash
    docker compose -f progressa-mysql-compose.yml up -d
    ```

3. Verify the container is running:

    ``` bash
    docker ps
    ```
   
4. Access the database using the credentials `root` for both the username and password.

5. Create the database with the following SQL command:

    ``` SQL Command
    CREATE DATABASE progressa_db;
    ```