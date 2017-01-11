(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('UserCtrl', ['$scope', '$rootScope', '$location',  '_', 'UserFactory', '$uibModal',
		   function($scope, $rootScope, $location, _, UserFactory, $uibModal) {
			
			  $rootScope.mainMenu = true;

			  UserFactory.getUser().then(function(item) {
				      $scope.user = item;
				      $scope.getPublished();
				      $scope.getCompany();
			  });
			  
			  $scope.getAppliedUsers = function(id){
				  UserFactory.getAppliedUsers(id).then(function(items) {
					  $scope.appliedUsers = items;
				  });
			  }
			  
			  $scope.getPublished = function(){
				  UserFactory.getPublished($scope.user.id).then(function(items) {
				      $scope.published = items;
				  });
			  }
			  
			  $scope.getPublishedByStatus = function(status){
				  UserFactory.getPublishedByStatus(status, $scope.user.id).then(function(items) {
				      $scope.published = items;
				  });
			  }
			  
			  $scope.remove = function(id){
				  UserFactory.remove(id);
			  }
			  
			  $scope.accept = function(id){
				  UserFactory.accept(id, $scope.company.id);
			  }
			  
			  $scope.deny = function(id){
				  UserFactory.deny(id);
			  }
			  
			  $scope.getCompany = function(){
				  UserFactory.getCompany($scope.user.id).then(function(item) {
				      $scope.company = item;
				      $scope.getAppliedUsers(item.id);
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