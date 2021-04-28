<template>
  <div class="home">
    <div class="container">
      <h2>Product List - DB JSON file</h2>
      <!-- <div class="grid grid-cols-2 gap-3 lg:grid-cols-3 xl:grid-cols-4"> -->
      <!-- <div class="grid grid-cols-3 gap-3"></div> -->

      <!-- <button
          class="absolute top-0 mt-32 left-0 bg-white rounded-full shadow-md h-12 w-12 text-2xl text-indigo-600 hover:text-indigo-400 focus:text-indigo-400 -ml-6 focus:outline-none focus:shadow-outline"
        >
          <span class="block" style="transform: scale(-1)">&#x279c;</span>
        </button>
        <button
          class="absolute top-0 mt-32 right-0 bg-white rounded-full shadow-md h-12 w-12 text-2xl text-indigo-600 hover:text-indigo-400 focus:text-indigo-400 -mr-6 focus:outline-none focus:shadow-outline"
        >
          <span class="block" style="transform: scale(1)">&#x279c;</span>
        </button> -->

      <carousel-list
        ><productList
          v-for="product in productResults"
          :key="product.id"
          :product="product"
          @editReview="toggleEditModal"
          @deleteReview="deleteArray"
        ></productList
      ></carousel-list>

      <!-- <pre>{{ productResults }}</pre> -->
      <!-- productResults.productname -> "http://localhost:4001/images/get/{{productResults.productname}} -> [obj File]" -->
      <!-- </div> -->

      <div>
        <p class="text-xl font-semibold">&#60; 1 &#62;</p>
      </div>
    </div>

    <!--Modal Edit-->

    
    
  </div>
</template>

<script>
import productList from "../components/ProductList.vue";
import CarouselList from "../components/CarouselList.vue";

export default {
  name: "Home",
  components: {
    productList,
    CarouselList,
  },
  data() {
    return {
      productResults: [],
      brandsResults: [],
      colorsResults: [],
      formValue: {
        productname: "",
        description: "",
        price: 0.0,
        date: "",
        brands: "",
        colors: [],
        image: null,
      },
      preview: null,
      openModal: false
    };
  },
  methods: {
    async fetchProductResult() {
      const res = await fetch("http://localhost:4001/products");
      const data = await res.json();
      return data;
    },

    deleteArray(id) {
      this.productResults = this.productResults.filter((product) => {
        return product.id !== id;
      });
    },
    toggleEditModal(){
      this.openModal = !this.openModal
    }
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

