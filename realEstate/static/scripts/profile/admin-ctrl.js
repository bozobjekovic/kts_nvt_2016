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
			
			$scope.accept = function(id){
				AdminFactory.acceptInappropriate(id);
			};
			
			$scope.reject = function(id){
				AdminFactory.rejectInappropriate(id);
			};
			
			$scope.acceptClerk = function(id){
				AdminFactory.acceptClerk(id);
			};
			
			$scope.denyClerk = function(id){
				AdminFactory.denyClerk(id);
			};
	}]);
})(angular);