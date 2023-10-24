<template>
  <transition name="fade" mode="out-in">
    <div style="height: 100%; width: 100%">
      <iframe frameborder="none" scrolling="auto" :src="defaultUrl" />
    </div>
  </transition>
</template>
<script setup>
const props = defineProps({
  // 动态的iframe，需传入menuId，从menu列表中获取menu路径
  frameName: {
    type: String,
    default: '',
  },
  // 固定地址，可以直接传入
  defaultUrl: {
    type: String,
    default: '',
  },
  isShow: {
    type: Boolean,
    default: true,
  },
})
</script>
