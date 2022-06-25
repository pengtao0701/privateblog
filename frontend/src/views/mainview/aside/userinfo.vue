<template>
        <div v-if="isuserinforeload">

                <!-- 用户头像 登录状态 用户名 -->
                <el-row class="demo-avatar" type="flex" :gutter="10">
                        <el-col :span="6">

                                <div class="block">
                                        <el-avatar shape="square" :size="50">
                                                <img v-bind:src=avatarurl />

                                        </el-avatar>
                                </div>
                        </el-col>
                        <el-col :span="2">
                                <el-divider class="el-divider-userinfo" direction="vertical"></el-divider>
                        </el-col>
                        <el-col :span="15">
                                <div class="sub-title">{{ logintitle }}</div>
                                <div class="sub-nickname">{{ nickname }}</div>
                        </el-col>
                        <br />
                </el-row>
                <!---------------------- 登录注册 按钮 / -登录用户信息------------------------->

                <el-row type="flex" class="el-row-loginbuttonstyle" justify="space-around" v-if="lsbuttonVisible">
                        <el-col :span="9">
                                <el-button icon="el-icon-user-solid" type="primary" round
                                        @click="loginDialogVisible = true">登录
                                </el-button>

                        </el-col>
                        <el-col :span="9">
                                <el-button icon="el-icon-edit" type="primary" round @click="signinDialogVisible = true"
                                        v-on:click="getVcodeimg">
                                        注册
                                </el-button>

                        </el-col>
                </el-row>
                <!---------------------- 下拉框 ------------------------->
                <Userinfofooter v-else></Userinfofooter>

                <!--------------- 登录LoginDialog start ------------->
                <el-dialog class="logindialog" title="Login in" :visible.sync="loginDialogVisible" center>
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
                <!--------------- 登录LoginDialog end ------------->
                <!--------------- 注册signinDialog start ------------->
                <el-dialog class="logindialog" title="Sign in" :visible.sync="signinDialogVisible" center>
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
                <!--------------- 注册signinDialog end ------------->
        </div>


</template>

<script>
import Userinfofooter from './userinfofooter.vue'
import {getWelcomeText} from '../../../utils/util'

export default {
        name: 'userinfo',
        components: { Userinfofooter },
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


                this.num = this.createUUID(11, 8);
                return {
                        // 登录注册按钮是否显示
                        lsbuttonVisible: true,
                        // 全屏加载
                        fullscreenLoading: false,
                        isuserinforeload: true,
                        logintitle: "未登录",
                        nickname: "未设置用户昵称",
                        avatarurl: require('../../../assets/images/visitor.png'),
                        vcodeimg: '',
                        loginDialogVisible: false,
                        signinDialogVisible: false,

                        // login表单初始化
                        loginForm: {
                                username: '',
                                password: '',
                                isRemember: false
                        },
                        // signin表单初始化
                        signForm: {
                                username: '',
                                mailaddres: '',
                                password: '',
                                vcode: '',
                        }, loginrules: {
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

                        }
                }
        },

        methods: {
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
                // ------------ 生成验证码 --------------//
                getVcodeimg() {
                        this.vcodeimg = `/api/User/Getvcode?date=${new Date().valueOf()}`;
                },

                // --------- 生成随机用户UID --------- 
                createUUID(len, radix) {
                        var chars = '0123456789'.split('');
                        var uuid = [], i;
                        radix = radix || chars.length;
                        if (len) {
                                for (i = 0; i < len; i++) {
                                        uuid[i] = chars[0 | Math.random() * radix];
                                }
                        }
                        return '_' + uuid.join('');
                },
                // ---------  提交登录表单 --------- 
                submitLoginForm(loginForm) {
                        let that = this
                        that.fullscreenLoading = true
                        this.$refs[loginForm].validate((valid) => {
                                if (valid) {
                                        this.$api.login.login(this.loginForm)
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
                                                                that.lsbuttonVisible = false,
                                                                        that.logintitle = getWelcomeText(),
                                                                        that.nickname = data.nickname
                                                                that.$message.success('登录成功')
                                                                // 加载结束
                                                                that.fullscreenLoading = false
                                                                // 关闭登录dialog
                                                                that.loginDialogVisible = false
                                                                // 重新加载userinfo组件
                                                                this.$forceUpdate()

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
                                                                that.lsbuttonVisible = false,
                                                                that.logintitle = getWelcomeText(),
                                                                that.nickname = data.nickname
                                                                that.fullscreenLoading = false
                                                                that.$message.success('注册成功')
                                                                // 加载结束
                                                                that.fullscreenLoading = false
                                                                // 关闭注册dialog
                                                                that.signinDialogVisible = false
                                                                // 重新加载userinfo组件
                                                                this.$forceUpdate()

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

                        }
                        return JSON.stringify(responsedate);
                },

        },

        mounted() {
                let userinfo = JSON.parse(localStorage.getItem('userinfo'));
                console.log("初始化从logcalStorage获得userinfo:" + userinfo)
                if (userinfo === null) return {};

                console.log("判断isLogin:" + userinfo.isLogin)
                if (userinfo.isLogin) {
                        this.lsbuttonVisible = false,
                                this.logintitle = getWelcomeText(),
                                this.nickname = userinfo.nickname
                }

        },

        watch: {


        }

}



</script>
<style>
/* 主页侧边栏用户情报 */

.el-row.el-row.demo-avatar {
        height: 40px;
}


/*. 登录dialog */
.el-dialog.el-dialog--center,
.el-dialog.logindialog {
        width: 25%;
        padding: 0 3% 0 3%;
        border-radius: 7px
}

/* 头像旁标题 */
.sub-title {
        margin-bottom: 7px;
}

/* 用户昵称 */
.sub-nickname {
        font-size: 7px;
}

/* 侧边栏登录注册按钮 */
.el-row-loginbuttonstyle {

        margin-top: 40px;
        height: auto;
}

.el-button-style {
        width: 100%;
        margin-top: 20px;
        margin-bottom: 20px;
}


.login {
        text-align: center;
        width: 100%;
        min-height: 100%;
}

.el-input.username-input,
.el-input.password-input {
        float: left;
        width: 100%;
}

.el-row.el-row-vcode {
        width: 100%;
}

.el-col.vcode-col-img {
        padding-top: 39px;
}

.el-input.vcode-input {
        float: left;
        width: 100%;
}

.el-divider.el-divider-userinfo {
        height: 50px;
}
</style>