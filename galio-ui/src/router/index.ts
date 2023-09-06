import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/login',
    name: 'Login',
    meta: {
        title: '登录',
        keepAlive: true,
        requireAuth: false
    },
    component: () => import('@/pages/login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
});
export default router;