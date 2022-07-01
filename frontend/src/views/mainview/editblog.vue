<template>
        <div>
                <el-row>
                        <el-page-header @back="goBack" content="发布博客">
                        </el-page-header>
                        <el-divider class="artc"></el-divider>
                        <!--<el-row>{{ tinymceObj }}</el-row>-->
                        <el-form :model="addForm" :rules="rules" ref="addForm" label-width="100px"
                                class="demo-ruleForm">
                                <!----------------标题------------------->
                                <el-form-item prop="blogTitle">
                                        <el-input v-model="addForm.blogTitle" placeholder="请输入文章标题"></el-input>
                                </el-form-item>
                                <br/>
                                <!----------------封面图------------------->
                                <el-form-item prop="coverimg">

                                        <el-upload v-model="addForm.coverimg" class="upload-demo" drag
                                                action="http://localhost:5000/api/Blog/FileUpload"
                                                :with-credentials="true" :headers="headers" :limit="1"
                                                :on-preview="handlePictureCardPreview" :on-success="handleSuccess"
                                                list-type="picture-card" :before-remove="beforeRemove"
                                                :on-remove="onremove" :class="uploadshow ? 'hide_box' : ''">

                                                <i class="el-icon-upload">
                                                        <div class="el-upload__text">请选择一张封面图拖到此处，或<em>点击上传</em></div>
                                                </i>
                                        </el-upload>
                                        <el-dialog :visible.sync="dialogVisible">
                                                <img width="100%" height="100%" :src="dialogImageUrl" alt="">
                                        </el-dialog>

                                </el-form-item>
                                
                                <el-row type="flex" class="row-bg">
                                        <el-col :span="10">
                                                <!----------------分类------------------->
                                                <el-form-item prop="blogSortID">

                                                        <el-select v-model="addForm.blogSortID" placeholder="请选择文章分类">
                                                                <el-option v-for="item in options" :key="item.sortID"
                                                                        :label="item.sortName" :value="item.sortID">
                                                                </el-option>
                                                        </el-select>

                                                </el-form-item>
                                        </el-col>
                                        <el-col :span="16">
                                                <!----------------标签------------------->
                                                <el-form-item prop="blogLabelName">
                                                        <el-tag v-model="addForm.blogLabelName" :key="index"
                                                                v-for="(tag, index) in dynamicTags" closable
                                                                :disable-transitions="false" @close="handleClose(tag)"
                                                                @click="changeValue(tag)">
                                                                {{ tag }}
                                                        </el-tag>
                                                        <el-input class="input-new-tag" v-if="inputVisible"
                                                                v-model="inputtagValue" ref="saveTagInput" size="small"
                                                                @keyup.enter.native="handleInputConfirm"
                                                                @blur="handleInputConfirm">
                                                        </el-input>
                                                        <el-button v-else class="button-new-tag" size="small"
                                                                @click="showInput">
                                                                {{ newTag }}</el-button>

                                                </el-form-item>
                                        </el-col>
                                </el-row>
                                <br/>
                                <!---------------- 文章简介 -------------------->
                                <el-form-item prop="synopsis">
                                        

                                        <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 4, }"
                                                maxlength="256" placeholder="简介(必填):显示在列表中时，更方便了解博客内容"
                                                v-model="addForm.synopsis" show-word-limit>
                                        </el-input>
                                       
                                </el-form-item>
                                <br/>
                                <!----------------------博客内容------------------------->
                                <el-form-item prop="blogContent">
                                        <Tedit v-model="addForm.blogContent" ref="tinymceRef" @input="input"
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
                                                                        <el-button class="el-icon-upload el-icon--left"
                                                                                type="primary" round
                                                                                @click="tinymceSave"
                                                                                v-loading.fullscreen.lock="fullscreenLoading">
                                                                                提交
                                                                        </el-button>


                                                                        <el-button round
                                                                                class="el-icon-view el-icon--left"
                                                                                style="margin-left:100px">
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

