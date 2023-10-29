<template>
  <AppPage :show-footer="true" bg-cover :style="{ backgroundImage: `url(${bgImg})` }">
    <div
      style="transform: translateY(25px)"
      class="m-auto max-w-700 min-w-345 f-c-c rounded-10 bg-white bg-opacity-60 p-15 card-shadow"
      dark:bg-dark
    >
      <div hidden w-380 px-20 py-35 md:block>
        <img src="@/assets/images/login_banner.webp" w-full alt="login_banner" />
      </div>

      <div w-320 flex-col px-20 py-35>
        <h5 f-c-c text-24 font-normal color="#6a6a6a">
          <img src="@/assets/images/logo.png" height="50" class="mr-10" />
          {{ title }}
        </h5>
        <div mt-30>
          <n-input
            v-model:value="loginInfo.name"
            autofocus
            class="h-50 items-center pl-10 text-16"
            placeholder="admin"
            :maxlength="20"
          />
        </div>
        <div mt-30>
          <n-input
            v-model:value="loginInfo.password"
            class="h-50 items-center pl-10 text-16"
            type="password"
            show-password-on="mousedown"
            placeholder="123456"
            :maxlength="20"
            @keypress.enter="handleLogin"
          />
        </div>

        <div mt-20>
          <n-checkbox :checked="isRemember" label="记住我" :on-update:checked="(val) => (isRemember = val)" />
        </div>

        <div mt-20>
          <n-button h-50 w-full rounded-5 text-16 type="primary" :loading="loading" @click="handleLogin">
            登录
          </n-button>
        </div>
      </div>
    </div>
  </AppPage>
</template>

<script setup>
import { lStorage, setToken } from '@/utils'
import bgImg from '@/assets/images/login_bg.webp'
import { login } from '../api/auth'
import { initAuthRoute } from '@/router'
import { useStorage } from '@vueuse/core'

const title = import.meta.env.VITE_TITLE

const router = useRouter()
const { query } = useRoute()

const loginInfo = ref({
  name: '',
  password: '',
})

initLoginInfo()

function initLoginInfo() {
  const localLoginInfo = lStorage.get('loginInfo')
  if (localLoginInfo) {
    loginInfo.value.name = localLoginInfo.name || ''
    loginInfo.value.password = localLoginInfo.password || ''
  }
}

const isRemember = useStorage('isRemember', false)
const loading = ref(false)
async function handleLogin() {
  const { name, password } = loginInfo.value
  if (!name || !password) {
    $message.warning('请输入用户名和密码')
    return
  }
  try {
    loading.value = true
    $message.loading('正在验证...')
    const res = await login({ name, password: password.toString() })
    if (res.code === 20000) {
      $message.success('登录成功')
      // 设置用户信息缓存
      setToken(res.data.token)
      if (isRemember.value) {
        lStorage.set('loginInfo', { name, password })
      } else {
        lStorage.remove('loginInfo')
      }
      // 初始化路由
      await initAuthRoute()

      // 登录后重定向页面
      if (query.redirect) {
        const path = query.redirect
        Reflect.deleteProperty(query, 'redirect')
        router.push({ path, query })
      } else {
        router.push({ name: import.meta.env.VITE_ROUTE_HOME_NAME })
      }
    } else {
      loading.value = false
      $message.error(res.msg)
    }
  } catch (error) {
    console.error(error)
    $message.removeMessage()
  }
  loading.value = false
}
</script>
