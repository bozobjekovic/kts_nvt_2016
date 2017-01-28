(function (angular) {
    'use strict';

    angular
        .module('registrate', [])
        .controller('RegistrateCtrl', RegistrateCtrl)
        .controller('RegistrateModalCtrl', RegistrateModalCtrl);

    RegistrateCtrl.$inject = ['$scope', '$uibModal', 'RegistrateFactory'];
    RegistrateModalCtrl.$inject = ['$scope', '$uibModalInstance', 'registrateUser', 'RegistrateFactory'];

    function RegistrateCtrl($scope, $uibModal, RegistrateFactory){
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

    function RegistrateModalCtrl($scope, $uibModalInstance, registrateUser, RegistrateFactory){
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
})(angular);