export default {
        components: { Tedit, },
        name: 'editblog',
        data() {
                let self = this
                let validateTags = (rule, value, callback) => {
                        console.log("this.dynamicTags.length=" + this.dynamicTags.length)

                        if (this.dynamicTags.length <= 0) {
                                return callback(new Error('请至少添加一个标签(最多5个)'))
                        } else {
                                return callback()
                        }
                }
                return {
                        fullscreenLoading: false,
                        // 封面缩略图
                        uploadshow: false,
                        headers: {
                                Authorization: localStorage.getItem('token'),
                        },
                        uploadcoverimg: '',
                        dialogImageUrl: '',
                        dialogVisible: false,
                        // 分类
                        options: '',
                        // 初始化标签
                        newTag: '+ 创建标签',
                        dynamicTags: [],
                        inputVisible: false,
                        inputtagValue: '',
                        tempTag: '',
                        // 重复标签
                        isRepeatedData: false,
                        // 是否改变原来的值
                        isChange: false,

                        inputValue: '',
                        tinymceObj: '',
                        addForm: {
                                blogTitle: '',
                                blogSortID: '',
                                blogLabelName: [],
                                coverimg: '',
                                synopsis: '',
                                blogContent: '',
                        },
                        rules: {
                                blogTitle: [{
                                        required: true,
                                        message: "请为您的博客起个标题",
                                        trigger: "blur",
                                },],
                                blogSortID: [{
                                        required: true,
                                        message: "请选择一个分类",
                                        trigger: "blur",
                                },],
                                blogLabelName: [
                                        { validator: validateTags, trigger: ['blur', 'change'] }
                                        ,],
                                synopsis: [{
                                        required: true,
                                        message: "请输入博客简介，最多不能超过256个字符",
                                        trigger: "blur",
                                },],
                                blogContent: [{
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
                tinymceSave(addForm) {
                        this.$nextTick(() => {
                                this.addForm.coverimg = this.uploadcoverimg
                                this.addForm.blogLabelName = this.dynamicTags.join()
                                let that = this
                                //富文本框保存获取值
                                let pText = this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelectorAll('p');
                                let html = this.$refs.tinymceRef.$el.querySelector('iframe').contentDocument.querySelector('body').innerHTML;

                                that.fullscreenLoading = true
                                this.$refs.addForm.validate((valid) => {
                                        if (valid) {
                                                this.$api.blog.editBlogSave(this.addForm)
                                                        .then(response => {


                                                        }).catch(function (error) {
                                                                that.$message.error("请求失败，请稍后再试")
                                                        }).finally(() => that.fullscreenLoading = false,)

                                        } else {
                                                that.fullscreenLoading = false
                                        }
                                })

                        })
                },

                input(inputvalue) {
                        this.tinymceObj = inputvalue.activeEditor;
                },
                change(changevalue) {
                        this.tinymceObj = changevalue.activeEditor;
                },

                goBack() {
                        this.$router.push('/')
                },
                // 关闭标签
                handleClose(tag) {
                        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
                },
                // 点击新建标签
                showInput() {
                        if (this.dynamicTags.length >= 5) {
                                return this.$message.warning("最多只能添加5个标签！")
                        }
                        this.tempTag = ''
                        this.inputVisible = true;
                        this.inputValue = ''
                        this.isChange = false

                        this.$nextTick(_ => {
                                this.$refs.saveTagInput.$refs.input.focus();
                        });
                },
                // 标签输入的内容
                handleInputConfirm() {
                        console.log("标签输入的内容")
                        this.isRepeatedData = false
                        console.log("this.inputtagValue=" + this.inputtagValue)
                        let inputValue = this.inputtagValue;
                        // 去空格
                        inputValue = inputValue.replace(/^\s\s*/, '').replace(/\s\s*$/, '')
                        if (inputValue == '') {
                                this.inputVisible = false
                                return
                        }
                        // 判断新增的值是否重复
                        console.log("this.dynamicTags.indexOf(inputValue)=" + this.dynamicTags.indexOf(inputValue))
                        if (this.dynamicTags.indexOf(inputValue) != -1) {
                                this.isRepeatedData = true
                                this.$message.warning("不允许添加重复数据！")
                                return
                        } else {
                                this.isRepeatedData = false
                                // this.isChange = false
                        }
                        // 判断是否修改原有值，是 替换修改好的值，否新增
                        if (this.isChange) {
                                this.dynamicTags[this.dynamicTags.indexOf(this.tempTag)] = this.inputtagValue
                                this.inputVisible = false
                                return
                        }
                        // 点击添加时，追加
                        if (inputValue) {
                                this.dynamicTags.push(inputValue);
                                console.log(inputValue + 'tt:' + this.dynamicTags)
                        }
                        this.tempTag = inputValue
                        this.inputVisible = false;
                        this.inputtagValue = '';

                },
                changeValue(tag) {
                        this.inputVisible = true
                        this.$nextTick(_ => {
                                this.$refs.saveTagInput.$refs.input.focus();
                        });
                        this.inputtagValue = tag
                        this.tempTag = tag
                        this.isChange = true
                },

                // 表示图片
                handlePictureCardPreview(file) {
                        this.dialogImageUrl = file.url;
                        this.dialogVisible = true;

                },
                handleSuccess(response) {
                        this.uploadcoverimg = response.data.urlPath
                        this.uploadshow = true;
                },
                // 移除封面图
                beforeRemove(file) {
                        return this.$confirm(`确定移除 ${file.name}？`);
                },
                onremove() {
                        this.uploadshow = false;
                }

        },
        mounted() {
                this.$api.sort.getAllSort().then(result => {
                        const { code, message, data } = result
                       
                        this.options = data.sortModel
                })

        },

        watch: {
                // inputtagValue: function(){
                //         return this.inputtagValue = self.inputtagValue
                // }
        }

}

</script>

<style scoped>
.hide_box>>>.el-upload--picture-card {
        display: none;
}
</style>
<style >
.demo-ruleForm {
        margin-left: -100px;
}

.upload-demo {
        margin-bottom: 40px;
}

.el-upload--picture-card {
        border: 0
}
.el-upload-list__item-thumbnail {
          /* 图片在方框内显示长边 */
          object-fit: contain;
        }

.button-new-tag {
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
}

.input-new-tag {
        width: 90px;
        margin-left: 10px;
        vertical-align: bottom;
}
</style>