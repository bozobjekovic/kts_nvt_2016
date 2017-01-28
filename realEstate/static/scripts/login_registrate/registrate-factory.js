(function(angular) {
    angular
        .module('registrate')
        .factory('RegistrateFactory', RegistrateFactory);

    RegistrateFactory.$inject = ['Restangular', '_', 'toastr'];

    function RegistrateFactory(Restangular, _, toastr){
      var retVal = {};

      retVal.registrateUser = function(registrateUser) {
          registrateUser.authority = 'user';
          if (registrateUser.email && registrateUser.username && registrateUser.password
              && registrateUser.name && registrateUser.surname) {
              return Restangular.all('registrate').post(registrateUser)
                  .then(function() {
                      toastr.success('Registration successful!', 'Please log in now!');
                      return true;
                  }, function(response) {
                      if (response.status === 409) {
                          toastr.warning('Username or email already exists!', 'Warning');
                          return false;
                      }
                  });
          } else {
              toastr.error('Fill required filleds!', 'Error');
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
                          toastr.success('Registration successful!', 'Please log in now!');
                          return true;
                      }, function(response) {
                          if (response.status === 422) {
                              toastr.warning('Check location details!', 'Warning');
                          } else {
                              toastr.warning('Username or email or name of phone number already exists!', 'Warning');
                          }
                          return false;
                      });
          } else {
              toastr.error('Fill required filleds!', 'Error');
          }
      };

      retVal.getAllCities = function() {
          return Restangular.all('locations/city').getList()
              .then(function(cities) {
                  return cities.data;
              })
      };

      return retVal;
    }
})(angular);
