angular.module('realEstateClientApp')
	.factory('AdminFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';

		var retVal = {};
		var unapprovedClerks = [];
		var inappropriates = [];

		retVal.getAdmin = function() {
			return Restangular.one('admin/').get().then(function(item) {
				return item.data;
			});
		};

		retVal.getAllUnapprovedClerks = function() {
			return Restangular.all("admin/unapproved/clerks").getList().then(function(entries) {
				unapprovedClerks = entries.data;
				return unapprovedClerks;
    		});
		};

		retVal.getAllInappropriates = function() {
			return Restangular.all("admin/inappropriate").getList().then(function(entries) {
				inappropriates = entries.data;
				return inappropriates;
    		});
		};

		retVal.acceptInappropriate = function(id) {
			return Restangular.one("admin/inappropriate/accept", id).remove().then(function() {
				_.remove(inappropriates, {
          			id: id
        		});
    		});
		};

		retVal.rejectInappropriate = function(id) {
			return Restangular.one("admin/inappropriate/reject", id).remove().then(function() {
				_.remove(inappropriates, {
          			id: id
        		});
    		});
		};

		retVal.acceptClerk = function(id) {
			return Restangular.one("admin/accept", id).put().then(function() {
				_.remove(unapprovedClerks, {
          			id: id
        		});
    		});
		};

		retVal.denyClerk = function(id) {
			return Restangular.one("admin/deny", id).remove().then(function() {
				_.remove(unapprovedClerks, {
          			id: id
        		});
    		});
		};

		retVal.regVerifier = function(verifier) {
			return Restangular.one("admin/registrate").all("new").post(verifier);
		};

		return retVal;
	}]);
