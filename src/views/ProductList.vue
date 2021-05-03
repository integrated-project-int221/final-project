<template>
  <div class="product-list ">
    <div class="">
      <button class="border border-black p-3" @click="this.$router.go(-1)"> Back </button>
    </div>
    <div class="flex items-center justify-around">
      <div class="grid grid-cols-2 gap-3 lg:grid-cols-3 xl:grid-cols-4">
        <!-- <slot></slot> -->
        <product-item
          v-for="product in productResults"
          :key="product.id"
          :product="product"
        ></product-item>
      </div>
    </div>
  </div>
</template>

<script>
import ProductItem from "../components/Product.vue";

export default {
  components: { ProductItem },
  data() {
    return {
      productResults: [],
    };
  },
  methods: {
    async fetchProductResult() {
      const res = await fetch("http://localhost:4001/products/");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.productResults = await this.fetchProductResult();
  },
};
</script>