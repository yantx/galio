const view = {
  Crud: () => import('@/modules/demo/view/crud/index.vue'),
  MDEditor: () => import('@/modules/demo/view/editor/md-editor.vue'),
  RichTextEditor: () => import('@/modules/demo/view/editor/rich-text.vue'),
  Upload: () => import('@/modules/demo/view/upload/index.vue'),
}

export default {
  view,
}
