function _typeof(obj) { "@babel/helpers - typeof"; if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

var _excluded = ["type", "size", "color", "className", "tabIndex", "title", "onIconLoad", "theme", "style"];

function _extends() { _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; }; return _extends.apply(this, arguments); }

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) { symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); } keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _objectWithoutProperties(source, excluded) { if (source == null) return {}; var target = _objectWithoutPropertiesLoose(source, excluded); var key, i; if (Object.getOwnPropertySymbols) { var sourceSymbolKeys = Object.getOwnPropertySymbols(source); for (i = 0; i < sourceSymbolKeys.length; i++) { key = sourceSymbolKeys[i]; if (excluded.indexOf(key) >= 0) continue; if (!Object.prototype.propertyIsEnumerable.call(source, key)) continue; target[key] = source[key]; } } return target; }

function _objectWithoutPropertiesLoose(source, excluded) { if (source == null) return {}; var target = {}; var sourceKeys = Object.keys(source); var key, i; for (i = 0; i < sourceKeys.length; i++) { key = sourceKeys[i]; if (excluded.indexOf(key) >= 0) continue; target[key] = source[key]; } return target; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); return Constructor; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); if (superClass) _setPrototypeOf(subClass, superClass); }

function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }

function _createSuper(Derived) { var hasNativeReflectConstruct = _isNativeReflectConstruct(); return function _createSuperInternal() { var Super = _getPrototypeOf(Derived), result; if (hasNativeReflectConstruct) { var NewTarget = _getPrototypeOf(this).constructor; result = Reflect.construct(Super, arguments, NewTarget); } else { result = Super.apply(this, arguments); } return _possibleConstructorReturn(this, result); }; }

function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } else if (call !== void 0) { throw new TypeError("Derived constructors may only return object or undefined"); } return _assertThisInitialized(self); }

function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }

function _isNativeReflectConstruct() { if (typeof Reflect === "undefined" || !Reflect.construct) return false; if (Reflect.construct.sham) return false; if (typeof Proxy === "function") return true; try { Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {})); return true; } catch (e) { return false; } }

function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */
import React, { PureComponent } from 'react';
import PropTypes from "prop-types";
import classNames from 'classnames';
import { keysOf } from '../common';
import { typeToPathMap } from './icon_map';
import { icon as empty } from './assets/empty';
import { enqueueStateChange } from '../../services/react';
import { htmlIdGenerator, withEuiTheme } from '../../services';
export { COLORS } from './named_colors';
import { isNamedColor } from './named_colors';
import { euiIconStyles } from './icon.styles';
import { jsx as ___EmotionJSX } from "@emotion/react";

var getIsAppIcon = function getIsAppIcon(iconType) {
  if (typeof iconType !== 'string') return false;
  if (iconType === 'dataVisualizer') return true; // Special case

  if (iconType.indexOf('data:') === 0) return false; // Inline data URIs should be short-circuited for performance

  return iconType.endsWith('App') || iconType.endsWith('Job');
};

export var TYPES = keysOf(typeToPathMap);
export var SIZES = ['original', 's', 'm', 'l', 'xl', 'xxl'];

function isEuiIconType(x) {
  return typeof x === 'string' && typeToPathMap.hasOwnProperty(x);
}

function getInitialIcon(icon) {
  if (icon == null) {
    return undefined;
  }

  if (isEuiIconType(icon)) {
    if (iconComponentCache.hasOwnProperty(icon)) {
      return iconComponentCache[icon];
    }

    return undefined;
  }

  return icon;
}

