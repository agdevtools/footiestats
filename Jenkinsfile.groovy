node {
    def gitRepository = 'https://github.com/agdevtools/footiestats.git/'
    def gitBranch = '*/master'
    def githubcreds = [
            $class      : 'UsernamePasswordMultiBinding',
            credentialsId : 'githubcreds',
            usernameVariable : 'GIT_USER',
            passwordVariable : 'GIT_PASS'
    ]

    stage('Clean Workspace') {
        cleanWs()
    }

    stage('Git Checkout') {
        withCredentials([githubcreds]){
            checkout([
                    $class      : 'GitSCM',
                    branches    : [[name:"${gitBranch}"]],
                    doGenerateSubModuleConfigurations : false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId  : 'githubcreds',
                                         url            :"${gitRepository}"]]

            ])

        }
    }

    stage('Build') {

        sh './gradlew clean build -x test'
        sh 'curl -H "Content-Type: application/json" --request POST \\\n' +
                '  --url https://events.pagerduty.com/v2/change/enqueue \\\n' +
                '  --data \'{"payload":{"summary":"From Jenkins","timestamp":"2021-07-02T11:15:22Z","source":"string","custom_details":{}},"routing_key":"e429be3c8ea94c06c0429c80719dd399","links":[{"href":"string","text":"string"}]}\''

    }

    stage('Test') {

        sh './gradlew clean test --info'

    }

    stage('Deploy to Heroku') {

        sh './gradlew build deployHeroku'

    }
}