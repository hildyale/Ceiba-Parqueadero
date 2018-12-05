pipeline {
	//where and how to execute the Pipeline
	agent {
		label 'Slave_Induccion'
	}
	
	options {
		//Mantener artefactos y salida de consola para el # especï¿½fico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '5')) 
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds() 
	}
	
	 //Una secciÃ³n que define las herramientas para â€œautoinstalarâ€� y poner en la PATH
	tools {
		jdk 'JDK8_Centos'
		gradle 'Gradle4.5_Centos'
	}
	
	/*
	triggers {
		pollSCM('@hourly')
	} 
	*/
	
	stages{
		
		stage('Checkout') {
			steps{
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_hildyale', url: 'https://github.com/hildyale/Ceiba-Parqueadero']]])
				sh 'gradle clean'
			}
		}
		
		stage('Compile') {
			steps{
				echo "------------>Compile<------------"
				sh 'gradle --b ./build.gradle compileJava'
			}
		}
		
		stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --stacktrace test --tests co.com.ceiba.ceibaParqueadero.unitarias'
				junit '**/build/test-results/test/*.xml' //aggregate test results - JUnit
				step( [ $class: 'JacocoPublisher' ] )
			}
		}
		
		stage('integration Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --stacktrace test --tests co.com.ceiba.ceibaParqueadero.integracion'
				junit '**/build/test-results/test/*.xml' //aggregate test results - JUnit
				step( [ $class: 'JacocoPublisher' ] )
			}
		}
		
		stage('Static Code Analysis') {
			steps{
				echo '------------>Analisis de codigo estatico<------------'
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
				}
			}
		}
		
		stage('Build') {
			steps {
				echo "------------>Build<------------"
				sh 'gradle --b ./build.gradle build -x test'
			}
		}
	}
	
	post {
		always {
			echo 'This will always run'
		}
		success {
			echo 'This will run only if successful'
			junit '**/build/test-results/test/*.xml'
		}
		failure {
			echo 'This will run only if failed'
			//send notifications about a Pipeline to an email
			mail (to: 'alejandro.isaza@ceiba.com.co',
			      subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
			      body: "Something is wrong with ${env.BUILD_URL}")
		}
		unstable {
			echo 'This will run only if the run was marked as unstable'
		}
		changed {
			echo 'This will run only if the state of the Pipeline has changed'
			echo 'For example, if the Pipeline was previously failing but is now successful'
		}
	}
}