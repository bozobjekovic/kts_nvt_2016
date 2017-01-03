(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('RegistrateCtrl', ['$scope', '$uibModal',
            function($scope, $uibModal) {
                $scope.openModal = function() {
                    var registrateUser = {
                        email : '',
                        username : '',
                        password : '',
                        name : '',
                        surname : '',
                        phoneNumber : '',
                        address : '',
                        city : '',
                        authority : '',
                        bankAccount : '',
                        image : '',
                        //
                        companyName : '',
                        companyPhoneNumber : '',
                        site : '',
                        //
                        companyAddress : '',
                        companyLocation : {
                            city : '',
                            zipCode : '',
                            partOfTheCity : ''
                        }
                    };

                    var modalInstance = $uibModal.open({
                        templateUrl : 'views/modals/registrate.html',
                        controller  : 'RegistrateModalCtrl',
                        scope       : $scope,
                        resolve     : {
                            registrateUser : function() {
                                return registrateUser;
                            }
                        }
                    });
                }
            }
        ])
        .controller('RegistrateModalCtrl', ['$scope', '$uibModalInstance', 'registrateUser', 'RegistrateFactory',
            function($scope, $uibModalInstance, registrateUser, RegistrateFactory) {
                $scope.registrateUser = registrateUser;

                $scope.ok_user = function() {
                    RegistrateFactory.registrateUser($scope.registrateUser);
                    $uibModalInstance.close('ok_user')
                };

                $scope.ok_clerk = function() {
                    RegistrateFactory.registrateClerk($scope.registrateUser);
                    $uibModalInstance.close('ok_clerk')
                };

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
