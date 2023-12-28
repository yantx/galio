export const views = {
  NotFound: () => import('@/components/not-found/index.vue'),
  LOGIN: () => import('@/modules/login/view/index.vue'),
  Workbench: () => import('@/modules/workbench/view/index.vue'),
  'a-1': () => import('@/modules/multi-menu/view/a-1/index.vue'),
  'a-1-1': () => import('@/modules/multi-menu/view/a-1/a-1-1/index.vue'),
  'a-1-2': () => import('@/modules/multi-menu/view/a-1/a-1-2/index.vue'),
  '1-2': () => import('@/modules/multi-menu/view/a-2/index.vue'),
  'a-2-1': () => import('@/modules/multi-menu/view/a-2/a-2-1/index.vue'),
  Crud: () => import('@/modules/demo/view/table/index.vue'),
  MDEditor: () => import('@/modules/demo/view/editor/md-editor.vue'),
  RichTextEditor: () => import('@/modules/demo/view/editor/rich-text.vue'),
  Upload: () => import('@/modules/demo/view/upload/index.vue'),
  BaseComponents: () => import('@/modules/base/view/button/index.vue'),
  KeepAlive: () => import('@/modules/base/view/keep-alive/index.vue'),
  CodeGenerator: () => import('@/modules/system-tool/view/gen/list.vue'),
}
