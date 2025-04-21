pipeline {
    agent {
        label 'selenium'
    }

    tools {
        maven 'maven-399'
        allure 'allure-commandline'
    }

    stages {

        stage('github') {
            steps {
                git credentialsId: '1c3c642f-c3f4-4496-89e4-8759c2a81671', url: 'https://github.com/apribylov90/moscow-books.git'
            }
        }

        stage('run-test') {
            steps {
                sh 'mvn clean test'
            }
        }

    }

    post {
        always {
            // Always generate allure report even if tests fail
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}