import { defineConfig, loadEnv } from 'vite'
import * as path from 'path'
import { wrapperEnv } from './build/utils'
import serverProxy from './server.proxy'
import { createVitePlugins } from './build/plugin'

export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd())
  const viteEnv = wrapperEnv(env)
  const isBuild = command === 'build'
  // 这样就可以拿到定义好的环境变量了，也可以使用process.env.xxx这种方式进行访问
  const { VITE_PORT, VITE_PUBLIC_PATH } = viteEnv

  return {
    plugins: [createVitePlugins(viteEnv, isBuild)],
    base: VITE_PUBLIC_PATH || '/',
    resolve: {
      //设置别名
      alias: {
        '~': path.resolve(__dirname),
        '@': path.resolve(__dirname, 'src'),
        '@modules': path.resolve(__dirname, 'src/modules'),
        '@assets': path.resolve(__dirname, 'src/assets'),
        '@store': path.resolve(__dirname, 'src/store'),
        '@components': path.resolve(__dirname, 'src/components'),
      },
    },
    server: {
      https: false,
      open: false,
      host: '0.0.0.0',
      port: env.VITE_PORT, //启动端口
      proxy: serverProxy,
    },
    build: {
      target: 'es2020',
      outDir: 'dist',
      reportCompressedSize: false, // 启用/禁用 gzip 压缩大小报告
      chunkSizeWarningLimit: 1024, // chunk 大小警告的限制（单位kb）
    },
  }
})
