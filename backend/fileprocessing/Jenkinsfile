pipeline {
    agent any
    
    environment{    
        GIT_CREDENTIALS="jenkins-dev-arribajim"
        ROOT_FOLDER="backend/fileprocessing"
        DOCKER_IMAGE_NAME="fileprocessing"
        DOCKER_REPO="arribajim"
    }


  stages {
    stage("checking version"){
      steps{
          sh "git --version"
          sh "java -version"
          sh "mvn -version"
          sh "docker --version"
          sh "kubectl version"

      }
    }
    stage("maven clean and package"){
      steps{
          sh '''
            cd ${ROOT_FOLDER}
            mvn -DskipTests=true  clean package
            '''
      }
    }
    stage("docker build image"){
      steps{
        script{
          def dockerImage = docker.build("${DOCKER_REPO}/${DOCKER_IMAGE_NAME}","-f ${ROOT_FOLDER}/Dockerfile .") 
          dockerImage.push()
        }
      }
    }
    stage("k8s delete deployment"){
      steps{
        script{
          sh "kubectl delete -f ${ROOT_FOLDER}/deployment.yaml"
        }
      }
    }
    stage("k8s dev deployment apply"){
      steps{
        script{
          sh "kubectl apply -f ${ROOT_FOLDER}/deployment.yaml"
        }
      }
    }
  }
}