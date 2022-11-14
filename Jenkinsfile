pipeline {
    agent any
 
    stages {
	  
       stage('Build Artifact - Maven') {
			
	       steps {		
				sh "mvn clean package -DskipTests=true"
		       archive 'target/*.jar'
			}
		}
	    /*stage('SonarQube Test') {
			steps {
			
				sh "mvn sonar:sonar -Dsonar.projectKey=springapp -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=d9836be6315b0f0764097ca520c4bdb36017e1a6"
			}
			}*/
	    stage('Docker Build and Push') {
       steps {
         withDockerRegistry([credentialsId: "docker-hub", url: ""]) {
           sh 'printenv'
           sh 'sudo docker build -t eyach/ci:latest .'
           sh 'docker push eyach/ci:latest '
         }
       }
     }
    }
}
