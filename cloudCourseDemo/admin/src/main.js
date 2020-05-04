import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios;
Vue.prototype.$api_url = 'http://127.0.0.1:9000/';
Vue.prototype.$isDeBug = true;

/**
 *  axios拦截器
 */
axios.interceptors.request.use((config)=>{
  console.log("请求：",config);
  return config;
},error=>{})
axios.interceptors.response.use((response)=>{
  console.log("返回结果：",response);
  return response;
},error=>{});

/**
 * vue启动
 */
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
