<template>
<el-row>
    <el-row type="flex" class="row-footer-all" justify="center">
      <el-col :span="24" >
        <el-col :span="5">
          <el-row type="flex" class="row-footer" justify="center">
            <el-image style="width: 100px; height: 100px" :src=QRcodeurl :preview-src-list="srcList" />
          </el-row>
        </el-col>

        <el-col  class="el-col-divider">
          <el-divider direction="vertical" class="el-divider-footer"/>
        </el-col>

        <el-col :span="5">
          <el-row type="flex" class="row-footer" justify="center">
            <span> 联系方式：pengtao19940719@gmail.com</span>
          </el-row>
        </el-col>

        <el-col  class="el-col-divider">
          <el-divider direction="vertical" class="el-divider-footer"/>
        </el-col>

        <el-col :span="5">
          <el-row type="flex" class="row-footer" justify="center">
            <span> GitHub：pengtao19940719@gmail.com</span>
          </el-row>
        </el-col>

        <el-col class="el-col-divider">
          <el-divider direction="vertical" class="el-divider-footer"/>
        </el-col>

        <el-col :span="5">
          <el-row type="flex" class="row-footer" justify="center">
            <span> GitHub：pengtao19940719@gmail.com</span>
          </el-row>
        </el-col>
      </el-col>
    </el-row>
    <br />
    <el-row type="flex" class="row-footer" justify="center">
      <el-col :span="18">
        <div class="Copyright">
          Copyright © 2022 Provide Blog.
        </div>
        <div class="Ceater">
          Posted by: Peng Tao
        </div>
        <div class="Ceater">
          Powered by: Vue & Springboot
        </div>
      </el-col>
    </el-row>
    </el-row>
</template>

<script>
export default {
  name: 'footerview',
  data() {
    return {
      QRcodeurl: require('../assets/images/QRcode_200_200.jpg'),
      srcList: [
        require('../assets/images/QRcode_841_841.jpg'),
      ]
    };
  }
}

</script>
<style>
.row-footer-all{
  min-width: 1200px;
}

.row-footer{
  padding: 0;
  width: 100%;
}


.el-col.el-col-divider{
  width: 5px;
  margin-right: 20px;
}

.el-divider.el-divider-footer{
  width: 1px;
  height: 100px;
}

.Copyright {
  font-family: "微软雅黑";
  font-size: 14px;
  text-align: center;
  color: dimgray;

}

.Ceater {
  font-family: "微软雅黑";
  font-size: 8px;
  text-align: center;
  color: dimgray;
}
</style>