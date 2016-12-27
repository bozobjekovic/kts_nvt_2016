angular.module('realEstateClientApp')
	.factory('BuyFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var advertisements = [];
		var retVal = {};
		
		retVal.getAdvertisements = function() {
			return Restangular.all("advertisments").getList().then(function(entries) {
				advertisements = entries;
				return advertisements;
    		});
		};
		
		return retVal;
	}]);