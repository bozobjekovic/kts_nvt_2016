(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .controller('RegistrateCtrl', ['$scope', '$uibModal', 'RegistrateFactory',
            function($scope, $uibModal, RegistrateFactory) {

                /* for location */
				$scope.cities = [];

				RegistrateFactory.getAllCities().then(function(cities) {
					$scope.cities = cities;
				});

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
                        image : 'images/user1.jpg',
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

                $scope.submitRegistrateUserForm = function() {
                    RegistrateFactory.registrateUser($scope.registrateUser).then(function(succeeded) {
                        if (succeeded) {
                            $uibModalInstance.close('ok_user');
                        }
                    });
                };

                $scope.submitRegistrateClerkForm = function() {
                    RegistrateFactory.registrateClerk($scope.registrateUser).then(function(succeeded) {
                        if (succeeded) {
                            $uibModalInstance.close('ok_clerk');
                        }
                    });
                };

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);
