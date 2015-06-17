'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('MainCtrl', function($scope) {

        $scope.price = 0;

        $scope.slider = {
            options: {
                orientation: 'horizontal',
                min: 0,
                max: 500,
                range: 'min'
            }
        };



  });
