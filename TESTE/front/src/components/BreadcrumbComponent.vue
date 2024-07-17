<script>
export default {
  props: [
    'previous',
    'current'
  ]
}
</script>

<template>
 <div class="breadcrumb flex-start-center">
  <span v-if="previous"><RouterLink :to="previous.path">{{previous.name}}</RouterLink> / </span> 
  <strong>{{current}}</strong>
 </div>
</template>

<style lang="scss" scoped>
  .breadcrumb {
    height: 50px;
    color: #8200FF;
    font-size: 14px;
    border-bottom: 1px solid rgba(130, 0, 255, .1);

    a {
      padding: 2px;
      text-decoration: none;
    }

    strong {
      padding: 2px;
    }
  }
</style>