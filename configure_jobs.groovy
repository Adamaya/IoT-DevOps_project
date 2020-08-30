job('build_launch_container_in_rpi') {
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
        shell("sudo kubectl apply -f /home/pi/workspace/devops_project/pri.yaml")
    }
}
