(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('BuyCtrl', ['$scope', '$rootScope', '_', 'BuyFactory',
		   function($scope, $rootScope, _, BuyFactory) {
			
				$rootScope.mainMenu = false;
			
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
				
				$scope.getAdvertisementsByCategory = function(purpose, category) {
					BuyFactory.getAdvertisementsByCategory(purpose, category).then(function(items){
						$scope.buyAdvertisements = items;
					});
				};
				
				$scope.showMenu = function(category){
					if(category === "Residental"){
						$scope.menu = "residental";
					}
					else if(category === "Office"){
						$scope.menu = "office";
					}
					else{
						$scope.menu = "property";
					}
				}
			
		
		}])
		.controller('PaginationCtrl', function ($scope) {	
			  $scope.maxSize = 5;
			  $scope.bigTotalItems = 20;
			  $scope.bigCurrentPage = 1;
		});
})(angular);