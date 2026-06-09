pipeline {
    agent any
    tools {
        maven 'maven_3_5_0' 
    }
    
    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'e42ca3a9-e6c0-4953-8d93-8bb35cbf2be8', url: 'https://github.com/Simantase/Kubernete-Practice']])
                bat 'mvn clean install'
            }
        }
        
        stage('Build docker image') {
            steps {
                script {
                    bat 'docker build -t simanta96/devops-integration .'
                }
            }
        }
        
        stage('Push Docker Image to Dockerhub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                        bat 'docker login -u simanta96 -p %dockerhubpwd%'
                        bat 'docker push simanta96/devops-integration'
                    }
                }
            }
        }
        
        stage('Deploy to kubernetes') {
            steps {
                script {
                    withKubeConfig([credentialsId: 'k8s-config']) {
                        bat 'kubectl apply -f deployment.yaml'
                        bat 'kubectl apply -f service.yaml'
                        bat 'kubectl apply -f hpa.yaml'
                        bat 'kubectl rollout restart deployment/spring-boot-k8s'
                    }
                }
            }
        }
    } 
} 