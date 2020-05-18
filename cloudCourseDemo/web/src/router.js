import Vue from 'vue'
import Router from 'vue-router'
import Index from "./views/index.vue"

Vue.use(Router);

export default new Router({
    mode: 'history',// hash 会变成 xxx.com/#/xxx
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: '/index',
    },{
        path: '/index',
        component: Index,
    } 
     ]
})