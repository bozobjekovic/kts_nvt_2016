(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('RentCtrl', ['$scope', '$rootScope', '$location', '_', 'BuyFactory',
		   function($scope, $rootScope, $location, _, BuyFactory) {
			
			$rootScope.mainMenu = false;
			$scope.purpose = "rent";
			$scope.category = "Residental";

			$scope.cityFilter = {};
			$scope.partFilter = {};
			$scope.heatingType = {};
			
			$scope.maxSize = 5;
			$scope.itemsPerPage = 12;
			$scope.totalItems = 0;
			$scope.currentPage = 0;
			
			var filterForm = {
					priceFrom     : '',
					priceTo       : '',
					landSizeFrom  : '',
					landSizeTo    : '',
					rateFrom      : '',
					rateTo        : '',
					roomsFrom     : '',
					roomsTo       : '',
					floorsFrom    : '',
					floorsTo      : '',
					bathroomsFrom : '',
					bathroomsTo   : '',
					buildYearFrom : '',
					buildYearTo   : ''
					
			};
			
			$scope.filterForm = filterForm;
		
			BuyFactory.getCities().then(function(items) {
			      $scope.cities = items;
			});

			BuyFactory.getPartsOfTheCities().then(function(items) {
			      $scope.partsOfTheCities = items;
			});
			
			$scope.filterAdvertisements = function() {
				$scope.filter = "";
				$scope.filter += "purpose:" + $scope.purpose;
				
				if($scope.type != null && typeof $scope.type != 'undefined'){
					$scope.filter += ",type:" + $scope.type;
				}
				$scope.filter += ",city:";
				for (var property in $scope.cityFilter) {
				    if ($scope.cityFilter.hasOwnProperty(property)) {
				    	if($scope.cityFilter[property] === true){
				    		$scope.filter += property + '_';
				    	}
				    }
				}
				$scope.filter = $scope.filter.substring(0, $scope.filter.length - 1);
				$scope.filter += ",partOfTheCity:";
				for (var property in $scope.partFilter) {
				    if ($scope.partFilter.hasOwnProperty(property)) {
				    	if($scope.partFilter[property] === true){
				    		$scope.filter += property + '_';
				    	}
				    }
				}
				$scope.filter = $scope.filter.substring(0, $scope.filter.length - 1);
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
				$scope.filter += ",heatingType:";
				for (var property in $scope.heatingType) {
				    if ($scope.heatingType.hasOwnProperty(property)) {
				    	if($scope.heatingType[property] === true){
				    		$scope.filter += property + '_';
				    	}
				    }
				}
				$scope.filter = $scope.filter.substring(0, $scope.filter.length - 1);
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

				BuyFactory.filterAdvertisements($scope.category, $scope.filter, $scope.currentPage-1, $scope.itemsPerPage).then(function(object){
					$scope.rentAdvertisements = object.advertisements;
					$scope.totalItems = object.count;
				});
			};
			
			$scope.filterAdvertisements();
			
			$scope.showMenu = function(category){
				$scope.type = null;
				$scope.category = category;
			}

			$scope.setType = function(type){
				$scope.type = type;
			}
			
			$scope.getAdvertisement = function(advertisement){
				$location.path('/advertisement/' + advertisement.id);
			}

			$scope.setPage = function() {
				$scope.filterAdvertisements();
			}

			$scope.buy = function(){
				$location.path('/buy');
			}
	}]);
})(angular);