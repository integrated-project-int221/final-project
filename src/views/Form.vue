<template>
  <div class="form">
    <div class="container">
      <form
        @submit.prevent="submit"
        class="w-full md:w-1/2 border-2 border-blue-800 p-6 bg-blue-200"
      >
        <h2 class="text-2xl pb-3 font-semibold">Add Product</h2>
        <div class="flex justify-between">
          <div class="">
            <div class="form-group">
              <div class="container border p-2 mt-3">
                <template v-if="!preview">
                  <label
                    class="border-2 border-blue-800 h-64 w-64 flex flex-col items-center justify-center cursor-pointer rounded-lg shadow-lg"
                  >
                    <svg
                      class="w-8 h-8"
                      fill="currentColor"
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                    >
                      <path
                        d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"
                      />
                    </svg>
                    <span class="mt-2 text-base leading-normal"
                      >Upload a Image</span
                    >
                    <input
                      type="file"
                      accept="image/*"
                      @change="previewImage"
                      class="hidden form-control-file"
                      id="my-file"
                    />
                  </label>
                </template>

                <template v-if="preview">
                  <div
                    class="border-2 h-64 w-64 border-blue-800 rounded-lg shadow-lg"
                  >
                    <span>
                      <img
                        :src="preview"
                        class="w-full h-full flex items-center justify-center object-cover object-center m-auto rounded-lg"
                      />
                    </span>
                  </div>
                  <label
                    class="flex flex-col items-center px-3 py-1 mt-2 bg-white text-blue rounded-lg shadow-lg tracking-wide border border-blue cursor-pointer hover:bg-blue hover:text-white"
                  >
                    <svg
                      class="w-6 h-6"
                      fill="currentColor"
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                    >
                      <path
                        d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"
                      />
                    </svg>
                    <span class="text-sm leading-normal">Upload new Image</span>
                    <input
                      type="file"
                      accept="image/*"
                      @change="previewImage"
                      class="hidden form-control-file"
                      id="my-file"
                    />
                  </label>
                  <p class="mb-0">file name: {{ formValue.image.name }}</p>
                  <p class="mb-0">size: {{ formValue.image.size / 1024 }}KB</p>
                </template>
              </div>
            </div>
          </div>

          <div class="flex flex-col mx-6">
            <div class="flex justify-between mb-3">
              <!--Product Names input-->
              <div class="">
                <base-input
                  v-model="formValue.productname"
                  label="Name"
                  type="text"
                ></base-input>
              </div>

              <!--Brands input-->
              <div class="flex flex-row mb-3">
                <div>
                  <label>Select a Brand</label>
                  <select class="field" v-model="formValue.brands">
                    <option value="" hidden disabled selected>
                      Please select one
                    </option>
                    <option
                      v-for="option in brandsResults"
                      :value="option"
                      :key="option"
                      :selected="option === formValue.brands"
                    >
                      {{ option.brandName }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <!--Price input-->
            <div class="flex justify-between mb-3">
              <div>
                <label>Price</label>
                <input
                  class="field"
                  v-model.number="formValue.price"
                  type="number"
                  name="number"
                  step="0.01"
                  placeholder="price"
                />
                $
              </div>

              <!--Colors input-->
              <div class="flex flex-col mb-3">
                <div class="">
                  <label>Select Product Color(s)</label>
                </div>
                <div class="grid grid-cols-3">
                  <div v-for="option in colorsResults" :key="option">
                    <label
                      class="p-1 cursor-pointer block h-6 w-6 border border-gray-400 rounded-lg"
                      :for="option.id"
                      :style="{ 'background-color': option.colorValue }"
                    >
                      <input
                        v-model="formValue.colors"
                        type="checkbox"
                        :id="option.id"
                        :value="option"
                        class="hidden"
                      />
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <!--Date input-->
            <div class="flex flex-col mb-3">
              <form>
                <label for="date">Date </label>
                <input
                  type="date"
                  id="date"
                  name="date"
                  v-model="formValue.date"
                />
              </form>
            </div>

            <!--Description input-->
            <div class="flex flex-col mb-3">
              <label>Description</label>
              <textarea
                class="field"
                rows="4"
                v-model="formValue.description"
                placeholder="add multiple lines"
              ></textarea>
            </div>
          </div>
        </div>

        <div class="w-full pt-3 mt-4">
          <button
            type="submit"
            class="w-full bg-blue-100 border-2 border-blue-600 px-4 py-2 transition duration-50 focus:outline-none font-semibold hover:bg-blue-400 hover:text-white text-xl cursor-pointer"
          >
            Send
          </button>
        </div>
      </form>
    </div>
  </div>
  <div class="flex flex-col mb-3">
    <pre>{{ formValue }}</pre>
  </div>
</template>

<script>
import axios from "axios";


export default {
  data() {
    return {
      urlRegisterData: "http://localhost:4001/products",
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
    };
  },
  methods: {
    submit() {
      axios
        .post(this.urlRegisterData, {
          productname: this.formValue.productname,
          description: this.formValue.description,
          price: this.formValue.price,
          date: this.formValue.date,
          brands: this.formValue.brands,
          colors: this.formValue.colors,
          image: this.formValue.image.name,
        })
        .then((response) => {
          console.log(response);
        });
    },
    previewImage(event) {
      var input = event.target;
      if (input.files) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.preview = e.target.result;
        };
        this.formValue.image = input.files[0];
        reader.readAsDataURL(input.files[0]);
        console.log(event);
      }
    },
    async fetchColorsResult() {
      const res = await fetch("http://localhost///colors");
      const data = await res.json();
      return data;
    },
    async fetchBrandsResult() {
      const res = await fetch("http://localhost//brands");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.colorsResults = await this.fetchColorsResult();
    this.brandsResults = await this.fetchBrandsResult();
  },
};
</script>

<style scoped>
input[type="date"] {
  padding: 6px;
  border: solid 1px #ccc;
  --tw-border-opacity: 1;
  border-color: rgba(37, 99, 235, var(--tw-border-opacity));
  background-color: aquamarine;
}
</style>
