(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('RegistrateCtrl', ['$scope', '$uibModal',
            function($scope, $uibModal) {
                $scope.openModal = function() {
                    var regustrateUser = {
                        username : '',
                        password : ''
                    };

                    var modalInstance = $uibModal.open({
                        templateUrl : 'views/modals/registrate.html',
                        controller  : 'RegistrateModalCtrl',
                        scope       : $scope,
                        resolve     : {
                            regustrateUser : function() {
                                return regustrateUser;
                            }
                        }
                    });
                }
            }
        ])
        .controller('RegistrateModalCtrl', ['$scope', '$uibModalInstance', 'regustrateUser',
            function($scope, $uibModalInstance, regustrateUser) {
                $scope.ok = function() {
                    //
                    $uibModalInstance.close('ok')
                };

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
