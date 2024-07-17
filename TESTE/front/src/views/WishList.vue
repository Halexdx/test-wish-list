<script setup>
  import Breadcrumb from '../components/BreadcrumbComponent.vue'
  import CardList from '../components/CardListComponent.vue'
  import EmptyWishComponent from '../components/EmptyWishComponent.vue'
  import getProducts from '@/client/products/ProductsClient';
  import wishlistClient from '@/client/wishlist/WishListClient';
  import { ref,onMounted } from 'vue';

  const previous = {
    name: 'Home',
    path: '/'
  }

  const products = ref([]);
  const loading = ref(true);
  const user = localStorage.getItem('user_id');
  console.log(products.value);

  onMounted(async () => {
    const { items } = await getProducts();
    const wishItems = await wishlistClient.getWish(user);
    const wishs = wishItems.item;
    loading.value = false;
    products.value = items.products.filter(p => wishs.includes(p.selectedProduct));
  });

  const RemoveWish =  (code) => {
    wishlistClient.removeWish(user, code);
    products.value = products.value.filter(p => p.selectedProduct !== code);
  }

</script>
<template>
  <div class="container">
    <Breadcrumb :previous="previous" :current="'WishList'"/>
    <div class="main-content">
      <div v-if="products.length > 0 && !loading">
        <CardList :productsList="products" :wishs="[]" :wishView="true" @RemoveWish="RemoveWish"/>
      </div>
      <div v-if="products.length == 0 && !loading">
        <EmptyWishComponent />
      </div>
    </div>
  </div>
</template>
