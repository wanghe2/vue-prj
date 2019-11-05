<template>
    <div>
        <h1>角色列表</h1>
        <div v-for="item in list" :key="item.code" >
            <h2>{{item}}</h2>
        </div>    
    </div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
    name:'rolelist',
    data(){
        return {
            list:['']
        }
    },
    methods:{
        ...mapActions([
            'getRoleList'
        ])
    },
    mounted(){
       this.getRoleList().then(
           resp=>{
               this.list=resp.data
           }
       ).catch(resp => {
          console.log('请求失败：'+resp.status+','+resp.statusText);
      }); 
    }
}
</script>
<style scoped>
h1{
    margin-bottom: 20px;
}
</style>
