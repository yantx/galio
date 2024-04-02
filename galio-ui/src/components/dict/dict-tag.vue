<template>
  <n-tag v-if="dictItemlabel" type="info">{ dictItemlabel }</n-tag>
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
const dictItemlabel = () => getDictLabel()

function getDictLabel() {
  try {
    useDictStore().getDictLabel({ dictKey: props.dictKey, dictItemValue: props.dictItemValue })
  } catch (error) {
    return ''
  }
}
</script>
