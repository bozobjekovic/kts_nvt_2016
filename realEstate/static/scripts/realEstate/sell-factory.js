(function(angular) {
    angular.module('realEstateClientApp')
        .factory('SellFactory', ['Restangular', '_', '$location', '$window',
            function(Restangular, _, $location, $window) {
                'use strict';

                var retVal = {};

                retVal.submitSell = function(advertisementCreate) {
                    if (advertisementCreate.name && advertisementCreate.location.city && advertisementCreate.location.zipCode
                        && advertisementCreate.landSize && advertisementCreate.address && advertisementCreate.heatingType
                        && advertisementCreate.category && advertisementCreate.type && advertisementCreate.price
                        && advertisementCreate.purpose && advertisementCreate.phoneNumber) {
                        return Restangular.all('advertisments').post(advertisementCreate)
                            .then(function(data) {
                                $location.path('/');
                            }, function(response) {
                                if (response.status === 422) {
                                    $window.alert('Check location details!');
                                } else {
                                    $window.alert('Phone number already exist!');
                                }
                            });
                    } else {
                        $window.alert('Fill required filleds!');
                    }
                };

                retVal.getAllPartOfTheCities = function(city) {
                    return Restangular.all('locations/city/partOfTheCity').customGET('', {city: city})
                        .then(function(partOfTheCities) {
                            return partOfTheCities.data;
                        })
                };

                retVal.getAllCities = function() {
                    return Restangular.all('locations/city').getList()
                        .then(function(cities) {
                            return cities.data;
                        })
                };

                return retVal;
            }
        ]);
})(angular);
