<template>
  <div class="home-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="category-card">
          <template #header>
            <span>算法分类</span>
          </template>
          <el-tree
            :data="categoryTree"
            :props="treeProps"
            @node-click="handleCategoryClick"
            default-expand-all
            style="max-height: 600px; overflow-y: auto"
          />
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="algorithms-card">
          <template #header>
            <div class="header-content">
              <span>{{ currentCategory ? `${currentCategory} - ${currentSubcategory || '全部算法'}` : '所有算法' }}</span>
              <div class="header-actions">
                <el-input
                  v-model="searchKeyword"
                  placeholder="搜索算法"
                  size="small"
                  style="width: 200px; margin-right: 10px"
                  @keyup.enter="searchAlgorithms"
                >
                  <template #prefix>
                    <el-icon><Search /></el-icon>
                  </template>
                </el-input>
                <el-button
                  size="small"
                  type="primary"
                  @click="searchAlgorithms"
                >
                  搜索
                </el-button>
                <el-button
                  size="small"
                  type="primary"
                  @click="refreshAlgorithms"
                  style="margin-left: 10px"
                >
                  <el-icon><Refresh /></el-icon>
                  刷新
                </el-button>
              </div>
            </div>
          </template>
          <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="8" v-for="algorithm in algorithms" :key="algorithm.id">
              <el-card
                class="algorithm-card"
                @click="goToAlgorithm(algorithm)"
              >
                <template #header>
                  <span class="algorithm-name">{{ algorithm.name }}</span>
                  <span v-if="algorithm.abbreviation" class="algorithm-abbreviation">({{ algorithm.abbreviation }})</span>
                </template>
                <div class="algorithm-info">
                  <p class="category-tag">
                    <el-tag size="small">{{ algorithm.category }}</el-tag>
                    <el-tag size="small" type="success">{{ algorithm.subcategory }}</el-tag>
                  </p>
                  <p class="description-preview">{{ algorithm.description }}</p>
                  <div class="complexity-info">
                    <span class="time-complexity-text">时间: {{ algorithm.timeComplexity }}</span>
                    <span class="space-complexity-text">空间: {{ algorithm.spaceComplexity }}</span>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <div v-if="algorithms.length === 0" class="empty-state">
            <el-empty description="暂无算法数据" />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Refresh, Search } from '@element-plus/icons-vue'
import { algorithmApi } from '../api/algorithm'

const router = useRouter()
const algorithms = ref([])
const categories = ref([])
const currentCategory = ref('')
const currentSubcategory = ref('')
const searchKeyword = ref('')

const treeProps = {
  label: 'name',
  children: 'children'
}

const categoryTree = computed(() => {
  const tree = []
  const categoryMap = new Map()

  categories.value.forEach(category => {
    const categoryNode = {
      name: category,
      children: []
    }
    categoryMap.set(category, categoryNode)
    tree.push(categoryNode)
  })

  // 为每个分类添加子分类
  algorithms.value.forEach(algorithm => {
    if (categoryMap.has(algorithm.category)) {
      const categoryNode = categoryMap.get(algorithm.category)
      // 检查子分类是否已存在
      const subcategoryExists = categoryNode.children.some(child => child.name === algorithm.subcategory)
      if (!subcategoryExists) {
        categoryNode.children.push({ name: algorithm.subcategory })
      }
    }
  })

  return tree
})

const loadAlgorithms = async () => {
  try {
    let data
    if (currentCategory.value && currentSubcategory.value) {
      data = await algorithmApi.getAlgorithmsByCategoryAndSubcategory(
        currentCategory.value,
        currentSubcategory.value
      )
    } else if (currentCategory.value) {
      data = await algorithmApi.getAlgorithmsByCategory(currentCategory.value)
    } else {
      data = await algorithmApi.getAllAlgorithms()
    }
    algorithms.value = data
  } catch (error) {
    ElMessage.error('加载算法失败: ' + error.message)
  }
}

const searchAlgorithms = async () => {
  try {
    if (!searchKeyword.value.trim()) {
      await loadAlgorithms()
      return
    }
    const data = await algorithmApi.getAlgorithmsByName(searchKeyword.value)
    algorithms.value = data
  } catch (error) {
    ElMessage.error('搜索算法失败: ' + error.message)
  }
}

const loadCategories = async () => {
  try {
    const data = await algorithmApi.getAllCategories()
    categories.value = data
  } catch (error) {
    ElMessage.error('加载分类失败: ' + error.message)
  }
}

const handleCategoryClick = async (data) => {
  if (data.children && data.children.length > 0) {
    // 点击的是分类
    currentCategory.value = data.name
    currentSubcategory.value = ''
  } else {
    // 点击的是子分类
    currentSubcategory.value = data.name
    // 查找对应的分类
    const parentCategory = categories.value.find(category => {
      const categoryNode = categoryTree.value.find(node => node.name === category)
      return categoryNode && categoryNode.children.some(child => child.name === data.name)
    })
    if (parentCategory) {
      currentCategory.value = parentCategory
    }
  }
  await loadAlgorithms()
}

const goToAlgorithm = (algorithm) => {
  if (algorithm.abbreviation) {
    router.push(`/algorithm/abbreviation/${algorithm.abbreviation}`)
  } else {
    router.push(`/algorithm/${algorithm.id}`)
  }
}

const refreshAlgorithms = async () => {
  await loadAlgorithms()
  ElMessage.success('已刷新')
}

onMounted(async () => {
  await loadCategories()
  await loadAlgorithms()
})
</script>

<style scoped>
.home-container {
  padding: 20px 0;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-actions {
  display: flex;
  align-items: center;
}

.category-card {
  height: 100%;
  border-radius: 12px;
}

.algorithms-card {
  height: 100%;
  border-radius: 12px;
}

.algorithm-card {
  cursor: pointer;
  height: 100%;
  transition: all 0.3s ease;
}

.algorithm-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.algorithm-name {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.algorithm-abbreviation {
  font-size: 14px;
  color: #667eea;
  margin-left: 8px;
  font-weight: normal;
}

.algorithm-info {
  padding: 10px 0;
}

.category-tag {
  margin-bottom: 10px;
}

.category-tag .el-tag {
  margin-right: 5px;
}

.description-preview {
  font-size: 14px;
  color: #666;
  line-height: 1.5;
  margin-bottom: 10px;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.complexity-info {
  font-size: 12px;
  color: #888;
}

.time-complexity-text {
  margin-right: 15px;
}

.empty-state {
  padding: 40px 0;
}
</style>
