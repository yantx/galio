<template>
  <n-tag type="info">{{ dictLabel }}</n-tag>
</template>

<script setup>
import { useDictStore } from '@/store'

const props = defineProps({
  /**
   * @remote 字典Key
   */
  dictKey: {
    type: String,
    default: '',
  },
  /**
   * @remote 字典项值
   */
  dictItemValue: {
    type: String,
    default: '',
  },
})
const dictStore = useDictStore()
const dictLabel = dictStore.getDictLabel(props.dictKey, props.dictItemValue)
</script>
