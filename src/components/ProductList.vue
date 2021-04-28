<template>
  <div class="px-2 py-4 w-full">
    <div class="rounded-xl border-2 border-black">
      <img
        class="container h-64 w-64 rounded-t-xl object-cover object-center border-4 shadow-xl hover:border-yellow-500 cursor-pointer"
        :src="require('../assets/' + product.image)"
        :alt="product.id"
      />
      <div class="mx-3">
        <span
          class="flex items-center justify-between my-2 text-sm font-medium leading-none text-black"
          >ID:
          <div class="mr-2">
            <span class="text-xl text-black font-medium">{{ product.id }}</span>
          </div>
        </span>
        <span
          class="flex items-center justify-between my-2 text-sm font-medium leading-none text-black"
          >Name:
          <div class="">
            <span class="text-xl text-black font-medium">{{
              product.productname
            }}</span>
          </div>
        </span>
        <span
          class="flex items-center justify-between my-2 text-sm font-medium leading-none text-black"
          >Price:
          <div class="">
            <span class="text-xl text-black font-medium"
              >${{ product.price }}</span
            >
            <span class="ml-1 text-xs text-gray-600">/pc</span>
          </div>
        </span>
        <div class="">
          <span
            class="flex items-center justify-between my-2 text-sm font-medium leading-none text-black"
            >Colors:
            <div class="">
              <span class="text-xl text-black font-medium flex flex-row">
                <div
                  class=""
                  v-for="eachColor in product.colors"
                  v-bind:key="eachColor.id"
                >
                  <div>
                    <span
                      class="border border-black rounded-md w-6 h-6 block cursor-pointer ml-1"
                      :style="{ 'background-color': eachColor.colorValue }"
                    ></span>
                  </div>
                </div>
              </span>
            </div>
          </span>
        </div>
        <div class="flex justify-around mt-5 mb-3 text-black">
          <button
            class="border border-black w-1/3 bg-blue-200"
            @click="editProduct(product.id)"
          >
            edit
          </button>
          <button
            class="border border-black w-1/3 bg-red-200"
            @click="deleteReview(product.id)"
          >
            delete
          </button>
        </div>
      </div>
    </div>
    <!--page-->
    <!-- <pre>{{ product.image }}</pre> -->
    <div
      class="another-modal fixed w-full inset-0 z-50 overflow-hidden flex justify-center items-center animated fadeIn faster"
      style="background: rgba(0, 0, 0, 0.7)"
      v-show="openModal"
    >
      <div
        class="border border-blue-500 shadow-lg modal-container bg-white w-4/12 md:max-w-11/12 mx-auto rounded-xl shadow-lg z-50 overflow-y-auto"
      >
        <div class="modal-content py-4 text-left px-6">
          <!--Title-->
          <div class="flex justify-between items-center pb-3">
            <p class="text-2xl font-bold text-gray-500">
              Edit Mode Id: {{ product.id }}
            </p>
            <div
              class="modal-close cursor-pointer z-50"
              @click="toggleEditModal"
            >
              <svg
                class="fill-current text-gray-500"
                xmlns="http://www.w3.org/2000/svg"
                width="18"
                height="18"
                viewBox="0 0 18 18"
              >
                <path
                  d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"
                ></path>
              </svg>
            </div>
          </div>
          <!--Body-->
          <div class="my-5 mr-5 ml-5 flex">
            <form>
              <div>
                <div class="flex justify-between mb-3">
                  <!--Product Names input-->
                  <div class="">
                    <base-input
                      label="Name"
                      type="text"
                    ></base-input>{{product.productname}}
                  </div>
                </div>

              </div>
            </form>
          </div>
          <!--Footer-->
        </div>
      </div>
    </div>
    <!--ending modal-->
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["product"],
  data() {
    return {
      //productResults.productname -> "http://localhost:4001/images/get/{{productResults.productname}} -> [obj File]"
      // :src="require(`@/assets/${product.image}`)"
      colorsResult: [],
      preview: "logo.png",
      image: "",
      openModal: false,
    };
  },
  methods: {
    toggleEditModal() {
      this.openModal = !this.openModal;
    },
    editProduct(id) {
      this.toggleEditModal();
      alert('this is "Edit" button.');
      this.$emit("editReview", id);
    },
    deleteReview(id) {
      console.log(id);
      axios.delete(`http://localhost:4001/products/${id}`).then((response) => {
        return response.data;
      });
      alert('this is "Delete" button.');
      this.$emit("deleteReview", id);
    },

  },
};
</script>

