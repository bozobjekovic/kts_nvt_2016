angular.module('realEstateClientApp')
	.factory('AdvertisementFactory', ['Restangular', function(Restangular) {
		'use strict';
		
		var retVal = {};
		var advertisement = {};
		var publisher = {};
		
		retVal.getAdvertisement = function(id) {
			return Restangular.one("advertisments", id).get().then(function(entry) {
				advertisement = entry;
				return advertisement;
    		});
		};

		retVal.getPublisher = function(id) {
			return Restangular.one("advertisments/publisher", id).get().then(function(entry) {
				publisher = entry;
				return publisher;
    		});
		};

		retVal.getCompany = function(id) {
			return Restangular.one("advertisments/company", id).get().then(function(entry) {
				return entry;
    		});
		};

		retVal.getComments = function(id) {
			return Restangular.one("comments/all").customGET('', {id : id}).then(function(entries) {
				return entries;
    		});
		};
		
		retVal.leaveComment = function(id, comment) {
			return Restangular.one("comments/", id).all("new").post(comment).then(function(entry) {
				return entry;
    		});
		};
		
		retVal.report = function(inappropriate) {
			return Restangular.one("inappropriates/", inappropriate.id).all("new").post(inappropriate);
		};
		
		return retVal;
	}]);