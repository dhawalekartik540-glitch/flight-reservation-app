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
        stage('Deploy'){
            steps{
                sh '''
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-cred']]) {
                accessKeyId = env.AWS_ACCESS_KEY_ID
                secretAccessKey = env.AWS_SECRET_ACCESS_KEY
                }
                cd frontend
                aws s3 ls
                
                '''  
            }
        }
    }
}