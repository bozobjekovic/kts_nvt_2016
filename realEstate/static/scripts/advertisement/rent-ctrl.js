(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('RentCtrl', ['$scope', '_', 'RentFactory',
		   function($scope, _, RentFactory) {
			
			$scope.mainMenu = true;
			
			RentFactory.getAdvertisements().then(function(items) {
			      $scope.rentAdvertisements = items;
			   });

			$scope.showResidentalMenu = function() {
				$scope.menu = 'residental';
			};
			
			$scope.showOfficeMenu = function() {
				$scope.menu = 'office';
			};
			
			$scope.showPropertyMenu = function() {
				$scope.menu = 'property';
			};
	}]);
})(angular);