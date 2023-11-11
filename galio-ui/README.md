# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VS Code](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).


# 按钮权限控制

  1. 使用v-if+引入函数判断方式

  ```vue
  <n-button v-if="hasBtnPermission('system.member.add')" type="tertiary">
  <script setup>
  import { hasBtnPermission } from '@/utils/auth'
  const route = useRoute()
  </script>
  ```

  2. 自定义指令方式

  ```vue
  <n-button v-permission="'system.member.add'" type="tertiary">
  ```