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
                git branch: 'uat',url: 'https://github.com/rifkikesepara/SWE-302-DEVOPS.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'gradle build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Login Docker Hub') {
            steps {
                sh 'echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password--stdin'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'your-dockerhub-credentials', url: '']) {
                    sh 'docker push ${DOCKER_IMAGE}'
                }
            }
        }

        stage('Pull & Run Container') {
            steps {
                sh 'docker pull ${DOCKER_IMAGE}'
                sh 'docker run -d --name my-container ${DOCKER_IMAGE}'
            }
        }
    }
}