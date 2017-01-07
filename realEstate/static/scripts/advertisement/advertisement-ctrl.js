(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('AdvertisementCtrl', ['$scope', '$rootScope', 'AdvertisementFactory',
		    function($scope, $rootScope, AdvertisementFactory) {
			
				$scope.comment = {
					description : ''
				}
				
				AdvertisementFactory.getAdvertisement($rootScope.advertisement.id).then(function(item) {
				      $scope.advert = item;
				});

				AdvertisementFactory.getPublisher($rootScope.advertisement.id).then(function(item) {
				      $scope.publisher = item;
				});

				AdvertisementFactory.getCompany($rootScope.advertisement.id).then(function(item) {
				      $scope.company = item;
				});

				AdvertisementFactory.getComments($rootScope.advertisement.id).then(function(items){
					$scope.comments = items;
				});
				
				$scope.leaveComment = function() {
					AdvertisementFactory.leaveComment($rootScope.advertisement.id, $scope.comment).then(function(item){
						$scope.comments.unshift(item);		
					});
                		};
			
		}])
})(angular);