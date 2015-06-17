'use strict';

// Source: dist/.temp/brasil/filters/realbrasileiro.js
function formatReal(int) {

  if (int || int === 0) {

    var tmp = int + '';

    if (tmp.indexOf('.') === -1) {
      tmp = tmp + '.00';
    } else if (tmp.indexOf('.') === (tmp.length - 2)) {
      tmp = tmp + '0';
    }

    tmp = tmp.replace('.', '');
    tmp = tmp.replace(',', '');

    var neg = false;
    if (tmp.indexOf('-') === 0) {
      neg = true;
      tmp = tmp.replace('-', '');
    }
    if (tmp.length === 1) {
      tmp = '0' + tmp;
    }
    tmp = tmp.replace(/([0-9]{2})$/g, ',$1');
    if (tmp.length > 6) {
      tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, '.$1,$2');
    }
    if (tmp.length > 9) {
      tmp = tmp.replace(/([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, '.$1.$2,$3');
    }
    if (tmp.length > 12) {
      tmp = tmp.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, '.$1.$2.$3,$4');
    }
    if (tmp.indexOf('.') === 0) {
      tmp = tmp.replace('.', '');
    }
    if (tmp.indexOf(',') === 0) {
      tmp = tmp.replace(',', '0,');
    }
    return neg ? '-' + tmp : tmp;
  }

  return '';

}
angular.module('qopApp').filter('realbrasileiro', function () {
  return function (input) {
    return formatReal(input);
  };
});