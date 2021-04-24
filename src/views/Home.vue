<template>
  <div class="home">
    <div class="container">
      <h2>Product List - DB JSON file</h2>
      <div class="grid grid-cols-3 gap-3">
        <productList
          v-for="product in productResults"
          :key="product.id"
          :product="product"
        ></productList>
        <!-- <pre>{{ productResults }}</pre> -->
      </div>
      <div>
        <p class="text-xl font-semibold">&#60; 1 &#62;</p>
      </div>
    </div>
  </div>
</template>

<script>
import productList from "../components/ProductList.vue";

export default {
  name: "Home",
  components: {
    productList,
  },
  data() {
    return {
      productResults: [],
    };
  },
  methods: {
    async fetchProductResult() {
      const res = await fetch("http://localhost:4001/products");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.productResults = await this.fetchProductResult();
  },
};
</script>
