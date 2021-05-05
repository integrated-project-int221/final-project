<template>
  <div class="py-6">
    <div class="flex bg-white shadow-lg rounded-lg overflow-hidden">
      <img
        :src="require('../assets/' + product.imageName)"
        class="w-2/5 object-cover object-center"
      />
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
          <button class="mt-2 font-semibold text-gray-600 text-sm">
            <!-- class="px-3 py-2 bg-gray-800 text-white text-xs font-bold uppercase rounded" -->

            {{ product.prodManufactured }}
          </button>
        </div>
        <div class="flex item-center justify-between mt-3">
          <button
            class="border border-black w-1/2 bg-blue-200 mx-2 text-black font-semibold py-2 rounded-md shadow-lg"
            @click="gotoEditPage"
          >
            <!-- <router-link
              :to="{ name: 'EditProduct', params: { id: product.prodCode } }"
            > -->
            edit
            <!-- </router-link> -->
          </button>
          <button
            class="border border-black w-1/2 bg-red-200 mx-2 text-black font-semibold py-2 rounded-md shadow-lg"
            @click="toggleDelete"
          >
            <!-- @click="deleteReview(product.prodCode)" -->
            delete
          </button>
        </div>
      </div>
    </div>
  </div>

  <!--this modal-->

  <div
    v-show="this.modalDelete"
    class="fixed z-10 inset-0 overflow-y-auto"
  >
    <div
      class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"
    >
      <div
        class="fixed inset-0 bg-opacity-75 transition-opacity"
      ></div>

      <span
        class="hidden sm:inline-block sm:align-middle sm:h-screen"
        >&#8203;</span
      >
      <div
        class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
      >
        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
          <div class="sm:flex sm:items-start">
            <div
              class="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10"
            >
              <svg
                class="h-6 w-6 text-red-600"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                aria-hidden="true"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"
                />
              </svg>
            </div>
            <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
              <h3
                class="text-lg leading-6 font-medium text-gray-900"
              >
                Deactivate account
              </h3>
              <div class="mt-2">
                <p class="text-sm text-gray-500">
                  Are you sure you want to deactivate your account? All of your
                  data will be permanently removed. This action cannot be
                  undone.
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
          <button
            type="button"
            class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm"
            @click="deleteReview(product.prodCode)"
          >
            Delete
          </button>
          <button
            type="button"
            class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
            @click="toggleDelete"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  </div>
  
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
      //productResults.productname -> "http://localhost:4001/images/get/{{productResults.productname}} -> [obj File]"
      // :src="require(`@/assets/${product.image}`)"
      image: "",
      modalDelete: false,
    };
  },
  methods: {
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
};
</script>

