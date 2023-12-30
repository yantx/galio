<template>
  <n-affix :bottom="60" :right="30" :trigger-bottom="60">
    <n-button circle @click="isDrawer = !isDrawer">
      <template #icon>
        <icon-fluent:magic-wand-16-regular />
      </template>
    </n-button>
  </n-affix>
  <n-drawer v-model:show="isDrawer" :width="width" :placement="placement">
    <n-drawer-content :title="title" :native-scrollbar="false">
      <div class="drawer">
        <n-divider title-placement="center">主题</n-divider>

        <div class="justify-center drawer-setting-item dark-switch">
          <n-switch v-model:value="appStore.isDark" @update:value="toggleDark">
            <template #checked>
              <icon-mdi-white-balance-sunny color="#ffd93b" />
            </template>
            <template #unchecked>
              <icon-mdi-moon-waning-crescent color="#ffd93b" />
            </template>
          </n-switch>
        </div>

        <n-divider title-placement="center">系统主题</n-divider>
        <n-grid :x-gap="20" :y-gap="8" :cols="4">
          <n-grid-item span="2">Primary Color</n-grid-item>
          <n-grid-item>
            <n-color-picker
              v-model:value="appStore.themeConfig.primary"
              style="width: 100px"
              :show-preview="true"
              :actions="['clear']"
              :swatches="swatches"
            />
          </n-grid-item>
        </n-grid>
        <n-grid :x-gap="20" :y-gap="8" :cols="4">
          <n-grid-item span="2">Info Color</n-grid-item>
          <n-grid-item>
            <n-color-picker
              v-model:value="appStore.themeConfig.info"
              style="width: 100px"
              :show-preview="true"
              :actions="['clear']"
              :swatches="swatches"
            />
          </n-grid-item>
        </n-grid>
        <n-grid :x-gap="20" :y-gap="8" :cols="4">
          <n-grid-item span="2">Success Color</n-grid-item>
          <n-grid-item>
            <n-color-picker
              v-model:value="appStore.themeConfig.success"
              style="width: 100px"
              :show-preview="true"
              :actions="['clear']"
              :swatches="swatches"
            />
          </n-grid-item>
        </n-grid>
        <n-grid :x-gap="20" :y-gap="8" :cols="4">
          <n-grid-item span="2">Warning Color</n-grid-item>
          <n-grid-item>
            <n-color-picker
              v-model:value="appStore.themeConfig.warning"
              style="width: 100px"
              :show-preview="true"
              :actions="['clear']"
              :swatches="swatches"
            />
          </n-grid-item>
        </n-grid>
        <n-grid :x-gap="20" :y-gap="8" :cols="4">
          <n-grid-item span="2">Error Color</n-grid-item>
          <n-grid-item>
            <n-color-picker
              v-model:value="appStore.themeConfig.error"
              style="width: 100px"
              :show-preview="true"
              :actions="['clear']"
              :swatches="swatches"
            />
          </n-grid-item>
        </n-grid>

        <n-divider title-placement="center">导航栏模式</n-divider>

        <div class="drawer-setting-item align-items-top">
          <div class="drawer-setting-item-style align-items-top">
            <n-tooltip placement="top">
              <template #trigger>
                <img src="~@/assets/images/menu-vertical.svg" alt="左侧菜单模式" @click="togNavMode('vertical')" />
              </template>
              <span>左侧菜单模式</span>
            </n-tooltip>
            <n-badge v-show="appStore.navMode === 'vertical'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img src="~@/assets/images/menu-horizontal.svg" alt="顶部菜单模式" @click="togNavMode('horizontal')" />
              </template>
              <span>顶部菜单模式</span>
            </n-tooltip>
            <n-badge v-show="appStore.navMode === 'horizontal'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img src="~@/assets/images/menu-mix.svg" alt="菜单混合模式" @click="togNavMode('mix')" />
              </template>
              <span>菜单混合模式</span>
            </n-tooltip>
            <n-badge v-show="appStore.navMode === 'mix'" dot color="#19be6b" />
          </div>
        </div>

        <!-- <n-divider title-placement="center">导航栏风格</n-divider> -->
        <!-- <n-divider title-placement="center">界面功能</n-divider> -->

        <n-divider title-placement="center">界面显示</n-divider>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">仅显示内容页面</div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="appStore.onlyMainPageVisible" @update:value="toggleOnlyMainPageVicible" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <n-alert type="warning" :show-icon="false">
            <p>{{ alertText }}</p>
          </n-alert>
        </div>
      </div>
    </n-drawer-content>
  </n-drawer>
</template>

<script lang="js">
import { defineComponent, reactive, toRefs, unref, watch, computed } from 'vue'
import themeSetting from '@/settings/themeSetting'
import { useAppStore } from '@/store'
import { useDark, useToggle } from '@vueuse/core'

export default defineComponent({
  name: 'ThemeSetting',
  props: {
    title: {
      type: String,
      default: '项目配置',
    },
    width: {
      type: Number,
      default: 280,
    },
  },
  setup(props) {
    const appStore = useAppStore()
    const isDark = useDark()
    const swatches = themeSetting.swatches
    const state = reactive({
      width: props.width,
      title: props.title,
      isDrawer: false,
      placement: 'right',
      alertText:
        '该功能主要实时预览各种布局效果，更多完整配置在 themeSetting.js 中设置，建议在生产环境关闭该布局预览功能。',
      // appThemeList: designStore.appThemeList,
    })

    const toggleDark = () => {
      appStore.toggleDark()
      useToggle(isDark)()
    }

    const toggleOnlyMainPageVicible = (value) => {
      appStore.setOnlyMainPageVisible(value)
    }

    function openDrawer() {
      state.isDrawer = true
    }

    function closeDrawer() {
      state.isDrawer = false
    }

    function togNavMode(mode) {
      appStore.navMode = mode
    }

    return {
      ...toRefs(state),
      appStore,
      toggleDark,
      togNavMode,
      openDrawer,
      closeDrawer,
      toggleOnlyMainPageVicible,
    }
  },
})
</script>

<style lang="scss" scoped>
.drawer {
  .n-divider:not(.n-divider--vertical) {
    margin: 10px 0;
  }

  &-setting-item {
    display: flex;
    align-items: center;
    padding: 12px 0;
    flex-wrap: wrap;

    &-style {
      display: inline-block;
      position: relative;
      margin-right: 16px;
      cursor: pointer;
      text-align: center;
    }

    &-title {
      flex: 1 1;
      font-size: 14px;
    }

    &-action {
      flex: 0 0 auto;
    }

    &-select {
      flex: 1;
    }

    .theme-item {
      width: 20px;
      min-width: 20px;
      height: 20px;
      cursor: pointer;
      border: 1px solid #eee;
      border-radius: 2px;
      margin: 0 5px 5px 0;
      text-align: center;
      line-height: 14px;

      .n-icon {
        color: #fff;
      }
    }
  }

  .align-items-top {
    align-items: flex-start;
    padding: 2px 0;
  }

  .justify-center {
    justify-content: center;
  }

  .dark-switch .n-switch {
    ::v-deep(.n-switch__rail) {
      background-color: #000e1c;
    }
  }
}
</style>
