<template>
  <div class="algorithm-detail-container">
    <el-card class="detail-card">
      <template #header>
        <div class="header-content">
          <span class="algorithm-title">{{ algorithm.name }}</span>
          <el-button
            size="small"
            type="primary"
            @click="goBack"
          >
            <el-icon><ArrowLeft /></el-icon>
            返回首页
          </el-button>
        </div>
      </template>
      
      <div class="algorithm-meta">
        <el-tag size="large">{{ algorithm.category }}</el-tag>
        <el-tag size="large" type="success">{{ algorithm.subcategory }}</el-tag>
      </div>

      <div class="description-section">
        <h3>算法描述</h3>
        <p class="description">{{ algorithm.description }}</p>
      </div>

      <div class="code-section">
        <h3>代码示例</h3>
        <pre class="code-block"><code>{{ algorithm.codeExample }}</code></pre>
      </div>

      <div class="complexity-section">
        <h3>复杂度分析</h3>
        <div class="time-complexity">
          <strong>时间复杂度:</strong> {{ algorithm.timeComplexity }}
        </div>
        <div class="space-complexity">
          <strong>空间复杂度:</strong> {{ algorithm.spaceComplexity }}
        </div>
      </div>

      <div class="animation-section">
        <h3>动画演示</h3>
        <div class="animation-controls">
          <el-button type="primary" @click="startAnimation" :disabled="isPlaying">
            <el-icon><Play /></el-icon>
            开始演示
          </el-button>
          <el-button @click="pauseAnimation" :disabled="!isPlaying">
            <el-icon><Pause /></el-icon>
            暂停
          </el-button>
          <el-button @click="resetAnimation">
            <el-icon><Refresh /></el-icon>
            重置
          </el-button>
          <el-slider
            v-if="animationSteps.length > 0"
            v-model="currentStep"
            :min="0"
            :max="maxStep"
            @change="goToStep"
            style="flex: 1; margin: 0 20px"
          />
          <span class="step-info">{{ currentStep + 1 }} / {{ maxStep + 1 }}</span>
          <el-slider
            v-model="playSpeed"
            :min="200"
            :max="2000"
            :step="200"
            style="width: 150px; margin-left: 20px"
            :format-tooltip="(value) => `${value}ms`"
          />
        </div>
        <!-- 排序算法动画展示 -->
        <div v-if="algorithm.category === '排序算法'" class="animation-container">
          <div
            v-for="(value, index) in currentArray"
            :key="index"
            class="array-bar"
            :class="{
              'compared': comparedIndices.includes(index),
              'swapped': swappedIndices.includes(index),
              'sorted': sortedIndices.includes(index)
            }"
            :style="{ height: `${(value / arrayMax) * 150}px` }"
          >
            <span class="array-value">{{ value }}</span>
          </div>
        </div>
        
        <!-- 活动选择问题动画展示 -->
        <div v-else-if="algorithm.abbreviation === 'activity-selection'" class="activity-animation-container">
          <div class="time-axis">
            <div v-for="time in timeAxis" :key="time" class="time-mark">
              <span class="time-label">{{ time }}</span>
            </div>
          </div>
          <div class="activities-container">
            <div
              v-for="(activity, index) in currentActivities"
              :key="index"
              class="activity-bar"
              :class="{
                'selected': selectedIndices.includes(index),
                'current': currentIndex === index,
                'compared': comparedIndices.includes(index)
              }"
              :style="{
                left: `${activity.start * 10}%`,
                width: `${(activity.end - activity.start) * 10}%`
              }"
            >
              <span class="activity-label">{{ index + 1 }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="analysis-section">
        <h3>算法分析</h3>
        <div class="analysis">
          {{ algorithm.analysis }}
        </div>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ArrowLeft, VideoPlay as Play, VideoPause as Pause, Refresh } from '@element-plus/icons-vue'
import { algorithmApi } from '../api/algorithm'

const route = useRoute()
const router = useRouter()
const algorithm = ref({
  name: '',
  category: '',
  subcategory: '',
  description: '',
  codeExample: '',
  timeComplexity: '',
  spaceComplexity: '',
  animationData: '[]',
  analysis: ''
})

const animationSteps = ref([])
const currentStep = ref(0)
const isPlaying = ref(false)
const intervalId = ref(null)
const currentArray = ref([])
const comparedIndices = ref([])
const swappedIndices = ref([])
const sortedIndices = ref([])
const currentActivities = ref([])
const selectedIndices = ref([])
const currentIndex = ref(-1)
const timeAxis = ref([])
const playSpeed = ref(1000) // 默认播放速度为1秒

const maxStep = computed(() => Math.max(0, animationSteps.value.length - 1))

const arrayMax = computed(() => {
  if (currentArray.value.length === 0) return 1
  return Math.max(...currentArray.value)
})

const loadAlgorithm = async () => {
  try {
    let data
    if (route.params.id) {
      data = await algorithmApi.getAlgorithmById(route.params.id)
    } else if (route.params.name) {
      data = await algorithmApi.getAlgorithmByName(route.params.name)
    } else if (route.params.abbreviation) {
      data = await algorithmApi.getAlgorithmByAbbreviation(route.params.abbreviation)
    } else {
      // 无有效路由参数时直接报错
      ElMessage.error('缺少有效算法参数')
      goBack()
      return
    }
    
    if (data) {
      algorithm.value = data
      parseAnimationData(data.animationData)
    } else {
      ElMessage.error('算法不存在')
      goBack()
    }
  } catch (error) {
    ElMessage.error('加载算法失败: ' + error.message)
    goBack()
  }
}

