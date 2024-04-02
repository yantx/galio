<template>
  <n-form-item
    v-if="!isNullOrWhitespace(labelPlacement) && !isNullOrWhitespace(labelWidth)"
    :label="label"
    :path="path"
    :label-placement="labelPlacement"
    :label-width="labelWidth"
  >
    <div :style="{ width: contentWidth + 'px' }" flex-shrink-0>
      <slot />
    </div>
  </n-form-item>
  <n-form-item
    v-else-if="!isNullOrWhitespace(labelPlacement)"
    :label="label"
    :path="path"
    :label-placement="labelPlacement"
  >
    <div :style="{ width: contentWidth + 'px' }" flex-shrink-0>
      <slot />
    </div>
  </n-form-item>
  <n-form-item v-else-if="!isNullOrWhitespace(labelWidth)" :label="label" :path="path" :label-width="labelWidth">
    <div :style="{ width: contentWidth + 'px' }" flex-shrink-0>
      <slot />
    </div>
  </n-form-item>
  <n-form-item v-else :label="label" :path="path">
    <div :style="{ width: contentWidth + 'px' }" flex-shrink-0>
      <slot />
    </div>
  </n-form-item>
</template>

<script setup>
import { isNullOrWhitespace } from '@/utils'

defineProps({
  label: {
    type: String,
    default: '',
  },
  labelWidth: {
    type: Number,
    default: null,
  },
  labelPlacement: {
    type: String,
    default: null,
  },
  path: {
    type: String,
    default: '',
  },
  contentWidth: {
    type: Number,
    default: 220,
  },
})
</script>
