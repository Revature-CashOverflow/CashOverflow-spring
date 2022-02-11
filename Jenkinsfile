pipeline {
    agent any
    
    options {
        skipDefaultCheckout(true)
    }
    
    tools {
        maven 'maven-default'
    }
    
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
        AWS_DB_ENDPOINT = credentials('AWS_DB_ENDPOINT')
        AWS_USERNAME = credentials('AWS_USERNAME')
        AWS_PASSWORD = credentials('AWS_PASSWORD')
        AWS_ENV = credentials('aws-env')
        SONAR_TOKEN = credentials('SONAR_TOKEN')
        DOCKER_REPO = 'cashoverflow-spring'
    }

    stages {
        stage('Clean workspace') {
            steps {
                cleanWs()
                checkout scm
                // git branch: 'main',
                //     credentialsId: 'jenkins-integration-user',
                //     url: 'https://github.com/Revature-CashOverflow/CashOverflow-spring.git'
                echo "Building ${env.JOB_NAME}..."
            }
        }
        stage('Build') {
            steps {
                // sh 'mvn -f CashOverflow/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Revature-CashOverflow_CashOverflow-spring'
                sh 'mvn -f CashOverflow/pom.xml -Dmaven.test.failure.ignore=true clean package'
            }
            // post {
            //     // If Maven was able to run the tests, even if some of the test
            //     // failed, record the test results and archive the jar file.
            //     success {
            //         junit '**/target/surefire-reports/TEST-*.xml'
            //         archiveArtifacts 'target/*.jar'
            //     }
            // }
        }
        stage('Docker Build') {
            steps {
                script {
                    docker.build("rasc0l/${DOCKER_REPO}:${TAG}")
                }
            }
        }
        stage('Pushing Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-creds') {
                        docker.image("rasc0l/${DOCKER_REPO}:${TAG}").push()
                        docker.image("rasc0l/${DOCKER_REPO}:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh "docker stop ${DOCKER_REPO} | true"
                sh "docker rm ${DOCKER_REPO} | true"
                sh "docker run --env-file ${AWS_ENV} --name ${DOCKER_REPO} -d -p 9001:9001 rasc0l/${DOCKER_REPO}:${TAG}"
            }
        }
    }
}
