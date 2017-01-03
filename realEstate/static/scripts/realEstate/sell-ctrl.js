(function (angular) {
	'use strict';

	angular.module('realEstateClientApp')
        .controller('SellCtrl', ['$scope', '$rootScope', '_', 'SellFactory',
            function($scope, $rootScope, _, SellFactory) {
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
                    image: '',
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

                $scope.submitSellForm = function() {
					SellFactory.submitSell($scope.advertisementCreate);
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
