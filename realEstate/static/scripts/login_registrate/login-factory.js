(function(angular) {
    angular.module('realEstateClientApp')
        .factory('LoginFactory', ['Restangular', '_',
            function(Restangular, _) {
                var retVal = {};

                retVal.logInUser = function(loginUser) {
                    if (loginUser.username && loginUser.password) {
                        return Restangular.all('login').post(loginUser)
                            .then(function(token) {
                                return token.response;
                            })
                    }
                };

                retVal.getLoggedUserData = function(token) {
                    return Restangular.one('users/user/data').get()
                        .then(function(loggedUserData) {
                            return loggedUserData;
                    })
                };

                return retVal;
            }]);
})(angular);
