(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('UserCtrl', ['$scope', '$rootScope', '$location',  '_', 'UserFactory', '$uibModal',
		   function($scope, $rootScope, $location, _, UserFactory, $uibModal) {

			  UserFactory.getUser().then(function(item) {
				      $scope.user = item;
				      $scope.getPublished();
			  });
			  
			  $scope.getPublished = function(){
				  UserFactory.getPublished($scope.user.id).then(function(items) {
				      $scope.published = items;
				  });
			  }
			  
			  $scope.openModal = function() {
                  var user = {
					  name: $scope.user.name,
					  username : $scope.user.username,
					  email : $scope.user.email,
					  surname : $scope.user.surname,
					  phoneNumber : $scope.user.phoneNumber,
					  address : $scope.user.address,
					  city : $scope.user.city
    			  }
                  var modalInstance = $uibModal.open({
                      templateUrl : 'views/modals/updateUser.html',
                      controller  : 'UpdateUserModalCtrl',
                      scope       : $scope,
                      resolve     : {
                    	  user : function() {
                              return user;
                          }
                      }
                  });
              }
			  
		}])
		.controller('UpdateUserModalCtrl', ['$scope', '$uibModalInstance', 'user', 'UserFactory',
           function($scope, $uibModalInstance, user, UserFactory) {
               $scope.user = user;

               $scope.save = function() {
            	   UserFactory.updateUser($scope.user).then(function(item) {
            		   $scope.user = item;
                   });
                   $uibModalInstance.close('ok')
               }

               $scope.cancel = function() {
                   $uibModalInstance.close('cancel');
               };
           }
       ]);
})(angular);