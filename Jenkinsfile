pipeline {
    agent any
 
    stages {
	    stage('maven version ') {
			steps {
				sh "maven −version"
			}
	    }
       stage('Build Artifact - Maven') {
			
	       steps {		
				sh "maven clean package -DskipTests=true"
		       archive 'target/*.jar'
			}
		}
    }
}
