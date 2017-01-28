(function (angular) {
	'use strict';

	angular
		.module('user', [])
		.controller('UserCtrl', UserCtrl)
		.controller('UpdateUserModalCtrl', UpdateUserModalCtrl)
		.controller('UpdateAdvertisementModalCtrl', UpdateAdvertisementModalCtrl)
    .controller('RentModalCtrl', RentModalCtrl);

		UserCtrl.$inject = ['$scope', '$rootScope', '$location',  '_', '$localStorage', 'UserFactory', 'AdvertisementFactory', '$uibModal'];
		UpdateUserModalCtrl.$inject = ['$scope', '$uibModalInstance', 'user', 'UserFactory'];
		UpdateAdvertisementModalCtrl.$inject = ['$scope', '$uibModalInstance', 'advertisement', 'AdvertisementFactory'];
		RentModalCtrl.$inject = ['$scope', '$uibModalInstance', 'rent', 'UserFactory'];

		function UserCtrl($scope, $rootScope, $location, _, $localStorage, UserFactory, AdvertisementFactory, $uibModal){
			$rootScope.mainMenu = true;

			UserFactory.getUser().then(function(item) {
						$scope.user = item;
						$scope.getPublished();
						$scope.getCompany();
			});

			$scope.getAppliedUsers = function(id){
				UserFactory.getAppliedUsers(id).then(function(items) {
					$scope.appliedUsers = items;
				});
			}

			$scope.getPublished = function(){
				UserFactory.getPublished($scope.user.id).then(function(items) {
						$scope.published = items;
				});
			}

			$scope.getClerkPublished = function(){
				UserFactory.getClerkPublished($scope.user.id).then(function(items) {
						$scope.published = items;
				});
			}

			$scope.getPublishedByStatus = function(status){
				UserFactory.getPublishedByStatus(status, $scope.user.id).then(function(items) {
						$scope.published = items;
				});
			}

			$scope.remove = function(id){
				UserFactory.remove(id);
			}

			$scope.accept = function(id){
				UserFactory.accept(id, $scope.company.id);
			}

			$scope.deny = function(id){
				UserFactory.deny(id);
			}

			$scope.apply = function(id){
				UserFactory.apply(id);
				$scope.user.applied = true;
			}

			$scope.getCompany = function(){
				UserFactory.getCompany($scope.user.id).then(function(item) {
						$scope.company = item;
						if(item != null){
							$scope.getAppliedUsers(item.id);
						}
						else{
							$scope.getCompanies();
						}
				});
			}

			$scope.getCompanies = function(){
				UserFactory.getAllCompanies().then(function(items) {
						$scope.companies = items;
				});
			}

			$scope.advertisementPage = function(advertisement){
				$location.path('/advertisement/' + advertisement.id);
			}

			$scope.updateModal = function(id) {
				var advertisement = {};
				AdvertisementFactory.getAdvertisement(id).then(function(item) {
					advertisement = {
						advertismentId : item.advertismentId,
									name: item.name,
											location: {
													city : item.location.city,
													zipCode : item.location.zipCode,
													partOfTheCity : item.location.partOfTheCity
											},
											landSize: item.landSize,
											techEquipment: item.techEquipment,
											address: item.address,
											heatingType: item.heatingType,
											images: [],
											numOfBathRooms: item.numOfBathRooms,
											numOfBedRooms: item.numOfBedRooms,
											numOfFlors: item.numOfFlors,
											buildYear: item.buildYear,
											category: item.category,
											type: item.type,
											price: item.price,
											phoneNumber: item.phoneNumber,
											purpose: item.purpose,
											activeUntil: item.activeUntil,
											status : item.status
					}
					var modalInstance = $uibModal.open({
											templateUrl : 'views/modals/updateAdvertisement.html',
											controller  : 'UpdateAdvertisementModalCtrl',
											scope       : $scope,
											resolve     : {
												advertisement : function() {
															return advertisement;
													}
											}
									}).result.then(function (result) {
											if(result.status == "ok"){
												$scope.getPublished();
											}
									});
				});

						}

			$scope.openModal = function() {
								var user = {
									name: $scope.user.name,
									username : $scope.user.username,
									email : $scope.user.email,
									surname : $scope.user.surname,
									phoneNumber : $scope.user.phoneNumber,
									address : $scope.user.address,
									city : $scope.user.city
									}
								var modalInstance = $uibModal.open({
										templateUrl : 'views/modals/updateUser.html',
										controller  : 'UpdateUserModalCtrl',
										scope       : $scope,
										resolve     : {
											user : function() {
														return user;
												}
										}
								}).closed.then(function(){
									UserFactory.getUser().then(function(item) {
										$scope.user = item;
									});
								});
						}

			$scope.rentModal = function(id) {
						var rent = {
						 id: id,
						 rentDateFrom : '',
						 rentDateTo : ''
			}
						var modalInstance = $uibModal.open({
								templateUrl : 'views/modals/rentAdvertisement.html',
								controller  : 'RentModalCtrl',
								scope       : $scope,
								resolve     : {
									rent : function() {
												return rent;
										}
								}
						});
				}
		}

		function UpdateUserModalCtrl($scope, $uibModalInstance, user, UserFactory){
			$scope.user = user;

			$scope.save = function() {
				UserFactory.updateUser($scope.user).then(function(item) {
					$uibModalInstance.close('ok');
					});

			}

			$scope.cancel = function() {
					$uibModalInstance.close('cancel');
			};
		}

		function UpdateAdvertisementModalCtrl($scope, $uibModalInstance, advertisement, AdvertisementFactory){
			$scope.advertisement = advertisement;

			$scope.format = 'dd.MM.yyyy';
			$scope.dt = new Date();

			var max = new Date();
			max.setMonth(max.getMonth() + 3);

			$scope.dateOptions = {
				maxDate: max,
				minDate: new Date(),
				startingDay: 1
			};

			$scope.popup = {
				opened: false
			};

			$scope.open = function() {
				$scope.popup.opened = true;
			};

			$scope.save = function() {
				$scope.advertisement.activeUntil = $scope.dt;
				AdvertisementFactory.updateAdvertisement($scope.advertisement).then(function(item) {
				 $uibModalInstance.close({
					 status: 'ok',
					 item: item
				 });
					});

			}

			$scope.cancel = function() {
					$uibModalInstance.close('cancel');
			};
		}

		function RentModalCtrl($scope, $uibModalInstance, rent, UserFactory){
			$scope.rent = rent;
			$scope.format = 'dd.MM.yyyy';
			$scope.dt1 = new Date();
			$scope.dt2 = new Date();

			$scope.dateOptions = {
				 maxDate: new Date(2020, 5, 22),
				 minDate: new Date(),
				 startingDay: 1
			};

			$scope.popup1 = {
				 opened: false
			 };

			$scope.open1 = function() {
				 $scope.popup1.opened = true;
			};

			$scope.popup2 = {
				 opened: false
			 };

			$scope.open2 = function() {
				 $scope.popup2.opened = true;
			};

			$scope.save = function() {
			 $scope.rent.rentDateFrom = $scope.dt1;
			 $scope.rent.rentDateTo = $scope.dt2;
			 UserFactory.rent($scope.rent);
			 $uibModalInstance.close('ok');

			}

			$scope.cancel = function() {
					$uibModalInstance.close('cancel');
			};
		}
})(angular);
