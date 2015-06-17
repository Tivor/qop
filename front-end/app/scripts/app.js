'use strict';

/**
 * @ngdoc overview
 * @name qopApp
 * @description
 * # qopApp
 *
 * Main module of the application.
 */
var app = angular.module('qopApp', [
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngAnimate',
    'ngTouch',
    'toaster',
    'ui.bootstrap',
    'ui.slider',
    'sca-ngular'
]);
//configura rotas
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: 'views/main.html'})
        .when('/404', {templateUrl: 'views/404.html'})
        .when('/details', {templateUrl: 'views/product-details.html'})
        .otherwise({redirectTo: '/'});
}]);

//Tratamento global de excecoes
app.config(['$httpProvider', 'scangularConfig', function($httpProvider, scangularConfig) {

    var nomeApp = 'Mensagem do Sistema';

    scangularConfig.applicationName = 'qopsearch';

    $httpProvider.interceptors.push(
        ['$q', 'toaster', '$window','$injector',
            function($q, toaster, $window) {

                return {
                    'response': function(response) {

                        var title = response.headers('title');
                        var message = response.headers('message');

                        switch (response.status) {
                            case 200: break;
                            default: toaster.pop('success', title, message);
                        }

                        return response;
                    },

                    'responseError': function (errorResponse) {

                        switch (errorResponse.status) {
                            case 0:
                                toaster.pop('error', 'Desculpe', 'Nossos servidores encontram-se indisponíveis no momento.');
                                break;
                            case 400:
                                //toaster.pop('warning', errorResponse.data.error, 'Campo ' + '"' + errorResponse.data.violations[0].field + '"' + errorResponse.data.violations[0].message);
                                angular.forEach(errorResponse.data.violations, function(value) {
                                    toaster.pop('warning', nomeApp, value.message);
                                });
                                //toaster.pop('warning', nomeApp, errorResponse.data.error);
                                break;
                            case 403: // 403-Forbidden - Tem autenticacao, mas, o acesso é proibido para este usuario.
                                toaster.pop('warning', nomeApp, 'Você não tem permissão para acessar este conteúdo, ou partes, do conteudo, que foi requisitado.');
                                break;
                            case 404:
                                toaster.pop('info', nomeApp, 'Nenhum registro encontrado');
                                break;
                            case 409:
                                angular.forEach(errorResponse.data.violations, function(value) {
                                    toaster.pop('warning', nomeApp, value);
                                });
                                break;
                            case 500:
                                //Neste caso o melhor é
                                toaster.pop('error', nomeApp, errorResponse.data.error +
                                    ' ticket[' + errorResponse.data.ticket + ']',9000,'');
                                break;
                        }

                        return $q.reject(errorResponse);
                    }
                };
            }]);
}]);


//Configuração para acertar data
app.config(function (datepickerConfig, datepickerPopupConfig) {
  datepickerConfig.startingDay = 1;
  datepickerConfig.showWeeks = false;
  datepickerPopupConfig.showButtonBar = true;
  datepickerPopupConfig.closeOnDateSelection = false;

  datepickerConfig.initDate = new Date();
  datepickerConfig.minDate = new Date("January 1, 2000 00:00:00");
  datepickerConfig.showWeeks = false;

});

angular.module('ui.bootstrap.carousel', ['ui.bootstrap.transition'])
    .controller('CarouselController', ['$scope', '$timeout', '$transition', '$q', function($scope, $timeout, $transition, $q) {
}]).directive('carousel', [function() {
    return {
    }
}]);