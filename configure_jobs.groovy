pipeline { 
    agent any 
    stages {
        stage('Build') { 
            steps { 
                echo "hello" 
            }
        }
        stage('Test'){
            steps {
                echo "test" 
            }
        }
        stage('Deploy') {
            steps {
                echo 'make publish'
            }
        }
    }
}
