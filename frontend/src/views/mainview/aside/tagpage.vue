<template>
  <el-row>
    <el-col>
      <span>标签</span>
      <el-divider class="tag-title-divider"></el-divider>
    </el-col>

    <el-col>
      <el-tag :key="tag.labelID" v-for="tag in labelModel" hit size="small">
        {{ tag.labelName }}
      </el-tag>
      </el-col>
  </el-row>

</template>

<script>
export default {
  name: 'tag',
  data() {
    return {
      labelModel:'',

    }
  },
  mounted() {
    this.$api.label.getAllLabel().then(result => {
      const { code, message, data } = result

      this.labelModel = data.labelModel
    })
  },
}

</script>
<style >
.tag-title-divider {
  margin-top: 15px;
}
</style>