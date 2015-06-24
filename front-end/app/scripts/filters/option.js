'use strict';

angular.module('qopApp').filter('option', function ($filter) {
  return function (options, option) {
    var optionObj = $filter('filter')(options, {'id': option});
    return optionObj[0].valueOption;
  };
});