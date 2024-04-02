<template>
  <n-select v-model:value="localValue" :options="dictItems" @update:value="handleCheckedChange" />
</template>

<script setup>
import { useDictStore } from '@/store'
import { ref } from 'vue'

const props = defineProps({
  selectValue: {
    type: String,
    default: '',
  },
  /**
   * @remote 字典Key
   */
  dictKey: {
    type: String,
    default: '',
  },
})

const emit = defineEmits(['onChange', 'onDataChange'])

const localValue = ref(props.selectValue)
function handleCheckedChange(value) {
  emit('onChange', value)
  this.localValue.value = value
}

const dictStore = useDictStore()
const dictItems = dictStore.getDictItems(props.dictKey)
</script>
