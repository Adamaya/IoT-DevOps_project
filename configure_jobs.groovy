job('1_build_container') {
    steps {
      scm {
        git {
          remote {
            url('https://github.com/Adamaya/IoT-DevOps_project.git')
          }
          branch('*/master')
        }
      }
      triggers {
        scm('* * * * *')
      }
                dockerBuilderPublisher {
                  dockerFileDirectory("/home/pi/workspace/devops_project")
                  fromRegistry {
                    url("adamayasharma")
                    credentialsId("3f885629-0783-4229-8808-f2610c781c80")
                  }
                cloud("rpi")
    
                tagsString("adamayasharma/light-controller-webserver")
                pushCredentialsId("3f885629-0783-4229-8808-f2610c781c80")
                pushOnSuccess(true)
                cleanImages(false)
                cleanupWithJenkinsJobDelete(false)
                noCache(false)
                pull(true) 
        }
    }
}
job('2_launch_container') {
    steps {
        triggers {
        upstream {
      upstreamProjects("1_build_container")
            }
        }
      shell("sudo kubectl apply -f /home/pi/workspace/devops_project/pri.yaml")
    }
}  
