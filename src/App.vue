<template>
  <nav id="nav" class="w-full top-0 p-1 text-white bg-gray-300">
    <div class="flex justify-between">
      <div class="ml-6 self-center">
        <span>
          <router-link to="/">Home </router-link>
        </span>
      </div>
      <div class="mr-6 self-center">
        <router-link to="/teammate">Team</router-link>
        <router-link to="/addproduct">Add Product</router-link>
      </div>
    </div>
  </nav>
  <router-view />
</template>

<script>
export default {
  name: "App",
};
</script>

<style scoped>
#nav {
  padding: 1em;
  justify-content: center;
  align-items: center;
  text-align: center;
}

#nav a.router-link-exact-active {
  color: #ce4848;
}
</style>
