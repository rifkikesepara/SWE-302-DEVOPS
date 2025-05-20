pipeline {
    agent {
        label 'win-agent'
    }

    environment {
        DOCKER_IMAGE = "rifkikesepara/ozmenapp"
        DOCKERHUB_CREDENTIALS=credentials("rifki-dockerhub")
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'jenkins',url: 'https://github.com/rifkikesepara/SWE-302-DEVOPS.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean'
                sh 'mvn package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push ${DOCKER_IMAGE}'
            }
        }

        stage('K8 Deployment') {
            steps {
                sh 'kubectl apply -f k8/pvc.yaml'
            }
        }

        stage('Pull & Run Container') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
            }
        }
    }
}