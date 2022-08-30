var _excluded = ["title", "titleId"];

function _extends() { _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; }; return _extends.apply(this, arguments); }

function _objectWithoutProperties(source, excluded) { if (source == null) return {}; var target = _objectWithoutPropertiesLoose(source, excluded); var key, i; if (Object.getOwnPropertySymbols) { var sourceSymbolKeys = Object.getOwnPropertySymbols(source); for (i = 0; i < sourceSymbolKeys.length; i++) { key = sourceSymbolKeys[i]; if (excluded.indexOf(key) >= 0) continue; if (!Object.prototype.propertyIsEnumerable.call(source, key)) continue; target[key] = source[key]; } } return target; }

function _objectWithoutPropertiesLoose(source, excluded) { if (source == null) return {}; var target = {}; var sourceKeys = Object.keys(source); var key, i; for (i = 0; i < sourceKeys.length; i++) { key = sourceKeys[i]; if (excluded.indexOf(key) >= 0) continue; target[key] = source[key]; } return target; }

/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
// THIS IS A GENERATED FILE. DO NOT MODIFY MANUALLY. @see scripts/compile-icons.js
import * as React from 'react';
import { jsx as ___EmotionJSX } from "@emotion/react";

var EuiIconEditorChecklist = function EuiIconEditorChecklist(_ref) {
  var title = _ref.title,
      titleId = _ref.titleId,
      props = _objectWithoutProperties(_ref, _excluded);

  return ___EmotionJSX("svg", _extends({
    xmlns: "http://www.w3.org/2000/svg",
    width: 16,
    height: 16,
    fill: "none",
    viewBox: "0 0 16 16",
    "aria-labelledby": titleId
  }, props), title ? ___EmotionJSX("title", {
    id: titleId
  }, title) : null, ___EmotionJSX("path", {
    d: "M4.597 3a.411.411 0 00-.299.132l-1.85 1.993-.756-.7a.412.412 0 00-.28-.124.42.42 0 00-.292.098.333.333 0 00-.12.257.335.335 0 00.127.254l1.064.982A.418.418 0 002.488 6a.411.411 0 00.288-.126L4.904 3.58a.328.328 0 00.095-.25.337.337 0 00-.126-.238A.421.421 0 004.597 3zm-.299 4.132A.411.411 0 014.597 7a.421.421 0 01.276.093c.076.062.12.147.126.238a.328.328 0 01-.095.25L2.776 9.874a.411.411 0 01-.288.126.418.418 0 01-.297-.108L1.127 8.91A.335.335 0 011 8.656a.333.333 0 01.12-.257.42.42 0 01.292-.098c.108.005.21.05.28.123l.757.701 1.849-1.993zM4.597 11a.411.411 0 00-.299.132l-1.85 1.993-.756-.7a.412.412 0 00-.28-.124.421.421 0 00-.292.098.333.333 0 00-.12.257.335.335 0 00.127.254l1.064.982a.418.418 0 00.297.108.411.411 0 00.288-.126l2.128-2.293a.328.328 0 00.095-.25.337.337 0 00-.126-.238.421.421 0 00-.276-.093zM6.5 4a.5.5 0 000 1h7a.5.5 0 000-1h-7zM6 8.5a.5.5 0 01.5-.5h7a.5.5 0 010 1h-7a.5.5 0 01-.5-.5zm.5 3.5a.5.5 0 000 1h7a.5.5 0 000-1h-7z"
  }));
};

export var icon = EuiIconEditorChecklist;