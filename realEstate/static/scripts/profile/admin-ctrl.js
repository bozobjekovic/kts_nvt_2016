(function (angular) {
	'use strict';

	angular.module('realEstateClientApp')
		.controller('AdminCtrl', ['$scope', '$rootScope', '_', 'AdminFactory', '$uibModal',
		   function($scope, $rootScope, _, AdminFactory, $uibModal) {

			$rootScope.mainMenu = true;

			AdminFactory.getAdmin().then(function(item) {
				$scope.admin = item;
			});

			AdminFactory.getAllUnapprovedClerks().then(function(items) {
				$scope.unapprovedClerks = items;
			});

			AdminFactory.getAllInappropriates().then(function(items) {
			    $scope.inappropriates = items;
			});

			$scope.accept = function(id){
				AdminFactory.acceptInappropriate(id);
			};

			$scope.reject = function(id){
				AdminFactory.rejectInappropriate(id);
			};

			$scope.acceptClerk = function(id){
				AdminFactory.acceptClerk(id);
			};

			$scope.denyClerk = function(id){
				AdminFactory.denyClerk(id);
			};

            $scope.openModal = function() {
                var verifier = {
                	  email			: '',
                	  username		: '',
                	  password		: ''
  			  }
                var modalInstance = $uibModal.open({
                    templateUrl : 'views/modals/registerVerifier.html',
                    controller  : 'regVerifierModalCtrl',
                    scope       : $scope,
                    resolve     : {
                    	verifier : function() {
                            return verifier;
                        }
                    }
                });
            }
	}])
	.controller('regVerifierModalCtrl', ['$scope', '$uibModalInstance', 'verifier', 'AdminFactory',
       function($scope, $uibModalInstance, verifier, AdminFactory) {
           $scope.verifier = verifier;

           $scope.regVerifier = function() {
        	   AdminFactory.regVerifier($scope.verifier).then(function(succeeded) {
				   if (succeeded) {
				   		$uibModalInstance.close('ok');
				   }
			   });
           }

           $scope.cancel = function() {
               $uibModalInstance.close('cancel');
           };
       }
   ]);
})(angular);
