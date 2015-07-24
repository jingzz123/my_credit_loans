/**
 * 添加一些公用的js
 */

//四舍五入保留小数位（toFixed(n)有时会出现计算不准确的情况）
var Digit = {};
Digit.round = function(digit, length) {
    length = length ? parseInt(length) : 0;
    if (length <= 0) return Math.round(digit);
    digit = Math.round(digit * Math.pow(10, length)) / Math.pow(10, length);
    return digit;
};

