<template>
    <div id="mainpage" class='user-manage'>
        <div class="head" style="text-align: right;}">
            用户名：{{loginPerson}}    
            <button @click="logout" id='logout'>退出</button>
        </div>
        <div>
            <div class="left">
                <h2 @click="userlist" >用户</h2>
                <h2 @click="rolelist">角色</h2>
            </div>
            <div class="right">
               <div style="width:100%;"><tagsview ></tagsview></div>  
                <div><router-view></router-view></div> 
            </div>
        </div>
    </div>
</template>
<script>
import axios from '@/common/axios'
import useradd from './UserAdd'
import useredit from './UserEdit'
import confirm from './confirm'
import tagsview from './TagsView'
export default {
    name:'mainpage'  ,
    components:{
        useradd:useradd,
        confirm:confirm,
        useredit:useredit,
        tagsview:tagsview
    },
    data(){
        return{
            loginPerson:''
        }
    },
    methods:{
        logout(){
            console.log('*******退出前：**********'+this.$store.state.userId)
            this.$store.commit('logout')
            console.log('*******退出后：**********'+this.$store.state.userId)
            console.log('*******再次打印：**********'+this.$store.state.userId)
            this.$router.go(0)
        },
        userlist(){
            this.$router.push({
                name:'userlist',
                params:{
                name:'用户'
                }
            });
        },
        rolelist(){
            this.$router.push({
                
                name:'rolelist',
                params:{
                    name:'角色'
                }
            });
        },
        addTags(){
          const route=this.$route;//获取地址栏路由
          this.$store.commit({
          type:'addTags',
          route
          })
        } 

    },
    watch:{
      $route(){
        this.addTags();
        console.log('------路由发生变化---------------')
        console.log('---路由数组--'+this.$store.state.visitedTags+'---');
      }//地址栏变化了就触发这个添加方法
    },
    mounted(){
       this.loginPerson= this.$store.state.userId 
    }  
}
</script>
<style  scoped>
#usertable .ivu-table td {

text-align: center;

}
.user-manage {
    width: 100%;
    height: auto;
  }
  .v-transfer-dom{
      margin-left: 200px
  }
  #logout{
    margin-right: 40px;
    margin-left: 30px;
  }

  .left{
      width: 20%;
      float: left;
      height: 800px;
      background-color: blanchedalmond;
  } 
  .right{
      width: 79%;
      float: right;
      height: 800px;
      background-color:lightpink;
  }

 .head{
      margin-bottom: 40px;
  } 

  #usertable{
      margin-top: 20px;
  }
  h2{
      color: blue;
  }

</style>
