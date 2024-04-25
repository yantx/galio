<template>
  <n-select
    v-model:value="localValue"
    :multiple="multiple"
    :filterable="filterable"
    :clearable="clearable"
    :options="dictItems"
    :size="size"
    :placeholder="placeholder"
    @update:value="handleCheckedChange"
  />
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
  multiple: {
    type: Boolean,
    default: false,
  },
  filterable: {
    type: Boolean,
    default: false,
  },
  clearable: {
    type: Boolean,
    default: true,
  },
  size: {
    type: String,
    default: null, //tiny | small | medium | large
  },
  placeholder: {
    type: String,
    default: '请选择',
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
