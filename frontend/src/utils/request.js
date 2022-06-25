/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:27:57 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-25 01:34:16
 */
import axios from "axios";
axios.defaults.withCredentials = true
// 请求超时设置 5秒
const request = axios.create({
    baseURL:'http://localhost:5000/api/',                //后端url和端口指定
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
      }
});

// 拦截器：将Token放入header
request.interceptors.request.use(config=>{
    const token = localStorage.getItem('token')
    if(token){
        config.headers["authorization"] = token;
    }
    return config;
})

// 返回response取得其中的data
request.interceptors.response.use(response => {
    return response.data
  })


export default request;