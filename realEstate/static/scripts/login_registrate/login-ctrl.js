(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('LoginCtrl', ['$scope', '$uibModal',
            function($scope, $uibModal) {
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
                    });
                }
            }
        ])
        .controller('LoginModalCtrl', ['$scope', '$uibModalInstance', 'loginUser', 'LoginFactory',
            function($scope, $uibModalInstance, loginUser, LoginFactory) {
                $scope.loginUser = loginUser;

                $scope.ok = function() {
                    LoginFactory.logInUser($scope.loginUser);
                    $uibModalInstance.close('ok')
                }

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
