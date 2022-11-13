pipeline {
    agent any
 
    stages {
	  
       stage('Build Artifact - Maven') {
			
	       steps {		
				sh "mvn clean package -DskipTests=true"
		       archive 'target/*.jar'
			}
		}
	    stage('SonarQube Test') {
			steps {
			
				sh "mvn sonar:sonar -Dsonar.projectKey=springapp -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=d9836be6315b0f0764097ca520c4bdb36017e1a6"
			}
			}
    }
}
