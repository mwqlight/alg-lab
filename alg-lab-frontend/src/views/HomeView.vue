<template>
  <div class="home" v-loading="loading">
    <h2>欢迎来到算法实验室</h2>
    <p>在这里您可以学习和演示各种算法的实现过程。</p>
    
    <el-row :gutter="20">
      <el-col :span="8" v-for="category in categories" :key="category.name">
        <el-card class="algorithm-category">
          <template #header>
            <div class="card-header">
              <span>{{ category.name }}</span>
            </div>
          </template>
          <div class="algorithm-list">
            <el-button 
              v-for="algorithm in category.algorithms" 
              :key="algorithm.id" 
              type="primary" 
              @click="goToAlgorithm(algorithm.id)"
            >
              {{ algorithm.name }}
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getAllAlgorithms } from '@/api/algorithm'

export default {
  name: 'HomeView',
  data() {
    return {
      loading: true,
      algorithms: [],
      categories: []
    }
  },
  async created() {
    await this.loadAlgorithms();
    this.organizeAlgorithmsByCategory();
  },
  methods: {
    async loadAlgorithms() {
      this.loading = true;
      try {
        const response = await getAllAlgorithms();
        this.algorithms = response.data;
      } catch (error) {
        console.error('获取算法列表失败:', error);
        this.$message.error('获取算法列表失败');
      } finally {
        this.loading = false;
      }
    },
    organizeAlgorithmsByCategory() {
      // 按分类组织算法
      const categoryMap = {};
      this.algorithms.forEach(algorithm => {
        if (!categoryMap[algorithm.category]) {
          categoryMap[algorithm.category] = {
            name: algorithm.category,
            algorithms: []
          };
        }
        categoryMap[algorithm.category].algorithms.push(algorithm);
      });
      
      this.categories = Object.values(categoryMap);
    },
    goToAlgorithm(id) {
      this.$router.push(`/algorithm/${id}`);
    }
  }
}
</script>

<style scoped>
.algorithm-category {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.algorithm-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.algorithm-list .el-button {
  width: 100%;
}
</style>