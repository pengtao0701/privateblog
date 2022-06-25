/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:28:00 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-25 02:34:29
 */
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import api from './api';
import Baseurl from './api/baseurl'
import axios from 'axios'

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$baseUrl = Baseurl
axios.defaults.baseURL = Baseurl.baseurl
axios.defaults.withCredentials = true

new Vue({
  
  router,
  render: (h) => h(App),

}).$mount('#app')
