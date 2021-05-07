<template>
  <div class="container">
    <h1 class="font-bold">Add product</h1>
    <!--Add new Product-->
    <form-input @form-submit="addProduct"> </form-input>

    <base-modal v-if="!modalOpen" @close="toggleModal" title="Add Product Completed">
      <p class="text-sm leading-5 text-gray-500">
        This product was add to 
        <span class="font-bold text-green-600">Product list</span>
        at home page.
      </p>
      
    </base-modal>
  </div>
</template>

<script>
import FormInput from "../components/FormInput.vue";
import axios from "axios";
import BaseModal from "../components/BaseModal.vue";

export default {
  components: { FormInput, BaseModal },
  data() {
    return {
      modalOpen: false,
    };
  },
  methods: {
    toggleModal() {
      this.modalOpen = !this.modalOpen;
    },
    addProduct(newProduct) {


      const fd = new FormData();
      fd.append("File", newProduct.imageObj, newProduct.imageObj.name);

      axios
        .post(
          `http://52.187.35.188:3000/images/upload/${newProduct.prodName}`,
          fd
        )
        .then((res) => {
          console.log(res);
          console.log(newProduct.prodName);
          console.log("this images was send then post product");
        });

      axios
        .post("http://52.187.35.188:3000/products/add", newProduct)
        .then((response) => {
          console.log(response);
          console.log("this product added");
        })
        .then(this.toggleModal());
        // .then();
      // window.location.href = "http://localhost:8081/"
      // this.$router.push("/")
    },
  },
};
</script>
