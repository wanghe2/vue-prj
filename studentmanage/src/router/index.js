import Vue from 'vue'
import Vuex from 'vuex'

import Router from 'vue-router'
import Login from '@/components/Login'
import Main from '@/components/main'
import iView from 'iview'
import Userlist from '@/components/UserList'
import Rolelist from '@/components/Rolelist'
import Homepage from '@/components/Homepage'
import Moretable from '@/components/MoreTable'
import store from '../store'
Vue.use(Router)
const router=new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/main',
      name: 'main',
      redirect:'/homepage',
      component: Main,
      meta:{
        notCache:true, 
      },
      children:[{
          path: '/homepage',
          name: 'homepage',
          component: Homepage
      }]

    },{
      path:'user',
      name:'user',
      component:Main,
      meta:{
        notCache:true
      },
      children:[{
        path: '/userlist',
        name: 'userlist',
        component: Userlist
      }]
    },{
      path:'/moretable',
      name:'moretable',
      component:Moretable,
    },
    {
      path:'role',
      name:'role',
      component:Main,
      meta:[{
        notCache:true,
      }],
      children:[{
        path: '/rolelist',
        name: 'rolelist',
        component: Rolelist
      }]
    }
  ]
})

router.beforeEach((to,from,next)=>{
    iView.LoadingBar.start()
    console.log ('++++++++'+store.state.userId);
    const userId=store.state.userId
    const LOGIN_PAGE_NAME = 'login'
    if(userId=='null'&&to.name!=LOGIN_PAGE_NAME){
      next({
        name:LOGIN_PAGE_NAME
      })
    }else if(userId!='null'&&to.name==LOGIN_PAGE_NAME){
      next({
        name:'main'
      })
    }else{
      next()
    }
})
router.afterEach(to => {
  iView.LoadingBar.finish()
  window.scrollTo(0, 0)
})
export default router
