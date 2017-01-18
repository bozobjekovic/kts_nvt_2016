(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('LoginCtrl', ['$scope', '$rootScope', '$uibModal', '$localStorage', 'LoginFactory', '$location',
            function($scope, $rootScope, $uibModal, $localStorage, LoginFactory, $location) {
                $rootScope.mainMenu = true;

                $scope.loggedUser = ($localStorage.token) ? true : false;

                var getLoggedUserData = function() {
                    LoginFactory.getLoggedUserData($localStorage.token)
                        .then(function(loggedUser) {
                            $scope.logData = {
                                name : loggedUser.name + ' ' + loggedUser.surname,
                                role : loggedUser.role
                            };
                            $scope.loggedUser = true;
                            $rootScope.currentUser = true;
                            $localStorage.currentUser = loggedUser;
                        });
                };

                if ($localStorage.token) {
                    getLoggedUserData();
                }

                $scope.logOut = function() {
                    $localStorage.$reset();
                    $scope.loggedUser = false;
                    $rootScope.currentUser = false;
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

                $scope.openModal = function(path) {
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
                    }).result.then(function(status){
                        if (status === 'ok') {
                            getLoggedUserData();
                            if (path) {
                                $location.path(path);
                            }
                        }
                    });
                };
            }
        ])
        .controller('LoginModalCtrl', ['$scope', '$uibModalInstance', 'loginUser', 'LoginFactory', '$localStorage',
            function($scope, $uibModalInstance, loginUser, LoginFactory, $localStorage) {
                $scope.loginUser = loginUser;

                $scope.submitLoginForm = function() {
                    LoginFactory.logInUser($scope.loginUser).then(function(response) {
                        if (response.status) {
                            $localStorage.token = response.token;
                            $uibModalInstance.close('ok');
                        }
                    });
                }

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
