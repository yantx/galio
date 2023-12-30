<template>
  <n-layout has-sider wh-full>
    <n-layout-sider
      v-if="appStore.navMode !== 'horizontal' && appStore.siderVisible"
      bordered
      collapse-mode="width"
      :collapsed-width="sider.collapsedWidth"
      :width="sider.width"
      :native-scrollbar="false"
      :collapsed="appStore.collapsed"
    >
      <SideBar />
    </n-layout-sider>

    <article flex-col flex-1 overflow-hidden>
      <header
        v-if="appStore.headerVisible"
        border-b="1 solid #eee"
        class="flex items-center bg-white px-15"
        dark="bg-dark border-0"
        :style="`height: ${header.height}px`"
      >
        <AppHeader :menu-mode="appStore.navMode" />
      </header>
      <section v-if="appStore.tagsVisible" hidden border-b bc-eee sm:block dark:border-0>
        <AppTags :style="{ height: `${tags.height}px` }" />
      </section>
      <section flex-1 overflow-hidden bg-hex-f5f6fb dark:bg-hex-101014>
        <AppMain />
      </section>
    </article>
    <ThemeAffix></ThemeAffix>
  </n-layout>
</template>

<script setup>
import AppHeader from './components/header/index.vue'
import SideBar from './components/sidebar/index.vue'
import AppMain from './components/AppMain.vue'
import AppTags from './components/tags/index.vue'
import ThemeAffix from './components/themeAffix/index.vue'
import { useAppStore } from '@/store'
import themeSetting from '@/settings/themeSetting'

const appStore = useAppStore()

const { header, tags, sider } = themeSetting
</script>
