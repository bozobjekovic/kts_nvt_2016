(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('AdvertisementCtrl', ['$scope', '$rootScope', 'AdvertisementFactory',
		    function($scope, $rootScope, AdvertisementFactory) {
				
				AdvertisementFactory.getAdvertisement($rootScope.advertisement.id).then(function(item) {
				      $scope.advert = item;
				});
			
		}])
})(angular);