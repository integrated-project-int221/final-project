<template>
  <div class="flex items-center justify-around">
    <div>
      <button
        class="bg-teal-100 text-teal-500 border-2 border-black hover:text-orange-500 font-bold hover:shadow-lg rounded-full w-12 h-12 -ml-6"
        @click="plusDivs(-1)"
      >
        &#8592;
      </button>
    </div>
    <div class="grid grid-cols-2 gap-3 lg:grid-cols-3 xl:grid-cols-4">
    <!-- <div class="">
      <div
        class="flex flex-row object-cover object-center overflow-hidden max-w-screen-xl"
      > -->
        <slot></slot>
      <!-- </div>
    </div> -->
    
    </div>

    <div>
      <button
        class="bg-teal-100 text-teal-500 border-2 border-black hover:text-orange-500 font-bold hover:shadow rounded-full w-12 h-12 -mr-6"
        @click="plusDivs(1)"
      >
        &#8594;
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>
