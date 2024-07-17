<template>
  <div class="wrapper-cards">
    <div class="card" v-for="(p, index) in productList" :key="index">
        <div>
          <img width="100%"
          :src="p.product.image" 
          alt="fotinha do tenis">
          <div class="card-content">
            <p class="description">{{p.product.details.name}}</p>
            <div class="wrapper-rating">
              <img src="../assets/star.png" class="rating" alt="avaliação" width="15px" v-for="n in 5" :key="n">
              <span>(5.0)</span>
            </div>
            <div class="wrapper-prices">
            <p class="old-price"><span>100,00</span></p>
            <p class="new-price"><strong>50,00</strong></p>
            </div>
          </div>
          <div class="wish-button">
            <button v-if="!isWishView" class="add-to-wish-button" 
              :class="{ active: wishList.includes(p.selectedProduct) }"
              @click="$emit('addOrRemoveWish', p.selectedProduct)">
              <img src="../assets/heart.svg" class="add-to-wish" alt="adicionar a lista de desejos" width="90%">
            </button>

            <button v-if="isWishView" class="remove-to-wish-button" 
              :class="{ active: wishList.includes(p.selectedProduct) }"
              @click="$emit('RemoveWish', p.selectedProduct)">
            </button>
          </div>
        </div>
    </div>
  </div>
</template>

<script setup>
import { computed, defineProps} from 'vue';

const props = defineProps({
  productsList: Array,
  wishs: Array,
  wishView: Boolean,
});

const productList = computed(() => props.productsList);
const wishList = computed(() => props.wishs);
const isWishView = computed(() => props.wishView);

</script>

<style lang="scss" scoped>
  .wrapper-cards {
    padding: 30px 50px;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
  }

  .card {
    width: 70%;
    border-radius: 2px;
    box-shadow: 2px 0px 6px 2px rgba(0, 0, 0, 0.5);
    justify-self: center;
    position: relative;

    .card-content {
      padding: 0 20px;

      p {
        margin: 0;
      }

      .old-price { 
        span {
          color: #8c8c8c;
          text-decoration: line-through;
          font-size: 0.8rem;
          line-height: .71429rem;
        }
      }
      
      .new-price {
        margin: 0;
        color: #8200FF;
        font-size: 1.3rem;
        font-weight: 700;
      }

      .wrapper-rating { 
        display: flex;
        justify-content: start;
        align-items: center;
      }
    } 

    .wish-button {
      position: absolute;
      top: 1%;
      right: 1%;

      .add-to-wish-button {
        background-color: #ccc;
        border: none;
        border-radius: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 35px;
        height: 35px;
        cursor: pointer;

        &:hover , &.active{
          background-color: #ff2a00;
        }
      }

      .remove-to-wish-button {
        position: relative;
        border: none;
        background: none;
        width: 15px;
        height: 15px;

        &:before {
          content: "";
          height: 15px;
          width: 2px;
          display: block;
          background: #000;
          rotate: 45deg;
          position: absolute;
          top: 0;
        }
        &:after { 
          content: "";
          height: 15px;
          width: 2px;
          display: block;
          background: #000;
          rotate: 135deg;
          position: absolute;
          top: 0;
        }
      }
    }
  }
</style>