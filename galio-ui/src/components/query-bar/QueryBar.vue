<template>
  <n-form
    v-if="showQuery"
    ref="queryRef"
    min-h-60
    flex
    items-start
    justify-between
    b-1
    rounded-8
    dark:bg-black
    :model="queryItems"
    :size="formSize"
    :label-placement="labelPlacement"
    :label-width="labelWidth"
  >
    <slot name="formItems" />
  </n-form>
  <n-flex mt-1 important-justify-end>
    <n-button secondary type="primary" @click="emit('reset')">
      <TheIcon icon="mdi:refresh" :size="12" class="mr-5" />
      重置
    </n-button>
    <n-button ml-20 type="primary" @click="emit('search')">
      <TheIcon icon="mdi:magnify" :size="12" class="mr-5" />
      搜索
    </n-button>
  </n-flex>
</template>

<script setup>
import emitter from '@/utils/bus'
const emit = defineEmits(['search', 'reset'])

defineProps({
  showQuery: {
    type: Boolean,
    default: false,
  },
  formSize: {
    type: String,
    default: 'medium',
  },
  labelWidth: {
    type: Number,
    default: null,
  },
  labelPlacement: {
    type: String,
    default: 'left',
  },
  queryItems: {
    type: Object,
    default() {
      return {}
    },
  },
})
</script>
