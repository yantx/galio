import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import store from './store'
import router from './router'

//创建Vue实例
const app = createApp(App)

// 挂载pinia
app.use(store)
// 挂载路由
app.use(router)

// 挂载实例
app.mount('#app');