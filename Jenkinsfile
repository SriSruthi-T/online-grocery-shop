pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-11'
    }

    environment {
        DOCKER_IMAGE = 'online-grocery-shop'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📥 Checking out source code from Git...'
                git branch: 'main',
                    url: 'https://github.com/SriSruthi-T/online-grocery-shop.git'
            }
        }

        stage('Build') {
            steps {
                echo '🔨 Building the Online Grocery Shopping application...'
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                echo '🧪 Running unit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo '📦 Packaging application as WAR...'
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Integration Test') {
            steps {
                echo '🔗 Running integration tests...'
                sh "docker run -d --name grocery-test-${BUILD_NUMBER} -p 9090:8080 ${DOCKER_IMAGE}:${DOCKER_TAG}"
                sh 'sleep 15'
                sh 'curl -f http://localhost:9090/ || exit 1'
            }
            post {
                always {
                    sh "docker stop grocery-test-${BUILD_NUMBER} || true"
                    sh "docker rm grocery-test-${BUILD_NUMBER} || true"
                }
            }
        }

        stage('Deploy') {
            steps {
                echo '🚀 Deploying Online Grocery Shop to production...'
                sh 'docker-compose down || true'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        success { echo '✅ Pipeline completed successfully!' }
        failure { echo '❌ Pipeline failed! Check the logs.' }
        always  { cleanWs() }
    }
}