'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @descriptionf
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('qopApp')
  .controller('SurveyCtrl', function($scope, $routeParams, Survey, toaster, $filter, $modal, loginService, Test, $interval, $rootScope) {

        function loadSurvey() {

            Test.getCategories(null, function(response){
                $scope.categories = response;
            });

            var questions = [
                {quest: "Acho que eu gostaria de usar frequentemente o filtro do Teste ", value : 0},
                {quest: "Achei desnecessariamente complexo o filtro do Teste ", value : 0},
                {quest: "Achei fácil de usar o filtro do Teste ", value : 0},
                {quest: "Acho que seria necessário o apoio de um técnico para poder usar o filtro do Teste ", value : 0},
                {quest: "Achei que estavam bem integradas as várias funções do filtro no Teste ", value : 0},
                {quest: "Acho que existem muitas inconsistências no filtro do Teste ", value : 0},
                {quest: "Imagino que a maioria das pessoas aprenderiam rapidamente a usar o filtro do Teste ", value : 0},
                {quest: "Achei muito estranho de usar o filtro do Teste ", value : 0},
                {quest: "Me senti muito seguro/confiante ao utilizar o filtro do Teste ", value : 0},
                {quest: "Preciso aprender várias coisas antes de usar o filtro do Teste ", value : 0}
            ];

            $scope.aboutTest = [
                {question : questions},
                {question : questions},
                {question : questions}
            ];

            $scope.options = [
                {value : 1, desc : "Discordo fortemente - 1"},
                {value : 2, desc : "2"},
                {value : 3, desc : "3"},
                {value : 4, desc : "4 - Concordo plenamente"}
            ];

            $scope.currentSurvey = Survey.getCurrentSurvey();

            if($scope.currentSurvey == null && loginService.getCurrentUser()) {
                Survey.getSavedSurvey(null, function(savedSurvey){
                    if (savedSurvey == null) savedSurvey = {};
                    $scope.currentSurvey = savedSurvey;
                    Survey.setCurrentSurvey($scope.currentSurvey);
                    $scope.autoSave = $interval($scope.saveSurvey, 10000);
                });
            }

        }

        $scope.saveSurvey = function(silent){
            if ($scope.currentSurvey != null) {

                Survey.logSurvey($scope.currentSurvey, function(){
                    if (silent === undefined)
                        toaster.pop('success', 'Sucesso', 'Pesquisa Salva com Sucesso');
                });

            }
        };

        loadSurvey();

        var dereg = $rootScope.$on('$locationChangeSuccess', function() {
            $interval.cancel($scope.autoSave);
            dereg();
        });

        $scope.$on('event:userLogout', function () {
            $interval.cancel($scope.autoSave);
            $scope.currentSurvey = null;
            Survey.setCurrentSurvey($scope.currentSurvey);
        });

        $scope.$on('event:auth-loginConfirmed', function () {
            loadSurvey();
        });

  });