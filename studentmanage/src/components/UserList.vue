<template>
    <div id="mainpage" class='user-manage'>
        <Table id='usertable' align="center" height="360" border ref="selection" :columns="columns" :data="userList"></Table>
        <confirm ref="myConfirm"
            @userBehavior='delUser'
        ></confirm>
        
        <useradd ref='useradd'  @saveuser="saveUser"></useradd>
        <useredit ref='useredit' :is_show='editshow'  @saveuser="saveUserByEdit"
            @closeForm="closeEditForm"
        ></useredit>  
    </div>
</template>
<script>
import axios from '@/common/axios'
import useradd from './UserAdd'
import useredit from './UserEdit'
import confirm from './confirm'
export default {
    name:'userlist'  ,
    components:{
        useradd:useradd,
        confirm:confirm,
        useredit:useredit
    },
    data(){
        return{
            editshow:false,
            userList:[{}],
            columns:[{
                title: '用户',
                width: 150,
                align: 'center',
                key: 'name'
            },{
                title: '密码',
                width: 150,
                align: 'center',
                key: 'password'  
            },{
                title: '介绍',
                width: 150,
                align: 'center',
                key: 'introduce'
            },{
                title: '操作',
                key: 'action',
                width: 220,
                align: 'center',
                render:(h,params)=>{
                   return h('div',[
                       h('Button',{
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                this.handleUserAdd()
                            }
                        }    
                       },'增加')
                       ,h('Button',{
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                this.handleUserEdit(params.row)
                            }
                        }    
                       },'编辑'),
                       h('Button',{
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                this.handleUserDel(params.row.name)
                            }
                        }    
                       },'删除')
                   ])     
                }
            }]
        }
    },
    methods:{
        handleUserEdit(params){
           this.editshow=true
           this.$refs.useredit.showData(params)
        },
        saveUserByEdit(params){
            axios.getRequest({
                url:'/editUser',
                params:params
            }).then(resp=>{
                this.search()
            }).catch(resp=>{
                alert('请求失败')
            })
            this.editshow=false
        },
        closeEditForm(){
            this.editshow=false
        },
        handleUserDel(name){
            const config={
                titleText:'用户删除',
                data:name
            }
            this.$refs.myConfirm.show('确认要删除该用户吗？ ', config)
        },
        delUser(type, data){
            if(type==='clickConfirm'){
                axios.getRequest({
                    url:'/delUser',
                    params:{
                        username:data
                    }
                }).then(resp=>{
                    this.search()
                }).catch(resp => {
                    console.log('请求失败：'+resp.status+','+resp.statusText);
                });
            }
        },
        handleUserAdd(){
            this.$refs.useradd.open()
        },
        saveUser(params){
            axios.getRequest({
                url:'/addUser',
                params:params
            }).then(resp=>{
                this.search()
            }).catch(resp=>{
                alert('请求失败')
            })
        },
        search(){
            axios.getRequest({
                url:'/userList'
            }).then(resp=>{
                this.userList=resp.data
            }).catch(resp=>{
                alert('请求失败')
            })
        }

    },
    mounted(){
       this.search()
    }  
}
</script>
<style scoped>


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


  #usertable{
      margin-top: 20px;
  }

</style>
