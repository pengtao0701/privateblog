<template>
  <el-menu class="el-userinfofooter">
    <el-submenu index="1" class="el-submenu-style">
      <template slot="title">
        <i class="el-icon-menu"></i>个人中心
      </template>
      <el-menu-item-group>
      
        <router-link style="text-decoration: none;" to="/editblog">
        <el-menu-item index="1-1">
          <i class="el-icon-edit-outline" />发布博客
        </el-menu-item>
        </router-link>

        <router-link style="text-decoration: none;" to="/userpage">
        <el-menu-item index="1-2">
          <i class="el-icon-setting" />个人资料
        </el-menu-item>
        </router-link>

        <el-menu-item index="1-3" @click="logout">
          <i class="el-icon-switch-button" />退出
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: 'userinfosubmenu',
  data() {
    return new Object
  },

  methods: {
    logout() {
      this.$api.user.logout().then(response => {
        
          localStorage.removeItem('userinfo');
          localStorage.removeItem('token');
          this.$router.go(0)
        
        
      })
      
    }
  },

}

</script>
<style type="scss"> 
  .el-icon-arrow-down:before {content: "\E790" !important; } 
  .el-submenu__icon-arrow { font-size: 16px !important;; } 
  .el-submenu__title i { color:dimgray !important; }
</style>


<style>
.el-menu.el-userinfofooter{
  border-right:0;
}

.el-submenu.el-submenu-style{
  
}


.el-userinfofooter {
  margin-top: 20px;
}
</style>