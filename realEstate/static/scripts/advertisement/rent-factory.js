angular.module('realEstateClientApp')
	.factory('RentFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';

		var advertisements = [];
		var locations = [];
		var retVal = {};
		
		retVal.getAdvertisements = function() {
			return Restangular.all("advertisments/purpose/rent").getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		retVal.getLocations = function() {
			return Restangular.all("locations/all").getList().then(function(entries) {
				locations = entries;
				return locations;
    		});
		};

		retVal.getAdvertisementsByType = function(purpose, category, type) {
			return Restangular.one("advertisments/purpose", purpose).one("category", category).one("type", type).getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		retVal.getAdvertisementsByCategory = function(purpose, category) {
			return Restangular.one("advertisments/purpose", purpose).one("category", category).getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		return retVal;
	}]);