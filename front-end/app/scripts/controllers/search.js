'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:SearchCtrl
 * @description
 * # SearchCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('SearchCtrl', function($scope,$position,$timeout, $window) {

        $scope.items = [
			{
				"id": 1,
				"first_name": "Necessidade_1",
				"value" : 0
			},
			{
				"id": 2,
				"first_name": "Necessidade_2",
				"value" : 0
			},
			{
				"id": 3,
				"first_name": "Necessidade_3",
				"value" : 0
			},
			{
				"id": 4,
				"first_name": "Necessidade_4",
				"value" : 0
			},
			{
				"id": 5,
				"first_name": "Necessidade_5",
				"value" : 0
			},
			{
				"id": 6,
				"first_name": "Necessidade_6",
				"value" : 0
			},
			{
				"id": 7,
				"first_name": "Necessidade_7",
				"value" : 0
			},
			{
				"id": 8,
				"first_name": "Necessidade_8",
				"value" : 0
			},
			{
				"id": 9,
				"first_name": "Necessidade_9",
				"value" : 0
			},
			{
				"id": 10,
				"first_name": "Necessidade_10",
				"value" : 0
			},
			{
				"id": 11,
				"first_name": "Necessidade_11",
				"value" : 0
			}
		];

		var features = [
		    {id : 1, desc: "Feature_1", qtd: 0},
		    {id : 2, desc: "Feature_2", qtd: 0},
		    {id : 3, desc: "Feature_3", qtd: 0},
		    {id : 4, desc: "Feature_4", qtd: 0},
		    {id : 5, desc: "Feature_5", qtd: 0},
		    {id : 6, desc: "Feature_6", qtd: 0},
		    {id : 7, desc: "Feature_7", qtd: 0},
		    {id : 8, desc: "Feature_8", qtd: 0},
		    {id : 9, desc: "Feature_9", qtd: 0}
		];

		var dePara = [
		    {need : 1, features : [1,2]},
		    {need : 2, features : [2,4,5]},
		    {need : 3, features : [6,7,9]},
		    {need : 4, features : [1,3,4,6]},
		    {need : 5, features : [2]},
		    {need : 6, features : [4,7]},
		    {need : 7, features : [2,6,8]},
		    {need : 8, features : [3]},
		    {need : 9, features : [2]},
		    {need : 10, features : [6,8]},
		    {need : 11, features : [3,4,5]}
		];

		$scope.produtos = [
		    {id : 1, nome: "Produto_1", featureValue : [5,7,0,6,9,1,3,4,6]},
		    {id : 2, nome: "Produto_2", featureValue : [2,4,8,6,9,4,5,0,2]},
		    {id : 3, nome: "Produto_3", featureValue : [3,6,5,8,6,9,8,7,5]},
		    {id : 4, nome: "Produto_4", featureValue : [3,7,8,4,8,6,3,2,1]},
		    {id : 5, nome: "Produto_5", featureValue : [5,7,0,6,5,8,3,4,6]},
            {id : 6, nome: "Produto_6", featureValue : [2,4,8,6,9,4,5,0,2]},
            {id : 7, nome: "Produto_7", featureValue : [3,6,5,8,7,9,8,7,5]},
            {id : 8, nome: "Produto_8", featureValue : [3,7,8,7,6,5,3,2,1]},
            {id : 9, nome: "Produto_9", featureValue : [5,7,0,6,9,1,3,4,6]},
            {id : 10, nome: "Produto_10", featureValue : [2,0,5,8,7,9,8,0,2]},
            {id : 11, nome: "Produto_11", featureValue : [3,8,0,7,6,5,3,7,5]},
            {id : 12, nome: "Produto_12", featureValue : [3,0,6,0,9,1,3,2,1]},
            {id : 13, nome: "Produto_13", featureValue : [5,8,6,9,0,8,6,9,4]},
            {id : 14, nome: "Produto_14", featureValue : [2,4,8,6,9,5,8,7,9]},
            {id : 15, nome: "Produto_15", featureValue : [3,6,5,8,7,8,7,6,5]},
            {id : 16, nome: "Produto_16", featureValue : [3,7,8,7,6,0,6,9,1]},
            {id : 17, nome: "Produto_17", featureValue : [3,4,6,6,9,1,3,4,6]},
            {id : 18, nome: "Produto_18", featureValue : [5,0,2,6,9,4,8,6,2]},
            {id : 19, nome: "Produto_19", featureValue : [8,7,5,8,7,6,5,8,5]},
            {id : 20, nome: "Produto_20", featureValue : [3,7,8,7,6,7,8,7,1]}
		];

		$scope.radar = {};
		$scope.donut = {};

        var updateRadar = function() {

            $scope.radar = {
                labels:[],
                data:[[]]
            };

            $scope.donut = {
                labels: [],
                data: []
            };

            var total = 0;

            $(features).each(function(idx, el){
                $scope.donut.labels[idx] = el.desc;
                $scope.donut.data[idx] = 0;
                el.qtd = 0;
            });


            $($scope.items).each(function(idx, el){
                total += el.value;
            });

            $($scope.items).each(function(idx, el){
                $scope.radar.labels[idx] = el.first_name;
                $scope.radar.data[0][idx] = el.value;

                var deParas = dePara[idx].features;

                $(deParas).each(function(idx2, el2){
                    features[el2-1].qtd = (el.value > 0) ? features[el2-1].qtd + (el.value/total) : features[el2-1].qtd;
                });
            });

            $(features).each(function(idx, el){
                $scope.donut.data[idx] = el.qtd;
            });

            $scope.setOrder();
            $scope.$apply();
        }

		$scope.timer = 0;

		function rearrange(){

		    $('.item').each(function(idx, el){
				var $el = $(el);
				var newTop = idx * 50;

				if (newTop != parseInt($el.css('top'))) {
					$el.css({
						'top': newTop
					})
					.one('webkitTransitionEnd', function (evt){
						$(evt.target).removeClass('moving');
					})
					.addClass('moving');
				}

			});
			updateRadar();

		}

        var finishSlide = function (event, ui) {
              $scope.items[ui.handle.offsetParent.id - 1].value = ui.value;
              $scope.$apply();
              $window.clearTimeout($scope.timer);
              $scope.timer = $window.setTimeout(rearrange, 50);
        }

            // Slider options with event handlers
			$scope.slider = {
				'options': {
//					start: function (event, ui) { $log.info('Event: Slider start - set with slider options', event); },
    				stop: finishSlide,
    				min: 0,
                    max: 100
				}
			};

			$scope.sliderValues = {
				1: 0,
				2: 0,
				3: 0,
				4: 0,
				5: 0,
				6: 0,
				7: 0,
				8: 0,
				9: 0,
				10:0,
				11:0
			};

            $scope.setOrder = function () {

                  var i;

                for (i = 0; i < $scope.produtos.length; i++) {

                    $scope.produtos[i].order = 0;
                    $scope.produtos[i].calculation = 0;

                    $($scope.donut.data).each(function(idx, el){
                        $scope.produtos[i].calculation += (el * $scope.produtos[i].featureValue[idx]);
                    });

                 }

                 for (i = 0; i < $scope.produtos.length; i++) {

                    for (var j = i + 1; j < $scope.produtos.length; j++) {
                        if ($scope.produtos[i].calculation < $scope.produtos[j].calculation) {
                            $scope.produtos[i].order++;
                        }
                    }
                 }

                 calcGridPosition();
           };

            function calcGridPosition() {
              for (var i = 0; i < $scope.produtos.length; i++) {
                var item = $scope.produtos[i];

                // columns, left-to-right, top-to-bottom
                var columns = 10;
                item.column = item.order%columns;
                item.row = Math.floor(item.order/columns);
              }
            }


			$window.clearTimeout($scope.timer);
            $scope.timer = $window.setTimeout(rearrange, 100);
            $scope.setOrder();
  });
