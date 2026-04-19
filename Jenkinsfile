pipeline {
    agent any    
    
    // This block enables the pipeline to listen for GitHub Webhook triggers
    triggers {
        githubPush()
    }

    tools {
        maven 'Maven 3.9'
        jdk 'JDK 17' 
    }
    
    stages {
        stage('Checkout & Setup Name') {
            steps {
                // 1. Pull the code
                checkout scm
                
                script {
                    try {
                        // 2. Fetch Git info
                        // Using --pretty=%s to get ONLY the subject line (concise)
                        // Using @echo off to prevent the command itself from appearing in the string
                        String commitHash = bat(script: "@echo off & git rev-parse --short HEAD", returnStdout: true).trim()
                        String commitMsg = bat(script: "@echo off & git log -1 --pretty=%s", returnStdout: true).trim()
                        
                        // 3. Set the display name
                        // We take only the first 50 characters to keep the UI neat
                        String shortMsg = commitMsg.take(50)
                        currentBuild.displayName = "#${env.BUILD_NUMBER} - ${shortMsg} (${commitHash})"
                        
                        echo "Successfully updated build name to: ${currentBuild.displayName}"
                    } catch (Exception e) {
                        echo "Could not update build name. Error: ${e.message}"
                        currentBuild.displayName = "#${env.BUILD_NUMBER} - Git Info Error"
                    }
                }
            }
        }        
        
        stage('Run Automation Tests') {
            steps {
                // Ensure headless is true for Jenkins/Server environments
                bat 'mvn clean test -Dheadless=true'
            }
        }
    }
    
    post {
        always {
            // This will help you find the reports in Jenkins after the run
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/*.html', allowEmptyArchive: true
        }
    }
}