var app = angular.module('randomApp', ['ngRoute', 'ngResource']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/views/main.html',
            controller: 'mainController'
        })
        .otherwise(
            {redirectTo: '/'}
        );
});