var randomSorterApp = angular.module("randomApp");

randomSorterApp.factory('randomSorterService',
    function ($resource) {

        var sortResource = $resource('/api/sort');
        var historyResource = $resource('/api/list?max=20');

        var sortNumbers = function (numbers, fnSuccess, fnError) {
            sortResource.save(numbers);
        };

        return {
            sort: function (numbers, fnSuccess, fnError) {
                sortNumbers(numbers, fnSuccess, fnError);
            },

            loadResults: function(fnSuccess, fnError) {
                historyResource.query().$promise.then(
                    function (data) {
                        fnSuccess(data)
                    },
                    function (err) {
                        fnError();
                    });
            }
        }
    }
);