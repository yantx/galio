<template>
  <n-config-provider :locale="zhCN" :theme="getDarkTheme" :theme-overrides="getThemeOverrides" :date-locale="dateZhCN">
    <AppProvider>
      <router-view v-slot="{ Component }">
        <component :is="Component" />
      </router-view>
    </AppProvider>
  </n-config-provider>
</template>

<script setup>
import { zhCN, dateZhCN, darkTheme } from 'naive-ui'
import AppProvider from '@/components/application/index.vue'
</script>

<style lang="scss">
#app {
  height: 100%;
  .n-config-provider {
    height: inherit;
  }
}
</style>
