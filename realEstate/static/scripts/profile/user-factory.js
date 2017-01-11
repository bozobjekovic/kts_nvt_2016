angular.module('realEstateClientApp')
	.factory('UserFactory', ['Restangular', '_',  function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		var published = [];
		var appliedUsers = [];
		
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
		
		retVal.getCompany = function(id) {
			return Restangular.one("users/company", id).get().then(function(entry) {
				return entry;
    		});
		};
		
		retVal.getAppliedUsers = function(id) {
			return Restangular.one("users/company", id).one('applied').get().then(function(entries) {
				appliedUsers = entries;
				return appliedUsers;
    		});
		};
		
		retVal.accept = function(id, id_company) {
			return Restangular.one("users/accept", id).one('company', id_company).put().then(function() {
				_.remove(appliedUsers, {
					id : id
				});
    		});
		};
		
		retVal.deny = function(id) {
			return Restangular.one("users/deny", id).put().then(function() {
				_.remove(appliedUsers, {
					id : id
				});
    		});
		};
		
		retVal.getAllCompanies = function() {
			return Restangular.all("companies/all").getList().then(function(entries) {
				return entries;
    		});
		};
		
		retVal.apply = function(id_company) {
			return Restangular.one("users/apply", id_company).put();
		};
		
		return retVal;
	}]);