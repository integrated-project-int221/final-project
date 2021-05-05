<template>
  <div class="container border border-black">
    <div>
      <h1>Edit</h1>
      <pre> product: {{ this.id}}</pre>
    <!-- <pre class="w-screen overflow-hidden"> product: {{ productByid }}</pre> -->
    </div>
    
    <!--Add new Product-->
    <form-input
    :testEditData="productByid"
      
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
    };
  },
  
  methods: {
    async fetchProductResult() {
      // const res = await fetch(`http://localhost:4001/products/${this.id}`);
      const res = await fetch(`http://52.187.35.188:3000/products/item/${this.id}`);
      const data = await res.json();
      return data;
    },
    editArray(editValue) {
      console.log(editValue)
      alert(editValue)
      // this.editData = editValue;
      axios
        .put(`http://52.187.35.188:3000/products/update/${this.id}`, editValue)
        .then((response) => {
          console.log(response);
        })
        .then(
          window.location.href = "http://localhost:8081/"
          // this.$router.push("/")
          );
    },
    // onSave() {
    //   const fd = new FormData();
    //   fd.append("File", this.selectedFile, this.selectedFile.name);
    //   this.testPicValue = fd
    //   console.log("testPicValue: "+this.testPicValue)
    //   axios.post(`http://52.187.35.188:3000/images/update/${this.testEditData.prodCode}`, fd).then((res) => {
    //     console.log(res);
    //   });
    // },
  },
  async created() {
    this.productByid = await this.fetchProductResult();
  },
};
</script>
