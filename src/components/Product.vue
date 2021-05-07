<template>
  <div class="py-6">
    <div class="flex bg-white shadow-lg rounded-lg overflow-hidden">
      <img class=" w-2/5 object-cover object-center max-h-80" :src="imageUrl" />
      <!-- :src="require(imageUrl)" -->

      <div class="w-2/3 p-4">
        <h1 class="text-gray-900 font-bold text-2xl">
          {{ product.prodName }}
        </h1>
        <div class="flex item-center justify-between">
          <p class="mt-2 font-semibold text-gray-600 text-sm">
            {{ product.brands.brandName }}
          </p>

          <h1 class="text-gray-700 font-bold text-xl">{{ product.price }}$</h1>
        </div>
        <p class="mt-2 text-gray-600 text-sm max-h-28 h-28 overflow-auto">
          {{ product.prodDescription }}
        </p>
        <div class="flex item-center justify-between mt-3">
          <h1 class="text-gray-700 font-bold text-xl">
            <!-- {{ product.productColor }} -->
            <span class="text-xl text-black font-medium flex flex-row">
              <div
                class=""
                v-for="eachColor in product.productColor"
                v-bind:key="eachColor.colorId"
              >
                <div>
                  <span
                    class="border border-black rounded-md w-6 h-6 block cursor-pointer ml-1"
                    :style="{ 'background-color': eachColor.colorValue }"
                  ></span>
                </div>
              </div>
            </span>
          </h1>
          <div class="mt-2 font-semibold text-gray-600 text-sm">
            {{ product.prodManufactured }}
          </div>
        </div>
        <div class="flex item-center justify-around mt-3">
          <button
            class="justify-center w-full rounded-md border border-transparent border-black px-4 py-2 bg-blue-200 text-lg leading-6 font-semibold text-black shadow-sm hover:bg-blue-300 focus:outline-none focus:border-blue-700 focus:shadow-outline-red transition ease-in-out duration-150 sm:leading-5 mx-2"
            @click="gotoEditPage"
          >
            edit
          </button>
          <button
            class="inline-flex justify-center w-full rounded-md underline border-2 border-transparent border-gray-300 px-4 py-2 bg-white text-base leading-6 font-semibold text-red-600 shadow-sm hover:bg-red-100 hover:border-red-300 focus:outline-none focus:border-red-700 focus:shadow-outline-red transition ease-in-out duration-150 sm:leading-5 mx-2"
            @click="toggleDelete"
          >
            delete
          </button>
        </div>
      </div>
    </div>
    <div>
      <base-modal
        v-if="modalDelete"
        bgcolor="#FEE2E2"
        @close="toggleDelete"
        title="Delete Product"
      >
        <p class="text-sm leading-5 text-black">
          This product will be permanently deleted from this store Are you sure
          you want to
          <span class="font-bold text-red-600">Delete</span>
          this product? All of your data will be permanently removed.
        </p>
        <template v-slot:footer>
          <span class="flex w-full rounded-md shadow-sm sm:ml-3 sm:w-auto">
            <button
              type="button"
              @click="deleteReview(product.prodCode)"
              class="inline-flex justify-center w-full rounded-md border border-transparent px-4 py-2 bg-red-600 text-base leading-6 font-medium text-white shadow-sm hover:bg-red-500 focus:outline-none focus:border-red-700 focus:shadow-outline-red transition ease-in-out duration-150 sm:text-sm sm:leading-5"
            >
              Confirm Delete
            </button>
          </span>
        </template>
      </base-modal>
    </div>
  </div>
  <!-- <div class="test">
    <pre>{{ imageUrl }}</pre>
    <img :src="checkImage()" />
  </div> -->
  <!-- <pre> {{ this.modalDelete }} </pre> -->
</template>

<script>
import axios from "axios";

export default {
  props: {
    product: {
      default: "",
    },
  },
  data() {
    return {
      modalDelete: false,
      imageUrl: `http://52.187.35.188:3000/images/get/${this.product.imageName}`,
    };
  },
  methods: {
    checkImage() {
      try {
        this.imageUrl = `http://52.187.35.188:3000/images/get/${this.product.imageName}`;
      } catch (error) {
        this.imageUrl =
          "http://52.187.35.188:3000/images/get/OngYingGuitar.jpg";
        console.log(error);
      }

      return this.imageUrl;
    },
    toggleDelete() {
      this.modalDelete = !this.modalDelete;
    },
    gotoEditPage() {
      this.$router.push({
        name: "EditProduct",
        params: { id: this.product.prodCode },
      });
    },
    deleteReview(id) {
      console.log(id);
      axios
        .delete(
          `http://52.187.35.188:3000/images/delete/${this.product.imageName}`
        )
        .then((response) => {
          return response.data;
        });
      axios
        .delete(`http://52.187.35.188:3000/products/delete/${id}`)
        .then((response) => {
          return response.data;
        })
        .then(this.toggleDelete());
      this.$emit("deleteReview", id);
    },
    editArray(editValue) {
      let id = this.editData.id;
      // this.editData = editValue;
      axios
        .put(`http://52.187.35.188:3000/products/update/${id}`, editValue)
        .then((response) => {
          console.log(response);
        })
        .then(
          this.$router.push("/")
          // this.$emit("editReview", id);
        );
    },
  },
  computed: {},
};
</script>

