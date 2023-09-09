import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

export default defineConfig(({command, mode}) => {

  const env = loadEnv(mode, process.cwd());

  return {
    base: env.VITE_PUBLIC_PATH,
    resolve: {
      //设置别名
      alias: {
        '@': path.resolve(__dirname, 'src'),
        "@assets": path.resolve(__dirname, "src/assets"),
        "@store": path.resolve(__dirname, "src/store"),
        "@components": path.resolve(__dirname, "src/components")
      }
    },
    plugins: [
      vue(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      })],
    server: {
      https: false,
      open: false,
      host: '0.0.0.0',
      port: Number(env.VITE_PORT), //启动端口
      hmr: {
        host: '127.0.0.1',
        port: 8080
      },
      // 设置 https 代理
      proxy: {
        '/api': {
          target: '',
          changeOrigin: true,
          secure: false, // 如果是https接口，需要配置这个参数
          rewrite: (path) => path.replace(/^\/api/, '')
        }
      }
    },
    build: {
      target: 'es2015',
      cssTarget: 'chrome80',
      outDir: "dist",
      minify: 'terser', // 混淆器，terser 构建后文件体积更小，'terser' | 'esbuild'
      reportCompressedSize: false,
      chunkSizeWarningLimit: 2000, //chunk 大小警告的限制，默认500KB
      terserOptions: {
        // 生产环境移除console
        compress: {
          drop_console: env.VITE_DROP_CONSOLE,
          drop_debugger: env.VITE_DROP_CONSOLE,
        },
        // 10月更新
        output: {
           comments: true, // 去掉注释内容
        },
      }
    }
  };
});
