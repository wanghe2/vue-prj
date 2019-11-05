import axios from '@/common/axios'

const state={

}

const actions = {
    getRoleList({commit}){
        return new Promise((resolve, reject) => {
            axios.getRequest({
                url:'/rolelist'
            }).then(res => {
                resolve(res)
            }).catch(error => {
                reject(error)
            })
        })

    }
}

const mutations = {
}

export default {
    state,
    mutations,
    actions
}