(function (angular) {
	'use strict';

	angular
		.module('advertisement', [])
		.controller('AdvertisementCtrl', AdvertisementCtrl)
		.controller('InappropriateModalCtrl', InappropriateModalCtrl);

		AdvertisementCtrl.$inject = ['$scope', '$rootScope', '$routeParams', 'AdvertisementFactory', '$uibModal'];
		InappropriateModalCtrl.$inject = ['$scope', '$uibModalInstance', 'inappropriate', 'AdvertisementFactory'];

		function AdvertisementCtrl($scope, $rootScope, $routeParams, AdvertisementFactory, $uibModal) {
			$rootScope.mainMenu = false;

			$scope.max = 5;
			$scope.isReadonly = false;
			var param = $routeParams.param;

			$scope.myInterval = 5000;
			$scope.noWrapSlides = false;
			$scope.active = 0;
			//var slides = $scope.slides = [];
			var currIndex = 0;

			$scope.comment = {
				description : ''
			}

			AdvertisementFactory.getAdvertisement(param).then(function(item) {
						$scope.advert = item;
						$scope.rate = $scope.advert.rate;
			});

			AdvertisementFactory.getPublisher(param).then(function(item) {
						$scope.publisher = item;
						$scope.rateUser = $scope.publisher.rate;
			});

			AdvertisementFactory.getCompany(param).then(function(item) {
						$scope.company = item;
			});

			AdvertisementFactory.getComments(param).then(function(items){
				$scope.comments = items;
			});

			$scope.leaveComment = function() {
				AdvertisementFactory.leaveComment(param, $scope.comment).then(function(item){
					$scope.comments.unshift(item);
					$scope.comment.description = '';
				});
							};

							$scope.rateAdvert = function() {
								AdvertisementFactory.rate(param, $scope.rate).then(function(item){
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
		}

		function InappropriateModalCtrl($scope, $uibModalInstance, inappropriate, AdvertisementFactory) {
			$scope.inappropriate = inappropriate;

			$scope.report = function() {
				AdvertisementFactory.report($scope.inappropriate).then(function(succeeded) {
					$uibModalInstance.close('ok');
				});
		   }

				$scope.cancel = function() {
						$uibModalInstance.close('cancel');
				};
		}
})(angular);
