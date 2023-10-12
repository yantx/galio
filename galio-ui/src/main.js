import '@/styles/index.scss'
import 'uno.css'

import { createApp } from 'vue'
import { setupRouter } from '@/router'
import { setupStore } from '@/store'
import App from './App.vue'
import { setupNaiveDiscreteApi } from './utils'

async function setupApp() {
  const app = createApp(App)

  setupStore(app)
  setupNaiveDiscreteApi()

  await setupRouter(app)

  app.mount('#app')
}

setupApp()
