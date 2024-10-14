<template>
  <div class="container" @keyup.enter="login">
    <div class="login-wrapper">
      <div class="left-flex">
        <img src="../assets/img/login.jpg" alt="">
      </div>
      <div class="right-flex">
        <el-form :model="user" :rules="rules" ref="loginRef">
          <div class="header">Log in</div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" v-model="user.username" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="user.password" placeholder="密码" show-password></el-input>
          </el-form-item>
          <el-form-item prop="inputVa">
            <div style="display: flex;">
              <div style="flex:1">
                <el-input prefix-icon="el-icon-s-promotion" v-model="user.inputVa" placeholder="验证码"></el-input>
              </div>
              <div style="flex:1">
                <ValidateCode @change="getCode" ref="rvc" />
              </div>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="login()">登 录</el-button>
          </el-form-item>
          <div class="right-flex-footer">
            <div>还没有账号？
              <router-link to="/register">
                <span style="color: #abc1ee">注册</span>
              </router-link>
            </div>
            <div><a href="#">忘记密码</a></div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import ValidateCode from "@/components/ValidateCode.vue";
import request from "@/utils/request";
export default {
  name: "LoginPage",
  components: {
    ValidateCode,
  },
  data(){
    //验证码校验规则
    const validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if (value.toUpperCase() !== this.changeVa){
        callback(new Error('验证码错误'))
      }
      callback()
    };

    return {
      user:{
        username:'', //用户输入的账号
        password:'',  //用户输入的密码
        inputVa:'',  //用户输入的验证码
      },
      changeVa:'',  //获取验证码组件的验证码
      rules:{
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        inputVa: [
          { validator: validateCode, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    //获取验证码数据
    getCode(value){
      // console.log(value)
      this.changeVa = value
    },
    //登录
    login() {
      this.$refs.loginRef.validate((valid) => {
        //校验通过
        if (valid) {
          request.get("/login",{
            params: {
              username: this.user.username,
              password: this.user.password,
            },
          }).then((res) => {
            // console.log(res)
            if (res.code === '200'){
              this.$router.push("/home")
              localStorage.setItem("userDate",JSON.stringify(res.data))
            }else {
              this.$message.error(res.msg)
            }
          })
        } else {
          //校验未通过
          // console.log('error submit!!');
          //重新加载新的验证码
          this.$refs.rvc.draw()
          return false;
        }
      });
    },
  },
  computed: {

  },
  mounted() {

  }
}
</script>

<style scoped>
  .container {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background-image: url("@/assets/img/P5R.jpg");
  }
  .login-wrapper {
    display: flex;
    height: 560px;
    width: 60%;
    border-radius: 15px;
    overflow: hidden;
    background-color: white;
  }
  .left-flex {
    flex: 1;
  }
  .right-flex {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .header {
    font-size: 35px;
    font-weight: bold;
    text-align: center;
    /*line-height: 80px;*/
    margin-bottom: 70px;
  }
  .btn{
    width: 100%;
    height: 40px;
    border: none;
    border-radius: 4px;
    font-size: 1em;
    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
    color: #ffffff;
    cursor: pointer;
  }
  .el-form{
    width: 80%;
  }
  .right-flex-footer{
    display: flex;
    justify-content: space-between;
  }
  a {
    text-decoration-line: none;
  }
</style>