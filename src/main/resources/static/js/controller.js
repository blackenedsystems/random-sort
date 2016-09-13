var randomSorterApp = angular.module("randomApp");

var mainController = randomSorterApp.controller("mainController",
    function ($scope, randomSorterService) {

        $scope.results = [];

        var loadResults = function() {
           randomSorterService.loadResults(function(data) {
               $scope.results = data;
           });
        };

        $scope.sort = function(numbers) {
            console.log(numbers);
            randomSorterService.sort(numbers);
            loadResults();
        };

        loadResults();
    }
);