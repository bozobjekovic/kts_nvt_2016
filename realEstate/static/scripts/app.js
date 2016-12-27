(function(angular) {
'use strict';

angular
    .module('realEstateClientApp', [
        'ngResource',
        'ngRoute',
        'restangular',
        'ui.bootstrap',
        'lodash',
        'angular.filter'
    ])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'AboutCtrl',
                controllerAs: 'about'
            })
            .when('/advertisement', {
                templateUrl: 'views/advertisement.html',
                controller: 'AdvertisementCtrl',
                controllerAs: 'advertisement'
            })
            .when('/buy', {
                templateUrl: 'views/buy.html',
                controller: 'BuyCtrl',
                controllerAs: 'buy'
            })
            .when('/contact', {
                templateUrl: 'views/contact.html',
                controller: 'ContactCtrl',
                controllerAs: 'contact'
            })
            .when('/profile', {
                templateUrl: 'views/profile.html',
                controller: 'ProfileCtrl',
                controllerAs: 'profile'
            })
            .when('/rent', {
                templateUrl: 'views/rent.html',
                controller: 'RentCtrl',
                controllerAs: 'rent'
            })
            .when('/sell', {
                templateUrl: 'views/sell.html',
                controller: 'SellCtrl',
                controllerAs: 'sell'
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