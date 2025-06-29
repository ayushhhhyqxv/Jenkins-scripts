pipeline {
    agent any 

    stages {
        stage('Code'){
            steps{
                echo "Cloning github repo"
                git url : <your-repo-link>,branch: <required-branch>
            }
        }
        stage('Build'){
            steps{
                echo "Building code"
            }
        }
        stage('Test'){
            steps{
                echo "Testing your code"
            }
        }
        stage('Deploy'){
            steps{
                echo "Deploy to PD"
            }
        }
    }
}