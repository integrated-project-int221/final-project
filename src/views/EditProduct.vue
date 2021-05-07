<template>
  <div class="container border border-black">
    <h1 class="font-bold">Edit</h1>
    <div>
      <!-- <pre> product: {{ this.id }}</pre> -->
      <!-- <pre> url: {{ imgURL }}</pre> -->
      <!-- <img :src="imgURL" /> -->
      <!-- <pre class="w-screen overflow-hidden"> product: {{ productByid }}</pre> -->
    </div>

    <!--Add new Product-->
    <form-input
      :testEditData="productByid"
      :imgUrlValue="imgURL"
      @form-submit="editArray"
    >
    </form-input>
  </div>
</template>

<script>
import FormInput from "../components/FormInput.vue";
import axios from "axios";

export default {
  props: ["id"],
  components: { FormInput },
  data() {
    return {
      productByid: {},
      imgURL: "",
    };
  },

  methods: {
    editArray(editValue) {

      if (editValue.imageObj == "") {
        axios
          .put(
            `http://52.187.35.188:3000/images/updatename/${this.id}/${editValue.prodName}`
          )
          .then(() => {
            console.log("this images was send then post product");
          });
      } else {
        const fd = new FormData();
        fd.append("File", editValue.imageObj, editValue.imageObj.name);
        

        axios
          .put(
            `http://52.187.35.188:3000/images/update/${editValue.prodName}/${this.productByid.imageName}`,
            fd
          )
          .then(() => {
            console.log(editValue.imageName);
            console.log(this.productByid.imageName);
          });
      }

      console.log("imageName");
      console.log(editValue.imageName);

      axios
        .put(`http://52.187.35.188:3000/products/update/${this.id}`, editValue)
        .then((response) => {
          console.log(response);
        })
        .then
        // (window.location.href = "http://localhost:8081/")
        this.$router.push("/")
        ();
    },
    async fetchProductResult() {
      const res = await fetch(
        `http://52.187.35.188:3000/products/item/${this.id}`
      );
      const data = await res.json();
      return data;
    },
  },
  async created() {
    this.productByid = await this.fetchProductResult();
    this.imgURL = `http://52.187.35.188:3000/images/get/${this.productByid.imageName}`;
  },
};
</script>
