<script setup>
</script>

<template>
  <div class="wrapper-header">
    <div class="wrapper-icon">
      <img src="../assets/logo-net.svg" className="app-icon" alt="logo" />
    </div>

    <div class="wrapper-wish-list flex-center">
      <RouterLink to="/wishlist" class="wrapper-wish-list flex-center link">
        <img src="../assets/heart.svg" class="wishlist-icon" alt="lista de desejos">
        <p class="wish-list-text"><strong>WishList</strong></p>
      </RouterLink>
    </div>

    <div class="wrapper-user-login flex-center">
      <a href="">
        <img src="../assets/user-icon.png" class="user-icon" alt="icone de usuário">
      </a>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .wrapper-header {
    background-color: #8200FF;
    height: 79px;
    padding: 0 20px 0 20px;
    display: grid;
    grid-template-columns: auto auto auto auto auto auto;

    .wrapper-icon {
      grid-column: 1 / 5;
      display: flex;
      align-items: center;
      .app-icon {
        width: 160px;
      }
    }

    .wrapper-wish-list {
      &.link {
        text-decoration: none;
      }

      .wishlist-icon {
        width: 25px;
        display: block;
        margin-right: 8px;
      }

      .wish-list-text {
        font-size: 14px;
        color: #FFF;
      }
    }

    .wrapper-user-login {
      .user-icon {
        width: 25px;
      }
    }
  }
</style>