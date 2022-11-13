pipeline {
    agent any
 
    stages {
	    stage('maven version ') {
			steps {
				sh "mvn −version"
			}
	    }
       stage('Build Artifact - Maven') {
			
	       steps {		
				sh "mvn clean package -DskipTests=true"
		       archive 'target/*.jar'
			}
		}
    }
}
