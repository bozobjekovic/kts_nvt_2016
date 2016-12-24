(function(angular) {
'use strict';

angular
    .module('realEstateClientApp', [
        'ngResource',
        'ngRoute',
        'restangular',
        'ui.bootstrap',
        'lodash'
    ])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html'
            })
            .when('/admin', {
                templateUrl: 'views/admin.html',
                controller: 'AdminCtrl',
                controllerAs: 'admin'
            })
            .otherwise({
                redirectTo: '/'
            });
    }])
    .run(['Restangular', '$log', function(Restangular, $log) {
        Restangular.setBaseUrl('realEstate');
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true;
            }
            return true;
        });
    }]);

})(angular);