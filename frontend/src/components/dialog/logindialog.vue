<template>
        <div class="logindialog">
                <el-dialog class="logindialog" title="Login in" :visible.sync="DialogVisible"
                        :before-close="handleClose" center>
                        <div class="login">

                                <!--ref校验信息-->
                                <!--rules规则-->
                                <el-form :model="loginForm" :rules="loginrules" ref="loginForm">

                                        <!-- prop双向数据绑定data-->
                                        <el-form-item class="el-form-item-style" label="账号" prop="username">
                                                <el-input class="username-input" v-model="loginForm.username"
                                                         placeholder="请输入邮箱或用户名" />
                                        </el-form-item>
                                        <el-form-item class="el-form-item-style" label="密码" prop="password">
                                                <el-input class="password-input" v-model="loginForm.password"
                                                        placeholder="请输入密码" type="password" show-password />
                                        </el-form-item>
                                        <el-form-item class="el-form-item-style manbercheckbox" prop="isRemember">
                                                <el-row type="flex" class="row-bg" justify="space-between">
                                                        <el-col :span="8">
                                                                <el-checkbox label="记住密码"
                                                                        v-model="loginForm.isRemember"></el-checkbox>
                                                        </el-col>
                                                        <el-col :span="8">
                                                                <a href="/forget" style="text-decoration: none">忘记密码</a>
                                                        </el-col>
                                                </el-row>
                                        </el-form-item>

                                        <br />
                                        <el-button @click="submitLoginForm('loginForm')"
                                                v-loading.fullscreen.lock="fullscreenLoading" type="primary"
                                                class="el-button-style">登&nbsp;&nbsp;&nbsp;陆</el-button>
                                </el-form>
                        </div>

                </el-dialog>
        </div>
</template>

<script>
export default {

        name: 'logindialog',

        props: {
                loginDialogVisible: {
                        type: Boolean,
                        default: false,
                }
        },




        data() {

                return {
                        
                        // 默认关闭dialog
                        DialogVisible: false,
                        // 全屏加载
                        fullscreenLoading: false,
                        // login表单初始化
                        loginForm: {
                                username: '',
                                password: '',
                                isRemember: false
                        },
                        loginrules: {
                                username: [{
                                        required: true,
                                        message: "用户名或邮箱不可为空",
                                        trigger: "blur",
                                }],

                                password: [{
                                        required: true,
                                        message: "密码不可为空",
                                        trigger: "blur",
                                }]

                        },
                }
        },

        methods: {

                // ---------  提交登录表单 --------- 
                submitLoginForm(loginForm) {
                        let that = this
                        that.fullscreenLoading = true
                        this.$refs[loginForm].validate((valid) => {
                                if (valid) {
                                        this.$api.user.login(this.loginForm)
                                                .then(response => {
                                                        console.log("请求成功返回response:" + response)
                                                        const { code, message, data } = response;
                                                        // Login请求成功
                                                        console.log("resultcode:" + code)
                                                        console.log("resultmsg:" + message)
                                                        console.log("data:" + data)
                                                        console.log("token:" + data.token)
                                                        if (code === "200" && message === "OK") {
                                                                // 存放token
                                                                localStorage.setItem("token", data.token);
                                                                localStorage.setItem("userinfo", this.setUserInfo(data));
                                                                // that.lsbuttonVisible = false,
                                                                // that.logintitle = getWelcomeText(),
                                                                // that.nickname = data.nickname
                                                                that.$message.success('登录成功')
                                                                // 加载结束
                                                                that.fullscreenLoading = false
                                                                // 关闭登录dialog
                                                                that.handleClose()
                                                                // 重新加载userinfo组件
                                                                that.reloaduserinfo()


                                                                // Login请求失败
                                                        } else {
                                                                console.log("请求失败")
                                                                that.fullscreenLoading = false
                                                                that.$message.error(code + ":" + message)

                                                        }
                                                }).catch(function (error) {
                                                        that.$message.error("请求失败，请稍后再试")
                                                }).finally(() => that.fullscreenLoading = false,)
                                } else {
                                        that.fullscreenLoading = false

                                }
                        }
                        )
                },

                // 存放response内容
                setUserInfo(data) {
                        let responsedate = {
                                isLogin: true,
                                uuid: data.uuid,
                                nickname: data.nickname,
                                username: data.username,
                                mailaddres: data.mailaddres,
                                userprofilephoto: data.userprofilephoto,
                                introduction: data.introduction,
                                status: data.status,

                        }
                        return JSON.stringify(responsedate);
                },

                // 关闭dialog
                handleClose() {
                        this.$emit('loginHandleClose')
                },

                // 重载userinfo
                reloaduserinfo() {
                        this.$emit('reloaduserinfo')
                }
        },
        watch: {
                // 监听loginDialog是否显示
                loginDialogVisible(val) {
                        this.DialogVisible = val;
                },
        },

}

</script>
<style >
</style>