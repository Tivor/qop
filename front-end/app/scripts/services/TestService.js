'use strict';

/**
 * @ngdoc service
 * @name custeioApp.custeioService
 * @description
 * # myService
 * Service in the custeioApp.
 */


angular.module('custeioApp').factory('Controle', function ($resource) {
    return $resource('/qop/api/Controle/:id', {}, {
        'findAtivosByGrupo': { method: 'GET', url: '/cliq/api/Controle/findAtivosByGrupo'},
        'calculaTotais': { method: 'POST', url: '/cliq/api/Controle/calculaTotais'},
        'hasPendencia': { method: 'POST', url: '/cliq/api/Controle/hasPendencia'},
        'historicoLancamentos': {method: 'GET', url:'/cliq/api/Controle/historicoLancamentoValores/:medidor', params:{medidor:'@medidor'}, isArray: true}
    });
});
