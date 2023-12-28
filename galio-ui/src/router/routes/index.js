const Layout = () => import('@/layout/index.vue')

export const basicRoutes = [
  {
    name: 'NotFound',
    path: '/404',
    component: () => import('@/components/not-found/index.vue'),
    isHidden: true,
  },

  {
    name: 'LOGIN',
    path: '/login',
    component: () => import('@/modules/login/view/index.vue'),
    isHidden: true,
    meta: {
      title: '登录页',
    },
  },
]

export const NOT_FOUND_ROUTE = {
  name: 'NotFound',
  path: '/:pathMatch(.*)*',
  redirect: '/404',
  isHidden: true,
}

export const EMPTY_ROUTE = {
  name: 'Empty',
  path: '/:pathMatch(.*)*',
  component: null,
}
// modules文件夹下的路由都会作为动态路由
const modules = import.meta.glob('@/modules/**/route/index.js', { eager: true })
const asyncRoutes = []
Object.keys(modules).forEach((key) => {
  asyncRoutes.push(modules[key].default)
})

asyncRoutes.push({
  name: 'ExternalLink',
  path: '/external-link',
  component: Layout,
  meta: {
    title: '外部链接',
    icon: 'mdi:link-variant',
    order: 4,
  },
  children: [
    {
      name: 'LinkVueSrc',
      path: 'vue',
      component: () => import('@/components/iframe/index.vue'),
      meta: {
        title: 'Vue',
        icon: 'mdi:github',
        externalUrl: 'https://cn.vuejs.org/guide/introduction.html#what-is-vue',
      },
    },
    {
      name: 'LinkNaiveUISrc',
      path: 'naive-ui',
      component: () => import('@/components/iframe/index.vue'),
      meta: {
        title: 'Naive UI',
        icon: 'simple-icons:gitee',
        externalUrl: 'https://www.naiveui.com/zh-CN/',
      },
    },
    {
      name: 'LinkBingSrc',
      path: 'https://cn.bing.com/',
      meta: {
        title: '文档 - vuepress',
        icon: 'mdi:vuejs',
      },
    },
  ],
})
export { asyncRoutes }
