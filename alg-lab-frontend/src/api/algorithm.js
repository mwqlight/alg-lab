import request from '../utils/request'

export const algorithmApi = {
  getAllAlgorithms: () => request.get('/algorithms'),
  getAlgorithmById: (id) => request.get(`/algorithms/${id}`),
  getAlgorithmByName: (name) => request.get(`/algorithms/name/${name}`),
  getAlgorithmsByCategory: (category) => request.get(`/algorithms/category/${category}`),
  getAlgorithmsByCategoryAndSubcategory: (category, subcategory) => 
    request.get(`/algorithms/category/${category}/subcategory/${subcategory}`),
  getAllCategories: () => request.get('/algorithms/categories'),
  getSubcategoriesByCategory: (category) => 
    request.get(`/algorithms/categories/${category}/subcategories`),
  createAlgorithm: (data) => request.post('/algorithms', data),
  updateAlgorithm: (id, data) => request.put(`/algorithms/${id}`, data),
  deleteAlgorithm: (id) => request.delete(`/algorithms/${id}`)
}
