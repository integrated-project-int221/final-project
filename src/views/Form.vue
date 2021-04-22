<template>
  <div class="form">
    <div class="container">
      <form
        @submit.prevent="submit"
        class="w-full md:w-1/2 border-2 border-blue-800 p-6 bg-blue-200"
      >
        <h2 class="text-2xl pb-3 font-semibold">Add Product</h2>
        <div>
          <div class="col-md-5 offset-md-1">
            <form class="mb-3">
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
                        class="hidden form-control-file "
                        id="my-file"
                      />
                    </label>
                  </template>

                  <template v-if="preview">
                      <div class="border-2 h-64 w-64 border-blue-800 rounded-lg shadow-lg">
                        <span>
                          <img
                            :src="preview"
                            class="w-full h-full flex items-center justify-center object-cover object-center m-auto rounded-lg"
                          />
                        </span>
                      </div>
                      <label
                        class="flex flex-col items-center px-3 py-1 mt-2 bg-white text-blue rounded-lg shadow-lg tracking-wide  border border-blue cursor-pointer hover:bg-blue hover:text-white"
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
                        <span class=" text-sm leading-normal"
                          >Upload new Image</span
                        >
                        <input
                        type="file"
                        accept="image/*"
                        @change="previewImage"
                        class="hidden form-control-file"
                        id="my-file"
                      />
                      </label>
                    <p class="mb-0">file name: {{ image.name }}</p>
                    <p class="mb-0">size: {{ image.size / 1024 }}KB</p>
                  </template>
                </div>
              </div>
            </form>
          </div>

          <div class="flex justify-between mb-3">
            <div>
              <base-input
                v-model="formValue.productname"
                label="Name"
                type="text"
              ></base-input>
            </div>
            <div class="flex flex-row mb-3 ">
              <div>
                <label>Select a Brand</label>
                <select class="field">
                  <option selected disabled hidden value="">
                    Please select one
                  </option>
                  <option value="Apple">Apple</option>
                  <option value="Samsung">Samsung</option>
                  <option value="Realme">Realme</option>
                  <option value="Xiaome">Xiaome</option>
                </select>
              </div>
            </div>
          </div>

          <div class="flex justify-between mb-3">
            <div>
              <label>Price</label>
              <input
                class="field"
                v-model.number="formValue.price"
                type="number"
                name="number"
                step="0.01"
                min="0"
                placeholder="price"
              />
              $
            </div>
            <div class="flex flex-row mb-3">
              <div class="mx-3">
                <label>Select Product Color(s)</label>
              </div>
              <div class="grid grid-cols-2 gap-2">
                <div v-for="option in colorsResults" :key="option">
                  <input
                    v-model="formValue.colors"
                    type="checkbox"
                    :id="option"
                    :value="option"
                  />
                  <label
                    class="p-1"
                    :for="option.id"
                    :style="{ 'background-color': option.colorValue }"
                    >{{ option.colorName }}</label
                  >
                </div>
              </div>
            </div>
          </div>

          <div class="flex flex-col mb-3">
            <label>Description</label>
            <textarea
              class="field"
              rows="4"
              v-model="formValue.description"
              placeholder="add multiple lines"
            ></textarea>
          </div>
          <div class="flex flex-col mb-3">
            <pre>{{ formValue }}</pre>
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
</template>

<script>
export default {
  data() {
    return {
      colorsResults: [],
      // productResults: [],
      formValue: {
        productname: "",
        description: "",
        price: "",
        colors: [],
      },
      preview: null,
      image: null,
    };
  },
  methods: {
    previewImage: function (event) {
      var input = event.target;
      if (input.files) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.preview = e.target.result;
        };
        this.image = input.files[0];
        reader.readAsDataURL(input.files[0]);
      }
    },
    async fetchColorsResult() {
      const res = await fetch("http://localhost:4001/colors");
      const data = await res.json();
      return data;
    },
    async fetchBrandResult() {
      const res = await fetch("http://localhost:4001/brands");
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.colorsResults = await this.fetchColorsResult();
  },
};
</script>

<style scoped>
.p-image {
  position: absolute;
  top: 167px;
  right: 30px;
  color: #666666;
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}
</style>
