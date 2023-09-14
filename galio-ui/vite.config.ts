import { ConfigEnv, UserConfig, defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import serverProxy from './server.proxy'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { viteMockServe } from 'vite-plugin-mock';


export default (({ mode }: ConfigEnv): UserConfig => {

  const env = loadEnv(mode, process.cwd());

  return {
    base: env.VITE_PUBLIC_PATH,
    resolve: {
      //设置别名
      alias: {
        '@': path.resolve(__dirname, 'src'),
        '@modules': path.resolve(__dirname, 'src/modules'),
        "@assets": path.resolve(__dirname, "src/assets"),
        "@store": path.resolve(__dirname, "src/store"),
        "@components": path.resolve(__dirname, "src/components")
      }
    },
    plugins: [
      vue(),
      AutoImport({
        imports: ['vue', 'vue-router'],
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        // dirs 指定组件所在位置，默认为 src/components
				// 可以让我们使用自己定义组件的时候免去 import 的麻烦
				dirs: ['src/components/', 'src/layout'],
				// 配置需要将哪些后缀类型的文件进行自动按需引入，'vue'为默认值
				extensions: ['vue', 'ts'],
        resolvers: [ElementPlusResolver()],
      }),
      viteMockServe({
        ignore: /^\_/,
        mockPath: 'mock',
        localEnabled: env.VITE_USE_MOCK
      })
    ],
    server: {
      https: false,
      open: false,
      host: '0.0.0.0',
      port: Number(env.VITE_PORT), //启动端口
      proxy: serverProxy
    },
    build: {
      outDir: env.VITE_OUT_DIR,
      minify: 'terser', // 混淆器，terser 构建后文件体积更小，'terser' | 'esbuild'
      reportCompressedSize: false,
      chunkSizeWarningLimit: 2000, //chunk 大小警告的限制，默认500KB
      terserOptions: {
        // 生产环境移除console
        compress: {
          drop_console: Boolean(env.VITE_DROP_CONSOLE),
          drop_debugger: Boolean(env.VITE_DROP_CONSOLE)
        },
        output: {
           comments: true, // 去掉注释内容
        },
      }
      // rollupOptions: {
      //   //配置多页应用程序入口文件
      //   input: multiPages(filsteModules),
      //   //打包到目标目录
      //   output: multiBuild(filsteModules)
      // }
    }
  };
});
