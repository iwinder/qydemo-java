import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'
import Welcome from './views/admin/welcome.vue'
import Chapter from './views/admin/chapter.vue'
import Section from './views/admin/section.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',// hash 会变成 xxx.com/#/xxx
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: '/login',
    }, {
        path: '/login',
        component: Login
    }, {
        path: '/',
        name: 'admin', // 为每个路由增加name属性，后续做通用sidebar激活样式方法时需要用到，
        component: Admin,
        children: [{
            path: 'welcome',
            name: 'welcome',
            component: Welcome,
        },{
            path: 'business/chapter',
            name: 'business/chapter',
            component: Chapter,
        },{
            path: 'business/section',
            name: 'business/section',
            component: Section,
        }]
    }]
})