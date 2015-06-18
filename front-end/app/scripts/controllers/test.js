'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('TestCtrl', function($scope, $routeParams) {

        $scope.testCase = $routeParams.testcase;

        $scope.idcat = $routeParams.idcat;

        $scope.categories = [
            {id : 1, name : 'Categoria 1'},
            {id : 2, name : 'Categoria 2'},
            {id : 3, name : 'Categoria 3'},
            {id : 4, name : 'Categoria 4'},
            {id : 5, name : 'Categoria 5'},
            {id : 6, name : 'Categoria 6'}

        ];

        $scope.features = [
            {name : 'Feature 1', filterValue : null, type : 1,
                options: {min: 15, max: 75}},
            {name : 'Feature 2', filterValue : null, type : 2,
            options: [{desc : 'Muito Bom'}, {desc : 'Bom'}, {desc : 'Médio'}, {desc : 'Ruim'}]},
            {name : 'Feature 3', filterValue : null, type : 1,
            options: {min: 15, max: 75}},
            {name : 'Feature 4', filterValue : null, type : 2,
            options: {min: 15, max: 75}},
            {name : 'Feature 5', filterValue : null, type : 2,
            options: {min: 15, max: 75}},
            {name : 'Feature 6', filterValue : null, type : 1,
                options: {min: 15, max: 75}}
        ];

         $scope.produtosPaginados = [];
         $scope.page = 1;
         $scope.pageSize = 6;

          $scope.filterProducts = function() {
            $scope.todos = [];
            for (var i=1;i<=12;i++) {
                var price = 23.45 * i;
              $scope.todos.push({ id:i, name:"Produto_" + i, price: price});
            }
            $scope.total = $scope.todos.length;
          };
          $scope.filterProducts();

          $scope.doPaging = function(page, pageSize, total){
              var begin = ((page - 1) * pageSize);
              var end = begin + pageSize;
              $scope.produtosPaginados = $scope.todos.slice(begin, end);
          };

          $scope.doPaging($scope.page, $scope.pageSize, $scope.total);


  });
