angular.module('realEstateClientApp')
	.factory('BuyFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var advertisements = [];
		var locations = [];
		var retVal = {};
		
		retVal.getLocations = function() {
			return Restangular.all("locations/all").getList().then(function(entries) {
				locations = entries;
				return locations;
    		});
		};
		
		retVal.getAdvertisements = function() {
			return Restangular.all("advertisments/purpose/sell").getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		retVal.getAdvertisementsByType = function(purpose, category, type) {
			return Restangular.one("advertisments/purpose", purpose).one("category", category).one("type", type).getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		return retVal;
	}]);
