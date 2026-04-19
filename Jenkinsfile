pipeline {
    agent any 
    
    triggers {
        githubPush()
    }
    
    stages {
        stage('Docker Build') {
            steps {
                // Build the image using the Dockerfile in the current directory
                bat 'docker build -t automation-framework .'
            }
        }
        
        stage('Run Automation Tests in Docker') {
            steps {
                /* We run the container and mount the current workspace 
                   to a Linux path /app inside the container.
                */
                bat 'docker run --rm -v "%WORKSPACE%":/app automation-framework'
            }
        }
    }
    
    post {
        always {
            // Jenkins will look for these in the Windows workspace after the container finishes
            archiveArtifacts artifacts: 'target/SparkReport/ExtentReport.html', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}