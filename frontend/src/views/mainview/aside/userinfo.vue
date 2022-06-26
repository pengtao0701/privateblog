<template>
        <el-row>
                <!-- 用户头像 登录状态 用户名 -->
                <el-row class="demo-avatar" type="flex" :gutter="10">
                        <el-col :span="6">

                                <div class="block">
                                        <el-avatar shape="square" :size="50">
                                                <img v-bind:src=avatarurl />
                                        </el-avatar>
                                </div>
                        </el-col>
                        <el-col :span="3">
                                <el-divider class="el-divider-userinfo" direction="vertical"></el-divider>
                        </el-col>
                        <el-col :span="15">
                                <div class="sub-title">{{ logintitle }}</div>
                                <div class="sub-nickname">{{ nickname }}</div>
                        </el-col>
                        <br />
                </el-row>
                <!---------------------- 登录注册 按钮 / -登录用户信息------------------------->

                <el-row type="flex" v-if="lsbuttonVisible" class="el-row-loginbuttonstyle" justify="space-around">
                        <el-col :span="9">
                                <el-button icon="el-icon-user-solid" type="primary" round @click="showLoginDialog()">登录
                                </el-button>

                        </el-col>
                        <el-col :span="9">
                                <el-button icon="el-icon-edit" type="primary" round @click="showsigninDialog()">注册
                                </el-button>

                        </el-col>
                </el-row>

                <!---------------- 用户信息底部 -------------->
                <div v-else>
                        <!---------------------- 用户统计情报 ----------------------->
                        <Userinfostatis></Userinfostatis>
                        <!---------------------- 下拉框 ------------------------->
                        <Userinfosubmenu></Userinfosubmenu>
                </div>
                <!--------------- 登录LoginDialog start ------------->
                <LoginDialog 
                        :loginDialogVisible.sync="loginDialogVisible" 
                        @loginHandleClose="loginHandleClose"
                        @reloaduserinfo="reloaduserinfo">
                </LoginDialog>

                <!--------------- 注册signinDialog start ------------->
                <SigninDialog 
                        :signinDialogVisible.sync="signinDialogVisible" 
                        @signinHandleClose="signinHandleClose"
                        @reloaduserinfo="reloaduserinfo">
                </SigninDialog>


        </el-row>

</template>

<script>
import Userinfosubmenu from './userinfosubmenu.vue'
import Userinfostatis from './userinfostatis.vue'
import { getWelcomeText } from '../../../utils/util'
import LoginDialog from '../../../components/dialog/logindialog.vue'
import SigninDialog from '../../../components/dialog/signindialog.vue'


export default {
        // 重载画面
        inject: ['reload'],
        name: 'userinfo',
        components: { Userinfostatis, Userinfosubmenu, LoginDialog, SigninDialog },
        data() {


                this.num = this.createUUID(11, 8);
                return {

                        // 登录注册按钮是否显示
                        lsbuttonVisible: true,

                        logintitle: getWelcomeText(),
                        nickname: "未登录",
                        avatarurl: require('../../../assets/images/visitor.png'),
                        // 登录按钮初始化false
                        loginDialogVisible: false,
                        // 注册按钮初始化false
                        signinDialogVisible: false,
                }
        },

        methods: {

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

                // 显示登录Dialog
                showLoginDialog() {
                        this.loginDialogVisible = true
                },
                // 显示注册Dialog
                showsigninDialog() {
                        this.signinDialogVisible = true
                },
                // 关闭登录Dialog
                loginHandleClose() {
                        this.loginDialogVisible = false
                },
                // 关闭注册Dialog
                signinHandleClose() {
                        this.signinDialogVisible = false
                },

                //重载userinfo组件
                reloaduserinfo() {
                        // 重新加载userinfo组件
                        this.reload()
                }

        },

        mounted() {
                // userinfo组件初始化
                // 获取用户信息
                let userinfo = JSON.parse(localStorage.getItem('userinfo'));
                console.log("初始化从logcalStorage获得userinfo:" + userinfo)
                if (userinfo === null) return {};

                console.log("判断isLogin:" + userinfo.isLogin)
                if (userinfo.isLogin) {
                        console.log("userinfo初始化登录注册按钮不显示 " + this.lsbuttonVisible)
                        // 登录注册按钮不显示
                        this.lsbuttonVisible = false,
                        // 欢迎语
                        this.logintitle = getWelcomeText(),
                        // 用户昵称
                        this.nickname = userinfo.nickname
                        // 用户头像设置
                        if(userinfo.userprofilephoto){
                                this.avatarurl = userinfo.userprofilephoto
                        }
                        //邮箱未验证，弹提醒框
                        if(userinfo.status === 1){
                                this.$notify.info({
                                        title: '验证邮箱',
                                        message: '一封验证信已发至您的邮箱，点击连接即可完成验证。',
                                        offset: 60,
                                        duration: 6400
                                });
                        }
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
.sub-nickname {}

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