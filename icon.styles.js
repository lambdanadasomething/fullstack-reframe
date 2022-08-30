var _templateObject;

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

function _EMOTION_STRINGIFIED_CSS_ERROR__() { return "You have tried to stringify object returned from `css` function. It isn't supposed to be used directly (e.g. as value of the `className` prop), but rather handed to emotion so it can handle it (e.g. as value of `css` prop)."; }

/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
import { css, keyframes } from '@emotion/react';
import { logicalCSS, euiCanAnimate } from '../../global_styling';

var iconSize = function iconSize(size) {
  return "\n    ".concat(logicalCSS('width', size), ";\n    ").concat(logicalCSS('height', size), ";\n  ");
};

export var iconLoadingOpacity = 0.05;
var iconLoading = keyframes(_templateObject || (_templateObject = _taggedTemplateLiteral(["\n  0% {\n    opacity: ", ";\n  }\n\n  100% {\n    opacity: 1;\n  }\n"])), iconLoadingOpacity);

var _ref = process.env.NODE_ENV === "production" ? {
  name: "njd749-logoElasticOutline",
  styles: "*[fill]{fill:none!important;}.outline{fill:currentColor!important;};label:logoElasticOutline;"
} : {
  name: "njd749-logoElasticOutline",
  styles: "*[fill]{fill:none!important;}.outline{fill:currentColor!important;};label:logoElasticOutline;",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
};

var _ref2 = process.env.NODE_ENV === "production" ? {
  name: "uacjc5-inherit",
  styles: "color:inherit;label:inherit;"
} : {
  name: "uacjc5-inherit",
  styles: "color:inherit;label:inherit;",
  toString: _EMOTION_STRINGIFIED_CSS_ERROR__
};

export var euiIconStyles = function euiIconStyles(_ref3) {
  var euiTheme = _ref3.euiTheme;
  return {
    // Base
    euiIcon: /*#__PURE__*/css("flex-shrink:0;display:inline-block;vertical-align:middle;fill:currentColor;transform:translate(0, 0);.euiIcon__fillNegative{fill:", euiTheme.colors.darkestShade, ";};label:euiIcon;"),
    // Colors
    accent: /*#__PURE__*/css("color:", euiTheme.colors.accentText, ";;label:accent;"),
    danger: /*#__PURE__*/css("color:", euiTheme.colors.dangerText, ";;label:danger;"),
    ghost: /*#__PURE__*/css("color:", euiTheme.colors.ghost, ";*[fill],.euiIcon__fillNegative{fill:currentColor!important;};label:ghost;"),
    primary: /*#__PURE__*/css("color:", euiTheme.colors.primaryText, ";;label:primary;"),
    success: /*#__PURE__*/css("color:", euiTheme.colors.successText, ";;label:success;"),
    subdued: /*#__PURE__*/css("color:", euiTheme.colors.subduedText, ";;label:subdued;"),
    text: /*#__PURE__*/css("color:", euiTheme.colors.title, ";*[fill],.euiIcon__fillNegative{fill:currentColor!important;};label:text;"),
    warning: /*#__PURE__*/css("color:", euiTheme.colors.warningText, ";;label:warning;"),
    inherit: _ref2,
    default: /*#__PURE__*/css(";label:default;"),
    customColor: /*#__PURE__*/css(";label:customColor;"),
    logoElasticOutline: _ref,
    // Sizes
    original: /*#__PURE__*/css(";label:original;"),
    s: /*#__PURE__*/css(iconSize(euiTheme.size.m), ";label:s;"),
    m: /*#__PURE__*/css(iconSize(euiTheme.size.base), ";label:m;"),
    l: /*#__PURE__*/css(iconSize(euiTheme.size.l), ";label:l;"),
    xl: /*#__PURE__*/css(iconSize(euiTheme.size.xl), ";label:xl;"),
    xxl: /*#__PURE__*/css(iconSize(euiTheme.size.xxl), ";label:xxl;"),
    // Variants
    // App icons are two-toned. This provides the base color.
    app: /*#__PURE__*/css("fill:", euiTheme.colors.text, ";.euiIcon__fillSecondary{fill:", euiTheme.colors.successText, ";};label:app;"),
    logo: /*#__PURE__*/css(";label:logo;"),
    // Loading states
    isLoading: /*#__PURE__*/css("opacity:", iconLoadingOpacity, ";background-color:currentColor;border-radius:", euiTheme.border.radius.small, ";;label:isLoading;"),
    isLoaded: /*#__PURE__*/css(euiCanAnimate, "{animation:", iconLoading, " ", euiTheme.animation.normal, " ease-in 0s 1 forwards;};label:isLoaded;")
  };
};