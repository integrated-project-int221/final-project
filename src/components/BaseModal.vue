<template>
  <div
    class="fixed bottom-0 inset-x-0 px-4 pb-4 sm:inset-0 sm:flex sm:items-center sm:justify-center "
  >
    <div class="fixed inset-0 transition-opacity">
      <div class="absolute inset-0 bg-gray-500 opacity-75"></div>
    </div>

    <div
      class="z-10 rounded-lg overflow-hidden shadow-xl transform transition-all sm:max-w-lg"
      :style="{ 'background-color': bgcolor}"
      role="dialog"
      aria-modal="true"
      aria-labelledby="modal-headline"
    >
      <div class="px-4 pt-4 pb-4 sm:p-5 sm:pb-5">
        <div class="sm:flex sm:items-start">
          <div class="mt-3 text-center sm:mt-0 sm:text-left">
            <div class="flex items-center justify-between mb-4">
              <h3
                class="text-xl leading-6 font-semibold text-gray-900"
                id="modal-headline"
              >
                {{ title }}
              </h3>
              <button @click="handleClose">
                <svg class="h-5 w-5" fill="currentColor" viewBox="0 0 20 20">
                  <path
                    fill-rule="evenodd"
                    d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                    clip-rule="evenodd"
                  ></path>
                </svg>
              </button>
            </div>
            <div class="mt-2">
              <slot />
            </div>
          </div>
        </div>
      </div>
      <div
        class="px-4 pb-6 pt-3 sm:pt-2 sm:flex sm:flex-row-reverse"
        :style="{ 'background-color': bgcolor }"
      >
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      required: true,
    },
    bgcolor: {
      type: String,
      required: false,
    }
  },
  methods: {
    handleClose() {
      this.$emit("close");
    },
  },
};
</script>
