const Layout = () => import('@/layout/index.vue')

export default {
  name: 'SystemTool',
  path: '/tool',
  component: Layout,
  redirect: '/tool/index',
  meta: {
    title: '系统工具',
    icon: 'carbon:tool-kit',
    order: 1,
  },
  children: [
    {
      name: 'CodeGenerator',
      path: 'index',
      component: () => import('../view/gen/table.vue'),
      meta: {
        title: '代码生成器',
        icon: 'file-icons:graphql-codegenerator',
      },
    },
  ],
}
