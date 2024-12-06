pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/aleonlozano/franchise-technical-test.git',
                    credentialsId: 'git-credentials'
            }
        }
        stage('Dependency Check') {
            steps {
                bat '''
                    C:\\dependency-check\\bin\\dependency-check.bat ^
                    --project "ProjectName" ^
                    --out dependency-check-output ^
                    --scan . ^
                    --exclude-test-scope
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'dependency-check-output/**'
        }
    }
}
