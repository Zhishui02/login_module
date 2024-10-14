<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>渲染的数据</span>
      </div>
      <div>
        <el-table
            :data="users"
            style="width: 100%">
          <el-table-column prop="id" label="ID" width="180"></el-table-column>
          <el-table-column prop="username" label="用户名" width="180"></el-table-column>
          <el-table-column prop="password" label="密码" width="180"></el-table-column>
          <el-table-column prop="name" label="姓名" width="180"></el-table-column>
          <el-table-column prop="phone" label="电话号码" width="180"></el-table-column>
          <el-table-column prop="email" label="电子邮箱" width="200"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "HomePage",
  data(){
    return {
      users:[]
    }
  },
  mounted() {
    request.get("/user/selectAll").then((res) => {
      // console.log(res)
      this.users = res.data;
    })
  }
}
</script>

<style scoped>

</style>