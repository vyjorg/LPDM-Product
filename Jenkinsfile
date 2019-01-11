pipeline {
    agent any
    tools {
        maven 'Apache Maven 3.5.2'
    }
    stages{
        stage('Checkout') {
            steps {
                git 'https://github.com/vyjorg/LPDM-Product'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    error 'The tests failed'
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker stop LPDM-ProductMS || true && docker rm LPDM-ProductMS || true'
                sh 'docker pull vyjorg/lpdm-product:latest'
                sh 'docker run -d --name LPDM-ProductMS -p 28086:28086 --link LPDM-ProductDB --restart always --memory-swappiness=0  vyjorg/lpdm-product:latest'
            }
        }
    }
}
