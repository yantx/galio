import { generate } from '@ant-design/colors'
import { colord } from 'colord'
import { upperFirst, kebabCase } from 'lodash-es'
import { commonDark, commonLight } from 'naive-ui'

// ColorType = 'primary' | 'info' | 'success' | 'warning' | 'error';
// ColorTypeCase = 'Primary' | 'Info' | 'Success' | 'Warning' | 'Error';
// ColorScene = '' | 'Suppl' | 'Hover' | 'Pressed';
// ButtonColorScene = '' | 'Hover' | 'Pressed' | 'Focus' | 'Disabled';
// ColorKey = `${ColorType}Color${ColorScene}`;
// ButtonColorKey = `textColor${ButtonColorScene}${ColorTypeCase}`;
// ThemeColor = Partial<Record<ColorKey, string>>;
// ButtonThemeColor = Partial<Record<ColorKey, string>>;
// ThemeConfig = { [key in ColorType]?: string;};
// CssObject = { [key: string]: string;};

// ColorAction {scene: ColorScene; handler: (color: string) => string;}

function getRGBColor(color) {
  return colord(color).toRgb()
}

/**
 * 根据颜色获取色系
 *
 * @param {string} color #1890ff
 * @param {boolean} darkMode 暗黑模式
 * @return {string[]} ['#E6F7FF', '#BAE7FF', '#91D5FF', ''#69C0FF', '#40A9FF', '#1890FF', '#096DD9', '#0050B3', '#003A8C', '#002766']
 */
function getGenerateColors(color, darkMode) {
  return darkMode
    ? generate(color, {
        theme: 'dark',
        backgroundColor: commonDark.bodyColor,
      })
    : generate(color)
}

/**
 * 获取其他组件颜色，主要用于适配暗色下文字颜色，该方法可以按需取舍
 *
 * @desc 比如暗黑模式下Primary按钮文字、Checkbox勾号默认都是黑色的，所以需要特殊处理变成白色,可能会有遗漏，需要的话按需补充
 * @param {ThemeConfig} config store themeConfig
 * @param {boolean} darkMode 暗黑模式
 * @return {naive-ui@GlobalThemeOverrides}
 */
function getOtherColor(config, darkMode) {
  const otherColor = {
    Button: {},
    Checkbox: {
      checkMarkColor: getTextColor(darkMode),
    },
    DatePicker: {
      itemTextColorActive: getTextColor(darkMode),
    },
    Calendar: {
      dateTextColorCurrent: getTextColor(darkMode),
    },
    Switch: {
      buttonColor: getTextColor(darkMode),
    },
  }
  const keys = Object.keys(config)
  const scenes = ['', 'Hover', 'Pressed', 'Focus', 'Disabled']
  keys.forEach((key) => {
    scenes.forEach((scene) => {
      const colorKey = `textColor${scene}${upperFirst(key)}`
      otherColor.Button[colorKey] = getTextColor(darkMode)
    })
  })
  return otherColor
}

/**
 * 获取主题颜色
 *
 * @param {ThemeConfig} config store themeConfig
 * @param {boolean} darkMode 暗黑模式
 * @return {ThemeColor}
 */
function getThemeColors(config, darkMode) {
  const themeColor = {}
  const keys = Object.keys(config)
  const colorActions = [
    { scene: '', handler: (color) => getGenerateColors(color, darkMode)[5] },
    {
      scene: 'Hover',
      handler: (color) => getGenerateColors(color, darkMode)[4],
    },
    {
      scene: 'Suppl',
      handler: (color) => getGenerateColors(color, darkMode)[4],
    },
    {
      scene: 'Pressed',
      handler: (color) => getGenerateColors(color, darkMode)[6],
    },
  ]
  keys.forEach((key) => {
    colorActions.forEach((action) => {
      const color = action.handler(config[key])
      const colorKey = `${key}Color${action.scene}`
      themeColor[colorKey] = color
    })
  })
  return themeColor
}

/**
 * 获取文字颜色，主要用于适配暗黑模式文字颜色
 *
 * @param {boolean} darkMode 暗黑模式
 * @return {string}
 */
function getTextColor(darkMode) {
  return darkMode ? commonDark.textColor2 : commonLight.baseColor
}

/**
 * 获取动态主题ThemeOverrides
 *
 * @param {ThemeConfig} config store themeConfig
 * @param {boolean} darkMode 暗黑模式
 * @return {naive-ui@GlobalThemeOverrides}
 */
function getThemeOverrides(config, darkMode) {
  const themeColors = getThemeColors(config, darkMode)
  addCssVarsToHtml(config, darkMode, themeColors)
  return {
    common: {
      ...themeColors,
    },
    ...getOtherColor(config, darkMode),
  }
}

/**
 * 将CSS文本解析为CSS对象
 *
 * @param {string} cssText "--primary-color1: 211,224,215;--primary-color2: 167,212,182;"
 * @return {CssObject} { --primary-color1: '211,224,215', --primary-color2: '167,212,182' }
 */
function parseCssText(cssText) {
  const cssObj = {}
  cssText.split(';').forEach((rule) => {
    if (rule) {
      const [key, value] = rule.split(':')
      cssObj[key.trim()] = value.trim()
    }
  })
  return cssObj
}

/**
 * 将CSS变量添加到HTML文档中
 *
 * @param {ThemeConfig} config - store themeConfig
 * @param {boolean} darkMode - 暗黑模式
 * @param {ThemeColor} themeColors - getThemeColors返回的颜色列表
 * @return {void}
 */
function addCssVarsToHtml(config, darkMode, themeColors) {
  const $root = document.documentElement
  const cssText = $root.style.cssText
  const cssObj = parseCssText(cssText)
  const configCssObj = {}
  const configEntries = Object.entries(config)
  const themeColorsEntries = Object.entries(themeColors)

  for (const [key, value] of themeColorsEntries) {
    const { r, g, b } = getRGBColor(value)
    configCssObj[`--n-${kebabCase(key)}`] = `${r},${g},${b}`
  }

  for (const [key, value] of configEntries) {
    const generateColors = getGenerateColors(value, darkMode)
    generateColors.map((color, index) => {
      const { r, g, b } = getRGBColor(color)
      configCssObj[`--n-${key}-color-${index + 1}`] = `${r},${g},${b}`
    })
  }

  const newCssText = Object.entries({
    ...cssObj,
    ...configCssObj,
  })
    .map(([key, value]) => `${key}: ${value};`)
    .join(' ')

  $root.style.cssText = newCssText
}
export default {
  header: {
    visible: true,
    height: 60,
  },
  themeConfigDefault: {
    primary: '#00828b',
    info: '#2080f0',
    success: '#18a058',
    warning: '#f0a020',
    error: '#d03050',
  },
  swatches: ['#FFFFFF', '#207f4c', '#18A058', '#2080F0', '#F0A020', 'rgba(208, 48, 80, 1)'],
  tags: {
    visible: true,
    height: 50,
  },
  footer: {
    visible: true,
  },
  sider: {
    visible: true,
    width: 220,
    collapsedWidth: 64,
  },
  getThemeOverrides: getThemeOverrides,
  getThemeColors: getThemeColors,
  parseCssText: parseCssText,
  addCssVarsToHtml: addCssVarsToHtml,
}
