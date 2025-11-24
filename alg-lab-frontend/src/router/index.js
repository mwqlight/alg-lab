import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AlgorithmDetail from '../views/AlgorithmDetail.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/algorithm/abbreviation/:abbreviation',
    name: 'AlgorithmDetailByAbbreviation',
    component: AlgorithmDetail,
    props: true
  },
  {
    path: '/algorithm/:id',
    name: 'AlgorithmDetail',
    component: AlgorithmDetail,
    props: true
  },
  {
    path: '/algorithm/name/:name',
    name: 'AlgorithmDetailByName',
    component: AlgorithmDetail,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
