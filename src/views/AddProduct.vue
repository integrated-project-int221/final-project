<template>
  <div class="container ">
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
      // console.log(newProduct)
      console.log(newProduct.imageObj)
      console.log(newProduct.imageObj.name)
      const fd = new FormData();
      fd.append("File", newProduct.imageObj, newProduct.imageObj.name);
      // axios.post(`http://52.187.35.188:3000/images/upload/${this.testEditData.prodCode}`, fd).then((res) => {
      //   console.log(res);
      // });
      
      axios
        .post("http://52.187.35.188:3000/products/add",newProduct)
        .then((response) => {
          console.log(response);
        })
        .then(
          // window.location.href = "http://localhost:8081/"
          // this.$router.push("/")
          )
        ;
    },
    // onSave() {
    //   const fd = new FormData();
    //   fd.append("File", this.selectedFile, this.selectedFile.name);
    //   axios.post(`http://52.187.35.188:3000/images/update/${this.testEditData.prodCode}`, fd).then((res) => {
    //     console.log(res);
    //   });
    // },
  }
};

</script>
