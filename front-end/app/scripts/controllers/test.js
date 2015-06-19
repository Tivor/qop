'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('TestCtrl', function($scope, $routeParams, Test) {

        $scope.testCase = $routeParams.testcase;

        $scope.products = [];
        $scope.productIds = [];

        Test.getCategories(null, function(response){
            $scope.categories = response;
        });

        $scope.features = [];
         /*[
            {name : 'Feature 1', typeMeasure : 1,
                optionValues: [{valueOption : 15}, {valueOption : 75}]},
            {name : 'Feature 2', typeMeasure : 2,
                optionValues: [{id: 1, valueOption : 'Bom'}, {id: 2, valueOption : 'Médio'}, {id: 3, valueOption : 'Ruim'}]},
            {name : 'Feature 3', typeMeasure : 1,
                optionValues: [{valueOption : 15}, {valueOption : 75}]},
            {name : 'Feature 4', typeMeasure : 2,
                optionValues: [{id: 4, valueOption : 15}, {id: 5, valueOption : 75}]},
            {name : 'Feature 5', typeMeasure : 2,
                optionValues: [{id: 6, valueOption : 15}, {id: 7, valueOption : 75}]},
            {name : 'Feature 6', typeMeasure : 1,
                optionValues: [{valueOption : 15}, {valueOption : 75}]}
        ];*/

        $scope.refine = function(){
            Test.refine($scope.features);
        };

        $scope.changeCategory = function(idcat){
           $scope.idcat = idcat;
           $scope.findAll();
           $scope.findFeatures();
        };


         $scope.productPage = [];
         $scope.page = 1;
         $scope.pageSize = 15;

          $scope.doPaging = function(page, pageSize){
              var begin = ((page - 1) * pageSize);
              var end = begin + pageSize;
              $scope.productPage = $scope.products.slice(begin, end);
          };


          $scope.findAll = function() {

              if ($scope.idcat > 0)
                  Test.findAllByCategory({idcat: $scope.idcat, testcase: $scope.testCase}, function(response){
                    $scope.productIds = response.selectedProducts;
                    $scope.products = response.refinementResult;
                    $scope.total = $scope.productIds.length;
                    $scope.doPaging($scope.page, $scope.pageSize);
                  });
          }

          $scope.findAll();

          $scope.findFeatures = function() {

                if ($scope.idcat > 0)
                    Test.getFeatures({idcat: $scope.idcat}, function(response){
                        $scope.features = response;
                    });
            }

            $scope.findFeatures();
  });
