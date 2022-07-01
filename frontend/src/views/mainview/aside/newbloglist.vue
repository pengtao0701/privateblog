<template>
        <el-row>
                <el-col>
                        <span>最新博客</span>
                        <el-divider class="newbloglist-divider"></el-divider>
                </el-col>

                <el-col>
                <el-skeleton :loading="true"/>
                <el-divider></el-divider>
                </el-col>
        </el-row>
</template>

<script>
export default {
        name: 'newbloglist',
        data() {
                return new Object
        }
}

</script>
<style >
.newbloglist-divider {
        margin-top: 15px;
}
</style>