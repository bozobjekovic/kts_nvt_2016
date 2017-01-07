angular.module('realEstateClientApp')
	.factory('AdminFactory', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		var unapprovedClerks = [];
		var inappropriates = [];
		
		retVal.getAdmin = function() {
			return Restangular.one('admin/').get().then(function(item) {
				return item;
			});
		};
		
		retVal.getAllUnapprovedClerks = function() {
			return Restangular.all("admin/unapproved/clerks").getList().then(function(entries) {
				unapprovedClerks = entries;
				return unapprovedClerks;
    		});
		};
		
		retVal.getAllInappropriates = function() {
			return Restangular.all("admin/inappropriate").getList().then(function(entries) {
				inappropriates = entries;
				return inappropriates;
    		});
		};
		
		return retVal;
	}]);
