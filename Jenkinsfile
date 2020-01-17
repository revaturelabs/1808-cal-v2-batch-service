podTemplate(containers: [
  containerTemplate(name: 'maven', image: 'maven:3.6.0-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ]) {

  node(POD_LABEL) {
    stage('Build a Maven project') {
      git credentialsId: 'GITLAB_KEY' 'https://gitlab.revaturelabs.com/caliber/batch.git'
      container('maven') {
          sh 'mvn -B clean package'
      }
    }
  }
}
