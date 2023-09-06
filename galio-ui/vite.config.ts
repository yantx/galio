import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path';
import {
  createStyleImportPlugin,
  ElementPlusResolve
} from 'vite-plugin-style-import'

export default defineConfig({
  resolve: {
      //设置别名
      alias: {
          '@': path.resolve(__dirname, 'src')
      }
  },
  plugins: [
    vue(),
    createStyleImportPlugin({
      resolves: [ElementPlusResolve()],
      libs: [
        {
          libraryName: 'element-plus',
          esModule: true,
          resolveStyle: (name: string) => {
            return `element-plus/theme-chalk/${name}.css`
          }
        }
      ]
    })],
  server: {
      port: 8080, //启动端口
      hmr: {
          host: '127.0.0.1',
          port: 8080
      },
      // 设置 https 代理
      proxy: {
          '/api': {
              target: 'your https address',
              changeOrigin: true,
              rewrite: (path) => path.replace(/^\/api/, '')
          }
      }
  }
});
