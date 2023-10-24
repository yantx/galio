import { resolveToken } from '../util'

const functions = [
  {
    id: '0',
    title: '工作台',
    url: '/workbench',
  },
  {
    id: '1',
    title: '多级菜单',
    children: [
      {
        id: 'a-1',
        title: 'a-1',
        url: 'multiple-menu',
        children: [
          {
            id: 'a-1-1',
            title: 'a-1-1',
            url: '/a-1-1',
          },
          {
            id: 'a-1-2',
            title: 'a-1-2',
            url: '/a-1-2',
          },
        ],
      },
      {
        id: '1-2',
        title: '1-2',
        children: [
          {
            id: '1-2-1',
            title: 'a-2-1（单个子菜单）',
            url: 'a-2-1',
          },
        ],
      },
    ],
  },
  {
    id: '2',
    title: '示例页面',
    children: [
      {
        id: '2-1',
        title: 'CRUD表格',
        url: 'crud',
      },
      {
        id: '2-2',
        title: 'MD编辑器',
        url: 'md-editor',
      },
      {
        id: '2-3',
        title: '富文本编辑器',
        url: 'rich-text',
      },
      {
        id: '2-4',
        title: '图片上传',
        url: 'upload',
        icon: 'mdi:upload',
      },
    ],
  },
  {
    id: '3',
    title: '基础功能',
    url: 'base',
    icon: 'majesticons:compass-line',
    children: [
      {
        id: '3-1',
        title: '基础组件',
        url: 'index',
        icon: 'material-symbols:auto-awesome-outline-rounded',
      },
      {
        id: '3-2',
        url: 'keep-alive',
        title: 'KeepAlive',
        icon: 'material-symbols:auto-awesome-outline-rounded',
      },
    ],
  },
  {
    id: '4',
    title: '外部链接',
    icon: 'mdi:link-variant',
    url: 'external-link',
    children: [
      {
        id: '4-1',
        title: '跳转链接',
        url: 'external',
        extendurl: 'https://router.vuejs.org/zh/guide/essentials/navigation.html',
      },
      {
        id: '4-2',
        title: '内嵌连接',
        url: 'framepane',
        extendurl: 'https://router.vuejs.org/zh/guide/essentials/navigation.html',
      },
    ],
  },
]

export default [
  {
    url: '/functions',
    method: 'get',
    response: ({ headers }) => {
      return {
        code: 20000,
        data: functions,
      }
    },
  },
]
