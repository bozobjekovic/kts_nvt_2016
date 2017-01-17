(function(angular) {
'use strict';

angular
    .module('realEstateClientApp', [
        'ngResource',
        'ngRoute',
        'restangular',
        'ui.bootstrap',
        'lodash',
        'angular.filter',
        'flow',
        'ngStorage',
        'ngMap',
        'toastr',
        'ngAnimate'
    ])
    .config(['$routeProvider', '$httpProvider', 'RestangularProvider', '$locationProvider', function($routeProvider, $httpProvider, RestangularProvider, $locationProvider) {
        $locationProvider.hashPrefix('');
        RestangularProvider.setFullResponse(true);
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/about', {
                templateUrl: 'views/about.html',
                controller: 'MainCtrl'
            })
            .when('/advertisement/:param', {
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
                controller: 'MainCtrl'
            })
            .when('/profile', {
                templateUrl: 'views/profile.html',
                controller: 'UserCtrl'
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
            .when('/profileVerifier', {
                templateUrl: 'views/profileVerifier.html',
                controller: 'VerifierCtrl',
                controllerAs: 'profileVerifier'
            })
            .when('/profileAdmin', {
                templateUrl: 'views/profileAdmin.html',
                controller: 'AdminCtrl',
                controllerAs: 'profileAdmin'
            })
            .when('/profileClerk', {
                templateUrl: 'views/profileClerk.html',
                controller: 'UserCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
        $httpProvider
            .interceptors.push(['$q', '$localStorage', '$location',
                function($q, $localStorage, $location) {
                    return {
                        'request': function(config) {
                            config.headers = config.headers || {};
                            if($localStorage.token) {
                                config.headers["X-Auth-Token"] = $localStorage.token;
                            }
                            return config;
                        },
                        'responseError': function(response) {
                            if (response.status === 401 || response.status === 403) {
                                $location.path('/');
                            }
                            return $q.reject(response);
                        }
                    };
                }
        ]);
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
