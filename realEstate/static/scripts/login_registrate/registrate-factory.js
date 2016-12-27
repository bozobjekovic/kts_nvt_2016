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
                            })
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
                                });
                    } else {
                        $window.alert('Fill required filleds!');
                    }
                };

                return retVal;
            }]);
})(angular);
