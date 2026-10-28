pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'Maven3'
    }

    environment {
        DOCKER_PATH = 'C:\\Users\\Asus\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe'
        IMAGE_NAME = 'docker-java-app-q5'
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
                bat '"%DOCKER_PATH%" build -t %IMAGE_NAME%:%BUILD_NUMBER% .'
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-cred',
                    usernameVariable: 'DOCKERHUB_USER',
                    passwordVariable: 'DOCKERHUB_PASS'
                )]) {

                    bat '"%DOCKER_PATH%" login -u "%DOCKERHUB_USER%" -p "%DOCKERHUB_PASS%"'

                    bat '"%DOCKER_PATH%" tag %IMAGE_NAME%:%BUILD_NUMBER% "%DOCKERHUB_USER%/%IMAGE_NAME%:%BUILD_NUMBER%"'

                    bat '"%DOCKER_PATH%" push "%DOCKERHUB_USER%/%IMAGE_NAME%:%BUILD_NUMBER%"'
                }
            }
        }

        stage('Deploy to Server') {
            steps {

                bat '"%DOCKER_PATH%" pull krishnaa20/docker-java-app-q5:%BUILD_NUMBER%'

                bat '"%DOCKER_PATH%" stop myapp'

                bat '"%DOCKER_PATH%" rm myapp'

                bat '"%DOCKER_PATH%" run -d -p 8082:8080 --name myapp krishnaa20/docker-java-app-q5:%BUILD_NUMBER%'
            }
        }

    }

    post {
        success {
            echo 'Build, Docker Image Creation, DockerHub Push and Deployment Successful!'
        }

        failure {
            echo 'Pipeline Failed! Check Console Output.'
        }
    }
}