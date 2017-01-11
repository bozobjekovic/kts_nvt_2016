(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('AdvertisementCtrl', ['$scope', '$rootScope', 'AdvertisementFactory', '$uibModal',
		    function($scope, $rootScope, AdvertisementFactory, $uibModal) {
			
				$scope.max = 5;
				$scope.isReadonly = false;
			
				$scope.comment = {
					description : ''
				}
				
				AdvertisementFactory.getAdvertisement($rootScope.advertisement.id).then(function(item) {
				      $scope.advert = item;
				      $scope.rate = $scope.advert.rate;
				});

				AdvertisementFactory.getPublisher($rootScope.advertisement.id).then(function(item) {
				      $scope.publisher = item;
				      $scope.rateUser = $scope.publisher.rate;
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
                
                $scope.rateAdvert = function() {
                	AdvertisementFactory.rate($rootScope.advertisement.id, $scope.rate).then(function(item){
                		$scope.advert = item;
                		$scope.rate = item.rate;
					});
                };
                
               $scope.giveRateUser = function() {
                	AdvertisementFactory.rateUser($scope.publisher.id, $scope.rateUser).then(function(item){
                		$scope.rateUser = item.rate;
                		$scope.publisher.rate = item.rate;
					});
                };
                
                $scope.openModal = function() {
                    var inappropriate = {
  					  title       : '',
  					  description : '',
  					  id          : $scope.advert.advertismentId
      			  }
                    var modalInstance = $uibModal.open({
                        templateUrl : 'views/modals/inappropriate.html',
                        controller  : 'InappropriateModalCtrl',
                        scope       : $scope,
                        resolve     : {
                        	inappropriate : function() {
                                return inappropriate;
                            }
                        }
                    });
                }
			
		}])
		.controller('InappropriateModalCtrl', ['$scope', '$uibModalInstance', 'inappropriate', 'AdvertisementFactory',
            function($scope, $uibModalInstance, inappropriate, AdvertisementFactory) {
                $scope.inappropriate = inappropriate;

                $scope.report = function() {
                	AdvertisementFactory.report($scope.inappropriate);
                    $uibModalInstance.close('ok')
                }

                $scope.cancel = function() {
                    $uibModalInstance.close('cancel');
                };
            }
        ]);
})(angular);