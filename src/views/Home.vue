<template>
  <div class="home">
    <div class="container">
      <section
        class="bg-image-slide w-full mx-auto bg-nordic-gray-light flex pt-12 md:pt-0 md:items-center bg-cover bg-right"
        style="max-width: 1600px; height: 32rem"
      >
        <div class="container mx-auto">
          <div
            class="flex flex-col w-full lg:w-1/2 justify-center items-start px-6 tracking-wide"
          >
            <h1 class="text-black text-2xl my-4">
              Stripy Zig Zag Jigsaw Pillow and Duvet Set
            </h1>
            <a
              class="text-xl inline-block no-underline border-b border-gray-600 leading-relaxed hover:text-black hover:border-black"
              href="#"
              >products</a
            >
          </div>
        </div>
      </section>
      <div class="flex justify-around">
        <div>Products</div>
        <div>
          <button class="" @click="goProductList">show more>></button>
        </div>
      </div>

      <!--product-->
      <div class="flex items-center justify-around">
        <div class="grid grid-cols-2 gap-3 lg:grid-cols-3 xl:grid-cols-4">
          <product
            v-for="product in productResults"
            :key="product.id"
            :product="product"
            @deleteReview="deleteArray"
          ></product>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import product from "../components/Product.vue";

export default {
  name: "Home",
  components: {
    product,
  },
  data() {
    return {
      productResults: [],
      openModal: false,
    };
  },
  methods: {
    async fetchProductResult() {
      const res = await fetch("http://localhost:4001/products/");
      const data = await res.json();
      return data;
    },

    deleteArray(id) {
      this.productResults = this.productResults.filter((product) => {
        return product.id !== id;
      });
    },
    editReview() {},
    toggleEditModal() {
      this.openModal = !this.openModal;
    },
    goProductList() {
      this.$router.push("/productlist");
    },
    // async fetchImageResult() {
    //   const res = await fetch("http://localhost:4001/images/get/"+{{productResults.productname}});
    //   const data = await res.json();
    //   return data;
    // },
  },
  async created() {
    this.productResults = await this.fetchProductResult();
  },
};
</script>

<style scoped>
.bg-image-slide {
  background-image: url("../assets/HomeGuitar2.jpg");
}
</style>