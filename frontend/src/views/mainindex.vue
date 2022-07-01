<template>
<div>
                <el-col :span="18" class="el-col-main-left">
                        <!-- 默认跳转mianblog -->
                        <router-view></router-view>
                </el-col>
                <el-col :span="4">
                        <!-- main侧边栏-->
                        <el-row class="el-row-aside">
                                <!-- 用户信息栏 -->
                                <el-aside class="el-aside-style">
                                        <Userinfo></Userinfo>
                                </el-aside>
                                <!-- 最新发布 -->
                                <!--<el-aside class="el-aside-style">
                                        <Newblog></Newblog>
                                </el-aside> -->
                                <!-- 分类 -->
                                <el-aside class="el-aside-style">
                                        <Sort></Sort>
                                </el-aside>
                                <!-- 标签栏 -->
                                <el-aside class="el-aside-style">
                                        <Tag></Tag>
                                </el-aside>
                        </el-row>
                </el-col>
</div>
</template>

<script>
import Userinfo from "./mainview/aside/userinfo.vue"
import Mainblog from "./mainview/mainblog.vue";
import Tag from "./mainview/aside/tagpage.vue"
import Sort from "./mainview/aside/sortpage.vue"
import Newblog from "./mainview/aside/newbloglist.vue"

export default {
        components: { Sort, Tag, Mainblog, Userinfo, Newblog },
        name: 'mainindex',
        data() {
                return new Object
        },

        mounted() {

        },


}

</script>
<style>
.el-col.el-col-main-left {
        background-color: white;
        min-height: 100%;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

.el-aside {
        background-color: white;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
        min-height: 100%;
        padding: 20px;
}

.el-row.el-row-aside {
        padding-left: 20px;
        padding-right: 0;
        min-width: 20%;
        height: 100%;
}

.el-footer.el-aside-style,
.el-aside.el-aside-style {
        width: 25%;
        height: 100%;
        margin-bottom: 10px;
        border-radius: 4px
}
</style>