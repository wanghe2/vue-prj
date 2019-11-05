import Vue from 'vue'
import Vuex from 'vuex'
import role from './module/role'
Vue.use(Vuex)
const state={
    userId:sessionStorage.getItem('userId')?sessionStorage.getItem('userId'):'null',
    visitedTags:[]
}

const mutations ={
    loginState(state,userId){
        sessionStorage.setItem('userId',userId)
        state.userId=sessionStorage.getItem('userId')
        return state.userId
    },
    logout(state){
        sessionStorage.setItem('userId','null')
        state.userId=sessionStorage.getItem('userId')
        return state.userId
    },
    addTags(state, payload) {
      let flag = state.visitedTags.some(
        item => item.path === payload.route.path
      );//打开标签后，判断数组中是否已经存在该路由
      if (!flag) {
        state.visitedTags.push(
          Object.assign(
            {},
            {
              path: payload.route.path,
              name: payload.route.name,
              params: payload.route.params
            }
          )
        );
      } //数组中路由存在不push ,单击左侧路由变化,点击标签路由变化均触发
    },

    closeTag(state, payload) {
      console.log(Array.from(state.visitedTags))
      const datalist=Array.from(state.visitedTags)
      for (var i=0;i<datalist.length;i++) {
        if (datalist[i].path === payload.view.path) {
          state.visitedTags.splice(i,1)
          break;
        }
      }
      return Array.from(state.visitedTags)
    }
}
const actions={
    closeTags({commit,rootState},payload){

      return new Promise((resolve, reject) => {
          Array.from(state.visitedTags)
          console.log('closetags#action')
          commit('closeTag', payload)
          rootState.visitedTags
          resolve(rootState.visitedTags)
        })
    }
}

export default new Vuex.Store({
    state,
    mutations,
    modules:{
        role
    },
    actions
})
