const view = {
  'a-1': () => import('@/modules/multi-menu/view/a-1/index.vue'),
  'a-1-1': () => import('@/modules/multi-menu/view/a-1/a-1-1/index.vue'),
  'a-1-2': () => import('@/modules/multi-menu/view/a-1/a-1-2/index.vue'),
  '1-2': () => import('@/modules/multi-menu/view/a-2/index.vue'),
  'a-2-1': () => import('@/modules/multi-menu/view/a-2/a-2-1/index.vue'),
}

export default {
  view,
}
