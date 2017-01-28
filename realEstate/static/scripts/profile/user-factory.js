angular.module('user')
	.factory('UserFactory', UserFactory);

	UserFactory.$inject = ['Restangular', '_', 'toastr'];

	function UserFactory(Restangular, _, toastr){
		var retVal = {};
		var published = [];
		var appliedUsers = [];

		retVal.getUser = function() {
			return Restangular.one("users/user").get().then(function(entries) {
				return entries.data;
    		});
		};

		retVal.getPublished = function(id) {
			return Restangular.one("users/published", id).getList().then(function(entries) {
				published = entries.data;
				return published;
    		});
		};

		retVal.updateUser = function(userDTO) {
			return Restangular.one("users/").customPUT(userDTO).then(function(entry) {
				return entry.data;
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
				published = entries.data;
				return published;
    		});
		};

		retVal.getCompany = function(id) {
			return Restangular.one("users/company", id).get().then(function(entry) {
				return entry.data;
    		});
		};

		retVal.getAppliedUsers = function(id) {
			return Restangular.one("users/company", id).one('applied').get().then(function(entries) {
				appliedUsers = entries.data;
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
				return entries.data;
    		});
		};

		retVal.apply = function(id_company) {
			return Restangular.one("users/apply", id_company).put();
		};

		retVal.rent = function(rent) {
			if(rent.rentDateFrom > rent.rentDateTo){
				toastr.warning('From date can not be after To Date!', 'Warning');
				return;
			}
			return Restangular.one("users/rent", rent.id).one('from', new Date(rent.rentDateFrom))
			.one('to', new Date(rent.rentDateTo)).put().then(function() {
                    	toastr.success('Renting real estate successful!');
                    	return true;
                	},function(response) {
	                    if (response.status === 409) {
	                        toastr.warning('Real estate is already rented in this period!', 'Warning');
	                    }
                });
		};

		return retVal;
	}
