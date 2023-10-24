<template>
  <n-dropdown :options="options" @select="handleSelect">
    <div flex cursor-pointer items-center>
      <img :src="memberStore.avatar" mr10 h-35 w-35 rounded-full />
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
