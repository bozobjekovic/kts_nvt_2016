(function (angular) {
	'use strict';

	angular
		.module('verifier', [])
		.controller('VerifierCtrl', VerifierCtrl);

	VerifierCtrl.$inject = ['$scope', '_', '$location', 'VerifierFactory'];

	function VerifierCtrl($scope, _, $location, VerifierFactory){
		VerifierFactory.getVerifier().then(function(item) {
			$scope.verifier = item;
		});

		VerifierFactory.getAllInappropriates().then(function(items) {
				$scope.inappropriates = items;
		});

		$scope.accept = function(id){
			VerifierFactory.acceptInappropriate(id);
		};

		$scope.reject = function(id){
			VerifierFactory.rejectInappropriate(id);
		};

		$scope.advertisementPage = function(id){
			$location.path('/advertisement/' + id);
		}
	}
})(angular);
