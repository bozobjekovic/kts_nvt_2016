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
		
		retVal.acceptAdvertisement = function(id) {
			return Restangular.one("verifiers/accept", id).put().then(function() {
				_.remove(unverifiedAdvers, {
          			id: id
        		});
    		});
		};
		
		retVal.rejectAdvertisement = function(id) {
			return Restangular.one("verifiers/reject", id).remove().then(function() {
				_.remove(unverifiedAdvers, {
          			id: id
        		});
    		});
		};
		
		return retVal;
	}]);
