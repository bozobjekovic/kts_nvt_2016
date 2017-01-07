(function (angular) {
	'use strict';
	
	angular.module('realEstateClientApp')
		.controller('AdvertisementCtrl', ['$scope', '$rootScope', 'AdvertisementFactory', '$uibModal',
		    function($scope, $rootScope, AdvertisementFactory, $uibModal) {
			
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