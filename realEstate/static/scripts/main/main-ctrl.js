(function (angular) {
    'use strict';

    angular.module('main', [])
        .controller('MainCtrl', MainCtrl);

    MainCtrl.$inject = ['$scope', '$rootScope', '$localStorage'];

    function MainCtrl($scope, $rootScope, $localStorage){
      $rootScope.mainMenu = true;

      $rootScope.currentUser = ($localStorage.currentUser) ? true : false;
    }
})(angular);
