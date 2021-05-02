<template>
  <div class="container border border-black">
    <div>Add product</div>
    <!--Add new Product-->
    <form-input @form-submit="addProduct">
    </form-input>
  </div>
</template>

<script>
import FormInput from "../components/FormInput.vue";
import axios from "axios";

export default {
  components: { FormInput },
  methods:{
    addProduct(newProduct){
      alert("add Product "+newProduct)
      alert("new Product " + newProduct.productname)
      axios
        .post("http://localhost:4001/products",newProduct
        // {
          // productname: this.newProduct.productname,
          // description: this.newProduct.description,
          // price: this.newProduct.price,
          // date: this.newProduct.date,
          // brands: this.newProduct.brands,
          // colors: this.newProduct.colors,
          // image: this.newProduct.image.name,
        // }
        )
        .then((response) => {
          console.log(response);
        })
        .then(this.$router.push("/"))
        ;
    }
  }
};

</script>
