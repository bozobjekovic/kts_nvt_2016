(function (angular) {
	'use strict';

	angular
		.module('admin', [])
		.controller('AdminCtrl', AdminCtrl)
		.controller('regVerifierModalCtrl', regVerifierModalCtrl);

	 AdminCtrl.$inject = ['$scope', '$rootScope', '_', 'AdminFactory', '$uibModal'];
	 regVerifierModalCtrl.$inject = ['$scope', '$uibModalInstance', 'verifier', 'AdminFactory'];

	 function AdminCtrl($scope, $rootScope, _, AdminFactory, $uibModal){
		 $rootScope.mainMenu = true;

		 AdminFactory.getAdmin().then(function(item) {
			 $scope.admin = item;
		 });

		 AdminFactory.getAllUnapprovedClerks().then(function(items) {
			 $scope.unapprovedClerks = items;
		 });

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
	 }

	 function regVerifierModalCtrl($scope, $uibModalInstance, verifier, AdminFactory){
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
})(angular);
