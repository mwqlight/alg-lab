<template>
  <div id="app">
    <el-container style="min-height: 100vh">
      <el-header class="header">
        <div class="header-content">
          <h1 class="title">算法实验室</h1>
          <p class="subtitle">探索算法的魅力，理解数据结构的奥秘</p>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer class="footer">
        <p>© 2024 算法实验室 | 用代码诠释算法之美</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
</script>

<style scoped>
.header {
  background: rgba(255, 255, 255, 0.95);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 0;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px 40px;
}

.title {
  font-size: 32px;
  font-weight: bold;
  margin: 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.subtitle {
  font-size: 16px;
  color: #666;
  margin: 5px 0 0 0;
}

.footer {
  background: rgba(0, 0, 0, 0.8);
  color: white;
  text-align: center;
  padding: 15px 0;
}

.footer p {
  margin: 0;
  font-size: 14px;
  opacity: 0.8;
}
</style>
