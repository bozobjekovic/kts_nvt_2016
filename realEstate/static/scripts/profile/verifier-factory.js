angular.module('realEstateClientApp')
	.factory('VerifierFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';

		var retVal = {};
		var inappropriates = [];

		retVal.getVerifier = function() {
			return Restangular.one('verifiers/verifier').get().then(function(item) {
				return item.data;
			});
		};

		retVal.getAllInappropriates = function() {
			return Restangular.all("verifiers/inappropriate").getList().then(function(entries) {
				inappropriates = entries.data;
				return inappropriates;
    		});
		};

		retVal.acceptInappropriate = function(id) {
			return Restangular.one("verifiers/inappropriate/accept", id).remove().then(function() {
				_.remove(inappropriates, {
          			id: id
        		});
    		});
		};

		retVal.rejectInappropriate = function(id) {
			return Restangular.one("verifiers/inappropriate/reject", id).remove().then(function() {
				_.remove(inappropriates, {
          			id: id
        		});
    		});
		};

		return retVal;
	}]);
