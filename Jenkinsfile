pipeline {
    agent any 
    
    triggers {
        githubPush()
    }
    
    stages {
        stage('Docker Build') {
            steps {
                // Building the image locally on the Windows host
                bat 'docker build -t automation-framework .'
            }
        }
        
        stage('Run Automation Tests in Docker') {
            steps {
                /* MODIFICATION: 
                   1. We mount the %WORKSPACE% to /app for your code.
                   2. We mount the Windows Maven Cache (%USERPROFILE%\.m2) to the 
                      container's Maven folder (/root/.m2) to save download time.
                */
                bat '''
                docker run --rm ^
                -v "%WORKSPACE%":/app ^
                -v "%USERPROFILE%\\.m2":/root/.m2 ^
                automation-framework
                '''
            }
        }
    }
    
    post {
        always {
            archiveArtifacts artifacts: 'target/SparkReport/ExtentReport.html', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}