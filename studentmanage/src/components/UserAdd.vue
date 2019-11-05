<template>
  <transition name="confirm-fade">
    <div v-if="is_show" class="my-confirm"  >
        <div class="confirm-content-wrap">
            <h3 class="my-confirm-title">增加用户</h3>
            <Form class="my-confirm-content">
                <FormItem prop="userName" >
                    <Input v-model="username" placeholder="请输入用户名">
                    </Input>
                </FormItem>
                <FormItem prop="password" >
                    <Input type="password" v-model="password" placeholder="请输入密码">
                    <span slot="prepend">
                        <Icon :size="14" type="md-lock"></Icon>
                    </span>
                    </Input>
                </FormItem>
                <FormItem prop="introduce" >
                    <Input  v-model="introduce" placeholder="请输入介绍">
                    <span slot="prepend">
                        <Icon :size="14" type="md-lock"></Icon>
                    </span>
                    </Input>
                </FormItem>
            </Form>
            <div class="my-operation">
                <div  class="my-cancel-btn" @click="closeModal">
                    <p class="my-btn-text my-border-right">取消</p>
                </div>
                <div class="confirm-btn" @click="ok">
                    <p class="my-btn-text">保存</p>
                </div>
            </div>
        </div>
    </div>
    </transition>
</template>
<script>
export default {
    name:'useradd',
    data(){
        return {
            is_show:false,
            username:'',
            password:'',
            introduce:''
        }
    },
    methods:{
       open(){
           this.is_show=true
       }, 
       ok(){
           const params={
               username: this.username,
               password: this.password,
               introduce: this.introduce
           }
           this.$emit('saveuser',params)
           this.closeModal()
       },
       closeModal(){
           this.is_show=false
           this.username=''
           this.password=''
           this.introduce=''
       }
    }
}
</script>
<style scoped>
 .my-confirm {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 998;
    /* 这里防止当用户长按屏幕，出现的黑色背景色块，以及 iPhone 横平时字体的缩放问题 */
    -webkit-text-size-adjust: 100%;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  }

  /* 进入和出去的动画 */
  .confirm-fade-enter-active {
    animation: opacity 0.3s;
  }
  .confirm-fade-enter-active .confirm-content-wrap {
    animation: scale 0.3s;
  }
  .confirm-fade-leave-active {
    animation: outOpacity 0.2s;
  }

  /* 包裹层容器样式 */
  .confirm-content-wrap {
    position: absolute;
    top: 28%;
    left: 0;
    right: 0;
    width: 480px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 5px;
    z-index: 999;
    user-select: none;
  }

  /* 顶部标题部分 */
  .my-confirm-title {
    padding-top: 20px;
    text-align: center;
    font-size: 20px;
    font-weight: 500;
    color: #333;
  }

  /* 中间内容部分 */
  .my-confirm-content {
    padding: 0 15px;
    padding-top: 20px;
    margin-bottom: 32px;
    text-align: center;
    font-size: 16px;
    color: #666;
    line-height: 1.5;
  }

  /* 底部按钮样式 */
  .my-operation {
    display: flex;
    border-top: 1px solid #eee;
  }
  .my-operation .my-cancel-btn, .confirm-btn {
    flex: 1;
  }
  .my-operation .confirm-btn {
    color: #ffb000;
  }
  .my-operation .my-btn-text {
    text-align: center;
    font-size: 16px;
    margin: 14px 0;
    padding: 6px 0;
  }

  /* 其他修饰样式 */
  .my-border-right {
    border-right: 1px solid #eee;
  }

  /* 进来的动画 */
  @keyframes opacity {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
  @keyframes scale {
    0% {
      transform: scale(0);
    }
    60% {
      transform: scale(1.1);
    }
    100% {
      transform: scale(1);
    }
  }

  /* 出去的动画 */
  @keyframes outOpacity {
    0% {
      opacity: 1;
    }
    100% {
      opacity: 0;
    }
  }


</style>
