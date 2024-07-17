import { createRouter, createWebHistory } from 'vue-router'
import Product from '../views/ProductList.vue'
import WishList from '../views/WishList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Produtos',
      component: Product
    },
    {
      path: '/wishlist',
      name: 'Lista de desejos',
      component: WishList
    }
  ]
})

export default router
