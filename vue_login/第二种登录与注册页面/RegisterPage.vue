<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="header">Sign in</div>
      <div class="form-wrapper">
        <input type="text" placeholder="账号" class="input-item">
        <input type="password" placeholder="密码" class="input-item">
        <div class="btn">
          <button type="button" class="button">注册</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterPage",

}
</script>

<style scoped>
  .container {
    height: 100%;
    background-image: url("@/assets/img/P5R.jpg");
    background-size: 100%,100%;
  }
  .login-wrapper {
    background-color: #fff;
    width: 388px;
    height: 588px;
    border-radius: 15px;
    padding: 0 50px;
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  .header {
    font-size: 38px;
    font-weight: bold;
    text-align: center;
    line-height: 200px;
  }
  .input-item {
    display: block;
    width: 95%;
    margin-bottom: 20px;
    border: 0;
    padding: 10px;
    border-bottom: 1px solid rgb(128, 125, 125);
    font-size: 15px;
    outline: none;
  }
  .input-item::placeholder {
    text-transform: uppercase;
  }
  .btn {
    text-align: center;
    padding: 8px;
    width: 100%;
    margin-top: 45px;
  }
  .msg {
    text-align: center;
    line-height: 100px;
  }
  a {
    text-decoration-line: none;
    color: #abc1ee;
  }
  .button{
    width: 100%;
    height: 40px;
    border: none;
    border-radius: 4px;
    font-size: 1em;
    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
    color: #ffffff;
    cursor: pointer;
  }
</style>