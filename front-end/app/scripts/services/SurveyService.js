'use strict';

/**
 * @ngdoc service
 * @name custeioApp.custeioService
 * @description
 * # myService
 * Service in the custeioApp.
 */


angular.module('qopApp').factory('Survey', function ($resource) {

    var service = $resource('/qopsearch/api/Product/:id', {}, {
        'logSurvey': { method: 'POST', url: '/qopsearch/api/Product/logSurvey'},
        'getSavedSurvey': { method: 'GET', url: '/qopsearch/api/Product/savedSurvey/:login', params:{login:'@login'}, }
    });

    var currentSurvey = null;

    service.getCurrentSurvey = function(){
        return currentSurvey;
    }

    service.setCurrentSurvey = function(savedSurvey) {
        currentSurvey = savedSurvey;
    }

    return service;
});
