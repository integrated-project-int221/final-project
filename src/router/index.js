import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/teammate',
    name: 'Teammate',
    component: () => import('../views/Teammate.vue')
  },
  {
    path: '/addproduct',
    name: 'AddProduct',
    component: () => import('../views/AddProduct.vue')
  },
  {
    path: '/productlist',
    name: 'ProductList',
    component: () => import('../views/ProductList.vue')
  },
  {
    path: '/editproduct/:id',
    name: 'EditProduct',
    component: () => import('../views/EditProduct.vue'),
    props: true,

  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
