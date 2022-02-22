# CashOverflow Startup Documentation

This startup file will guide you through initalizing your database so that the application will run. It  
will also go through deployment.

## Local Application Development

### Things you'll need

1. SQL script located in CashOverflow/src/main/resources
2. AWS RDS instance running some DBMS (project is already setup for PostgreSQL)
3. DBeaver
4. AWS RDS credentials

### Steps to start working on the application locally

1. Connect DBeaver to RDS Instance
2. If you are NOT using PostgreSQL, then go into the application.properties and configure it properly
3. Set your environment variables as described in the README.md
4. Create a database called cashoverflow inside your RDS instance
5. Run the provided script in DBeaver and skip over the drops on the first run
6. Use Boot Dashboard or just run the main method in top level package to start the application

The application should now be running on localhost:9001 unless you change the port in the boot config.

## Pipeline and Deployment

### Things you'll need

1. Jenkins
2. Docker Account
3. SonarCloud Account
4. Github Account

### Setting up the pipeline

1. Make sure you setup a multibranch pipeline in Jenkins.
2. Point it to the Github repository
3. Let Jenkins and the Jenkinsfile do the rest  

The Jenkinsfile will build your application using Maven with the SonarCloud and Jacoco plugins. If the branch being built is "main" it will also deploy the application on your machine inside of a Docker container mapped to port 9001. You need to set the environment variables found in the Jenkinsfile in your Jenkins credentials with an ID matching the strings you see in the credentials() function calls. Credentials that need to be set in Jenkins are:

1. aws-env (secret file) - this should be a secrets file with 3 lines and they're the same env variables you set on your machine to run the applicaion, this gets passed in to the docker containter
2. SONAR_TOKEN (secret text) - this should be a personal access token you generate in SonarCloud
3. DOCKER_REPO - the name of the docker repo to push the images to after they are built
4. docker-creds - a username password credential with the password being a generated personal access token from Docker.
5. DOCKER_USER - your Docker username
6. Finally, add your github credentials in the form of username/personal access token in Jenkins.

If you're running into issues make sure your secret IDs in your Jenkins credentials are matching the strings being passed into credentials() in the Jenkinsfile. Also be sure your github webhooks and credentials are properly configured.
