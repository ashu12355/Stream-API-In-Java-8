pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    stages {

        stage('Check Git') {
            steps {
                sh '''
                    echo "=== Jenkins Environment ==="
                    whoami

                    echo "=== Git Location ==="
                    which git

                    echo "=== Git Version ==="
                    git --version

                    echo "=== Git HTTP Version ==="
                    git config --global --get http.version || true
                '''
            }
        }

        stage('Checkout') {
            steps {
                sh '''
                    rm -rf source

                    git -c http.version=HTTP/1.1 clone \
                        --branch main \
                        https://github.com/ashu12355/Stream-API-In-Java-8.git \
                        source
                '''
            }
        }

        stage('Build & Test') {
            steps {
                dir('source') {
                    sh '''
                        chmod +x mvnw
                        ./mvnw clean package
                    '''
                }
            }
        }

        stage('Deploy Locally') {
            steps {
                sh '''
                    mkdir -p ~/deploy

                    echo "Copying JAR..."

                    cp source/target/*.jar ~/deploy/app.jar

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