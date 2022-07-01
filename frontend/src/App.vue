/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 23:07:43 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-29 02:48:29
 */
<template>
  <div id="app">

    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
export default {
  components: {},
  name: 'app',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {

    return {
      isRouterAlive: true
    }
  },

  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
        this.$router.push('/', () => { })
      });
    }
  },

  created() {

  },
  mounted() {
  },

}


</script>
    <style >
    

    html {
      height: 100%;
    }
    
    body {
      margin: 0;
      padding: 0;
      overflow-x: hidden;
      height: 100%;
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-color: whitesmoke;
    }
    
    .el-tag+.el-tag {
      margin-left: 10px;
      margin-top: 5px;
    }
    </style>
