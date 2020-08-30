pipeline{

    stages{
    
stage('job1_pull_repo_build_image') {
    steps {
      scm {
        git {
          remote {
            url('https://github.com/Adamaya/pipeline_implementation_with_k8s_-_jenkins.git')
          }
          branch('*/master')
        }
      }
      triggers {
        scm('* * * * *')
      }
      conditionalSteps {
            condition {
                shell("count=\$(ls | grep .php | wc -l); if [[ \$count -gt 0 ]]; then cp -vr * /home/php exit 0;else  exit 1; fi")
            }
            runner('DontRun')
            steps {
                dockerBuilderPublisher {
                  dockerFileDirectory("/home/php")
                  fromRegistry {
                    url("adamayasharma")
                    credentialsId("3f885629-0783-4229-8808-f2610c781c80")
                  }
                cloud("docker")
    
                tagsString("adamayasharma/-gphp-webserver")
                pushCredentialsId("3f885629-0783-4229-8808-f2610c781c80")
                pushOnSuccess(true)
                cleanImages(false)
                cleanupWithJenkinsJobDelete(false)
                noCache(false)
                pull(true)
                }    
            }
        }
    }
}     
}
}
