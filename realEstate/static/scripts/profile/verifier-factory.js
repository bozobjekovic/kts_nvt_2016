angular.module('realEstateClientApp')
	.factory('VerifierFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		var unverifiedAdvers = [];
		
		retVal.getVerifier = function() {
			return Restangular.one('verifiers/verifier').get().then(function(item) {
				return item;
			});
		};
		
		retVal.getAllUnverifiedAdvertisements = function() {
			return Restangular.all("verifiers/advertisement/unverified").getList().then(function(entries) {
				unverifiedAdvers = entries;
				return unverifiedAdvers;
    		});
		};
		
		return retVal;
	}]);
