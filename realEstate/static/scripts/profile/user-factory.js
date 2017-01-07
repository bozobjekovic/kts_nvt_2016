angular.module('realEstateClientApp')
	.factory('UserFactory', ['Restangular', function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		
		retVal.getUser = function() {
			return Restangular.one("users/user").get().then(function(entries) {
				return entries;
    		});
		};
		
		retVal.getPublished = function(id) {
			return Restangular.one("users/published", id).getList().then(function(entries) {
				return entries;
    		});
		};
		
		retVal.updateUser = function(userDTO) {
			console.log(userDTO.email);
			return Restangular.one("users/").customPUT(userDTO).then(function(entry) {
				return entry;
    		});
		};
		
		
		return retVal;
	}]);