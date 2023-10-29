import { resolveToken } from '../util'

const functions = [
  {
    id: '0',
    title: '工作台',
    path: 'workbench',
    icon: 'mdi:home',
    name: 'Workbench',
    order: 0,
  },
  {
    id: '1',
    title: '多级菜单',
    icon: 'ic:baseline-menu',
    order: 4,
    functionType: '1',
    name: 'MultipleMenu',
    path: 'multi-menu',
    children: [
      {
        id: 'a-1',
        title: 'a-1',
        name: 'a-1',
        path: 'multiple-menu',
        icon: 'ic:baseline-menu',
        children: [
          {
            id: 'a-1-1',
            title: 'a-1-1',
            name: 'a-1-1',
            path: 'a-1-1',
            icon: 'ic:baseline-menu',
          },
          {
            id: 'a-1-2',
            title: 'a-1-2',
            name: 'a-1-2',
            path: 'a-1-2',
            icon: 'ic:baseline-menu',
          },
        ],
      },
      {
        id: '1-2',
        title: '1-2',
        name: '1-2',
        path: '1-2',
        icon: 'ic:baseline-menu',
        children: [
          {
            id: '1-2-1',
            title: 'a-2-1（单个子菜单）',
            name: 'a-2-1',
            path: 'a-2-1',
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
    name: 'Demo',
    path: 'demo',
    order: 3,
    functionType: '1',
    children: [
      {
        id: '2-1',
        title: 'CRUD表格',
        icon: 'uil:pagelines',
        name: 'Crud',
        path: 'crud',
        isCache: true,
      },
      {
        id: '2-2',
        title: 'MD编辑器',
        name: 'MDEditor',
        icon: 'ri:markdown-line',
        path: 'md-editor',
        isCache: true,
      },
      {
        id: '2-3',
        title: '富文本编辑器',
        name: 'RichTextEditor',
        path: 'rich-text',
        isCache: true,
        icon: 'ic:sharp-text-rotation-none',
      },
      {
        id: '2-4',
        title: '图片上传',
        name: 'Upload',
        path: 'upload',
        icon: 'mdi:upload',
        isCache: true,
      },
    ],
  },
  {
    id: '3',
    title: '基础功能',
    name: 'Test',
    path: 'base',
    redirect: '/base/index',
    icon: 'majesticons:compass-line',
    order: 1,
    functionType: '1',
    children: [
      {
        id: '3-1',
        title: '基础组件',
        path: 'index',
        name: 'BaseComponents',
        icon: 'material-symbols:auto-awesome-outline-rounded',
        buttuns: ['addBtn', 'editBtn', 'queryBtn'],
      },
      {
        id: '3-2',
        path: 'keep-alive',
        name: 'KeepAlive',
        title: 'KeepAlive',
        icon: 'material-symbols:auto-awesome-outline-rounded',
      },
    ],
  },
  {
    id: '4',
    title: '外部链接',
    icon: 'mdi:link-variant',
    name: 'ExternalLink',
    path: 'external-link',
    order: 4,
    functionType: '1',
    children: [
      {
        id: '4-1',
        title: '跳转链接',
        name: 'bing',
        icon: 'logos:vue',
        path: 'https://cn.bing.com/',
      },
      {
        id: '4-2',
        title: '内嵌VUE连接',
        name: 'LinkVueSrc',
        path: 'vue',
        isFrame: true,
        icon: 'logos:naiveui',
        extendpath: 'https://router.vuejs.org/zh/guide/essentials/navigation.html',
      },
      {
        id: '4-3',
        title: '内嵌NaiveUI连接',
        name: 'LinkNaiveUISrc',
        path: 'naaive-ui',
        isFrame: true,
        icon: 'mdi:vuejs',
        extendpath: 'https://router.vuejs.org/zh/guide/essentials/navigation.html',
      },
    ],
  },
]

const users = {
  admin: {
    id: 1,
    name: 'admin',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    email: 'Ronnie@123.com',
    role: ['admin'],
    menus: functions,
  },
  editor: {
    id: 2,
    name: 'editor',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    email: 'Ronnie@123.com',
    role: ['editor'],
    menus: functions,
  },
  guest: {
    id: 3,
    name: 'guest',
    avatar: 'https://assets.qszone.com/images/avatar.jpg',
    role: [],
    menus: functions,
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
