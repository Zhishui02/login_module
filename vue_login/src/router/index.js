import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
const router = new Router({
    routes:[
        {
            path:'/',
            redirect:'/login',
        },
        {
            name:'home',
            path:'/home',
            meta:{
                title : '主页'
            },
            component:() => import('@/views/HomePage.vue'),
        },
        {
            name:'ciallo',
            path:'/ciallo',
            meta:{
                title : 'Ciallo~（∠・ω- ）⌒ ★'
            },
            component:() => import('@/views/Ciaoll.vue'),
        },
        {
            name:'login',
            path:'/login',
            meta:{
                title : '登录'
            },
            component:() => import('@/views/LoginPage.vue'),
        },
        {
            name:'register',
            path:'/register',
            meta:{
                title : '注册'
            },
            component:() => import('@/views/RegisterPage.vue'),
        },
    ]
})

//前置路由守卫


//后置路由守卫
router.afterEach((to) => {
    //设置每一个组件的标签名
    document.title = to.meta.title || '三三'
})

export default router;