var generateId = htmlIdGenerator();
var iconComponentCache = {};
export var clearIconComponentCache = function clearIconComponentCache(iconType) {
  if (iconType != null) {
    delete iconComponentCache[iconType];
  } else {
    iconComponentCache = {};
  }
};
export var appendIconComponentCache = function appendIconComponentCache(iconTypeToIconComponentMap) {
  for (var _iconType in iconTypeToIconComponentMap) {
    if (iconTypeToIconComponentMap.hasOwnProperty(_iconType)) {
      iconComponentCache[_iconType] = iconTypeToIconComponentMap[_iconType];
    }
  }
};
export var EuiIconClass = /*#__PURE__*/function (_PureComponent) {
  _inherits(EuiIconClass, _PureComponent);

  var _super = _createSuper(EuiIconClass);

  function EuiIconClass(props) {
    var _this;

    _classCallCheck(this, EuiIconClass);

    _this = _super.call(this, props);

    _defineProperty(_assertThisInitialized(_this), "isMounted", true);

    _defineProperty(_assertThisInitialized(_this), "loadIconComponent", function (iconType) {
      if (iconComponentCache.hasOwnProperty(iconType)) {
        // exists in cache
        _this.setState({
          isLoading: false,
          neededLoading: false,
          icon: iconComponentCache[iconType]
        });

        _this.onIconLoad();

        return;
      }

      import(
      /* webpackChunkName: "icon.[request]" */
      // It's important that we don't use a template string here, it
      // stops webpack from building a dynamic require context.
      // eslint-disable-next-line prefer-template
      './assets/' + typeToPathMap[iconType]).then(function (_ref) {
        var icon = _ref.icon;
        iconComponentCache[iconType] = icon;
        enqueueStateChange(function () {
          if (_this.isMounted && _this.props.type === iconType) {
            _this.setState({
              icon: icon,
              iconTitle: iconType,
              isLoading: false
            }, _this.onIconLoad);
          }
        });
      });
    });

    _defineProperty(_assertThisInitialized(_this), "onIconLoad", function () {
      var onIconLoad = _this.props.onIconLoad;

      if (onIconLoad) {
        onIconLoad();
      }
    });

    var type = props.type;
    var initialIcon = getInitialIcon(type);
    _this.state = {
      icon: initialIcon,
      iconTitle: undefined,
      isLoading: false,
      neededLoading: false
    };
    return _this;
  }

  _createClass(EuiIconClass, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      var type = this.props.type;

      if (isEuiIconType(type) && this.state.icon == null) {
        //eslint-disable-next-line react/no-did-mount-set-state
        this.setState({
          neededLoading: true,
          isLoading: true
        });
        this.loadIconComponent(type);
      } else {
        this.onIconLoad();
      }
    }
  }, {
    key: "componentDidUpdate",
    value: function componentDidUpdate(prevProps) {
      var type = this.props.type;

      if (type !== prevProps.type) {
        if (isEuiIconType(type)) {
          // eslint-disable-next-line react/no-did-update-set-state
          this.setState({
            neededLoading: iconComponentCache.hasOwnProperty(type),
            isLoading: true
          });
          this.loadIconComponent(type);
        } else {
          // eslint-disable-next-line react/no-did-update-set-state
          this.setState({
            icon: type,
            neededLoading: true,
            isLoading: false
          });
        }
      }
    }
  }, {
    key: "componentWillUnmount",
    value: function componentWillUnmount() {
      this.isMounted = false;
    }
  }, {
    key: "render",
    value: function render() {
      var _this$props = this.props,
          type = _this$props.type,
          _this$props$size = _this$props.size,
          size = _this$props$size === void 0 ? 'm' : _this$props$size,
          color = _this$props.color,
          className = _this$props.className,
          tabIndex = _this$props.tabIndex,
          title = _this$props.title,
          onIconLoad = _this$props.onIconLoad,
          theme = _this$props.theme,
          style = _this$props.style,
          rest = _objectWithoutProperties(_this$props, _excluded);

      var _this$state = this.state,
          isLoading = _this$state.isLoading,
          neededLoading = _this$state.neededLoading,
          iconTitle = _this$state.iconTitle;
      var isLoaded = !isLoading && neededLoading;
      var isCustomColor = color && !isNamedColor(color);
      var optionalCustomStyles = isCustomColor ? _objectSpread({
        color: color
      }, style) : style; // These icons are a little special and get some extra CSS flexibility

      var isAppIcon = getIsAppIcon(type); // App color styles are only applied if no color is passed or if color="default" is passed

      var appIconHasColor = color && color !== 'default'; // The Elastic logo should be an outline in text and ghost mode

      var isElasticLogoOutline = type === 'logoElastic' && (color === 'ghost' || color === 'text');
      var classes = classNames('euiIcon', className); // Emotion styles

      var styles = euiIconStyles(theme);
      var cssStyles = [styles.euiIcon, styles[size], color && isNamedColor(color) && styles[color], isCustomColor && styles.customColor, isElasticLogoOutline && styles.logoElasticOutline, isAppIcon && !appIconHasColor && styles.app, isLoading && styles.isLoading, !isLoading && neededLoading && styles.isLoaded];
      var icon = this.state.icon || empty; // This is a fix for IE and Edge, which ignores tabindex="-1" on an SVG, but respects
      // focusable="false".
      //   - If there's no tabindex specified, we'll default the icon to not be focusable,
      //     which is how SVGs behave in Chrome, Safari, and FF.
      //   - If tabindex is -1, then the consumer wants the icon to be focusable by JavaScript only.
      //   - If the tabindex is 0, the consumer wants the icon to be keyboard focusable.

      var focusable = tabIndex == null || tabIndex === -1 ? 'false' : 'true';

      if (typeof icon === 'string') {
        return ___EmotionJSX("img", _extends({
          alt: title ? title : '',
          src: icon,
          className: classes,
          css: cssStyles,
          tabIndex: tabIndex
        }, rest));
      } else {
        var Svg = icon; // If it's an empty icon, or if there is no aria-label, aria-labelledby, or title it gets aria-hidden true

        var isAriaHidden = icon === empty || !(this.props['aria-label'] || this.props['aria-labelledby'] || this.props.title);
        var hideIconEmpty = isAriaHidden && {
          'aria-hidden': true
        };
        var titleId; // If no aria-label or aria-labelledby is provided but there's a title, a titleId is generated
        //  The svg aria-labelledby attribute gets this titleId
        //  The svg title element gets this titleId as an id

        if (!this.props['aria-label'] && !this.props['aria-labelledby'] && title) {
          titleId = {
            titleId: generateId()
          };
        }

        return ___EmotionJSX(Svg, _extends({
          className: classes,
          style: optionalCustomStyles,
          css: cssStyles,
          tabIndex: tabIndex,
          focusable: focusable,
          role: "img",
          title: title,
          "data-icon-type": iconTitle,
          "data-is-loaded": isLoaded || undefined,
          "data-is-loading": isLoading || undefined
        }, titleId, rest, hideIconEmpty));
      }
    }
  }]);

  return EuiIconClass;
}(PureComponent);
EuiIconClass.propTypes = {
  className: PropTypes.string,
  "aria-label": PropTypes.string,
  "data-test-subj": PropTypes.string,

  /**
       * `Enum` is any of the named icons listed in the docs, `string` is usually a URL to an SVG file, and `elementType` is any React SVG component
       */
  type: PropTypes.oneOfType([PropTypes.oneOf(["accessibility", "addDataApp", "advancedSettingsApp", "agentApp", "aggregate", "alert", "analyzeEvent", "annotation", "apmApp", "apmTrace", "appSearchApp", "apps", "arrowDown", "arrowLeft", "arrowRight", "arrowUp", "arrowStart", "arrowEnd", "asterisk", "auditbeatApp", "beaker", "bell", "bellSlash", "bolt", "boxesHorizontal", "boxesVertical", "branch", "branchUser", "broom", "brush", "bug", "bullseye", "calendar", "canvasApp", "casesApp", "check", "checkInCircleFilled", "cheer", "classificationJob", "clock", "cloudDrizzle", "cloudStormy", "cloudSunny", "cluster", "codeApp", "color", "compute", "console", "consoleApp", "container", "continuityAbove", "continuityAboveBelow", "continuityBelow", "continuityWithin", "controlsHorizontal", "controlsVertical", "copy", "copyClipboard", "createAdvancedJob", "createMultiMetricJob", "createPopulationJob", "createSingleMetricJob", "cross", "crossClusterReplicationApp", "crossInACircleFilled", "crosshairs", "currency", "cut", "dashboardApp", "dataVisualizer", "database", "desktop", "devToolsApp", "discoverApp", "document", "documentEdit", "documentation", "documents", "dot", "doubleArrowLeft", "doubleArrowRight", "download", "editorAlignCenter", "editorAlignLeft", "editorAlignRight", "editorBold", "editorChecklist", "editorCodeBlock", "editorComment", "editorDistributeHorizontal", "editorDistributeVertical", "editorHeading", "editorItalic", "editorItemAlignBottom", "editorItemAlignCenter", "editorItemAlignLeft", "editorItemAlignMiddle", "editorItemAlignRight", "editorItemAlignTop", "editorLink", "editorOrderedList", "editorPositionBottomLeft", "editorPositionBottomRight", "editorPositionTopLeft", "editorPositionTopRight", "editorRedo", "editorStrike", "editorTable", "editorUnderline", "editorUndo", "editorUnorderedList", "email", "empty", "emsApp", "eql", "eraser", "exit", "expand", "expandMini", "exportAction", "eye", "eyeClosed", "faceHappy", "faceNeutral", "faceSad", "filebeatApp", "filter", "flag", "fleetApp", "fold", "folderCheck", "folderClosed", "folderExclamation", "folderOpen", "frameNext", "framePrevious", "fullScreen", "fullScreenExit", "function", "gear", "gisApp", "glasses", "globe", "grab", "grabHorizontal", "graphApp", "grid", "grokApp", "heart", "heartbeatApp", "heatmap", "help", "home", "iInCircle", "image", "importAction", "indexClose", "indexEdit", "indexFlush", "indexManagementApp", "indexMapping", "indexOpen", "indexPatternApp", "indexRollupApp", "indexRuntime", "indexSettings", "inputOutput", "inspect", "invert", "ip", "keyboard", "kqlField", "kqlFunction", "kqlOperand", "kqlSelector", "kqlValue", "kubernetesNode", "kubernetesPod", "layers", "lensApp", "lettering", "lineDashed", "lineDotted", "lineSolid", "link", "list", "listAdd", "lock", "lockOpen", "logoAWS", "logoAWSMono", "logoAerospike", "logoApache", "logoAppSearch", "logoAzure", "logoAzureMono", "logoBeats", "logoBusinessAnalytics", "logoCeph", "logoCloud", "logoCloudEnterprise", "logoCode", "logoCodesandbox", "logoCouchbase", "logoDocker", "logoDropwizard", "logoElastic", "logoElasticStack", "logoElasticsearch", "logoEnterpriseSearch", "logoEtcd", "logoGCP", "logoGCPMono", "logoGithub", "logoGmail", "logoGolang", "logoGoogleG", "logoHAproxy", "logoIBM", "logoIBMMono", "logoKafka", "logoKibana", "logoKubernetes", "logoLogging", "logoLogstash", "logoMaps", "logoMemcached", "logoMetrics", "logoMongodb", "logoMySQL", "logoNginx", "logoObservability", "logoOsquery", "logoPhp", "logoPostgres", "logoPrometheus", "logoRabbitmq", "logoRedis", "logoSecurity", "logoSiteSearch", "logoSketch", "logoSlack", "logoUptime", "logoWebhook", "logoWindows", "logoWorkplaceSearch", "logsApp", "logstashFilter", "logstashIf", "logstashInput", "logstashOutput", "logstashQueue", "machineLearningApp", "magnet", "magnifyWithExclamation", "magnifyWithMinus", "magnifyWithPlus", "managementApp", "mapMarker", "memory", "menu", "menuDown", "menuLeft", "menuRight", "menuUp", "merge", "metricbeatApp", "metricsApp", "minimize", "minus", "minusInCircle", "minusInCircleFilled", "mobile", "monitoringApp", "moon", "namespace", "nested", "node", "notebookApp", "number", "offline", "online", "outlierDetectionJob", "package", "packetbeatApp", "pageSelect", "pagesSelect", "paperClip", "partial", "pause", "payment", "pencil", "percent", "pin", "pinFilled", "pipelineApp", "play", "playFilled", "plus", "plusInCircle", "plusInCircleFilled", "popout", "push", "questionInCircle", "quote", "recentlyViewedApp", "refresh", "regressionJob", "reporter", "reportingApp", "returnKey", "save", "savedObjectsApp", "scale", "search", "searchProfilerApp", "securityAnalyticsApp", "securityApp", "securitySignal", "securitySignalDetected", "securitySignalResolved", "sessionViewer", "shard", "share", "snowflake", "sortDown", "sortLeft", "sortRight", "sortUp", "sortable", "spacesApp", "sqlApp", "starEmpty", "starEmptySpace", "starFilled", "starFilledSpace", "starMinusEmpty", "starMinusFilled", "starPlusEmpty", "starPlusFilled", "stats", "stop", "stopFilled", "stopSlash", "storage", "string", "submodule", "sun", "swatchInput", "symlink", "tableDensityCompact", "tableDensityExpanded", "tableDensityNormal", "tableOfContents", "tag", "tear", "temperature", "timeline", "timelionApp", "timeRefresh", "timeslider", "training", "trash", "unfold", "unlink", "upgradeAssistantApp", "uptimeApp", "user", "userAvatar", "users", "usersRolesApp", "vector", "videoPlayer", "visArea", "visAreaStacked", "visBarHorizontal", "visBarHorizontalStacked", "visBarVertical", "visBarVerticalStacked", "visGauge", "visGoal", "visLine", "visMapCoordinate", "visMapRegion", "visMetric", "visPie", "visTable", "visTagCloud", "visText", "visTimelion", "visVega", "visVisualBuilder", "visualizeApp", "watchesApp", "wordWrap", "wordWrapDisabled", "workplaceSearchApp", "wrench", "tokenAlias", "tokenAnnotation", "tokenArray", "tokenBinary", "tokenBoolean", "tokenClass", "tokenCompletionSuggester", "tokenConstant", "tokenDate", "tokenDenseVector", "tokenElement", "tokenEnum", "tokenEnumMember", "tokenEvent", "tokenException", "tokenField", "tokenFile", "tokenFlattened", "tokenFunction", "tokenGeo", "tokenHistogram", "tokenInterface", "tokenIP", "tokenJoin", "tokenKey", "tokenKeyword", "tokenMethod", "tokenMetricCounter", "tokenMetricGauge", "tokenModule", "tokenNamespace", "tokenNested", "tokenNull", "tokenNumber", "tokenObject", "tokenOperator", "tokenPackage", "tokenParameter", "tokenPercolator", "tokenProperty", "tokenRange", "tokenRankFeature", "tokenRankFeatures", "tokenRepo", "tokenSearchType", "tokenShape", "tokenString", "tokenStruct", "tokenSymbol", "tokenTag", "tokenText", "tokenTokenCount", "tokenVariable"]).isRequired, PropTypes.string.isRequired, PropTypes.elementType.isRequired]).isRequired,

  /**
       * One of EUI's color palette or a valid CSS color value https://developer.mozilla.org/en-US/docs/Web/CSS/color_value.
       * Note that coloring only works if your SVG is removed of fill attributes.
       */
  color: PropTypes.oneOfType([PropTypes.string.isRequired, PropTypes.any.isRequired]),

  /**
       * Note that every size other than `original` assumes the provided SVG sits on a square viewbox.
       */
  size: PropTypes.any,

  /**
       * Descriptive title for naming the icon based on its use
       */
  title: PropTypes.string,

  /**
       * A unique identifier for the title element
       */
  titleId: PropTypes.string,

  /**
       * Its value should be one or more element IDs
       */
  "aria-labelledby": PropTypes.string,

  /**
       * Callback when the icon has been loaded & rendered
       */
  onIconLoad: PropTypes.func
};
export var EuiIcon = withEuiTheme(EuiIconClass);