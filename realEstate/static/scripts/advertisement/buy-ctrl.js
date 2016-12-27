(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('BuyCtrl', ['$scope', '_', 'BuyFactory',
		   function($scope, _, BuyFactory) {
			
			BuyFactory.getAdvertisements().then(function(items) {
			      $scope.advertisements = items;
			   });
		
	}]);
})(angular);