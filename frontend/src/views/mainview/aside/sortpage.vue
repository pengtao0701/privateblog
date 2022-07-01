<template>
        <el-row>
                <el-col class="sort-title">
                        <span>文章分类</span>

                </el-col>

                <el-col>
                        <el-collapse v-model="activeNames" @change="handleChange" accordion v-loading="sortloading">
                                <el-collapse-item :title="item.sortName" :name="item.sortID" v-for="item in options"
                                        :key="item.sortID">
                                        <div v-if="bloglist ? true : false">bloglist</div>
                                        <div v-else> 什么也没有...</div>
                                </el-collapse-item>

                        </el-collapse>
                </el-col>
        </el-row>
</template>

<script>
export default {
        name: 'sort',
        data() {
                return {
                        sortloading: false,
                        title: '',
                        name: '',
                        // 分类
                        options: '',
                        bloglist: '',
                        activeNames: ['0']
                }
        },
        methods: {
                handleChange(val) {
                        console.log(val);
                        if (val === '' || val === undefined) {
                                return
                        }
                        this.sortloading = true;
                        setTimeout(() => {
                                this.sortloading = false;
                        }, 1000);
                }
        },
        mounted() {
                this.$api.sort.getAllSort().then(result => {
                        const { code, message, data } = result

                        this.options = data.sortModel
                })
        },
}

</script>
<style >
.sort-title {
        margin-bottom: 15px;
}
</style>