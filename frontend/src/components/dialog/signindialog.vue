<template>
        <div class="signindialog">
                <el-dialog class="logindialog" title="Sign in" :visible.sync="DialogVisible" :before-close="handleClose"
                        center>
                        <div class="login">

                                <!--ref校验信息-->
                                <!--rules规则-->
                                <el-form :model="signForm" :rules="signinrules" ref="signForm">

                                        <!-- prop双向数据绑定data-->
                                        <el-form-item class="el-form-item-style" label="用户名" prop="username">
                                                <el-input maxlength="16" class="username-input"
                                                        v-model="signForm.username" placeholder="请输入用户名" />
                                        </el-form-item>
                                        <el-form-item class="el-form-item-style" label="邮箱" prop="mailaddres">
                                                <el-input class="password-input" v-model="signForm.mailaddres"
                                                        placeholder="请输入邮箱" type="email" @blur="vaildEmail" />
                                        </el-form-item>
                                        <el-form-item class="el-form-item-style" label="密码" prop="password">
                                                <el-input maxlength="18" class="password-input"
                                                        v-model="signForm.password" placeholder="请输入密码" type="password"
                                                        show-password />
                                        </el-form-item>
                                        <el-row type="flex" class="el-form-item el-form-item-style vcode-row"
                                                :gutter="20">
                                                <el-col :span="18">
                                                        <el-form-item label="验证码" prop="vcode">
                                                                <el-input maxlength="4" class="vcode-input"
                                                                        v-model="signForm.vcode" placeholder="请输入验证码"
                                                                        type="text" />
                                                        </el-form-item>
                                                </el-col>
                                                <!-- 验证码-->
                                                <el-col class="vcode-col-img" :span="8">
                                                        <el-image style="width: 70px; height: 40px; bottom:0;"
                                                                :src=vcodeimg :contain=true v-on:click="getVcodeimg">
                                                        </el-image>
                                                </el-col>
                                        </el-row>
                                        <br />
                                        <el-button @click="submitSigninForm('signForm')"
                                                v-loading.fullscreen.lock="fullscreenLoading" type="primary"
                                                class="el-button-style">注&nbsp;&nbsp;&nbsp;册</el-button>
                                </el-form>
                        </div>
                </el-dialog>
        </div>
</template>

<script>
export default {
        name: 'signindialog',

        props: {
                signinDialogVisible: {
                        type: Boolean,
                        default: false,
                }

        },

        data() {

                let validateEmail = (rule, value, callback) => {
                        if (!this.vaildEmail(value)) {
                                callback(new Error('邮箱格式错误'))
                        } else {
                                callback()
                        }
                }
                let validatePassword = (rule, value, callback) => {
                        if (!this.vaildPassword(value)) {
                                callback(new Error('密码只能以字母开头，长度在5~18之间，只能包含字母、数字和下划线'))
                        } else {
                                callback()
                        }
                }
                let vaildateUsername = (rule, value, callback) => {
                        if (!this.vaildUsername(value)) {
                                callback(new Error('用户名长度4到16位（字母，数字，下划线，减号）'))
                        } else {
                                callback()
                        }
                }
                return {
                        // 默认关闭dialog
                        DialogVisible: false,
                        // 全屏加载
                        fullscreenLoading: false,
                        // 验证码
                        vcodeimg: '',
                        // signin表单初始化
                        signForm: {
                                username: '',
                                mailaddres: '',
                                password: '',
                                vcode: '',
                        },
                        signinrules: {
                                username: [{
                                        required: true,
                                        message: "用户名不可为空",
                                        trigger: "blur",
                                }, {
                                        validator: vaildateUsername,
                                        trigger: 'blur'
                                }],
                                mailaddres: [{
                                        required: true,
                                        message: "邮箱不可为空",
                                        trigger: "blur",
                                }, {
                                        validator: validateEmail,
                                        trigger: 'blur'
                                }],
                                password: [{
                                        required: true,
                                        message: "密码不可为空",
                                        trigger: 'blur',
                                }, {
                                        validator: validatePassword,
                                        trigger: 'blur'
                                }],
                                vcode: [{
                                        required: true,
                                        message: "验证码不可为空",
                                        trigger: "blur",
                                }]

                        },
                }
        },


        methods: {
                // ------------ 生成验证码 --------------//
                getVcodeimg() {
                        this.vcodeimg = `/api/User/Getvcode?date=${new Date().valueOf()}`;
                },
                // ------------ 验证登录注册表单 --------------//
                vaildEmail(s) {
                        return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
                },
                vaildPassword(s) {
                        return /^[a-zA-Z]\w{4,17}$/.test(s)
                },
                vaildUsername(s) {
                        return /^[a-zA-Z0-9_-]{4,16}$/.test(s)
                },
                // --------- 注册表单提交 ---------
                submitSigninForm(signForm) {

                        let that = this
                        this.fullscreenLoading = true
                        this.$refs[signForm].validate((valid) => {
                                if (valid) {
                                        this.$api.signin.signin(this.signForm)
                                                .then(response => {
                                                        const { code, message, data } = response;
                                                        // signin请求成功
                                                        if (code === "200" && message === "OK") {
                                                                // 存放token
                                                                localStorage.setItem("token", data.token);
                                                                localStorage.setItem("userinfo", this.setUserInfo(data));
                                                                // that.lsbuttonVisible = false,
                                                                // that.logintitle = getWelcomeText(),
                                                                // that.nickname = data.nickname
                                                                that.fullscreenLoading = false
                                                                that.$message.success('注册成功')
                                                                // 加载结束
                                                                that.fullscreenLoading = false
                                                                // 关闭登录dialog
                                                                that.handleClose()
                                                                // 重新加载userinfo组件
                                                                that.reloaduserinfo()

                                                                // signin请求失败
                                                        } else {
                                                                that.fullscreenLoading = false
                                                                that.$message.error(code + ":" + message)

                                                        }
                                                }).catch(function (error) {
                                                        that.$message.error("请求失败,请稍后再试")
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
                                nickname: data.nickname,
                                username: data.username,
                                mailaddres: data.mailaddres,
                                uuid: data.uuid,
                                status: data.status,

                        }
                        return JSON.stringify(responsedate);
                },


                // 关闭dialog
                handleClose() {
                        this.$emit('signinHandleClose')
                },

                // 重载userinfo
                reloaduserinfo() {
                        this.$emit('reloaduserinfo')
                }
        },
        watch: {
                // 监听signinDialog是否显示
                // 请求验证码
                signinDialogVisible(val) {
                        this.DialogVisible = val;
                        this.vcodeimg = `/api/User/Getvcode?date=${new Date().valueOf()}`;
                },
        },
}

</script>
<style >
</style>