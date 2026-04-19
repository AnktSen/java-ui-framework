pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            // This is the fix: It tells Jenkins to use a Linux-style path internally
            customWorkspace '/app'
            // -u root ensures permissions are high enough to write reports
            args '-u root'
        }
    }    
    
    triggers {
        githubPush()
    }
    
    stages {
        stage('Checkout & Setup Name') {
            steps {
                script {
                    try {
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
                // Inside the Docker container, it's a Linux environment
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