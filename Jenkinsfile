pipeline {
    agent any
 
    stages {
        
       stage('Build Artifact - Maven') {
			
	       withMaven(maven: 'mvn'){
				sh "mvn clean package -DskipTests=true"
				archive 'target/*.jar'
			}
		}
    }
}
