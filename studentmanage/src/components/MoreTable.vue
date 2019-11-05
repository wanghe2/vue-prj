<template>
    

    <!-- 1、动态组件 -->
    <div class="tab_area">
        <button v-for="tab in tabs" v-on:click="currentTab = tab">{{tab}}</button>
        <component v-bind:is="currentTabComponent"></component>
    </div>
</template>

    <!-- 1、动态组件 -->
<script >
import Vue from 'vue'
import Rolelist from '@/components/Rolelist'
        /* 2级子级组件模板 */
        Vue.component('tab-one',{
            template:`
                    <div>
                    one展示信息
                    在component里面使用 v-bind: is,可以实现动态组件的效果.
                    </div>    
                    `
        })
        Vue.component('tab-two',{
            template:"<div>two展示信息</div>"
        })
        Vue.component('tab-three',{
            template:"<div>three展示信息</div>"
        })
        Vue.component('tab-four',{
            template:"<div>four展示信息</div>"
        })
        Vue.component('tab-five',{
            template:"<div>five展示信息</div>"
        })
        Vue.component('tab-six',{
            template:"<div>six展示信息</div>"
        })
        /* 1、动态组件 */
Vue.component('tab-home',{
template:"<div> <div class='child_button'>"+
            "<button v-for='childTab in childTabs' v-on:click='currentChildTab = childTab'>{{childTab}}</button>"+
        "</div>"+
        "<component v-bind:is='childTabComponent'></component></div>"
,
data:function(){
return {
    currentChildTab:'one',
    childTabs:['one','two','three','four','five','six']
}
},
computed:{
childTabComponent:function(){
    return 'tab-'+this.currentChildTab;
}
}
});
Vue.component('tab-info',{
    template:"<div>info信心页展示信息</div>"
});
Vue.component('tab-location',{
    template:"<div>location位置展示信息</div>"
});
export default ({
    name:"tab_area",
    components:{
        rolelist:Rolelist
    },
    data(){
     return  {
            currentTab:'home',
            tabs:['home','info','location']
        }
    },
    computed:{
        currentTabComponent:function(){
            return 'tab-'+this.currentTab;
        }
    }
})

</script>


<style type="text/css">  
    *{  
        margin: 0;  
        padding: 0;  
    }  
    .tab_area{
        width: 600px;
        min-height: 300px;
        border: 1px solid black;
        margin: 20px auto;
    }
    .tab_area>button{
        width: 200px;
        height: 40px;
    }
    .child_button{
        width: 200px;
        height: auto;
        float: left;
    }
    .child_button>button{
        display: block;
        width: 100px;
        margin: 10px auto;
        height: 30px;
    }
    .child_button+div{
        float: right;
        width: 400px;
        text-align: left;
        padding: 20px;
        box-sizing: border-box;
        max-height: 250px;
        overflow-y: auto;
        line-height: 30px;
    }
</style>  