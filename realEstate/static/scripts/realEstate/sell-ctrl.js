(function (angular) {
	'use strict';

	angular.module('realEstateClientApp')
        .controller('SellCtrl', ['$scope', '$rootScope', '_', 'SellFactory', 'flowFactory', '$q', 
            function($scope, $rootScope, _, SellFactory, flowFactory, $q) {
                $rootScope.mainMenu = true;

				/* for type combobox */
				$scope.isTypesDisabled = true;
				$scope.typeOptions = [];
				//
				var residentalTypes = ['Apartment', 'House', 'Room', 'Garage'];
				var officeTypes = ['Office', 'Warehouse', 'Hospitality Facilities', 'Manifacturing Facilities', 'Sport Facilities'];
				var propertyTypes = ['Building Plots', 'Agricultural Plots'];

				/* for location */
				$scope.cities = [];
				$scope.partOfTheCities = [];

				SellFactory.getAllCities().then(function(cities) {
					$scope.cities = cities;
				});

                $scope.advertisementCreate = {
                    name: '',
                    location: {
                        city : '',
                        zipCode : '',
                        partOfTheCity : ''
                    },
                    landSize: '',
                    techEquipment: '',
                    address: '',
                    heatingType: '',
                    images: [],
                    numOfBathRooms: '',
                    numOfBedRooms: '',
                    numOfFlors: '',
                    buildYear: '',
                    category: '',
                    type: '',
                    price: '',
                    phoneNumber: '',
                    purpose: '',
                    activeUntil: ''
                };

				$scope.flowObject = flowFactory.create({});

				var readImagePath = function() {
					var deferred = $q.defer();
					var redImagesCounter = 0;
					for (var i in $scope.flowObject.files) {
						(function(file) {
							var fileReader = new FileReader();
							fileReader.readAsDataURL(file.file);
							fileReader.onload = function (event) {
								$scope.advertisementCreate.images.push(event.target.result);
								redImagesCounter++;

								if (redImagesCounter === $scope.flowObject.files.length) {
									deferred.resolve();
								}
							}
						})($scope.flowObject.files[i]);
					}

					return deferred.promise;
				}

                $scope.submitSellForm = function() {
					readImagePath().then(function() {
						SellFactory.submitSell($scope.advertisementCreate);
					});
                };

				$scope.getPartOfTheCities = function() {
					SellFactory.getAllPartOfTheCities($scope.advertisementCreate.location.city)
						.then(function(partOfTheCities) {
							$scope.partOfTheCities = partOfTheCities;
						})
				};

				$scope.updateType = function() {
					if ($scope.advertisementCreate.category === 'Residental') {
						$scope.isTypesDisabled = false;
						$scope.typeOptions = residentalTypes;
					} else if ($scope.advertisementCreate.category === 'Office') {
						$scope.isTypesDisabled = false;
						$scope.typeOptions = officeTypes;
					} else if ($scope.advertisementCreate.category === 'Property') {
						$scope.isTypesDisabled = false;
						$scope.typeOptions = propertyTypes;
					} else {
						$scope.isTypesDisabled = true;
						$scope.typeOptions = [];
					}
				};
            }
        ]);

})(angular);
