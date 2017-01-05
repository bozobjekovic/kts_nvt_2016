angular.module('realEstateClientApp')
	.factory('BuyFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var advertisements = [];
		var locations = [];
		var retVal = {};

		retVal.getAdvertisementSize = function() {
			return Restangular.all('advertisments/size').get().then(function(size) {
				return size;
			});
		};
		
		retVal.getCities = function() {
			return Restangular.all("locations/city").getList().then(function(entries) {
				locations = entries;
				return locations;
    		});
		};

		retVal.getPartsOfTheCities = function() {
			return Restangular.all("locations/partOfTheCities").getList().then(function(entries) {
				locations = entries;
				return locations;
    		});
		};
		
		retVal.filterAdvertisements = function(category, filterStr, page, size) {
			return Restangular.one("advertisments/category", category).all("filters").customGET('', {
				filter: filterStr,
				page: page,
				size: size
			}).then(function(entries) {
				return {
					advertisements: entries.filteredAdvertisementsDTO,
					count: entries.count
				};
    		});
		};
		
		return retVal;
	}]);
