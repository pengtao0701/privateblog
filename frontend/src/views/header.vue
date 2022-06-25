/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-18 15:27:19 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-23 00:30:51
 */
<template>
  <el-header class="top-header">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :span="16">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item>
            <!-- logo 跳转首页-->

            <el-avatar class="el-avatar-logo" shape="square" :size="50">
              <img src="../assets/images/logo.png" @click="reloadview" />
            </el-avatar>

          </el-menu-item>
          <el-menu-item class="el-icon-date" style="margin-left:20px">
              {{ datetimenow }}
          </el-menu-item>
          <el-row type="flex" class="row-menu" justify="end">

            <el-col :span="3">
              <!-- ssearch 搜索框 -->
              <div class="el-menu-col menu-search-col">
                <el-autocomplete class="menu-search-input" v-model="state" :fetch-suggestions="querySearchAsync"
                  placeholder="搜索" @select="handleSelect" prefix-icon="el-icon-search"></el-autocomplete>
              </div>
            </el-col>

            <el-col :span="4">
              <router-link style="text-decoration: none;" to="/toolview">
                <el-menu-item index="1" class="el-menu-col">小工具</el-menu-item>
              </router-link>
            </el-col>
            <el-col :span="4">
              <router-link style="text-decoration: none;" to="/apiview">
                <el-menu-item index="2" class="el-menu-col">测试API</el-menu-item>
              </router-link>
            </el-col>
            <el-col :span="4">
              <router-link style="text-decoration: none;" to="/aboutview">
                <el-menu-item index="3" class="el-menu-col">说明</el-menu-item>
              </router-link>
            </el-col>
          </el-row>
        </el-menu>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
export default {
  inject: ['reload'],
  data() {
    this.activeIndex = '1';
    return {
      // 搜索结果List
      restaurants: [],

      state: '',
      timeout: null,
      datetimenow: this.getdatetime(),
    };
  },

  mounted() {
    this.restaurants = this.loadAll();
  },

  handleSelect(key, keyPath) {
    console.log(key, keyPath);
  },

  methods: {
    // 重新刷新首页
    reloadview() {
      this.reload()
    },
    // 获取当前时间
    getdatetime() {
      var datetime = new Date()
      return datetime.getFullYear() + "/" + datetime.getMonth() + "/" + datetime.getDate();
    },

    // 搜索请求关键字
    loadAll() {
      // 搜索API return 返回结果List
      return [{ "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
      { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },];
    },
    querySearchAsync(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 3000);
    },
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    }
  },

  watch: {

  }
};
</script>

<style>
.a {
  text-decoration: none;
}

/* ---------header style start ----------*/
.el-header.top-header {
  background-color: white;
  width: 100%;
  padding: 0%;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

.row-menu {
  margin-left: 50%;
  text-align: center;
}


.el-menu-col.menu-search-col {
  width: 250px;
  margin-top: 10px;
  float: right;
}

.el-menu-item.el-menu-col {
  font-size: 15px;
}

.el-avatar.el-avatar-logo {
  background-color: white;
}

/* ---------header style end ----------*/
</style>