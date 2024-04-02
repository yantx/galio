<template>
  <n-select v-model:value="dictItemValue" :options="getDictItems()" @checked:change="handleCheckedChange" />
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
})
function handleCheckedChange(value) {
  if (value) {
    $emit('change', value)
  }
}
function getDictItems() {
  try {
    useDictStore().getDictItems(props.dictKey)
  } catch (error) {
    return ''
  }
}
</script>
