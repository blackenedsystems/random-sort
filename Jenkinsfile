node('maven') {
stage 'build'
       openshiftBuild(buildConfig: 'random-sort', showBuildLogs: 'true')
       stage 'deploy'
       openshiftDeploy(deploymentConfig: 'development')
}
