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

        $scope.filterName = ['A', 'B', 'C'];

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