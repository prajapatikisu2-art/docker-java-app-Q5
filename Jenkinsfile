
pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat '"C:\\Users\\Asus\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t docker-java-app-q5:%BUILD_NUMBER% .'
            }
        }
    }

    post {
        success {
            echo 'Build and Docker Image Created Successfully!'
        }
        failure {
            echo 'Build Failed! Check logs.'
        }
    }
}
