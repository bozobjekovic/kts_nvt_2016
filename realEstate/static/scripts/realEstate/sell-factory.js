(function(angular) {
    angular.module('realEstateClientApp')
        .factory('SellFactory', ['Restangular', '_', '$location', 'toastr',
            function(Restangular, _, $location, toastr) {
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
                                    toastr.warning('Check location details!', 'Warning');
                                } else if (response.status === 409) {
                                    toastr.warning('Phone number already exist!', 'Warning');
                                }
                            });
                    } else {
                        toastr.error('Fill required filleds!', 'Error');
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
