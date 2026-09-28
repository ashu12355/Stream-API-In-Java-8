pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Build & Test') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Deploy Locally') {
            steps {
                sh '''
                    mkdir -p ~/deploy

                    echo "Copying JAR..."

                    cp target/*.jar ~/deploy/app.jar

                    echo "Stopping old application..."

                    pkill -f "app.jar" || true

                    echo "Starting new application..."

                    nohup java -jar ~/deploy/app.jar \
                        > ~/deploy/app.log 2>&1 &

                    echo "Application started."
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}