const loadModuleView = async (moduleName) => {
  const modulePath = `${moduleName}/index.js`
  const moduleView = await import(/* @vite-ignore */ modulePath)
  return moduleView
}

const loadModuleViews = async () => {
  const files = await import.meta.glob('@/modules/*/index.js')
  const moduleViews = {}

  for (const file in files) {
    const moduleName = file.replace('@/modules/', '').replace('/index.js', '')
    const moduleView = await loadModuleView(moduleName)
    Object.assign(moduleViews, moduleView.default.view)
  }
  return moduleViews
}



export const views = {
  NotFound: () => import('@/components/not-found/index.vue'),
  ...(await loadModuleViews()),
}
