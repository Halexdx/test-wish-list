<script setup>
import { RouterView } from 'vue-router'
import HeaderComponentVue from './components/HeaderComponent.vue'
import { ref, onMounted } from 'vue';

const generateRandomId = () => {
  return Math.floor(Math.random() * (999999 - 100000 + 1)) + 100000;
};

const userID = ref('');

onMounted(() => {
  let idFromStorage = localStorage.getItem('user_id');
  if (!idFromStorage) {
    idFromStorage = generateRandomId();
    localStorage.setItem('user_id', idFromStorage);
  }
  userID.value = idFromStorage;
});
</script>

<template>
  <div>
    <HeaderComponentVue />
    <RouterView />
  </div>
</template>

<style scoped>
</style>
