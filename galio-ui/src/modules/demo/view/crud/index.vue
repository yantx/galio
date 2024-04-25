<template>
  <AppPage show-footer>
    <n-space vertical size="large">
      <n-card>
        <!-- 查询条件 -->
        <QueryBar
          v-model:query-items="queryItems"
          show-query
          @search="$table?.handleSearch"
          @reset="$table?.handleReset"
        >
          <template #formItems>
            <n-grid :x-gap="30" :cols="4">
              <n-form-item-gi label="标题" path="title">
                <n-input
                  v-model:value="queryItems.title"
                  type="text"
                  placeholder="请输入标题"
                  @keypress.enter="$table?.handleSearch"
                />
              </n-form-item-gi>
            </n-grid>
          </template>
        </QueryBar>
      </n-card>
      <n-card>
        <!-- 操作按钮 -->
        <n-flex mb-10>
          <n-button type="primary" size="small" secondary @click="$table?.handleExport()">
            <TheIcon icon="mdi:download" :size="12" class="mr-5" />
            导出
          </n-button>
          <n-button v-permission="'demo.crud.add'" type="primary" size="small" @click="handleAdd">
            <TheIcon icon="material-symbols:add" :size="12" class="mr-5" />
            新增
          </n-button>
        </n-flex>
        <!-- 数据表格 -->
        <CrudTable
          ref="$table"
          v-model:query-items="queryItems"
          :extra-params="extraParams"
          :scroll-x="1200"
          :columns="columns"
          :get-data="api.getPosts"
          @on-checked="onChecked"
          @on-data-change="(data) => (tableData = data)"
        ></CrudTable>
        <!-- 新增/编辑/查看窗口 -->
        <CrudModal
          v-model:visible="modalVisible"
          :title="modalTitle"
          :loading="modalLoading"
          :show-footer="modalAction !== 'view'"
          @on-save="handleSave"
        >
          <n-form
            ref="modalFormRef"
            label-placement="left"
            label-align="left"
            :label-width="80"
            :model="modalForm"
            :disabled="modalAction === 'view'"
          >
            <n-form-item label="作者" path="author">
              <n-input v-model:value="modalForm.author" placeholder="请输入作者" />
            </n-form-item>
            <n-form-item
              label="文章标题"
              path="title"
              :rule="{
                required: true,
                message: '请输入文章标题',
                trigger: ['input', 'blur'],
              }"
            >
              <n-input v-model:value="modalForm.title" placeholder="请输入文章标题" />
            </n-form-item>
            <n-form-item
              label="文章内容"
              path="content"
              :rule="{
                required: true,
                message: '请输入文章内容',
                trigger: ['input', 'blur'],
              }"
            >
              <n-input
                v-model:value="modalForm.content"
                placeholder="请输入文章内容"
                type="textarea"
                :autosize="{
                  minRows: 3,
                  maxRows: 5,
                }"
              />
            </n-form-item>
          </n-form>
        </CrudModal>
      </n-card>
    </n-space>
  </AppPage>
</template>

<script setup>
import { NButton, NSwitch, NTag } from 'naive-ui'
import { formatDateTime, renderIcon, isNullOrUndef } from '@/utils'
import { useCRUD } from '@/composables'
import api from '../../api/crud'
import { onActivated, onMounted } from 'vue'
import { hasBtnPermission } from '@/utils/auth'

defineOptions({ name: 'CrudIndex1' })

const $table = ref(null)
/** 表格数据，触发搜索的时候会更新这个值 */
const tableData = ref([])
/** QueryBar筛选参数（可选） */
const queryItems = ref({})
/** 补充参数（可选） */
const extraParams = ref({})

onMounted(() => {
  $table.value?.handleSearch()
})
const sortStatesRef = ref([])
const sortKeyMapOrderRef = computed(() =>
  sortStatesRef.value.reduce((result, { columnKey, order }) => {
    result[columnKey] = order
    return result
  }, {}),
)
const columns = [
  { type: 'selection', fixed: 'left' },
  {
    title: '序号',
    key: 'id',
    width: 60,
    align: 'center',
    fixed: 'left',
    sortOrder: sortKeyMapOrderRef.value.id || false,
    sorter(rowA, rowB) {
      return rowA.id - rowB.id
    },
  },
  {
    title: '发布',
    key: 'isPublish',
    width: 60,
    align: 'center',
    fixed: 'left',
    render(row) {
      return h(NSwitch, {
        size: 'small',
        rubberBand: false,
        value: row['isPublish'],
        loading: !!row.publishing,
        onUpdateValue: () => handlePublish(row),
      })
    },
  },
  { title: '标题', key: 'title', width: 150, ellipsis: { tooltip: true } },
  {
    title: '分类',
    key: 'category',
    width: 80,
    render(row) {
      return h(
        NTag,
        {
          type: 'info',
          size: 'small',
        },
        { default: () => row.category },
      )
    },
  },
  { title: '创建人', key: 'author', width: 80 },
  {
    title: '创建时间',
    key: 'createDate',
    width: 150,
    render(row) {
      return h('span', formatDateTime(row['createDate']))
    },
  },
  {
    title: '最后更新时间',
    key: 'updateDate',
    width: 150,
    render(row) {
      return h('span', formatDateTime(row['updateDate']))
    },
  },
  {
    title: '操作',
    key: 'actions',
    width: 240,
    align: 'center',
    fixed: 'right',
    hideInExcel: true,
    render(row) {
      return [
        hasBtnPermission('demo.crud.view')
          ? h(
              NButton,
              {
                size: 'small',
                type: 'primary',
                secondary: true,
                onClick: () => handleView(row),
                directives: [{ name: 'permission', value: 'demo.crud.view' }],
              },
              { default: () => '查看', icon: renderIcon('majesticons:eye-line', { size: 14 }) },
            )
          : null,
        hasBtnPermission('demo.crud.edit')
          ? h(
              NButton,
              {
                size: 'small',
                type: 'primary',
                style: 'margin-left: 15px;',
                onClick: () => handleEdit(row),
                directives: [{ name: 'if', value: hasBtnPermission('demo.crud.edit') }],
              },
              { default: () => '编辑', icon: renderIcon('material-symbols:edit-outline', { size: 14 }) },
            )
          : null,
        hasBtnPermission('demo.crud.delete')
          ? h(
              NButton,
              {
                size: 'small',
                type: 'error',
                style: 'margin-left: 15px;',
                onClick: () => handleDelete(row.id),
              },
              {
                default: () => '删除',
                icon: renderIcon('material-symbols:delete-outline', { size: 14 }),
              },
            )
          : null,
      ]
    },
  },
]

// 选中事件
function onChecked(rowKeys) {
  if (rowKeys.length) $message.info(`选中${rowKeys.join(' ')}`)
}

// 发布
function handlePublish(row) {
  if (isNullOrUndef(row.id)) return

  row.publishing = true
  setTimeout(() => {
    row.isPublish = !row.isPublish
    row.publishing = false
    $message?.success(row.isPublish ? '已发布' : '已取消发布')
  }, 1000)
}

const {
  modalVisible,
  modalAction,
  modalTitle,
  modalLoading,
  handleAdd,
  handleDelete,
  handleEdit,
  handleView,
  handleSave,
  modalForm,
  modalFormRef,
} = useCRUD({
  name: '文章',
  initForm: { author: 'Galio' },
  doCreate: api.addPost,
  doDelete: api.deletePost,
  doUpdate: api.updatePost,
  refresh: () => $table.value?.handleSearch(),
})
</script>
