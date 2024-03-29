(function (angular) {
    'use strict';

    angular.module('realEstateClientApp')
        .directive('nvtSubmit', ['$parse',
            function($parse){
                return {
                    restrict: 'A',
                    require: ['nvtSubmit', '?form'],
                    controller: ['$scope',
                        function($scope) {
                            this.attempted = false;
                            var formController = null;

                            this.setAttempted = function() {
                                this.attempted = true;
                            }

                            this.setFormController = function(controller) {
                                formController = controller;
                            }

                            this.needsAttention = function(filedModelController) {
                                if(!formController) return false;

                                if(filedModelController) {
                                    return filedModelController.$invalid && (filedModelController.$dirty || this.attempted);
                                } else {
                                    return formController && formController.$invalid && (formController.$dirty || this.attempted);
                                }
                            }
                        }
                    ],
                    compile: function(cElement, cAttributes, transclude) {
                        return {
                            pre: function(scope, formElement, attributes, controllers) {
                                var submitController = controllers[0];
                                var formController = (controllers.length > 1) ? controllers[1] : null;

                                submitController.setFormController(formController);

                                scope.nvt = scope.nvt || {};
                                scope.nvt[attributes.name] = submitController;
                            },
                            post: function(scope, formElement, attributes, controllers) {
                                var submitController = controllers[0];
                                var formController = (controllers.length > 1) ? controllers[1] : null;

                                var fn = $parse(attributes.nvtSubmit);

                                formElement.bind('submit', function (event) {
                                    submitController.setAttempted();

                                    if (!scope.$$phase) scope.$apply();

                                    if (!formController.$valid) return false;

                                    scope.$apply(function() {
                                        fn(scope, {$event:event})
                                    })
                                });
                            }
                        };
                    }
                };
            }])
})(angular);
