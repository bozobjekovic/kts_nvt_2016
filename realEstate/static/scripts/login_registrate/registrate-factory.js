(function(angular) {
    angular.module('realEstateClientApp')
        .factory('RegistrateFactory', ['Restangular', '_', '$window',
            function(Restangular, _, $window) {
                var retVal = {};

                retVal.registrateUser = function(registrateUser) {
                    registrateUser.authority = 'user';
                    if (registrateUser.email && registrateUser.username && registrateUser.password
                        && registrateUser.name && registrateUser.surname) {
                        return Restangular.all('registrate').post(registrateUser)
                            .then(function() {
                                $window.alert('Registration successful! Please log in now!');
                                return true;
                            }, function(response) {
                                if (response.status === 409) {
                                    $window.alert('Username or email already exists!');
                                    return false;
                                }
                            });
                    } else {
                        $window.alert('Fill required filleds!');
                    }
                };

                retVal.registrateClerk = function(registrateUser) {
                    registrateUser.authority = 'clerk';
                    if (registrateUser.email && registrateUser.username && registrateUser.password
                        && registrateUser.name && registrateUser.surname && registrateUser.companyName
                        && registrateUser.companyPhoneNumber && registrateUser.companyAddress
                        && registrateUser.companyLocation.city) {
                            return Restangular.all('registrate').post(registrateUser)
                                .then(function() {
                                    $window.alert('Registration successful! Please log in now!');
                                    return true;
                                }, function(response) {
                                    if (response.status === 422) {
                                        $window.alert('Check location details!');
                                    } else {
                                        $window.alert('Username or email or name of phone number already exists!');
                                    }
                                    return false;
                                });
                    } else {
                        $window.alert('Fill required filleds!');
                    }
                };

                retVal.getAllCities = function() {
                    return Restangular.all('locations/city').getList()
                        .then(function(cities) {
                            return cities.data;
                        })
                };

                return retVal;
            }]);
})(angular);
