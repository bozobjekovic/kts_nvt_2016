(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('MainCtrl', ['$scope', '$rootScope',
            function($scope, $rootScope) {
                $rootScope.mainMenu = true;


            }
        ]);
})(angular);
