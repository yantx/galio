<template>
  <div flex items-center>
    <TopLogo v-if="prop.menuMode === 'horizontal'" />
    <MenuCollapse v-if="prop.menuMode !== 'horizontal'" />
    <BreadCrumb v-if="prop.menuMode === 'vertical'" ml-15 hidden sm:block />
    <TopMenu v-if="prop.menuMode !== 'vertical'" :menu-mode="'horizontal'" />
  </div>
  <div ml-auto flex items-center>
    <MessageNotification />
    <ThemeSetting />
    <GithubSite />
    <FullScreen />
    <UserAvatar />
  </div>
</template>

<script setup>
import BreadCrumb from './components/BreadCrumb.vue'
import TopMenu from '../menu/index.vue'
import TopLogo from '../logo/index.vue'
import MenuCollapse from './components/MenuCollapse.vue'
import FullScreen from './components/FullScreen.vue'
import UserAvatar from './components/UserAvatar.vue'
import GithubSite from './components/GithubSite.vue'
import ThemeSetting from './components/ThemeSetting.vue'
import MessageNotification from './components/MessageNotification.vue'

const prop = defineProps({
  menuMode: {
    type: String,
    default: 'vertical',
  },
})
</script>
