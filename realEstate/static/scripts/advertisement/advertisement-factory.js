angular.module('realEstateClientApp')
	.factory('AdvertisementFactory', ['Restangular', function(Restangular) {
		'use strict';
		
		var retVal = {};
		var advertisement = {};
		
		retVal.getAdvertisement = function(id) {
			return Restangular.one("advertisments", id).get().then(function(entry) {
				advertisement = entry;
				return entry;
    		});
		};
		
		return retVal;
	}]);