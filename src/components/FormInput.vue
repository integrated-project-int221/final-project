<template>
  <div
    class="flex flex-row bg-white rounded-lg border border-gray-200 shadow-lg py-6 px-8"
  >
    <!--upload photo-->
    <div class="md:flex items-center justify-center py-6 px-8">
      <div class="upload-photo">
        <div class="container border p-2 mt-3">
          <template v-if="!preview">
            <label
              class="border-2 border-pink-500 h-64 w-64 flex flex-col items-center justify-center cursor-pointer rounded-lg shadow-lg"
            >
              <!-- <svg
                class="w-8 h-8"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
              >
                <path
                  d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"
                />
              </svg> -->
              <span class="mt-2 text-base leading-normal">Upload a Image</span>
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
            <p class="mb-0">file name: {{ editValue.imageName.name }}</p>
            <p class="mb-0">size: {{ editValue.imageName.size / 1024 }}KB</p>
          </template>
        </div>
      </div>
    </div>
    <!--form input-->
    <form @submit.prevent="submit" class="">
      <p class="text-gray-800 font-medium">Customer information</p>
      <div class="">
        <label class="block text-sm text-gray-00" for="name">Name</label>
        <input
          class="w-full px-5 py-1 text-gray-700 bg-gray-200 rounded"
          id="name"
          name="name"
          v-model="editValue.prodName"
          type="text"
          required=""
          placeholder="Your Name"
          aria-label="Name"
        />
        <!-- <p class="text-red-600 text-xs italic">Please fill out this field.</p> -->
      </div>
      <div class="mt-2">
        <label class="block text-sm text-gray-600" for="brand">Brands</label>
        <select
          id="brand"
          class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded"
          v-model="editValue.brands"
        >
          <option value="" hidden disabled selected>Please select one</option>
          <option
            v-for="option in brandsResults"
            :value="option"
            :key="option"
            :selected="option === editValue.brands"
          >
            {{ option.brandName }}
          </option>
        </select>
      </div>
      <div class="mt-2">
        <label class="block text-sm text-gray-600" for="">Color(s)</label>
        <div class="flex flex-row">
          <label
            v-for="option in colorsResults"
            :key="option"
            class="check-color flex p-1 border-2 border-gray-200 hover:border-black rounded-full transition ease-in duration-300 w-8 h-8 bg-blue-900 rounded-full justify-center items-center"
            :for="option.id"
            :style="{ 'background-color': option.colorValue }"
          >
            <input
              v-model="editValue.productColor"
              type="checkbox"
              :id="option.id"
              :value="option"
            />
            <!-- <span class="check-color block p-1 border-2 border-white hover:border-black rounded-full transition ease-in duration-300 w-6 h-6 bg-blue-900 rounded-full" :style="{ 'background-color': option.colorValue }"></span> -->
          </label>
        </div>
      </div>
      <div class="inline-block mt-2 w-1/2 pr-1">
        <label class="block text-sm text-gray-600" for="cus_email">Date</label>
        <input
          class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded"
          type="date"
          id="prodManufactured"
          name="prodManufactured"
          required=""
          placeholder="Date"
          aria-label="Email"
          v-model="editValue.prodManufactured"
        />
      </div>
      <div class="inline-block mt-2 -mx-1 pl-1 w-1/2">
        <label class="block text-sm text-gray-600" for="cus_email">Price</label>
        <div class="flex flex-row">
          <span
            class="flex items-center bg-grey-lighter rounded rounded-r-none px-3 font-bold text-grey-darker"
            >$</span
          >
          <input
            class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded"
            v-model.number="editValue.price"
            type="number"
            name="number"
            id="number"
            step="0.1"
            required=""
            placeholder="Price"
            aria-label="Email"
          />
        </div>
      </div>
      <div class="mt-4">
        <label class="block text-sm text-gray-600" for="cus_name"
          >Description</label
        >
        <textarea
          class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded"
          rows="4"
          v-model="editValue.prodDescription"
          placeholder="Description"
        ></textarea>
      </div>
      <div class="mt-4">
        <button
          class="px-4 py-1 w-full text-white font-light tracking-wider bg-gray-900 rounded"
        >
          Submit
        </button>
      </div>
    </form>
  </div>
  <div class="w-screen overflow-hidden">
    <!-- <pre>{{ testEditData }}</pre> -->

    <!--test img upload-->
    <div>
      <input type="file" @change="onFileSelceted" />
      <img :src="imagePreview" />
      <button @click="onSave">Save</button>
    </div>
    <pre> selectfile {{ selectedFile }} </pre>
    <!-- this.preview  =  this.editValue.imageObj > onload()reader > assign value > this.preview -->
    <pre> preview {{ preview }} </pre>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    testEditData: {
      type: Object,
    },
  },
  data() {
    return {
      colorsResults: [],
      brandsResults: [],
      editValue: {
        prodName: this.testEditData?.prodName || "",
        prodDescription: this.testEditData?.prodDescription || "",
        price: this.testEditData?.price || 0.0,
        prodManufactured: this.testEditData?.prodManufactured || "",
        brands: this.testEditData?.brands || "",
        productColor: this.testEditData?.productColor || [],
        imageName: this.testEditData?.imageName || "",
        imageObj: "",
      },
      preview: null,
      //test backend img
      imagePreview: null,
      selectedFile: null,
      //check validate
      checkValidate: {},
    };
  },
  methods: {
    formValidate() {
      if (!this.editValue.prodName) {
        this.errors.push("Name required.");
      }
      if (!this.email) {
        this.errors.push('Email required.');
      } else if (!this.validEmail(this.email)) {
        this.errors.push('Valid email required.');
      }

    },
    //
    previewImage(event) {
      this.editValue.imageObj = event.target.files[0];
      //
      var input = event.target;
      if (input.files) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.preview = e.target.result;
          console.log(this.preview);
        };
        this.editValue.imageName = input.files[0].name;
        reader.readAsDataURL(input.files[0]);
        // console.log(this.editValue.imageObj);
        // console.log(this.editPicValue)
        // const fd = new FormData();
        // fd.append("File", this.)
      }
    },
    showImage() {},
    onFileSelceted(event) {
      this.selectedFile = event.target.files[0];
      //
      console.log(this.selectedFile);
      // if (input.files) {
      var reader = new FileReader();
      reader.onload = (e) => {
        this.imagePreview = e.target.result;
      };
      //   this.editValue.imageName = input.files[0].name;
      reader.readAsDataURL(this.selectedFile);
      // console.log(this.selectedFile);
      // }
    },
    onSave() {
      const fd = new FormData();
      fd.append("File", this.selectedFile, this.selectedFile.name);
      this.testPicValue = fd;
      console.log("testPicValue: " + this.testPicValue);
      axios
        .post(
          `http://52.187.35.188:3000/images/update/${this.testEditData.prodCode}`,
          fd
        )
        .then((res) => {
          console.log(res);
        });
    },
    submit() {
      const formInputValue = this.editValue;
      this.$emit("form-submit", formInputValue);
    },
    async fetchColorsResult() {
      // const res = await fetch("http://localhost:4001/productColor/");
      // const res = await fetch("http://localhost//productColor");
      const res = await fetch("http://52.187.35.188:3000/colors");
      const data = await res.json();
      return data;
    },
    async fetchBrandsResult() {
      // const res = await fetch("http://localhost:4001/brands/");
      // const res = await fetch("http://localhost//brands");
      const res = await fetch("http://52.187.35.188:3000/brands");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.colorsResults = await this.fetchColorsResult();
    this.brandsResults = await this.fetchBrandsResult();
  },
  watch: {
    testEditData: function () {
      this.editValue = this.testEditData;
      // console.log("Prop changed: ", newVal, " | was: ", oldVal);
      // console.log(this.editValue);
    },
  },
};
</script>

<style scoped>
input[type="checkbox"] {
  position: relative;
  cursor: pointer;
}
input[type="checkbox"]:before {
  content: "";
  display: flex;
  position: absolute;
  width: 12px;
  height: 12px;
  border: 2px;
  border-radius: 3px;
  background-color: white;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
input[type="checkbox"]:checked:after {
  content: "";
  display: block;
  width: 5px;
  height: 10px;
  border: solid black;
  border-width: 0 2px 2px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
  position: absolute;
  left: 4px;
}
</style>
