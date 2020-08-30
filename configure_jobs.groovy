pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                scm {
        git {
          remote {
            url('https://github.com/Adamaya/pipeline_implementation_with_k8s_-_jenkins.git')
          }
          branch('*/master')
        }
      }
            }
        }
        stage('Test') { 
            steps {
                // 
            }
        }
        stage('Deploy') { 
            steps {
                // 
            }
        }
    }
}
