const Layout = () => import('@/layout/index.vue')

export default {
  name: 'Workbench',
  path: 'workbench',
  component: () => import('../view/index.vue'),
  meta: {
    title: '工作台',
    icon: 'mdi:home',
    order: 0,
  },
}
