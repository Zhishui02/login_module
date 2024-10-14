<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="left-flex">
        <img src="../assets/img/register.jpg" alt="">
      </div>
      <div class="right-flex">
        <el-form :model="user" :rules="rules" ref="registerRef">
          <div class="header">Sign in</div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" v-model="user.username" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" v-model="user.password" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item prop="confirmPass">
            <el-input prefix-icon="el-icon-lock" v-model="user.confirmPass" placeholder="请确认密码" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="btn" @click="register()">注 册</el-button>
          </el-form-item>
          <div class="right-flex-footer">
            <div>
              <router-link to="/login">
                <span style="color: #abc1ee">返回登录</span>
              </router-link>
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterPage",
  components: {

  },
  data(){
    //二次密码确认 校验规则
    const validateConfirmPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'));
      } else if (value !== this.user.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };

    return {
      user:{
        username:'', //用户输入的账号
        password:'',  //用户输入的密码
        confirmPass:'',  //用户确认输入的密码
      },
      rules:{
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPass: [
          { validator: validateConfirmPass, trigger: 'blur' }
        ],
      }
    }
  },
  methods:{
    register(){
      this.$refs.registerRef.validate((valid) => {
        //校验通过
        if (valid) {
          axios.post("http://localhost:9090/register",this.user,{

          }).then((res) => {
            console.log(res)
            if (res.data.code === '200'){
              this.$message.success("注册成功")
              this.$router.push("/login")
            }else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          //校验未通过
          // console.log('error submit!!');
          return false;
        }
      });
    }
  },
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
    justify-content: center;
  }
  a {
    text-decoration-line: none;
  }
</style>