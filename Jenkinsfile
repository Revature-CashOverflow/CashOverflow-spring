pipeline {
    agent any
    
    // options {
    //     skipDefaultCheckout(true)
    // }
    
    tools {
        maven 'maven-default'
        jdk 'jdk-11'
    }
    
    environment {
        AWS_DB_ENDPOINT = credentials('AWS_DB_ENDPOINT')
        AWS_USERNAME = credentials('AWS_USERNAME')
        AWS_PASSWORD = credentials('AWS_PASSWORD')
    }

    stages {
        stage('Clean workspace') {
            steps {
                cleanWs()
                // checkout scm
                git branch: 'main',
                    credentialsId: 'jenkins-integration-user',
                    url: 'https://github.com/Revature-CashOverflow/CashOverflow-spring.git'
                echo "Building ${env.JOB_NAME}..."
            }
        }
        stage('Build') {
            steps {
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
