pipeline {

    agent any

    tools {
        maven "396"
    }

    stages {

        stage('Git checkout') {
            steps {
                git 'https://github.com/FilSantos/indra.git'
            }
        }

        stage('Run Tests') {
            parallel {
                stage('UI') {
                    steps {
                        sh "mvn test -Dtest=TestWeb"
                    }
                    post {
                        always {
                            junit "**/TEST-*.xml"
                        }
                    }
                }
                stage('API - Rest') {
                    steps {
                        sh "mvn test -Dtest=TestRest"
                    }
                    post {
                        always {
                            junit "**/TEST-*.xml"
                        }
                    }
                }
            }
        }
    }
}
