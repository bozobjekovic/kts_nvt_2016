(function(angular) {
    angular.module('realEstateClientApp')
        .factory('LoginFactory', ['Restangular', '_',
            function(Restangular, _) {
                var retVal = {};

                retVal.logInUser = function(loginUser) {
                    if (loginUser.username && loginUser.password) {
                        return Restangular.all('login').post(loginUser)
                            .then(function(token) {
                                console.log(token.response);
                                // save and do next logic
                            })
                    }
                };

                return retVal;
            }]);
})(angular);
