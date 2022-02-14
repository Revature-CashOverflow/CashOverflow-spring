# CashOverflow-spring  
This is a Spring Boot stateless resource/authentication server.  It is one part of a multi server application, the other being an Angular front end.  

## Setup  
### Software  
1. Jenkins  
2. Maven  
3. Java 11  
4. Docker  
5. Docker Account  
6. SonarCloud Account  
7. GitHub Account  

### Environment variables for running the application  
You need 3 environment variables for the Spring Boot application to run  
1. AWS_DB_ENDPOINT - should point to an AWS RDS instance running PostgreSQL on port 5432.  It could also point to localhost if you're running your own DBMS.  
   Note: if you are running PostgreSQL on a different port or a different DBMS vendor altogether you will need to modify the application.properties file.  
2. AWS_DB_USERNAME - the username for the database  
3. AWS_DB_PASSWORD - the password for the database  

You also need to set these as secret text credentials in Jenkins.  

Once you have the environment variables setup and your DBMS configured in the application.properties file you should be able to run the application via your boot dashboard or by just running the main method in the class at the base package com.revature.  

### Building and deploying with Jenkins  
The Jenkinsfile will build your application using Maven with the SonarCloud and Jacoco plugins.  If the branch being built is "main" it will also deploy the application on your machine inside of a Docker container mapped to port 9001.  You need to set the environment variables found in the Jenkinsfile in your Jenkins credentials with an ID matching the strings you see in the credentials() function calls.  Credentials that need to be set in Jenkins are:
1. aws-env (secret file) - this should be a secrets file with 3 lines and they're the same env variables you set on your machine to run the applicaion, this gets passed in to the docker containter  
2. SONAR_TOKEN (secret text) - this should be a personal access token you generate in SonarCloud  
3. DOCKER_REPO - the name of the docker repo to push the images to after they are built  
4. docker-creds - a username password credential with the password being a generated personal access token from Docker.  
5. DOCKER_USER - your Docker username
6. Finally, add your github credentials in the form of username/personal access token in Jenkins.

If you're running into issues make sure your secret IDs in your Jenkins credentials are matching the strings being passed into credentials() in the Jenkinsfile.  Also be sure your github webhooks and credentials are properly configured.
