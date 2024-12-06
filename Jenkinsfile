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
                sh 'dependency-check --project "ProjectName" --out dependency-check-output --scan .'
                dependencyCheckPublisher pattern: 'dependency-check-output/dependency-check-report.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'dependency-check-output/**'
        }
    }
}
