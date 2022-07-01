<template>
<el-row>
  <el-col>
        <span >标签</span>
        <el-divider class="tag-title-divider"></el-divider>
        </el-col>

        <el-col>
        <el-tag>标签一</el-tag>
    <el-tag type="success">标签二</el-tag>
    <el-tag type="info">标签三</el-tag>
    <el-tag type="warning">标签四</el-tag>
    <el-tag type="danger">标签五</el-tag>
        </el-col>
        </el-row>
  
</template>

<script>
export default {
  name: 'tag',
  data() {
    return new Object
  }
}

</script>
<style >
.tag-title-divider{
  margin-top: 15px;
}
</style>