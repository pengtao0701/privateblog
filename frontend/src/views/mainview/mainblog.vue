<template>

  <el-row  >
    <el-col class="el-col-bloglist" v-for="(loadfig, index) in num" :key="index">
      <el-skeleton :rows="6" animated :loading="true" />
      <el-divider></el-divider>
    </el-col>
  </el-row>



</template>

<script>
export default {
  name: 'mainblog',
  data() {
    
    return {

      num: [1,2,3,4,5.6,7,8]
    };
  },

  methods: {
    

  },

  mounted() {
    
  }
}



</script>
<style>
.el-col.el-col-bloglist{
  
  margin-bottom: 10px;
}
</style>