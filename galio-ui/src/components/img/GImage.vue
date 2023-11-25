<template>
  <img :src="getImage(srcVal)" :style="style" rounded-full />
</template>

<script lang="js">
export default {
  name: 'GImage',
}
</script>

<script lang="js" setup>
import { isUrl } from '@/utils/common/is'

const props = defineProps({
  style: {
    type: String,
    default: '',
  },
  srcVal: {
    type: String,
    default: '',
  },
})

const getImage = (srcVal) => {
  if (isUrl(srcVal)) {
    return srcVal
  }
  // 其实就是将图片导为模块
  // 获取图片模块
  const picModules = import.meta.glob('@/assets/image/*', { eager: true })
  // 获取指定的图片
  const path = `/src/assets/image/${srcVal}`
  return picModules[path].default
}
</script>
