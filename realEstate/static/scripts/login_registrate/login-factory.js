(function(angular) {
    angular.module('realEstateClientApp')
        .factory('LoginFactory', ['Restangular', '_', 'toastr',
            function(Restangular, _, toastr) {
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
                                    toastr.error('Your account is not approved!', 'Error');
                                } else {
                                    toastr.error('Username or password are incorrect!', 'Error');
                                }
                                return {
                                    token: '',
                                    status : false
                                };
                            });
                    }
                };

                retVal.getLoggedUserData = function(token) {
                    return Restangular.one('user/data').get()
                        .then(function(loggedUserData) {
                            return loggedUserData.data;
                    })
                };

                return retVal;
            }]);
})(angular);
