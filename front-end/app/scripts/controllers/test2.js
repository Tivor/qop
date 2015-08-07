'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('Test2Ctrl', function($scope, Test, toaster, $filter, $modal) {

        $scope.filterName = ['A', 'B', 'C'];

        $scope.allFilters = [[], [], []];

        $scope.getValues = function(valores){

            var retorno = [];
            var minVal = parseInt(valores[0].valueOption);
            var maxVal = parseInt(valores[1].valueOption);

            retorno.push({valueOption: "Menor que " + minVal});
            retorno.push({valueOption: "Entre " + minVal + " e " + maxVal});
            retorno.push({valueOption: "Maior que " + maxVal});

            return retorno;

        };

        Test.getCategories(null, function(response){
            $scope.categories = response;
        });

        $scope.changeCategory = function(idcat){
           var oldValue = $scope.idcat;
           $scope.idcat = idcat;
           $scope.findFeatures();
           Test.log({op : 'changeCategory', testCase: 'allFilters', oldValue: oldValue, newValue: idcat});
        };

         $scope.findFeatures = function() {

            if ($scope.idcat > 0) {
                Test.getFeatures({idcat: $scope.idcat, testcase: 1}, function(response){
                    $scope.allFilters[0] = response;
                });

                Test.getFeatures({idcat: $scope.idcat, testcase: 2}, function(response){
                    $scope.allFilters[1] = response;
                });

                Test.getFeatures({idcat: $scope.idcat, testcase: 3}, function(response){
                    $scope.allFilters[2] = response;
                });
            }
        }

       $scope.findFeatures();

       Test.log({op: 'newTest', testCase: 'allFilters'});

  });