/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:27:57 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-27 05:01:53
 */
import Vue from "vue";
import VueRouter from "vue-router";
import { Promise } from 'core-js'
import axios from "axios";
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
import { Message } from 'element-ui'

axios.defaults.withCredentials = true
// 请求超时设置 5秒
const request = axios.create({
    baseURL:'http://localhost:5000/api/',                //后端url和端口指定
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
});

// 设置请求次数，请求的间隙
request.defaults.retry = 4;
request.defaults.retryDelay = 1000;


// 拦截器：将Token放入header
request.interceptors.request.use(config=>{
    const token = localStorage.getItem('token')
    if(token){
        config.headers["authorization"] = token;
    }
    return config;
},function(err){
    return Promise.reject(err)
})

// 返回response取得其中的data
request.interceptors.response.use(
    response => {
        return response.data
  },
  error => {
    if (error.response) {
      // console.log('err' + error) // for debug
      switch (error.response.status) {
        case 401:
          // console.log('err status' + error.response.status)
          //router.push('/login')
          Message.error("Token验证失效,请重新登录")
          break
        case 404:
          Message.error("未找到该网页")
          break
        case 500:
          break
      }
      return Promise.reject(error)
    } else {
      // 处理超时的情况
      let config = error.config
      // If config does not exist or the retry option is not set, reject
      if(!config || !config.retry) return Promise.reject(error)
  
      // Set the variable for keeping track of the retry count
      config.__retryCount = config.__retryCount || 0
  
      // Check if we've maxed out the total number of retries
      if(config.__retryCount >= config.retry) {
        // Reject with the error
        return Promise.reject(error)
      }
  
      // Increase the retry count
      config.__retryCount += 1
  
      // Create new promise to handle exponential backoff
      let backoff = new Promise(function(resolve) {
        setTimeout(function() {
          resolve()
        }, config.retryDelay || 1)
      })
  
      // Return the promise in which recalls axios to retry the request
      return backoff.then(function() {
        return request(config)
      })
    }
 
  })


export default request;