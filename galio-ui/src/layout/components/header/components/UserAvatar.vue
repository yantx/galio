<template>
  <n-dropdown :options="options" @select="handleSelect">
    <div flex cursor-pointer items-center>
      <g-avatar round size="medium" :src-val="memberStore.avatar" />
      <span>{{ memberStore.name }}</span>
    </div>
  </n-dropdown>
</template>

<script setup>
import { useMemberStore } from '@/store'
import { renderIcon } from '@/utils'

const memberStore = useMemberStore()

const options = [
  {
    label: '退出登录',
    key: 'logout',
    icon: renderIcon('mdi:exit-to-app', { size: '14px' }),
  },
]
const getImage = (name) => {
  // 其实就是将图片导为模块
  // 获取图片模块
  const picModules = import.meta.glob('@/assets/avatar/*', { eager: true })
  // 获取指定的图片
  const path = `/src/assets/avatar/${name}.jpg`
  return picModules[path].default
}

function handleSelect(key) {
  if (key === 'logout') {
    $dialog.confirm({
      title: '提示',
      type: 'info',
      content: '确认退出？',
      confirm() {
        memberStore.logout()
        $message.success('已退出登录')
      },
    })
  }
}
</script>
