import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AlgorithmView from '../views/AlgorithmView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/algorithm/:id',
    name: 'algorithm',
    component: AlgorithmView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router