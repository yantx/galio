<template>
  <n-avatar :round="round" :size="size" :src="getImage(srcVal)" :fallback-src="fallbackSrc" />
</template>

<script lang="js">
export default {
  name: 'GAvatar',
}
</script>

<script lang="js" setup>
import { isUrl } from '@/utils/common/is'

const props = defineProps({
  size: {
    type: String,
    default: 'large', // 'small' | 'medium' | 'large' | number
  },
  fallbackSrc: {
    type: String,
    default: 'https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg',
  },
  round: {
    type: Boolean,
    default: false,
  },
  srcVal: {
    type: String,
    default: '640.jpg',
  },
})

const getImage = (srcVal) => {
  if (isUrl(srcVal)) {
    return srcVal
  }
  // 其实就是将图片导为模块
  // 获取图片模块
  const picModules = import.meta.glob('@/assets/avatar/*', { eager: true })
  // 获取指定的图片
  const path = `/src/assets/avatar/${srcVal}`
  return picModules[path].default
}
</script>
