/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 23:07:43 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-27 00:44:38
 */
<template>
  <el-container class="el-container-home">
    <el-backtop :bottom="100" />
    <el-header class="el-header-top">
      <Headerview></Headerview>
    </el-header>
    <el-row type="flex" class="row-bg el-row-main" justify="center">
        <el-col :span="16">
          <!-- defulte to mainindex-->
          <router-view></router-view>
        </el-col>

    </el-row>
    <el-footer class="el-footer-style">
      <Footerview></Footerview>
    </el-footer>
  </el-container>
</template>

<script>
import Headerview from "./header.vue"
import Footerview from "./footer.vue"

export default {
  components: { Headerview, Footerview, },
  name: 'Home',

  data() {

    return {

      loadingFlg: true,
    };
  },

  methods: {
    // onloginflg(data){

    //   console.log("父组件接收到"+"," + data )
    //   this.loadingFlg = data
    // }

  },



  mounted() {
    // 初始化显示main内容
    //this.$nextTick(function () {
    //this.$router.Mainview
    //});
   

  },

  created() {
    //this.$router.push('/mainblog')
     this.$router.push('/mainindex')
  },


}


</script>
    <style>
    
    .el-container.el-container-home {
      display: flex;
      flex-direction: column;
      min-height: 100%;
    }
    
    .el-header.el-header-top {
      min-width: 900px;
      padding: 0;
    }
    
    .el-main {
      background-color: white;
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
      margin-right: 20px;
    }
    
    .row-bg.el-row-main{
      margin-top: 20px ;
      width: 100%;
    }
    
    .el-footer.el-footer-style {
      background-color: whitesmoke;
      padding-top: 40px;
    
    }
    </style>
