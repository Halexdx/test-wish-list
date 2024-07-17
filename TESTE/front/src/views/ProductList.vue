<script setup>
import CardList from '../components/CardListComponent.vue'
import Breadcrumb from '../components/BreadcrumbComponent.vue'
import getProducts from '@/client/products/ProductsClient';
import wishlistClient from '@/client/wishlist/WishListClient';
import { ref } from 'vue';

const products = ref([]);
const wishlist = ref([]);
const user = localStorage.getItem('user_id');

(async () => {
  const { items } = await getProducts();
  const wishItems = await wishlistClient.getWish(user);
  wishlist.value = wishItems.item;
  products.value = items.products;
})();

const addOrRemoveWish = (code) => {
  let wish = wishlist.value;
  if (wish.includes(code)){
    wishlistClient.removeWish(user, code);
    wish = wish.splice(wish.indexOf(code), 1);
    return wish;
  }
  const item = {
      user_id: user,
      product_code: code
    }
  wishlistClient.addWish(item);
  return wish.push(code);
  
}

</script>

<template>
  <main>
    <div class="container">
    <Breadcrumb :current="'Home'"/>
      <div class="main-content">
        <div v-if="products.length > 0">
          <CardList :productsList="products" :wishs="wishlist" @addOrRemoveWish="addOrRemoveWish"/>
        </div>
        <div v-else>
          <p>Loading...</p>
        </div>
      </div>
    </div>
  </main>
</template>
