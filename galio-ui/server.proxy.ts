export default {
    '/api': {
      target: '',
      changeOrigin: true,
      secure: false, // 如果是https接口，需要配置这个参数
      rewrite: (path) => path.replace(/^\/api/, '')
    }
  }