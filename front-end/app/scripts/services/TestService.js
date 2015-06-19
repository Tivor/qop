'use strict';

/**
 * @ngdoc service
 * @name custeioApp.custeioService
 * @description
 * # myService
 * Service in the custeioApp.
 */


angular.module('qopApp').factory('Test', function ($resource) {
    return $resource('/qopsearch/api/Product/:id', {}, {
//        'hasPendencia': { method: 'POST', url: '/cliq/api/Controle/hasPendencia'},
//        'historicoLancamentos': {method: 'GET', url:'/cliq/api/Controle/historicoLancamentoValores/:medidor', params:{medidor:'@medidor'}, isArray: true},
        'findAllByCategory': { method: 'GET', url: '/qopsearch/api/Product/findAll/:idcat/:testcase', params:{idcat:'@idcat', testcase:'@testcase'}},
        'getCategories': { method: 'GET', url: '/qopsearch/api/Product/getCategories', isArray: true},
        'getFeatures': { method: 'GET', url: '/qopsearch/api/Product/getFeatures/:idcat', params:{idcat:'@idcat'}, isArray: true},
        'refine': { method: 'POST', url: '/qopsearch/api/Product/refine'}
    });
});
