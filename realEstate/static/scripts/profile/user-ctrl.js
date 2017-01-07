(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('UserCtrl', ['$scope', '$rootScope', '$location',  '_', 'UserFactory',
		   function($scope, $rootScope, $location, _, UserFactory) {

			  UserFactory.getUser().then(function(item) {
				      $scope.user = item;
			  });
			  
			  $scope.getPublished = function(){
				  UserFactory.getPublished($scope.user.id).then(function(items) {
					  console.log(items);
				      $scope.published = items;
				  });
			  }
			  
		}])
})(angular);