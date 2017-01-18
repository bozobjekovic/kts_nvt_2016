(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('MainCtrl', ['$scope', '$rootScope', '$localStorage',
            function($scope, $rootScope, $localStorage) {

                $rootScope.mainMenu = true;

                $rootScope.currentUser = ($localStorage.currentUser) ? true : false;

            }
        ]);
})(angular);
