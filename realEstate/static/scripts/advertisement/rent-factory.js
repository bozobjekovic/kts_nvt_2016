angular.module('realEstateClientApp')
	.factory('RentFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var advertisements = [];
		var retVal = {};
		
		retVal.getAdvertisements = function() {
			return Restangular.all("advertisments/purpose/rent").getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		return retVal;
	}]);