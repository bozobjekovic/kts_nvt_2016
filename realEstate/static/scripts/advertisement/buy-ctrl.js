(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('BuyCtrl', ['$scope', '$rootScope', '_', 'BuyFactory',
		   function($scope, $rootScope, _, BuyFactory) {
			
				$rootScope.mainMenu = false;
				$scope.purpose = "sell";
				$scope.category = "Residental";
				
				$scope.filterForm = {
						priceFrom     : '',
						priceTo       : '',
						landSizeFrom  : '',
						landSizeTo    : '',
						rateFrom      : '',
						rateTo        : '',
						heatingType   : '',
						roomsFrom     : '',
						roomsTo       : '',
						floorsFrom    : '',
						floorsTo      : '',
						bathroomsFrom : '',
						bathroomsTo   : '',
						buildYearFrom : '',
						buildYearTo   : ''
						
				};
			
				BuyFactory.getLocations().then(function(items) {
				      $scope.locations = items;
				});
				
				BuyFactory.getAdvertisementsByCategory($scope.purpose, $scope.category).then(function(items) {
				      $scope.buyAdvertisements = items;
				});
				
				$scope.getAdvertisementsByType = function(type) {
					$scope.type = type;
					BuyFactory.getAdvertisementsByType($scope.purpose, $scope.category, $scope.type).then(function(items){
						$scope.buyAdvertisements = items;
					});
				};
				
				$scope.getAdvertisementsByCategory = function() {
					BuyFactory.getAdvertisementsByCategory($scope.purpose, $scope.category).then(function(items){
						$scope.buyAdvertisements = items;
					});
				};
				
				$scope.filterAdvertisements = function(location, data, status) {
					$scope.filter = "";
					if($scope.type != null && typeof $scope.type != 'undefined'){
						$scope.filter += ",type:" + $scope.type;
					}
					if(typeof location != 'undefined' && data === 'c' && status === true){
						console.log(location.city);
						$scope.filter += ",city:" + location.city;
					}
					if(typeof location != 'undefined' && data === 'p' && status === true){
						console.log(location.partOfTheCity);
						$scope.filter += ",partOfTheCity:" + location.partOfTheCity;
					}
					if($scope.filterForm.priceFrom != null && $scope.filterForm.priceFrom != ''){
						$scope.filter += ",price>" + $scope.filterForm.priceFrom;
					}
					if($scope.filterForm.priceTo != null && $scope.filterForm.priceTo != ''){
						$scope.filter += ",price<" + $scope.filterForm.priceTo;
					}
					if($scope.filterForm.landSizeFrom != null && $scope.filterForm.landSizeFrom != ''){
						$scope.filter += ",landSize>" + $scope.filterForm.landSizeFrom;
					}
					if($scope.filterForm.landSizeTo != null && $scope.filterForm.landSizeTo != ''){
						$scope.filter += ",landSize<" + $scope.filterForm.landSizeTo;
					}
					if($scope.filterForm.rateFrom != null && $scope.filterForm.rateFrom != ''){
						$scope.filter += ",rate>" + $scope.filterForm.rateFrom;
					}
					if($scope.filterForm.rateTo != null && $scope.filterForm.rateTo != ''){
						$scope.filter += ",rate<" + $scope.filterForm.rateTo;
					}
					if($scope.filterForm.roomsFrom != null && $scope.filterForm.roomsFrom != ''){
						$scope.filter += ",numOfBedRooms>" + $scope.filterForm.roomsFrom;
					}
					if($scope.filterForm.roomsTo != null && $scope.filterForm.roomsTo != ''){
						$scope.filter += ",numOfBedRooms<" + $scope.filterForm.roomsTo;
					}
					if($scope.filterForm.floorsFrom != null && $scope.filterForm.floorsFrom != ''){
						$scope.filter += ",numOfFlors>" + $scope.filterForm.floorsFrom;
					}
					if($scope.filterForm.floorsTo != null && $scope.filterForm.floorsTo != ''){
						$scope.filter += ",numOfFlors<" + $scope.filterForm.floorsTo;
					}
					if($scope.filterForm.bathroomsFrom != null && $scope.filterForm.bathroomsFrom != ''){
						$scope.filter += ",numOfBathRooms>" + $scope.filterForm.bathroomsFrom;
					}
					if($scope.filterForm.bathroomsTo != null && $scope.filterForm.bathroomsTo != ''){
						$scope.filter += ",numOfBathRooms<" + $scope.filterForm.bathroomsTo;
					}
					if($scope.filterForm.buildYearFrom != null && $scope.filterForm.buildYearFrom != ''){
						$scope.filter += ",buildYear>" + $scope.filterForm.buildYearFrom;
					}
					if($scope.filterForm.buildYearTo != null && $scope.filterForm.buildYearTo != ''){
						$scope.filter += ",buildYear<" + $scope.filterForm.buildYearTo;
					}
					BuyFactory.filterAdvertisements($scope.purpose, $scope.category , $scope.filter).then(function(items){
						$scope.buyAdvertisements = items;
					});
				};
				
				$scope.showMenu = function(category){
					if(category === "Residental"){
						$scope.category = "Residental";
					}
					else if(category === "Office"){
						$scope.category = "Office";
					}
					else{
						$scope.category = "Property";
					}
				}
			
		
		}])
		.controller('PaginationCtrl', function ($scope) {	
			  $scope.maxSize = 10;
			  $scope.bigTotalItems = 100;
			  $scope.bigCurrentPage = 1;
		});
})(angular);