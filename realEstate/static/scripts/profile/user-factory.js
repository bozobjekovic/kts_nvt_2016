angular.module('realEstateClientApp')
	.factory('UserFactory', ['Restangular', '_',  function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		var published = [];
		
		retVal.getUser = function() {
			return Restangular.one("users/user").get().then(function(entries) {
				return entries;
    		});
		};
		
		retVal.getPublished = function(id) {
			return Restangular.one("users/published", id).getList().then(function(entries) {
				published = entries;
				return published;
    		});
		};
		
		retVal.updateUser = function(userDTO) {
			return Restangular.one("users/").customPUT(userDTO).then(function(entry) {
				return entry;
    		});
		};
		
		retVal.remove = function(id) {
			return Restangular.one("advertisments/delete", id).put().then(function() {
				_.remove(published, {
          			id: id
        		});
    		});
		};
		
		retVal.getPublishedByStatus = function(status, id) {
			return Restangular.one("users/published/", status).one('user', id).getList().then(function(entries) {
				published = entries;
				return published;
    		});
		};		
		
		return retVal;
	}]);