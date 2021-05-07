<template>
  <div
    class="flex flex-row bg-white rounded-lg border border-gray-200 shadow-lg py-6 px-8"
  >
    <!--upload photo-->
    <div class="md:flex items-center justify-center py-6 px-8">
      <!-- <pre>img {{ imgUrlValue }} </pre> -->

      <div class="upload-photo">
        <div class="container border p-2 mt-3">
          <template v-if="!preview">
            <label
              class="border-2 border-pink-500 h-64 w-64 flex flex-col items-center justify-center cursor-pointer rounded-lg shadow-lg"
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
              <!-- :src="preview" -->
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
            <p class="mb-0">file name: {{ formInputValue.imageName.name }}</p>
            <p class="mb-0">
              size: {{ formInputValue.imageName.size / 1024 }}KB
            </p>
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
          v-model="formInputValue.prodName"
          @change="changeNameImage()"
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
          v-model="formInputValue.brands"
        >
          <option value="" hidden disabled selected>Please select one</option>
          <option
            v-for="option in brandsResults"
            :value="option"
            :key="option"
            :selected="option === formInputValue.brands"
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
              v-model="formInputValue.productColor"
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
  
          placeholder="Date"
          v-model="formInputValue.prodManufactured"
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
            v-model.number="formInputValue.price"
            type="number"
            name="number"
            id="number"
            step="0.1"
            required=""
            placeholder="Price"
          />
        </div>
      </div>
      <div class="mt-4">
        <label class="block text-sm text-gray-600" for="cus_name"
          >Description</label
        >
        <textarea
          class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded"
          required=""
          rows="4"
          v-model="formInputValue.prodDescription"
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
    <pre>{{ formInputValue }}</pre>
    <!--test img upload-->
    <div></div>
    <!-- this.preview  =  this.formInputValue.imageObj > onload()reader > assign value > this.preview -->
    <pre>img {{ imgUrlValue }} </pre>
    <pre>preview {{ preview }} </pre>
  </div>
</template>

<script>
export default {
  props: {
    testEditData: {
      type: Object,
    },
    imgUrlValue: {},
  },
  data() {
    return {
      colorsResults: [],
      brandsResults: [],
      formInputValue: {
        prodName: "",
        prodDescription: "",
        price: 0.0,
        prodManufactured: "",
        brands: "",
        productColor: [],
        imageName: "",
        imageObj: "",
      },
      preview: null,
      //test backend img
      //check validate
      checkValidate: {},
      //
      imgData: "",
      imgData2: "",
      //
      newFile: null,
    };
  },
  methods: {
    formValidate() {
      // if (!this.formInputValue.prodName) {
      // }
      // if (!this.formInputValue.prodDescription) {
      // }
      // if (!this.formInputValue.price) {
      // }
      // if (!this.formInputValue.prodManufactured) {
      // }
      // if (!this.formInputValue.brands) {
      // }
      // if (!this.formInputValue.productColor) {
      // }
    },

    changeNameImage() {
      this.imgData = this.formInputValue.prodName;

      return this.imgData;
    },
    //
    previewImage(event) {

      this.formInputValue.imageObj = event.target.files[0];
      //defualt ของ imageObj
      console.log("imgObj below");
      console.log(this.formInputValue.imageObj);

      var input = event.target;
      if (input.files) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.preview = e.target.result;
          // this.imgUrlValue = this.preview
          console.log("preview and Url");
          console.log(this.preview);
          console.log(this.imgUrlValue);
        };
        this.imgData2 = input.files[0].name;
        reader.readAsDataURL(input.files[0]);
      }
    },

    submit() {
      if (this.formInputValue.imageObj) {
        console.log(this.formInputValue.imageObj);
      }
      this.formInputValue.imageName = this.imgData + this.imgData2;
      const formInputValue = this.formInputValue;
      this.$emit("form-submit", formInputValue);
    },
    async fetchColorsResult() {
      try {
        const res = await fetch("http://52.187.35.188:3000/colors");
        const data = await res.json();
        return data;
      } catch (error) {
        console.log(error);
      }
    },
    async fetchBrandsResult() {
      try {
        const res = await fetch("http://52.187.35.188:3000/brands");
        const data = await res.json();
        return data;
      } catch (error) {
        console.log(error);
      }
    },
    async fetchAllProductResult() {
      const res = await fetch("http://52.187.35.188:3000/products/item");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    try {
      this.colorsResults = await this.fetchColorsResult();
      this.brandsResults = await this.fetchBrandsResult();
      (this.formInputValue.prodName = this.testEditData?.prodName || ""),
        (this.formInputValue.prodDescription =
          this.testEditData?.prodDescription || ""),
        (this.formInputValue.price = this.testEditData?.price || 0.0),
        (this.formInputValue.prodManufactured =
          this.testEditData?.prodManufactured || ""),
        (this.formInputValue.brands = this.testEditData?.brands || ""),
        (this.formInputValue.productColor =
          this.testEditData?.productColor || []),
        (this.formInputValue.imageName = this.testEditData?.imageName || ""),
        (this.formInputValue.imageObj = "");
      this.preview = this.imgUrlValue;
    } catch (error) {
      console.log(error);
    }
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
