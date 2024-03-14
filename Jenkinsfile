pipeline {
    
    agent any
    
    tools {
        maven "396"
    }
    
    stages {
        
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
                            junit healthScaleFactor: 0.5, testResults: '**/TEST-*.xml'
                            cleanWs()
                        }
                    }
                }
            }
        }
    }
}



