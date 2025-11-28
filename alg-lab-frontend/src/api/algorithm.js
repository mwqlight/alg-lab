import axios from 'axios'

const API_BASE_URL = '/api/algorithms'

// 创建 axios 实例
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000
})

// 获取所有算法
export const getAllAlgorithms = () => {
  return apiClient.get('/')
}

// 根据分类获取算法
export const getAlgorithmsByCategory = (category) => {
  return apiClient.get(`/category/${category}`)
}

// 根据ID获取算法详情
export const getAlgorithmById = (id) => {
  return apiClient.get(`/${id}`)
}

// 获取算法的动画步骤
export const getAnimationSteps = (id) => {
  return apiClient.get(`/${id}/animation`)
}

export default {
  getAllAlgorithms,
  getAlgorithmsByCategory,
  getAlgorithmById,
  getAnimationSteps
}