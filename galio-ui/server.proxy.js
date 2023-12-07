export default {
  // 拦截以api开头的请求路径 转发到接口网关中
  '/api': {
    target: 'http://localhost:9000',
    changeOrigin: true,
    rewrite: (path) => path.replace(/^\/api/, ''),
  },
}
