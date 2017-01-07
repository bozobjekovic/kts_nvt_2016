(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('VerifierCtrl', ['$scope', '_', 'VerifierFactory',
		   function($scope, _, VerifierFactory) {
			
			VerifierFactory.getVerifier().then(function(item) {
				$scope.verifier = item;
			});

			VerifierFactory.getAllUnverifiedAdvertisements().then(function(items) {
			      $scope.unverifiedAdvers = items;
			});
			
			$scope.accept = function(id){
				VerifierFactory.acceptAdvertisement(id);
			};
			
			$scope.reject = function(id){
				VerifierFactory.rejectAdvertisement(id);
			};
	}]);
})(angular);