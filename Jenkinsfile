pipeline {
    agent any
 
    stages {
	    
	    
	    stage('Git ') {
            steps {
                echo 'pulling Main Project from git ...';
                git branch: 'eya_cherni', credentialsId: 'git4', url: 'https://github.com/eyaghribi/projet_devops.git'            }
	    }
	    
	    
	  

	  
       stage('Build Artifact - Maven') {
			
	       steps {		
				sh "mvn clean package -DskipTests=true"
		       archive 'target/*.jar'
			}
		}
	    stage('SonarQube Test') {
			steps {
			
				sh "mvn sonar:sonar -Dsonar.projectKey=tpachat -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=4d2bd399c7b05c9b0291e2d4372f5cbd72d8dddd"
			}
			}
	    stage('Docker Build and Push') {
       steps {
         withDockerRegistry([credentialsId: "docker-hub", url: ""]) {
           sh 'printenv'
           sh 'sudo docker build -t eyach/ci:latest .'
           sh 'docker push eyach/ci:latest '
         }
       }
     }
	    stage('Nexus') {
			steps {				
				sh'mvn clean deploy -Dmaven.test.skip=true -Dresume=false'
			        }
	                } 
	     stage('Docker compose') {
       steps {
         parallel(
           "Docker compose": {
               sh 'docker-compose up '
           },
           "Delete running containers": {
		       sh 'sleep 2m '
               sh 'docker rm -f ci-spring ci-db  '
           }
         )
       }
	    }
		
	
    }
}
