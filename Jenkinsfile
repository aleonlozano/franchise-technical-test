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
                dependencyCheckPublisher datadir: 'dependency-check-data', outdir: 'dependency-check-output'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'dependency-check-output/**'
        }
    }
}
