pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17' 
            // We use -u root to ensure the container has permission to write reports to your workspace
            args '-v /tmp:/tmp -u root' 
        }
    }    
    triggers {
        githubPush()
    }
    stages {
        stage('Checkout & Setup Name') {
            steps {
                checkout scm                
                script {
                    try {
                        // Use 'sh' instead of 'bat' inside Docker
                        String commitHash = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                        String commitMsg = sh(script: "git log -1 --pretty=%s", returnStdout: true).trim()
                        
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
                // Use 'sh' for Linux-based Docker containers
                sh 'mvn clean test -Dheadless=true'
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