'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('TestCtrl', function($scope, $routeParams, Test, toaster, $filter, $modal) {

        $scope.testCase = $routeParams.testcase;

        $scope.products = [];
        $scope.productIds = [];

        Test.getCategories(null, function(response){
            $scope.categories = response;
        });

        $scope.refine = function(){
            Test.refine($scope.features);
        };

        $scope.changeCategory = function(idcat){
           var oldValue = $scope.idcat;

           $scope.idcat = idcat;
           $scope.findAll();
           $scope.findFeatures();
           Test.log({op : 'changeCategory', oldValue: oldValue, newValue: idcat});
        };

         $scope.productPage = [];
         $scope.page = 1;
         $scope.pageSize = 9;

          $scope.doPaging = function(page, pageSize, doLog){
              var begin = ((page - 1) * pageSize);
              var end = begin + pageSize;
              $scope.productPage = $scope.products.slice(begin, end);
              if(doLog) Test.log({op : 'doPaging', tPage: page})
          };

          $scope.findAll = function() {

              if ($scope.idcat > 0)
                  Test.findAllByCategory({idcat: $scope.idcat, testcase: $scope.testCase}, function(response){
                    $scope.productIds = response.selectedProducts;
                    $scope.products = response.refinementResult;
                    $scope.total = $scope.productIds.length;
                    $scope.doPaging($scope.page, $scope.pageSize, false);
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

          Test.log({op: 'newTest', testCase: $scope.testCase});

          function shuffle() {

                $scope.products = $filter('orderBy')($scope.products, function() {return 0.5 - Math.random();});
                $scope.page = 1;
                $scope.doPaging($scope.page, $scope.pageSize, false);
          }

          $scope.changeFilterRange = function(event, ui){

            shuffle();
            Test.log({op: 'changeFilterRange',
                        feature: $scope.features[ui.handle.parentNode.id],
                        values: ui.values});
          };

            $scope.changeFilterOptions = function(feature, value){

                shuffle();
                Test.log({op: 'changeFilterOptions',
                              feature: feature,
                              value: value});
            };


          $scope.changeFilterNeeds = function(event, ui){
              shuffle();
              Test.log({op: 'changeFilterNeeds',
                          feature: $scope.features[ui.handle.parentNode.id],
                          value: ui.value});
          };

            $scope.addToCart = function(product) {
              Test.log({op: 'addToCart', product: product});
              toaster.pop('success', 'Sucesso', 'Produto adicionado ao carrinho');
            };

            $scope.addToWishlist = function(product) {
              Test.log({op: 'addToWishlist', product: product});
              toaster.pop('success', 'Sucesso', 'Produto adicionado a lista de desejos');
            };

            $scope.showDetails = function(selectedProduct) {
              Test.log({op: 'showDetails', product: selectedProduct});
              $scope.openModalDetail(selectedProduct);
            };




            $scope.openModalDetail = function (selectedProduct) {

                var modalInstance = $modal.open({
                  animation: true,
                  templateUrl: 'productDetail.html',
                  controller: 'ModalInstanceCtrl',
                  size: 'lg',
                  resolve: {
                    selectedProduct: function () {
                      return selectedProduct;
                    }
                  }
                });
            };


  });

  angular.module('qopApp').controller('ModalInstanceCtrl', function ($scope, $modalInstance, Test, toaster, selectedProduct) {

    $scope.selectedProduct = selectedProduct;


    $scope.addToCartDetail = function(){
        Test.log({op: 'addToCartDetail', product: $scope.selectedProduct});
        toaster.pop('success', 'Sucesso', 'Produto adicionado ao carrinho');
    }

    $scope.ok = function () {
      $modalInstance.close();
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  });
