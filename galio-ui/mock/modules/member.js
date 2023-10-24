import { resolveToken } from '../util'

const users = {
  admin: {
    id: 1,
    name: 'admin',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    email: 'Ronnie@123.com',
    role: ['admin'],
    menus: menus,
  },
  editor: {
    id: 2,
    name: 'editor',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    email: 'Ronnie@123.com',
    role: ['editor'],
    menus: menus,
  },
  guest: {
    id: 3,
    name: 'guest',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    role: [],
    menus: menus,
  },
}
export default [
  {
    url: '/member',
    method: 'get',
    response: ({ headers }) => {
      const token = resolveToken(headers?.authorization)
      return {
        code: 20000,
        data: {
          ...(users[token] || users.guest),
        },
      }
    },
  },
]

const menus = [
  {
    id: '0',
    title: '工作台',
    url: '/workbench',
    icon: 'mdi:home',
  },
  {
    id: '1',
    title: '多级菜单',
    icon: 'ic:baseline-menu',
    children: [
      {
        id: 'a-1',
        title: 'a-1',
        url: 'multiple-menu',
        icon: 'ic:baseline-menu',
        children: [
          {
            id: 'a-1-1',
            title: 'a-1-1',
            url: '/a-1-1',
            icon: 'ic:baseline-menu',
          },
          {
            id: 'a-1-2',
            title: 'a-1-2',
            url: '/a-1-2',
            icon: 'ic:baseline-menu',
          },
        ],
      },
      {
        id: '1-2',
        title: '1-2',
        icon: 'ic:baseline-menu',
        children: [
          {
            id: '1-2-1',
            title: 'a-2-1（单个子菜单）',
            url: 'a-2-1',
            icon: 'ic:baseline-menu',
          },
        ],
      },
    ],
  },
  {
    id: '2',
    title: '示例页面',
    icon: 'uil:pagelines',
    children: [
      {
        id: '2-1',
        title: 'CRUD表格',
        icon: 'uil:pagelines',
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
        buttuns: ['addBtn', 'editBtn', 'queryBtn'],
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
