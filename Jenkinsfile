pipeline {
    agent any

    tools {
        gradle "8.14"
    }

    environment {
        DOCKER_IMAGE = "rifkikesepara/ozmenapp"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'uat',url: 'https://github.com/rifkikesepara/SWE-302-DEVOPS.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'cd Project2'
                sh 'gradle --version'
                sh 'gradle build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'your-dockerhub-credentials', url: '']) {
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }

        stage('Pull & Run Container') {
            steps {
                sh 'docker pull $DOCKER_IMAGE'
                sh 'docker run -d --name my-container $DOCKER_IMAGE'
            }
        }
    }
}