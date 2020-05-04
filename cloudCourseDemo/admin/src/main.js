import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios;
Vue.prototype.$api_url = 'http://127.0.0.1:9000/';
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
