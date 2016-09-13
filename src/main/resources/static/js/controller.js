var randomSorterApp = angular.module("randomApp");

var mainController = randomSorterApp.controller("mainController",
    function ($scope, randomSorterService) {

        $scope.sort = function(numbers) {
            console.log(numbers);
            randomSorterService.sort(numbers);
        }
    }
);