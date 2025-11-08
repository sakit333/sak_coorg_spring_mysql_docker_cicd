pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        choice(
            name: 'DEPLOY_ENV',
            choices: ['dev', 'qa', 'prod'],
            description: 'Select the deployment environment'
        )
        choice(
            name: 'ACTION',
            choices: ['deploy', 'remove'],
            description: 'Am selecting for the action'
        )
    }

    environment {
        DOCKERHUB_USERNAME = 'sakit333'
        DOCKER_IMAGE = 'coorg_sak_spring'
    }

    stages {
        stage('To Build Jar file'){
            steps{
                sh 'mvn clean package -Dskiptests'
            }
        }
        stage('Build the Docker images') {
            when{
                allOf {
                    expression { params.DEPLOY_ENV == 'dev' }
                    expression { params.ACTION == 'deploy' }
                }        
            }
            steps {
                sh 'sudo docker build -t ${DOCKERHUB_USERNAME}/${DOCKER_IMAGE}:latest .'
            }
        }
        stage('Remove the docker images') {
            when {
                allOf {
                    expression { params.DEPLOY_ENV == 'dev' }
                    expression { params.ACTION == 'remove' }
                }
            }
            steps {
                echo "Removing all the images Locally....!!!!"
                sh '''
                sudo docker rmi ${DOCKERHUB_USERNAME}/${DOCKER_IMAGE}:latest || echo "Image not found, skipping..."
                sudo docker system prune -af
                '''
            }
        }
    }
    post {
        success {
            echo "project is success"
        }
        failure {
            echo "project is failure"
        }
    }
}