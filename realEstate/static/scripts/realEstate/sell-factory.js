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
                                $window.alert('Woooho!');
                                //$location.path('index.html');
                            })
                    } else {
                        $window.alert('Fill required filleds!');
                    }
                };

                retVal.getAllPartOfTheCities = function(city) {
                    return Restangular.all('locations/city/partOfTheCity').customGET('', {city: city})
                        .then(function(partOfTheCities) {
                            return partOfTheCities;
                        })
                };

                retVal.getAllCities = function() {
                    return Restangular.all('locations/city').getList()
                        .then(function(cities) {
                            return cities;
                        })
                };

                return retVal;
            }
        ]);
})(angular);
