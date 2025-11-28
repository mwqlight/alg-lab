<template>
  <div id="app">
    <el-container>
      <el-header>
        <h1>算法实验室</h1>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @select="handleSelect"
          >
            <el-menu-item index="1">
              <span>首页</span>
            </el-menu-item>
            <el-sub-menu index="2">
              <template #title>
                <span>排序算法</span>
              </template>
              <el-menu-item index="2-1">冒泡排序</el-menu-item>
              <el-menu-item index="2-2">快速排序</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <span>搜索算法</span>
              </template>
              <el-menu-item index="3-1">二分搜索</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      // 路由跳转逻辑将由 vue-router 处理
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header {
  background-color: #409EFF;
  color: #fff;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #f5f5f5;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #fff;
  color: #333;
  padding: 20px;
}

body > .el-container {
  margin-bottom: 40px;
}
</style>