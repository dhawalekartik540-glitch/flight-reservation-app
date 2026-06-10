pipeline{
    agent any
    stages{
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/dhawalekartik540-glitch/flight-reservation-app.git'    
            }
        }
        // stage('Code-Build'){
        //     steps{
        //         sh '''
        //             cd frontend
        //             npm install
        //             npm run build
        //         '''
        //     }
        // }
       stage('Deploy') {
         steps {
            withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-cred']]) {
                sh '''
                    cd frontend
                    aws s3 ls
                '''
            }
        }
    }
    }
}