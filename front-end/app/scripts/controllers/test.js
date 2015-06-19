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
        $scope.idcat = $routeParams.idcat;

        $scope.products = [];
        $scope.productIds = [];

        $scope.categories = [
            {id : 1, name : 'Categoria 1'},
            {id : 2, name : 'Categoria 2'},
            {id : 3, name : 'Categoria 3'},
            {id : 4, name : 'Categoria 4'},
            {id : 5, name : 'Categoria 5'},
            {id : 6, name : 'Categoria 6'}

        ];

        $scope.features = [
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
        ];

        $scope.refine = function(){
            Test.refine($scope.features);
        };

         $scope.productPage = [];
         $scope.page = 1;
         $scope.pageSize = 6;

          $scope.doPaging = function(page, pageSize){
              var begin = ((page - 1) * pageSize);
              var end = begin + pageSize;
              $scope.productPage = $scope.products.slice(begin, end);
          };


          $scope.findAll = function() {

              if ($scope.idcat)
                  Test.findAllByCategory({idcat: $scope.idcat, testcase: $scope.testCase}, function(response){
                    $scope.productIds = response.selectedProducts;
                    $scope.products = response.refinementResult;
                    $scope.total = $scope.productIds.length;
                    $scope.doPaging($scope.page, $scope.pageSize);
                  });
          }

          $scope.findAll();
  });
