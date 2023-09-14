import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'


const modules : Record<string, Array<RouteRecordRaw>> = import.meta.glob('@modules/**/router/index.ts', {eager: true});

const routeModuleList: RouteRecordRaw[] = [];

Object.keys(modules).forEach((key) => {
  const mod = modules[key]?.default || {};
  const modList = Array.isArray(mod) ? [...mod] : [mod];
  routeModuleList.push(...modList);
});

console.log(routeModuleList)
console.log(JSON.stringify(routeModuleList))
const router = createRouter({
  history: createWebHistory(),
  routes: routeModuleList,
})
export { router }

