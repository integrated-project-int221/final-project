<template>
  <div class="px-2 py-4 w-full">
    <div class="rounded-xl border-2 border-black">
      <img
        class="container object-cover object-center h-64 rounded-t-xl border-4 shadow-xl hover:border-yellow-500 cursor-pointer"
        src="../assets/logo.png"
        :alt="product.id"
      />
      <span
        class="flex items-center justify-between my-2 text-sm font-semibold leading-none text-black"
        >ID:
        <div class="mr-2">
          <span class="text-xl text-black font-medium">{{ product.id }}</span>
        </div>
      </span>
      <span
        class="flex items-center justify-between my-2 text-sm font-semibold leading-none text-black"
        >Name:
        <div class="mr-2">
          <span class="text-xl text-black font-medium">{{
            product.productname
          }}</span>
        </div>
      </span>
      <span
        class="flex items-center justify-between my-2 text-sm font-semibold leading-none text-black"
        >Price:
        <div class="mr-2">
          <span class="text-xl text-black font-semibold"
            >${{ product.price }}</span
          >
          <span class="ml-1 text-xs text-gray-600">/pc</span>
        </div>
      </span>
      <div class="">
        <span
          class="flex items-center justify-between my-2 text-base font-semibold leading-none text-black"
          >Colors:
          <div class="mr-2">
            <span class="text-xl text-black font-semibold">
              <div
                class="flex flex-row"
                v-for="eachColor in product.colors"
                v-bind:key="eachColor.id"
              >
                <!-- <div class="flex flex-row"> -->
                <button
                  class="border-2 w-6 h-6"
                  :style="{ 'background-color': eachColor.colorValue }"
                ></button>
                <!-- </div> -->

                <!-- <p :style="{ 'color':eachColor.colorValue }"> test color</p> -->
              </div>
            </span>
          </div>
        </span>
      </div>
      <div class="flex justify-around mb-3 text-black">
        <button class="border-2 border-black w-1/3 bg-blue-300" @click="editProduct">
          edit
        </button>
        <button class="border-2 border-black w-1/3 bg-red-300" @click="deleteProduct">
          delete
        </button>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from "axios";

export default {
  props: ["product"],
  data(){
    return {
      colorsResult: [],
    }
  },
  methods:{
    editProduct(){
      alert("this is \"Edit\" button.")
    },
    deleteProduct(){
      alert("this is \"Delete\" button.")
    },
  }
};
</script>

