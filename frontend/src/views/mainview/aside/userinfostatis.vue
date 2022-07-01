<template>
<el-row type="flex" class="row-bg el-row-statis" justify="center" >
        <el-row :gutter="24" class="el-row-statis">
                <el-col :span="6">
                        <span class="statis-text-style">阅读数</span>
                        <el-row class="statis-text-style">
                          <span>{{ num }}</span>
                        </el-row>
                </el-col>
                <el-col :span="6">
                        <span class="statis-text-style">获点赞</span>
                        <el-row class="statis-text-style">
                          <span>{{ num }}</span>
                        </el-row>
                </el-col>
                <el-col :span="6">
                        <span class="statis-text-style">关注中</span>
                        <el-row class="statis-text-style">
                          <span >{{ num }}</span>
                        </el-row>
                </el-col>
                <el-col :span="6">
                        <span class="statis-text-style">粉丝数</span>
                        <el-row class="statis-text-style">
                          <span >{{ num }}</span>
                        </el-row>
                </el-col>
        </el-row>
        </el-row>
</template>

<script>
export default {
        name: 'userinfostatis',
        data() {
                return {
                        num: 0
                }

        },

        mounted() {
                // TODO：请求后端用户statis数据
        },
}



</script>
<style >
.el-row-statis{
        height: 100%;
        padding-top: 30px;
}
.statis-text-style{
        text-align: center;
        font-size: 14px;
}
</style>