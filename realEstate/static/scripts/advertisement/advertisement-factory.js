angular.module('realEstateClientApp')
	.factory('AdvertisementFactory', ['Restangular', 'toastr', function(Restangular, toastr) {
		'use strict';

		var retVal = {};
		var advertisement = {};
		var publisher = {};

		retVal.getAdvertisement = function(id) {
			return Restangular.one("advertisments", id).get().then(function(entry) {
				advertisement = entry.data;
				return advertisement;
    		});
		};

		retVal.updateAdvertisement = function(advertisement) {
			return Restangular.one('advertisments/').customPUT(advertisement).then(function(entry) {
				return entry.data;
			}, function(response) {
                if (response.status === 422) {
                    toastr.warning('Check location details!', 'Warning');
                } else {
					toastr.warning('Phone number already exist!', 'Warning');
                }
            });
		};

		retVal.getPublisher = function(id) {
			return Restangular.one("advertisments/publisher", id).get().then(function(entry) {
				publisher = entry.data;
				return publisher;
    		});
		};

		retVal.getCompany = function(id) {
			return Restangular.one("advertisments/company", id).get().then(function(entry) {
				return entry.data;
    		});
		};

		retVal.getComments = function(id) {
			return Restangular.one("comments/all").customGET('', {id : id}).then(function(entries) {
				return entries.data;
    		});
		};

		retVal.leaveComment = function(id, comment) {
			return Restangular.one('comments/', id).all('new').post(comment).then(function(entry) {
				return entry.data;
    		});
		};

		retVal.report = function(inappropriate) {
			return Restangular.one('inappropriates/', inappropriate.id).all('new').post(inappropriate)
				.then(function() {
					return true;
				}, function(response) {
					if (response.status === 409) {
						toastr.warning('You have already reported this advertisement!', 'Warning');
					}
					return false;
				});
		};

		retVal.rate = function(id, rate) {
			return Restangular.one('advertisments/advertisment', id).one('rate', rate).put().then(function(entry) {
				advertisement = entry.data;
				return advertisement;
    		});
		};

		retVal.rateUser = function(id, rate) {
			return Restangular.one('advertisments/user', id).one('rate', rate).put().then(function(entry) {
				return entry.data;
    		});
		};

		return retVal;
	}]);
