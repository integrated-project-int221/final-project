<template>
  <div class="container border border-black">
    <div>
      <h1>Edit</h1>
    </div>
    <pre> product: {{ productByid }}</pre>
    <!--Add new Product-->
    <form-input
    :testEditData="productByid"
      :oldproductname= productByid.productname
      :olddescription="productByid.description"
      :oldprice="productByid.price"
      :olddate="productByid.date"
      :oldbrands="productByid.brands"
      :oldcolors="productByid.colors"
      :oldimages="productByid.images"
      @form-submit="editArray"
    >
    </form-input>
    <!-- <pre> product ID: {{ id }} </pre> -->
    <!-- <pre> product: {{ productByid }}</pre> -->
  </div>
</template>

<script>
import FormInput from "../components/FormInput.vue";
// import axios from "axios";

export default {
  props: ["id"],
  components: { FormInput },
  data() {
    return {
      productByid: {
        default: "",
      },
    };
  },
  methods: {
    async fetchProductResult() {
      const res = await fetch(`http://localhost:4001/products/${this.id}`);
      //   const res = await fetch(`http://52.187.35.188:3000/products/items/${this.id}`);
      const data = await res.json();
      return data;
    },
    // editArray(editValue) {
    //   // this.editData = editValue;
    //   axios
    //     .put(`http://localhost:4001/products/${this.id}`, editValue)
    //     .then((response) => {
    //       console.log(response);
    //     })
    //     .then(this.$router.push("/"));
    //   //   this.toggleEditModal();
    // },
  },
  async created() {
    this.productByid = await this.fetchProductResult();
  },
};
</script>
