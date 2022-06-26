<template>
        <div>
                <el-row>
                        <el-page-header @back="goBack" content="发布博客">
                        </el-page-header>
                        <el-divider class="artc"></el-divider>




                        <el-row>{{ tinymceObj }}</el-row>
                        <el-form :model="addForm" :rules="rules" ref="addForm" label-width="100px"
                                class="demo-ruleForm">
                                <el-form-item prop="title">
                                        <el-input v-model="addForm.title" placeholder="文章标题"></el-input>
                                </el-form-item>
                                <el-form-item prop="type">
                                        <el-select v-model="addForm.dataDictionaryId" placeholder="请选择文章类型">
                                                <el-option v-for="item in options" :key="item.id" :label="item.dataText"
                                                        :value="item.id">
                                                </el-option>
                                        </el-select>
                                </el-form-item>
                                <el-form-item prop="content">
                                        <Tedit v-model="addForm.content" ref="tinymceRef" @input="input"
                                                @change="change">
                                        </Tedit>
                                </el-form-item>
                                <el-form-item class="tinymce-btn">
                                        <el-row type="flex" class="row-bg" justify="space-between">
                                                <!--------- 占位 --------->
                                                <el-col :span="3">
                                                        
                                                </el-col>

                                                <!--------------------阅览、提交-------------------->
                                                <el-col :span="12">
                                                 <el-col>
                                                                <el-row type="flex" class="row-bg" justify="center">
                                                                <el-button class="el-icon-upload el-icon--left" type="primary" round @click="tinymceSave">
                                                                提交
                                                                </el-button>

                                                       
                                                                <el-button round class="el-icon-view el-icon--left" style="margin-left:100px">
                                                                阅览
                                                                </el-button>
                                                                </el-row>
                                                        </el-col>
                                                </el-col>

                                                <!----------------删除内容---------------->
                                                <el-col :span="3">
                                                        <el-row type="flex" class="row-bg" justify="center">
                                                                <el-button type="danger" icon="el-icon-delete" circle
                                                                        @click="tinymceClose">
                                                                </el-button>
                                                        </el-row>
                                                </el-col>
                                                
                                        </el-row>

                                </el-form-item>
                        </el-form>


                </el-row>

        </div>






</template>

<script>

import Tedit from "../../components/tinymce/edit.vue"
import { deconstructText } from "../../utils/util"

export default {
        components: { Tedit, },
        name: 'editblog',
        data() {

                return {
                        options: '',
                        tinymceObj: '',
                        addForm: {
                                title: '',
                                dataDictionaryId: '',
                                content: ''

                        },
                        rules: {
                                title: [{
                                        required: true,
                                        message: "请为您的博客起个标题",
                                        trigger: "blur",
                                },],
                                dataDictionaryId: [{
                                        required: true,
                                        message: "请选择一个分类",
                                        trigger: "blur",
                                },],
                                content: [{
                                        required: true,
                                        message: "博客内容不可以为空",
                                        trigger: "blur",
                                },],

                        },
                }
        },

        methods: {
                dutyDetailClick() {
                        this.$nextTick(() => {
                                let html = '<p><span style="color: rgb(224, 62, 45);" data-mce-style="color: #e03e2d;">1、方便；</span></p><p>2、快捷；</p>';
                                if (!html) {
                                        html = '';
                                }
                                // 给富文本框赋值
                                this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelector('body').innerHTML = html;
                        });
                },

                tinymceClose() {
                        // 清空富文本框
                        this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelector('body').innerHTML = '';
                },
                tinymceSave() {
                        //富文本框保存获取值
                        let pText = this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelectorAll('p');
                        let html = this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelector('body').innerHTML;
                        let json = JSON.stringify(deconstructText(pText));//这一步是前端把值封装成后台想要个数据格式，根据你自己需求添加
                },

                input(inputvalue) {
                        this.tinymceObj = inputvalue
                },
                change(changevalue) {
                        this.tinymceObj = changevalue
                },

                goBack() {
                        this.$router.push('/')
                },

        },
        mounted() {

        },

        watch: {

        }

}

</script>
<style >
.demo-ruleForm {
        margin-left: -100px;
}
</style>