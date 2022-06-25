<template>
  <el-row class="row-bg el-row-error" type="flex" justify="center">
    <el-col :span="8">
      <el-row type="flex" class="row-bg row-errorimg" justify="end">
        <img src="../../assets/images/Notfound1.jpg" />
      </el-row>

    </el-col>
    <el-col :span="2">
      <el-row type="flex" class="row-bg row-divider" justify="center">
        <el-divider class="el-divider-error" direction="vertical"></el-divider>
      </el-row>
    </el-col>
    <el-col :span="8">
      <el-row class="row-bg el-row-error el-row-text" type="flex">
        <h1 style="color: #1890FF;">404 NotFound Page</h1>
      </el-row>
      <el-row>
        <h2 style="">当前页面不存在...</h2>
      </el-row>
      <el-row>
        <h3>请检查您输入的网址是否正确，或稍等片刻返回首页。</h3>
      </el-row>
      <el-row>
        <span style="color: #1890FF; font-size:32px"> {{ time }} </span>秒后自动跳转
      </el-row>
    </el-col>
  </el-row>



</template>

<script>
export default {
  name: 'NotFound',
  data() {
    return {
      time: 0,
    }
  },
  methods: {
    //   封装一个方法
    countDown() {
      let THIS = this;
      THIS.time--;
    },
  },
  mounted() {
    let THIS = this;
    // 把time改为5
    THIS.time = 5;
    // 设定一个定时器，每一秒调用一次coundDown方法，time-1
    setInterval(THIS.countDown, 1000);
  },
  watch: {
    //   监听time的值
    time: function (newVal) {
      if (newVal == 0) {
        this.$router.push("/");
      }
    },

  }
}

</script>
<style >
.row-bg.el-row-error {
  padding-top: 10%;
}


.el-divider.el-divider-error {
  height: 500px;
}

.row-bg.row-errorimg {
  margin-top: 50px;
}
</style>