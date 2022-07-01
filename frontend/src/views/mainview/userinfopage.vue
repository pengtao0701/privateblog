<template>
  <el-row>

    <!--------------背景图------------------->
    <el-row class="user-bk-img">
      <img class="bk-img" :src="coverImgUrl ? coverImgUrl : basebkImg" />
    </el-row>

    <el-row type="flex" class="row-bg" justify="space-between">
      <el-col class="el-col-userprofilephoto">
        <img class="" :src="userprofilephoto ? userprofilephoto : avatarurl" />
      </el-col>
      <el-col>
        <!--------------编辑资料按钮-------------->
        <el-row type="flex" class="row-bg" justify="end">
          <div class="el-col-edit-userinfo-button">
            <el-button icon="el-icon-edit" type="primary" round>编辑资料</el-button>
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
        <el-tag size="mini" v-for="item in items" :key="item.label" :type="item.type" effect="dark">
          <span>{{ item.label }}</span>
        </el-tag>
      </el-col>
    </el-row>

    <br /><br />

    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane name="first">
       <span slot="label"><i class="icon-home"></i> 全 部</span>
        <el-skeleton :rows="6" animated :loading="true" />
        <el-divider></el-divider>
      </el-tab-pane>
       
      <el-tab-pane name="second" >
     <span slot="label"><i class="icon-bubbles3"></i> 评 论</span>
        <el-skeleton :rows="6" animated :loading="true" />
        <el-divider></el-divider>
      </el-tab-pane>
      <el-tab-pane name="third">
      <span slot="label"><i class="icon-forward"></i> 转 发</span>
        <el-skeleton :rows="6" animated :loading="true" />
        <el-divider></el-divider>
      </el-tab-pane>
      <el-tab-pane  name="fourth">
      <span slot="label"><i class="icon-heart"></i> 点 赞</span>
        <el-skeleton :rows="6" animated :loading="true" />
        <el-divider></el-divider>
      </el-tab-pane>
    </el-tabs>

  </el-row>
</template>

<script>
import BasebkImg from "../../assets/images/basebkImg.jpg"
import Avatarurl from "../../assets/images/visitor.png"
export default {
  name: 'userinfopage',
  data() {
    return {
      activeName: 'first',
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
  methods: {
    handleClick(tab, event) {
      //console.log(tab, event);
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
.bk-img {
  width: 100%;
  height: 250px;
}

.el-row.user-bk-img {
  margin: -20px;
}

.el-col.el-col-userprofilephoto {
  margin-top: -40px;
}

.el-col-edit-userinfo-button {
  padding-top: 40px;
}

.el-row.el-row-userinfo {
  margin-top: 20px;
  margin-bottom: 10px;
}

.icon-home{
  content: "\e900";
}
.icon-bubbles3{
  content: "\e96f";
}

.icon-heart:before{
  content: "\e9da";
}
.icon-forward{
  content: "\e969";
}

.el-tabs__item {
  font-size: 16px;
  font-family:"Microsoft Yahei" ;
  width: 227px;
  text-align: center;
}
</style>