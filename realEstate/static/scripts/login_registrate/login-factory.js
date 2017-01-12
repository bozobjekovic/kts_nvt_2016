(function(angular) {
    angular.module('realEstateClientApp')
        .factory('LoginFactory', ['Restangular', '_', '$window',
            function(Restangular, _, $window) {
                var retVal = {};

                retVal.logInUser = function(loginUser) {
                    if (loginUser.username && loginUser.password) {
                        return Restangular.all('login').post(loginUser)
                            .then(function(token) {
                                return {
                                    token: token.data.response,
                                    status : true
                                };
                            }, function(response) {
                                if (response.status === 422) {
                                    $window.alert('Your account is not approved!');
                                } else {
                                    $window.alert('Username or password are incorrect!');
                                }
                                return {
                                    token: '',
                                    status : false
                                };
                            });
                    }
                };

                retVal.getLoggedUserData = function(token) {
                    return Restangular.one('users/user/data').get()
                        .then(function(loggedUserData) {
                            return loggedUserData.data;
                    })
                };

                return retVal;
            }]);
})(angular);
