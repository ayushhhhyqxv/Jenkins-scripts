pipeline {
    agent label {'<label-name>'}

    stages {
        stage('Code'){
            steps{
                echo "Cloning github repo"
                git url : <your-repo-link>,branch: <required-branch>
            }
        }
        stage('Build'){
            steps{
                sh 'docker build . -t <your-docker-hub-account>/<name-your-image>:latest'
            }
        }
        stage('Push'){
            steps{
                withCredentials([usernamePassword(credentialsId: "<your-jenkins-credential-id>", passwordVariable: "dockerHubPassword", usernameVariable: "dockerHubUser"]) {
                sh 'docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}'
                sh 'docker push <your-docker-hub-account>/<name-your-image>:latest'
            }
            }
        }
        stage('Test'{
            steps{
                // cases will be given by developer or test in dockerfile itself by adding npm run test,if test fails pipeline wont build :)
            }
        })
        stage('Deploy'){
            steps{
                sh 'docker-compose down && docker-compose up -d'
            }
        }
    }
}