const parseAnimationData = (animationData) => {
  try {
    animationSteps.value = JSON.parse(animationData)
    if (animationSteps.value.length > 0) {
      resetAnimation()
    }
  } catch (error) {
    console.error('解析动画数据失败:', error)
    animationSteps.value = []
  }
}

const startAnimation = () => {
  if (animationSteps.value.length === 0) {
    ElMessage.info('暂无动画数据')
    return
  }
  
  isPlaying.value = true
  playNextStep()
}

const playNextStep = () => {
  if (currentStep.value >= maxStep.value) {
    stopAnimation()
    ElMessage.success('动画演示完成')
    return
  }
  
  intervalId.value = setTimeout(() => {
    currentStep.value++
    updateAnimation()
    playNextStep()
  }, playSpeed.value)
}

const pauseAnimation = () => {
  isPlaying.value = false
  if (intervalId.value) {
    clearTimeout(intervalId.value)
    intervalId.value = null
  }
}

const stopAnimation = () => {
  pauseAnimation()
  currentStep.value = maxStep.value
  updateAnimation()
}

const resetAnimation = () => {
  pauseAnimation()
  currentStep.value = 0
  updateAnimation()
}

const goToStep = (step) => {
  currentStep.value = step
  updateAnimation()
}

const updateAnimation = () => {
  if (animationSteps.value.length === 0) return
  
  const step = animationSteps.value[currentStep.value]
  
  // 处理排序算法动画
  if (algorithm.value.category === '排序算法') {
    currentArray.value = step.array || []
    comparedIndices.value = step.compared || []
    sortedIndices.value = step.sorted || []
    
    // 处理swapped字段，如果是布尔值则表示上一步是否交换了元素
    if (typeof step.swapped === 'boolean') {
      // 如果是交换步骤，高亮显示最后两个比较的元素
      if (step.swapped && comparedIndices.value.length >= 2) {
        swappedIndices.value = comparedIndices.value.slice(-2)
      } else {
        swappedIndices.value = []
      }
    } else {
      // 如果是数组，则直接使用
      swappedIndices.value = step.swapped || []
    }
    
    // 如果是最后一步，标记所有元素为已排序
    if (currentStep.value === maxStep.value) {
      sortedIndices.value = Array.from({ length: currentArray.value.length }, (_, i) => i)
    }
  }
  // 处理活动选择问题动画
  else if (algorithm.value.abbreviation === 'activity-selection') {
    currentActivities.value = step.activities || []
    selectedIndices.value = step.selected || []
    currentIndex.value = step.current || -1
    comparedIndices.value = step.compared || []
    
    // 生成时间轴
    if (currentActivities.value.length > 0) {
      const maxEnd = Math.max(...currentActivities.value.map(activity => activity.end))
      timeAxis.value = Array.from({ length: maxEnd + 1 }, (_, i) => i)
    }
  }
}

const goBack = () => {
  router.push('/')
}

onMounted(async () => {
  await loadAlgorithm()
})

watch(() => route.params, async () => {
  await loadAlgorithm()
}, { deep: true })
</script>

<style scoped>
.algorithm-detail-container {
  padding: 20px 0;
}

.detail-card {
  border-radius: 12px;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.algorithm-title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.algorithm-meta {
  margin: 20px 0;
}

.algorithm-meta .el-tag {
  margin-right: 10px;
}

.description-section,
.code-section,
.complexity-section,
.animation-section,
.analysis-section {
  margin: 30px 0;
}

h3 {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-bottom: 15px;
  padding-bottom: 5px;
  border-bottom: 2px solid #667eea;
  display: inline-block;
}

.animation-controls {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 15px;
  background: #f5f5f5;
  border-radius: 8px;
}

.animation-controls .el-button {
  margin-right: 10px;
}

.step-info {
  font-size: 14px;
  color: #666;
  min-width: 80px;
  text-align: right;
}
/* 活动选择问题动画样式 */
.activity-animation-container {
  width: 100%;
  height: 200px;
  background-color: #f5f7fa;
  border-radius: 8px;
  padding: 20px;
  margin-top: 20px;
  position: relative;
}

.time-axis {
  width: 100%;
  height: 20px;
  background-color: #e4e7ed;
  border-radius: 10px;
  position: relative;
  margin-bottom: 30px;
}

.time-mark {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 1px;
  background-color: #c0c4cc;
  transform: translateX(-50%);
}

.time-mark:nth-child(odd) .time-label {
  display: block;
}

.time-mark:nth-child(even) .time-label {
  display: none;
}

.time-label {
  position: absolute;
  top: 25px;
  font-size: 12px;
  color: #606266;
  transform: translateX(-50%);
}

.activities-container {
  width: 100%;
  height: 100px;
  position: relative;
}

.activity-bar {
  position: absolute;
  top: 0;
  height: 40px;
  background-color: #67c23a;
  border-radius: 20px;
  margin: 0 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 14px;
  font-weight: bold;
  transition: all 0.3s ease;
  opacity: 0.7;
}

.activity-bar:hover {
  opacity: 1;
  transform: translateY(-2px);
}

.activity-bar.selected {
  background-color: #409eff;
  opacity: 1;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.4);
}

.activity-bar.current {
  background-color: #e6a23c;
  opacity: 1;
  transform: scale(1.1);
  box-shadow: 0 4px 12px rgba(230, 162, 60, 0.4);
}

.activity-bar.compared {
  background-color: #f56c6c;
  opacity: 1;
}

.activity-label {
  pointer-events: none;
}
</style>
