(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('LoginCtrl', ['$scope', '$uibModal', '$localStorage', 'LoginFactory', '$location',
            function($scope, $uibModal, $localStorage, LoginFactory, $location) {

                $scope.loggedUser = ($localStorage.token) ? true : false;

                var getLoggedUserData = function() {
                    LoginFactory.getLoggedUserData($localStorage.token)
                        .then(function(loggedUser) {
                            $scope.logData = {
                                name : loggedUser.name + ' ' + loggedUser.surname,
                                role : loggedUser.role
                            };
                            $scope.loggedUser = true;
                        });
                };

                if ($localStorage.token) {
                    getLoggedUserData();
                }

                $scope.logOut = function() {
                    $localStorage.$reset();
                    $scope.loggedUser = false;
                    $location.path('/');
                };

                $scope.profile = function() {
                    if ($scope.logData.role === "ADMIN") {
                        $location.path('/profileAdmin');
                    } else if ($scope.logData.role === "USER") {
                        $location.path('/profile');
                    } else if ($scope.logData.role === "CLERK") {
                        $location.path('/profileClerk');
                    } else if ($scope.logData.role === "VERIFIER") {
                        $location.path('/profileVerifier');
                    }
                };

                $scope.openModal = function() {
                    var loginUser = {
                        username : '',
                        password : ''
                    };

                    var modalInstance = $uibModal.open({
                        templateUrl : 'views/modals/signIn.html',
                        controller  : 'LoginModalCtrl',
                        scope       : $scope,
                        resolve     : {
                            loginUser : function() {
                                return loginUser;
                            }
                        }
                    }).closed.then(function(){
                        if ($localStorage.token) {
                            getLoggedUserData();
                        }
                    });
                };
            }
        ])
        .controller('LoginModalCtrl', ['$scope', '$uibModalInstance', 'loginUser', 'LoginFactory', '$localStorage',
            function($scope, $uibModalInstance, loginUser, LoginFactory, $localStorage) {
                $scope.loginUser = loginUser;

                $scope.ok = function() {
                    LoginFactory.logInUser($scope.loginUser).then(function(token) {
                        $localStorage.token = token;
                        $uibModalInstance.close('ok');
                    });
                }

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
