<template>
  <el-menu class="el-userinfofooter">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">我的博客</el-menu-item>
          <el-menu-item index="1-2" @click="logout">登出</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      
    </el-menu>
</template>

<script>
export default {
name:'userinfofooter',
data(){
        return new Object 
},

methods: {
        logout(){
                localStorage.removeItem('userinfo');
                localStorage.removeItem('token');
                this.$router.go(0)
        }
},

}

</script>
<style>

.el-userinfofooter{
        margin-top: 20px;
}
</style>