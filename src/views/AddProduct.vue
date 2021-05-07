<template>
  <div class="container">
    <h1 class="font-bold">Add product</h1>
    <!--Add new Product-->
    <form-input @form-submit="addProduct"> </form-input>
  </div>
</template>

<script>
import FormInput from "../components/FormInput.vue";
import axios from "axios";

export default {
  components: { FormInput },
  methods: {
    addProduct(newProduct) {
      // console.log(newProduct)
      console.log("test send image");
      console.log(newProduct);
      console.log(newProduct.imageObj.name);

      const fd = new FormData();
      fd.append("File", newProduct.imageObj, newProduct.imageObj.name);

      axios.post(`http://52.187.35.188:3000/images/upload/${newProduct.prodName}`, fd  ).then((res) => {
        console.log(res);
        console.log(newProduct.prodName)
        console.log("this images was send then post product");
      });

      axios
        .post("http://52.187.35.188:3000/products/add", newProduct)
        .then((response) => {
          console.log(response);
          console.log("this product ");
        })
        .then();
      // window.location.href = "http://localhost:8081/"
      // this.$router.push("/")
    },
  },
};
</script>
