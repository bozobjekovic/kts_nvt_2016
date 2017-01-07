(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('AdminCtrl', ['$scope', '_', 'AdminFactory',
		   function($scope, _, AdminFactory) {
			
			AdminFactory.getAdmin().then(function(item) {
				console.log(item);
				$scope.admin = item;
			});

			AdminFactory.getAllUnapprovedClerks().then(function(items) {
				$scope.unapprovedClerks = items;
			});

			AdminFactory.getAllInappropriates().then(function(items) {
			    $scope.inappropriates = items;
			});
	}]);
})(angular);