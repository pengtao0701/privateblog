<template>
  <el-row class="row-footer-all">
    <el-row type="flex" justify="center">
      <el-col :span="10">

        <el-row type="flex" class="row-bg" justify="center">

          <el-col :span="10">
          <el-row type="flex" class="row-bg" justify="end">
            <el-image style="width: 100px; height: 100px" :src=QRcodeurl :preview-src-list="srcList" />
            </el-row>
          </el-col>
          

          <el-col :span="2">
          <el-row type="flex" class="row-bg" justify="center">
            <el-divider direction="vertical" class="el-divider-footer" />
            </el-row>
          </el-col>

          <el-col :span="2" class="el-col-connection">
            <el-row type="flex" class="row-bg row-bg-connection">
              <el-col>
                <span> Email: </span>
              </el-col>
            </el-row>

            <el-row type="flex" class="row-bg row-bg-connection">
              <el-col>
                <span> QQ: </span>
              </el-col>
            </el-row>

            <el-row type="flex" class="row-bg row-bg-connection">
              <el-col>
                <span> GitHub: </span>
              </el-col>
            </el-row>

            <el-row type="flex" class="row-bg row-bg-connection">
              <el-col>
                <span> Skype: </span>
              </el-col>
            </el-row>
          </el-col>
<!-------------------------------------------------------->
          <el-col :span="8" class="el-col-connection">
            <el-row type="flex" class="row-bg row-bg-connection" >
                <span> pengtao19940719@gmail.com </span>
            </el-row>

            <el-row type="flex" class="row-bg  row-bg-connection">
              <el-col>
                <span> 351994578 </span>
              </el-col>
            </el-row>

            <el-row type="flex" class="row-bg  row-bg-connection">
              <el-col>
                <span> pengtao0701 </span>
              </el-col>
            </el-row>

            <el-row type="flex" class="row-bg  row-bg-connection">
              <el-col>
                <span> pengtao0701@gmail.com </span>
              </el-col>
            </el-row>
          </el-col>



        </el-row>
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
          Powered by:
          <a href="https://vuejs.org/">Vue.js</a> &
          <a href="https://spring.io/projects/spring-boot">Springboot</a>
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
.row-footer-all {
  min-width: 1200px;
  padding-bottom: 40px;
}

.row-footer {
  padding: 0;
  width: 100%;
}


.el-col.el-col-divider {
  width: 5px;
  margin-right: 20px;
}

.el-divider.el-divider-footer {
  width: 1px;
  height: 100px;
}

.el-col-connection{
  padding-top: 5px;
  font-size: 14px;
}
.row-bg-connection{
  margin-top: 3px;
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