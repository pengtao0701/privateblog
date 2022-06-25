// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
module.exports = {
  configureWebpack: {
    devtool: 'source-map'
  },
  chainWebpack: config=>{
    config.plugin('html')
    .tap(args=>{
      args[0].title='privateblog'
      return args
    })
  },
  devServer: {
    port: 8080,                                   // 前端端口号8080
    proxy: {
      "/api": {                                
        changeOrigin: true,                       // 是否跨域
        target: "http://localhost:5000/",         // 后端地址+端口号5000
      },
      "/upload": {
        target: "http://localhost:5000",
      }
    }
  }

}