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

            Survey.getQuestions(null, function(response) {

                $scope.aboutTest = [
                    {question : response},
                    {question : response},
                    {question : response}
                ];

            });

//            var questions = [
//                {quest: "Acho que eu gostaria de usar frequentemente as opções como no filtro do Teste ", value : 0},
//                {quest: "Achei desnecessariamente complexo os textos no filtro do Teste ", value : 0},
//                {quest: "Achei fácil de entender as opções de filtro do Teste ", value : 0},
//                {quest: "Acho que seria necessário o apoio de um técnico para poder filtrar pelas opções do Teste ", value : 0},
//                {quest: "Achei que estavam bem integradas as opções como no filtro do Teste ", value : 0},
//                {quest: "Acho que existem muitas inconsistências entre as opções de filtro no Teste ", value : 0},
//                {quest: "Imagino que a maioria das pessoas aprenderiam rapidamente a refinar uma busca com as opções de filtro como no Teste ", value : 0},
//                {quest: "Achei muito estranho filtrar resultados utilizando opções como no Teste ", value : 0},
//                {quest: "Me senti muito seguro/confiante ao utilizar as opções de filtro do Teste ", value : 0},
//                {quest: "Preciso aprender e entender várias coisas antes de usar  opções de filtro como no Teste ", value : 0}
//            ];

            $scope.options = [
                {value : 1, desc : "1"},
                {value : 2, desc : "2"},
                {value : 3, desc : "3"},
                {value : 4, desc : "4"},
                {value : 5, desc : "5"}
            ];

            Survey.getSavedSurvey(null, function(savedSurvey){
                if (savedSurvey == null) savedSurvey = {};
                $scope.currentSurvey = savedSurvey;
            });

        }

        $scope.saveSurvey = function(silent){
            if ($scope.currentSurvey != null) {

                Survey.logSurvey($scope.currentSurvey, function(){
                    if (silent === undefined)
                        toaster.pop('success', 'Sucesso', 'Pesquisa Salva com Sucesso');
                });

            }
        };

        $scope.$on('event:auth-loginConfirmed', function () {
            loadSurvey();
        });

        loadSurvey();

  });