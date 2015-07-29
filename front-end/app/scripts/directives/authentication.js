'use strict';

angular.module('authentication', ['http-auth-interceptor', 'base64'])
    .constant('scangularConfig', {
        applicationName: 'scangularConfig.applicationName'

    })
    .controller('loginController', ['$scope', 'loginService', 'authService', '$document', '$sanitize','$base64', '$animate', function ($scope, loginService, authService, $document, $sanitize,$base64, $animate) {

        $scope.authenticationError = false;

        function sanitizeCredentials() {
            return {
                username: $sanitize($scope.username),
//                password: $base64.encode($sanitize($scope.password)),
                password: '',
                ignoreAuthModule: 'ignoreAuthModule'
            };
        }

        $scope.submit = function () {

            if ($scope.loginForm.$valid) {

                $scope.authenticationError = false;

                loginService.login(sanitizeCredentials(),
                    function (data) {
                        loginService.activateLogin(data);
                    }, function (data) {
                        loginService.logout(data);
                        $scope.authenticationError = true;

                        var element = angular.element($document[0].querySelector("#shake-login"));

                        $animate.addClass(element, 'shake', function() {
                            $animate.removeClass(element, 'shake');
                          });
                    });
            }
        };

    }])
    .controller('userController', ['$scope', 'loginService', '$document', function ($scope, loginService) {

        $scope.logout = function () {
            loginService.logout();

        };

        $scope.changePerfil = function (perfil) {
            //TODO: depende de alteração do sca
        };

        $scope.$on('event:userDetailsPrepared', function () {
            $scope.user = loginService.getCurrentUser().userDetails;
        });

        $scope.$on('event:userLogout', function () {
            $scope.user = {};
        });

    }])
    .factory('loginService', ['$resource', 'authService', '$rootScope', 'scangularConfig', 'Survey',
        function ($resource, authService, $rootScope, scangularConfig, Survey) {
            var service = $resource('/' + scangularConfig.applicationName + '/api/authentication', {
                'username': '@username',
                'password': '@password'
            }, {
                'login': {
                    method: 'POST',
                    isArray: false,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    ignoreAuthModule: 'ignoreAuthModule'
                },
                'logoutApi': {
                    method: 'GET',
                    url: '/' + scangularConfig.applicationName + '/api/logout',
                    isArray: false,
                    ignoreAuthModule: 'ignoreAuthModule'
                },
                'authenticate': {
                    method: 'GET',
                    url: '/' + scangularConfig.applicationName + '/api/authenticate',
                    isArray: false,
                    ignoreAuthModule: 'ignoreAuthModule'
                },
                'user': {
                    method: 'GET',
                    url: '/' + scangularConfig.applicationName + '/api/user',
                    isArray: false
                },
                'goToPerfil': {
                    method: 'GET',
                    url: '/' + scangularConfig.applicationName + '/api/goToPerfil/:codigo',
                    params: {codigo: '@codigo'},
                    isArray: false
                }
            });

            var currentUser = null;

            service.getCurrentUser = function () {
                return currentUser;
            };

            service.activateLogin = function (obj) {
                currentUser = {username: obj.username, permissions: {}, userDetails: {}};
                service.authenticate({}, function (userDetails) {
                    service.setUserDetails(userDetails);
                    authService.loginConfirmed(obj);
                });

            };

            service.logout = function (data) {
                authService.loginCancelled(data);
                currentUser = null;
                service.logoutApi(function (success) {
                    $rootScope.$broadcast('event:userLogout');
                });

            };

            service.setUserDetails = function (data) {
                if (!currentUser) currentUser = {username: data.username, permissions: {}, userDetails: {}};
                currentUser.userDetails = data.userDetails;
                currentUser.permissions = data.permissions;
                $rootScope.$broadcast('event:userDetailsPrepared');
            };

            return service;
        }
    ])
    .directive('authenticatedApplication', function (loginService, $document, $location) {
        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {

                scope.isAuthenticated = false;

                if (loginService.getCurrentUser() === null || loginService.getCurrentUser().username === null || loginService.getCurrentUser().username === '') {
                    loginService.authenticate({}, function (data) {
                        scope.isAuthenticated = true;
                        loginService.setUserDetails(data);
                    }, function (data) {

                        scope.isAuthenticated = false;
                    });
                }
                elem.removeClass('waiting-for-angular');

                scope.$on('event:auth-loginRequired', function () {
                    scope.isAuthenticated = false;
                });
                scope.$on('event:auth-loginConfirmed', function () {
                    scope.isAuthenticated = true;
                });
                scope.$on('event:userLogout', function () {
                    scope.isAuthenticated = false;
                });
            }
        }
    })
    .directive('loginPanel', function () {
        return {
            restrict: 'A',
            template: '<div id="shake-login" class="panel panel-primary" ng-class="{\'panel-danger\' : authenticationError}" ng-controller="loginController" ng-hide="isAuthenticated">' +
                        '<div class="panel-heading">' +
                        '<h3><i class="fa fa-lock">&nbsp;&nbsp;</i><span ng-hide="authenticationError">Acesso Restrito</span>' +
                        '<span ng-show="authenticationError">E-mail inválido ou não cadastrado</span></h3>' +
                        '</div><div class="panel-body">' +
                        '<form name="loginForm" shake-that submitted="submitted" submit="submit()">' +
                           '<div class="form-group" ng-class="{\'has-error\': form.email.$invalid && submitted}">' +
                             '<label for="username" class="control-label">E-mail</label>' +
                             '<input type="email" class="form-control" id="username" name="username" ' +
                             'ng-class="{\'alert-warning\' : loginForm.username.$error.required || loginForm.username.$error.email, \'alert-success\' : loginForm.username.$valid}" ' +
                             'placeholder="Preencha com o seu E-mail" ng-model="username" ng-model-options="{updateOn: \'blur\'}" required>' +
                           '</div>' +
//                           '<div class="form-group" ng-class="{\'has-error\': form.password.$invalid && submitted}">' +
//                             '<label for="password" class="control-label">Senha</label>' +
//                             '<input type="password" class="form-control" id="password" name="password" '+
//                             'ng-class="{\'alert-warning\' : loginForm.password.$error.required}" ' +
//                             'placeholder="Senha" ng-model="password" ng-model-options="{updateOn: \'blur\'}" required>' +
//                           '</div>' +
                           '<button type="submit" class="btn btn-primary btn-block">Login&nbsp;&nbsp;<span><i class="fa fa-sign-in"></i></span></button>' +
                       '</form></div></div></div>'
        }
    })
    .directive('userPanel', function () {
        return {
            restrict: 'A',
            template:
            '<div class="navbar-right" ng-controller="userController" ng-show="isAuthenticated">' +
                '<ul class="nav navbar-nav">' +
                    '<li class="dropdown user user-menu">' +
                        '<a href="" class="dropdown-toggle" data-toggle="dropdown"> <i class="glyphicon glyphicon-user"></i></a>' +
                        '<ul class="dropdown-menu">' +
                            '<li class="user-header bg-light-blue"  style="height: auto;"><p><small>{{user.login}}</small></p><p>{{user.nome}}<small>Perfil: {{user.perfil}}</small></p></li>' +
                            '<li class="user-footer"><div class="pull-right"><a href="" ng-click="logout()" class="btn btn-danger" style="color:white;"> Sair</a></div></li>' +
                        '</ul>' +
                    '</li>' +
                '</ul>' +
            '</div>'
        }
    });
