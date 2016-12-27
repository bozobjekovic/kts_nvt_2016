(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('BuyCtrl', ['$scope', '_', 'BuyFactory',
		   function($scope, _, BuyFactory) {
			
			BuyFactory.getLocations().then(function(items) {
			      $scope.locations = items;
			});
			
			BuyFactory.getAdvertisements().then(function(items) {
			      $scope.buyAdvertisements = items;
			});
			
			$scope.getAdvertisementsByType = function(purpose, category, type) {
				BuyFactory.getAdvertisementsByType(purpose, category, type).then(function(items){
					$scope.buyAdvertisements = items;
				});
			};
		
		}])
		.controller('PaginationCtrl', function ($scope) {	
			  $scope.maxSize = 5;
			  $scope.bigTotalItems = 20;
			  $scope.bigCurrentPage = 1;
		});
})(angular);