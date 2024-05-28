pipeline {
    agent any
    parameters {
        string(
            name: 'BRANCH_OR_TAG',
            defaultValue: 'Grupo_II',
            description: 'Nombre de la rama o etiqueta a clonar'
        )
        string(
            name: 'CUCUMBER_OPTIONS',
            defaultValue: ' --tags @AdvancedSearch,@SearchActorInFilm,@FilmPageDetails,@SearchFilm,@Oscars',
            description: 'Opciones de Cucumber a pasar como parámetro'
        )
        string(
            name: 'REPOSITORY',
            defaultValue: 'https://gitlab.alten.es/practicafinalqa/practicafinalgrupo2',
            description: 'URL del repositorio'
        )
    }
    stages {
        stage('Checkout') {
            steps {
                // Hacemos el checkout
                checkout([$class: 'GitSCM', branches: [[name: "${BRANCH_OR_TAG}"]], userRemoteConfigs: [[url: "${REPOSITORY}"]]])
            }
        }
        stage('Run Tests') {
            steps {
                // Ejecutamos las pruebas
                bat("mvn clean test -Dcucumber.options= \"${CUCUMBER_OPTIONS}\"")
            }
        }
        stage('Create Zip') {
            steps {
                // Creamos el zip con el ExtentReport
                bat("zip -r report.zip ./target/cucumber-reports")
            }
        }
        stage('Archive Report') {
            steps {
                // Adjuntamos el zip
                archiveArtifacts artifacts: 'report.zip', allowEmptyArchive: true
            }
        }
    }
}