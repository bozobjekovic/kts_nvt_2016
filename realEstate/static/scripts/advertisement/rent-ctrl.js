(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('RentCtrl', ['$scope', '_', 'RentFactory',
		   function($scope, _, RentFactory) {
			
			$scope.mainMenu = true;
			
			RentFactory.getAdvertisements().then(function(items) {
			      $scope.rentAdvertisements = items;
			   });
			
			RentFactory.getLocations().then(function(items) {
			      $scope.locations = items;
			});
			
			$scope.getAdvertisementsByType = function(purpose, category, type) {
				RentFactory.getAdvertisementsByType(purpose, category, type).then(function(items){
					$scope.rentAdvertisements = items;
				});
			};
			
			$scope.getAdvertisementsByCategory = function(purpose, category) {
				RentFactory.getAdvertisementsByCategory(purpose, category).then(function(items){
					$scope.rentAdvertisements = items;
				});
			};

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