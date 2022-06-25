// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
const proxy = require('http-proxy-middleware');
module.exports = {
  configureWebpack: {
    devtool: 'source-map'
  },
  devServer: {
    port: 8080,                                   // 前端端口号8080
    proxy: {
      "/api": {                                // proxy websockets
        changeOrigin: true,                       // 是否跨域
        target: "http://localhost:5000/",
      },
      "/upload": {
        target: "http://localhost:5000",
      }
    }
  }

}