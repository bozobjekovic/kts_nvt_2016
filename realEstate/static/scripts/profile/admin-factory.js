angular
	.module('admin')
	.factory('AdminFactory', AdminFactory);

	AdminFactory.$inject = ['Restangular', '_', 'toastr'];

	function AdminFactory(Restangular, _, toastr){
		var retVal = {};
		var unapprovedClerks = [];

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
			return Restangular.one("admin/registrate").all("new").post(verifier)
				.then(function() {
					return true;
				}, function(response) {
					if (response.status === 409) {
						toastr.warning('Username or email already exists!', 'Warning');
					}
					return false;
				});
		};

		return retVal;
	}
