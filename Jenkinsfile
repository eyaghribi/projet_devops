pipeline {
    agent any
 
    stages {
        
       stage('Build Artifact - Maven') {
			
	       steps {		withMaven(maven: 'mvn'){
				sh "mvn clean package -DskipTests=true"
		       archive 'target/*.jar'}
			}
		}
    }
}
