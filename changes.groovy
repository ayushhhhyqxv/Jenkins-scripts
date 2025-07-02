pipeline {
    agent { label '<label-name>' }

    environment {
        DOCKER_IMAGE = '<your-docker-hub-account>/<name-your-image>:latest'
    }

    stages {
        stage('Code') {
            steps {
                echo "Cloning github repo"
                git url: '<your-repo-link>', branch: '<required-branch>'
            }
        }
        stage('Build') {
            steps {
                sh "docker build -t ${env.DOCKER_IMAGE} ."
            }
        }
        stage('Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: '<your-jenkins-credential-id>',
                    passwordVariable: 'dockerHubPassword',
                    usernameVariable: 'dockerHubUser'
                )]) {
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh "docker push ${env.DOCKER_IMAGE}"
                }
            }
        }
        stage('Test') {
            steps {
                // Example: sh 'npm test'
                echo "No tests defined yet"  // we must write here something else error would occur
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose down && docker-compose up -d'
            }
        }
    }
}