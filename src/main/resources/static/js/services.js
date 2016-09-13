var randomSorterApp = angular.module("randomApp");

randomSorterApp.factory('randomSorterService',
    function ($resource) {

        var sortResource = $resource('/api/sort');

        var sortNumbers = function (numbers, success, error) {
            sortResource.save(numbers);
        };

        return {
            sort: function (numbers, success, error) {
                sortNumbers(numbers, success, error);
            }
        }
    }
);