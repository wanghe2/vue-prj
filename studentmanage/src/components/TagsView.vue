
<template>
  <div class="tags-view-wrapper">
    <router-link class="tags-view-item" :to="item" :key="item.path" :class="isActive(item)?'active':''" v-for="(item) in Array.from(visitedViews)">
      <div class='linkname'>{{item.params.name}}</div>
      <div class='el-icon-close' @click.prevent.stop='closeSelectedTag(item)'><img src="../assets/logo.png" width="100%" height="100%" /></div>
    </router-link>
  </div>
</template>
<script>
export default {
    name:'tagview',
    data(){
      return {
        visitedViews:[]
      }
    },
    methods:{
      isActive(route) {
        return route.path === this.$route.path
      },//当前地址栏路径是否与渲染的路径相同 样式匹配
      closeSelectedTag(view){
         this.$store.dispatch({
           type:"closeTags",
           view
         }).then((views)=>{
            //此时的views是指的被删除后的visitedViews数组中存在的元素
           if (this.isActive(view)) {
            //当前关闭的标签是否是被选中的标签
             const latestView = views.slice(-1)[0];
             if (latestView) {
               this.$router.push(latestView);//如果数组不为空则让选中的标签为紧邻关闭标签的那一个
             } else {
               this.$router.push('/main') ;//如果为空则页面跳转到/
             }
           }
         })
      }
    },
    mounted(){
      this.visitedViews=this.$store.state.visitedTags;
    }
}
</script>
<style scoped>
 @import './tagView.css'
</style>



