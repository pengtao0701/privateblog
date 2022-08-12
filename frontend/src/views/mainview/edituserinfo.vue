<template>
        <el-row>

                <!--------------背景图------------------->
                <el-upload class="upload-demo" 
                drag action="https://jsonplaceholder.typicode.com/posts/" 
                multiple
                 :show-file-list="false"
                >
                        <i class="el-icon-upload"></i>
                        <el-row class="user-bk-img">
                                <img class="bk-img" :src="coverImgUrl ? coverImgUrl : basebkImg" />
                        </el-row>
                        </el-upload>
                        <el-row type="flex" class="row-bg" justify="space-between">
                                <el-col class="el-col-userprofilephoto">
                                        <img class="" :src="userprofilephoto ? userprofilephoto : avatarurl" />
                                </el-col>
                                <el-col>
                                        <!--------------编辑资料按钮-------------->
                                        <el-row type="flex" class="row-bg" justify="end">
                                                <div class="el-col-edit-userinfo-button">
                                                        <el-button icon="el-icon-edit" type="primary" round>编辑资料
                                                        </el-button>
                                                </div>
                                        </el-row>
                                </el-col>
                        </el-row>

                        <!--------------用户昵称-------------->
                        <el-row class="el-row-userinfo">
                                <el-col>
                                        {{ nickname }}
                                </el-col>
                        </el-row>

                        <!--------------个人简介-------------->
                        <el-row class="el-row-userinfo">
                                <el-col>
                                        {{ introduction }}
                                </el-col>
                        </el-row>

                        <!--------------个人标签-------------->
                        <br />
                        <el-row class="el-row-userinfo">
                                <el-col>
                                        <el-tag size="mini" v-for="item in items" :key="item.label" :type="item.type"
                                                effect="dark">
                                                <span>{{ item.label }}</span>
                                        </el-tag>
                                </el-col>
                        </el-row>
        </el-row>

</template>

<script>
import BasebkImg from "../../assets/images/basebkImg.jpg"
import Avatarurl from "../../assets/images/visitor.png"
export default {
        name: 'edituserinfo',
        data() {
                return {
                        // 用户昵称
                        nickname: '',
                        // 用户简介
                        introduction: '',
                        // 用户自定义背景图
                        coverImgUrl: '',
                        // 用户自定义头像
                        userprofilephoto: '',
                        // 默认背景图
                        basebkImg: BasebkImg,
                        // 默认头像
                        avatarurl: Avatarurl,
                        // 用户标签
                        items: [
                                { type: '', label: 'Java' },
                                { type: '', label: 'C#' },
                                { type: '', label: 'Vue' },
                                { type: '', label: 'Springboot' },
                                { type: '', label: 'Linux' }
                        ]
                }

        },
        mounted() {
                // 获取用户信息
                let userinfo = JSON.parse(localStorage.getItem('userinfo'));
                console.log("初始化从logcalStorage获得userinfo:" + userinfo)
                if (userinfo === null) return {};

                console.log("判断isLogin:" + userinfo.isLogin)
                if (userinfo.isLogin) {
                        // 用户昵称 
                        this.nickname = userinfo.nickname,
                                // 用户介绍
                                this.introduction = userinfo.introduction
                        // 用户头像设置
                        if (userinfo.userprofilephoto) {
                                this.avatarurl = userinfo.userprofilephoto
                        }

                }
        }
}

</script>
<style >
</style>