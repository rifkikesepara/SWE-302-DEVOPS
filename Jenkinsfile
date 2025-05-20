pipeline {
    agent {
        label 'win-agent'
    }

    environment {
        DOCKER_IMAGE = "rifkikesepara/devops4hw"
        DOCKER_CREDENTIALS_ID="rifki-dockerhub"
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

        stage('Login to Docker Hub') {
            steps {
                sh "docker logout"
                withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }


        stage('Push Docker Image') {
            steps {
                sh 'docker push ${DOCKER_IMAGE}'
            }
        }

        stage('Deploy Kubernetes Resources') {
            steps {
                sh 'kubectl apply -f k8/app-deployment.yaml'
                sh 'kubectl apply -f k8/app-service.yaml'
            }
        }

        stage('Verify Deployment') {
            steps {
                sh 'kubectl delete pods --all'
                sh 'kubectl get pods'
                sh 'kubectl get svc'
            }
        }

        // stage('Scale Application') {
        //     steps {
        //         sh 'kubectl scale deployment spring-app --replicas=2'
        //     }
        // }

        stage('Pull & Run Container') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose pull'
                sh 'docker-compose up -d'
            }
        }
    }
}