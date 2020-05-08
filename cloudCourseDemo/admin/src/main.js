import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios;
Vue.prototype.$api_url = 'http://127.0.0.1:9000/';
Vue.prototype.$isDeBug = true;

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;
/**
 *  axios拦截器
 */
axios.interceptors.request.use((config)=>{
  console.log("请求：",config);
  let token = Tool.getLoginUser().token;
  if(Tool.isNotEmpty(token)) {
    config.headers.token = token;
    console.log("请求headers增加token:", token);
  }
  return config;
},error=>{})
axios.interceptors.response.use((response)=>{
  console.log("返回结果：",response);
  return response;
},error=>{});

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if(to.matched.some(function(item){
    return item.meta.loginRequire
  })) {
    let loginUser = Tool.getLoginUser();
    if(Tool.isEmpty(loginUser)) {
      next("/login");
    } else {
      next();
    }
  } else {
    next();
  }
});

/**
 * vue启动
 */
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

console.log("环境：",process.env.NODE_ENV);