pipeline {
    agent any
    
    options {
        skipDefaultCheckout(true)
    }
    
    tools {
        maven 'maven-default'
        jdk 'jdk-11'
    }
    
    environment {
        DATE = new Date().format('yy.M')
        TAG = "${DATE}.${BUILD_NUMBER}"
        AWS_DB_ENDPOINT = credentials('AWS_DB_ENDPOINT')
        AWS_USERNAME = credentials('AWS_USERNAME')
        AWS_PASSWORD = credentials('AWS_PASSWORD')
        SONAR_TOKEN = credentials('SONAR_TOKEN')
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
                sh 'mvn -f CashOverflow/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Revature-CashOverflow_CashOverflow-spring'
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
                    docker.build("rasc0l/cashoverflow-spring:${TAG}")
                }
            }
        }
        stage('Pushing Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', credentials('docker-creds')) {
                        docker.image("rasc0l/cashoverflow-spring:${TAG}").push()
                        docker.image("rasc0l/cashoverflow-spring:${TAG}").push("latest")
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh "docker stop cashoverflow-spring | true"
                sh "docker rm cashoverflow-spring | true"
                sh "docker run --name cashoverflow-spring -d -p 9001:9001 rasc0l/cashoverflow-spring:${TAG}"
            }
        }
    }
    // Clean workspace with options
    post {
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
    }
}