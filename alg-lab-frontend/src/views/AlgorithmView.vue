<template>
  <div class="algorithm-detail" v-loading="loading">
    <el-page-header @back="goBack">
      <template #content>
        <span>{{ algorithm.name }}</span>
      </template>
    </el-page-header>
    
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="16">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>算法描述</span>
            </div>
          </template>
          <div v-html="algorithm.description"></div>
        </el-card>
        
        <el-card style="margin-top: 20px;">
          <template #header>
            <div class="card-header">
              <span>代码示例</span>
            </div>
          </template>
          <pre><code>{{ algorithm.codeExample }}</code></pre>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>复杂度分析</span>
            </div>
          </template>
          <div>
            <p><strong>时间复杂度:</strong> {{ algorithm.timeComplexity }}</p>
            <p><strong>空间复杂度:</strong> {{ algorithm.spaceComplexity }}</p>
          </div>
        </el-card>
        
        <el-card style="margin-top: 20px;">
          <template #header>
            <div class="card-header">
              <span>算法分析</span>
            </div>
          </template>
          <div v-html="algorithm.analysis"></div>
        </el-card>
        
        <el-card style="margin-top: 20px;">
          <template #header>
            <div class="card-header">
              <span>动画演示</span>
            </div>
          </template>
          <div class="animation-container">
            <div class="array-display">
              <div 
                v-for="(item, index) in currentArray" 
                :key="index"
                class="array-item"
                :class="{
                  'compared': comparedIndices.includes(index),
                  'swapped': swappedIndices.includes(index)
                }"
              >
                {{ item }}
              </div>
            </div>
            <div class="animation-controls">
              <el-button @click="playAnimation" :disabled="isPlaying || animationData.length === 0">播放</el-button>
              <el-button @click="pauseAnimation" :disabled="!isPlaying">暂停</el-button>
              <el-button @click="resetAnimation">重置</el-button>
            </div>
            <div class="step-info">
              步骤: {{ currentStep }}/{{ totalSteps }}
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getAlgorithmById, getAnimationSteps } from '@/api/algorithm'

export default {
  name: 'AlgorithmView',
  data() {
    return {
      algorithm: {},
      animationData: [],
      currentStep: 0,
      isPlaying: false,
      animationInterval: null,
      loading: true
    }
  },
  computed: {
    currentArray() {
      if (this.currentStep > 0 && this.animationData.length > 0) {
        try {
          return JSON.parse(this.animationData[this.currentStep - 1].arrayState);
        } catch (e) {
          return [];
        }
      }
      return this.animationData.length > 0 ? JSON.parse(this.animationData[0].arrayState) : [];
    },
    comparedIndices() {
      if (this.currentStep > 0 && this.animationData.length > 0) {
        try {
          return JSON.parse(this.animationData[this.currentStep - 1].comparedIndices);
        } catch (e) {
          return [];
        }
      }
      return [];
    },
    swappedIndices() {
      if (this.currentStep > 0 && this.animationData.length > 0) {
        return this.animationData[this.currentStep - 1].swapped ? this.comparedIndices : [];
      }
      return [];
    },
    totalSteps() {
      return this.animationData.length;
    }
  },
  async created() {
    await this.loadAlgorithmData();
  },
  methods: {
    async loadAlgorithmData() {
      this.loading = true;
      try {
        // 获取算法详情
        const algorithmResponse = await getAlgorithmById(this.$route.params.id);
        this.algorithm = algorithmResponse.data;
        
        // 获取动画步骤
        const animationResponse = await getAnimationSteps(this.$route.params.id);
        this.animationData = animationResponse.data;
      } catch (error) {
        console.error('获取算法数据失败:', error);
        this.$message.error('获取算法数据失败');
      } finally {
        this.loading = false;
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    playAnimation() {
      if (this.currentStep >= this.totalSteps) {
        this.currentStep = 0;
      }
      
      this.isPlaying = true;
      this.animationInterval = setInterval(() => {
        if (this.currentStep < this.totalSteps) {
          this.currentStep++;
        } else {
          this.pauseAnimation();
        }
      }, 1000);
    },
    pauseAnimation() {
      this.isPlaying = false;
      if (this.animationInterval) {
        clearInterval(this.animationInterval);
        this.animationInterval = null;
      }
    },
    resetAnimation() {
      this.pauseAnimation();
      this.currentStep = 0;
    }
  },
  beforeUnmount() {
    this.pauseAnimation();
  }
}
</script>

<style scoped>
.algorithm-detail {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.array-display {
  display: flex;
  justify-content: center;
  margin: 20px 0;
}

.array-item {
  width: 40px;
  height: 40px;
  border: 1px solid #409EFF;
  margin: 0 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  background-color: #ecf5ff;
  transition: all 0.3s;
}

.array-item.compared {
  background-color: #ffd700;
}

.array-item.swapped {
  background-color: #ff6b6b;
  color: white;
}

.animation-controls {
  text-align: center;
  margin: 20px 0;
}

.step-info {
  text-align: center;
  font-weight: bold;
}

pre {
  background-color: #f5f5f5;
  padding: 10px;
  border-radius: 4px;
  overflow-x: auto;
}
</style>