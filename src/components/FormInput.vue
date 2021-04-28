<template>
  <div
    class="flex flex-row bg-white rounded-lg shadow-lg  py-6 px-8"
  >
    <!--upload photo-->
    <div class="md:flex items-center justify-center py-6 px-8">
        <div class="upload-photo">
          <div class="container border p-2 mt-3">
            <template v-if="!preview">
              <label
                class="border-2 border-blue-800 h-64 w-64 flex flex-col items-center justify-center cursor-pointer rounded-lg shadow-lg"
              >
                <svg
                  class="w-8 h-8"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path
                    d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"
                  />
                </svg>
                <span class="mt-2 text-base leading-normal"
                  >Upload a Image</span
                >
                <input
                  type="file"
                  accept="image/*"
                  @change="previewImage"
                  class="hidden form-control-file"
                  id="my-file"
                />
              </label>
            </template>

            <template v-if="preview">
              <div
                class="border-2 h-64 w-64 border-blue-800 rounded-lg shadow-lg"
              >
                <span>
                  <img
                    :src="preview"
                    class="w-full h-full flex items-center justify-center object-cover object-center m-auto rounded-lg"
                  />
                </span>
              </div>
              <label
                class="flex flex-col items-center px-3 py-1 mt-2 bg-white text-blue rounded-lg shadow-lg tracking-wide border border-blue cursor-pointer hover:bg-blue hover:text-white"
              >
                <svg
                  class="w-6 h-6"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path
                    d="M16.88 9.1A4 4 0 0 1 16 17H5a5 5 0 0 1-1-9.9V7a3 3 0 0 1 4.52-2.59A4.98 4.98 0 0 1 17 8c0 .38-.04.74-.12 1.1zM11 11h3l-4-4-4 4h3v3h2v-3z"
                  />
                </svg>
                <span class="text-sm leading-normal">Upload new Image</span>
                <input
                  type="file"
                  accept="image/*"
                  @change="previewImage"
                  class="hidden form-control-file"
                  id="my-file"
                />
              </label>
              <p class="mb-0">file name: {{ formValue.image.name }}</p>
              <p class="mb-0">size: {{ formValue.image.size / 1024 }}KB</p>
            </template>
          </div>
        </div>
    </div>
    <!--form input-->
    <form class="">
    <p class="text-gray-800 font-medium">Customer information</p>
    <div class="">
      <label class="block text-sm text-gray-00" for="cus_name">Name</label>
      <input class="w-full px-5 py-1 text-gray-700 bg-gray-200 rounded" id="cus_name" name="cus_name" type="text" required="" placeholder="Your Name" aria-label="Name">
    </div>
    <div class="mt-2">
      <label class="block text-sm text-gray-600" for="cus_email">Email</label>
      <input class="w-full px-5  py-4 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Your Email" aria-label="Email">
    </div>
    <div class="mt-2">
      <label class=" block text-sm text-gray-600" for="cus_email">Address</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Street" aria-label="Email">
    </div>
    <div class="mt-2">
      <label class="hidden text-sm block text-gray-600" for="cus_email">City</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="City" aria-label="Email">
    </div>
    <div class="inline-block mt-2 w-1/2 pr-1">
      <label class="hidden block text-sm text-gray-600" for="cus_email">Country</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Country" aria-label="Email">
    </div>
    <div class="inline-block mt-2 -mx-1 pl-1 w-1/2">
      <label class="hidden block text-sm text-gray-600" for="cus_email">Zip</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email"  name="cus_email" type="text" required="" placeholder="Zip" aria-label="Email">
    </div>
    <p class="mt-4 text-gray-800 font-medium">Payment information</p>
    <div class="">
      <label class="block text-sm text-gray-600" for="cus_name">Card</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_name" name="cus_name" type="text" required="" placeholder="Card Number MM/YY CVC" aria-label="Name">
    </div>
    <div class="mt-4">
      <button class="px-4 py-1 text-white font-light tracking-wider bg-gray-900 rounded" type="submit">$3.00</button>
    </div>
  </form>
  </div>

  <!---->
  <div
    class="border border-blue-500 shadow-lg modal-container bg-white w-4/12 md:max-w-11/12 mx-auto rounded-xl shadow-lg z-50 overflow-y-auto"
  >
    <div class="modal-content py-4 text-left px-6">
      <!--Title-->
      <div class="flex justify-between items-center pb-3">
        <p class="text-2xl font-bold text-gray-500">Edit Caretaker</p>
        <div
          class="modal-close cursor-pointer z-50"
          onclick="modalClose('another-modal')"
        >
          <svg
            class="fill-current text-gray-500"
            xmlns="http://www.w3.org/2000/svg"
            width="18"
            height="18"
            viewBox="0 0 18 18"
          >
            <path
              d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"
            ></path>
          </svg>
        </div>
      </div>
      <!--Body-->
      <div class="my-5 mr-5 ml-5 flex justify-center">
        <form
          action="{{url_for('default.add_caretaker', apartment_id = apartment.id)}}"
          method="POST"
          id="add_caretaker_form"
          class="w-full"
        >
          <div class="">
            <div class="">
              <label for="names" class="text-md text-gray-600"
                >Full Names</label
              >
            </div>
            <div class="">
              <input
                type="text"
                id="names"
                autocomplete="off"
                name="names"
                class="h-3 p-6 w-full border-2 border-gray-300 mb-5 rounded-md"
                placeholder="Example. John Doe"
              />
            </div>
            <div class="">
              <label for="phone" class="text-md text-gray-600"
                >Phone Number</label
              >
            </div>
            <div class="">
              <input
                type="text"
                id="phone"
                autocomplete="off"
                name="phone"
                class="h-3 p-6 w-full border-2 border-gray-300 mb-5 rounded-md"
                placeholder="Example. 0729400426"
              />
            </div>
            <div class="">
              <label for="id_number" class="text-md text-gray-600"
                >ID Number</label
              >
            </div>
            <div class="">
              <input
                type="number"
                id="id_number"
                autocomplete="off"
                name="id_number"
                class="h-3 p-6 w-full border-2 border-gray-300 mb-5 rounded-md"
                placeholder="Caretaker's ID number"
              />
            </div>
          </div>
        </form>
      </div>
      <!--Footer-->
      <div class="flex justify-end pt-2 space-x-14">
        <button
          class="px-4 bg-gray-200 p-3 rounded text-black hover:bg-gray-300 font-semibold"
          onclick="modalClose('another-modal')"
        >
          Cancel
        </button>
        <button
          class="px-4 bg-blue-500 p-3 ml-3 rounded-lg text-white hover:bg-teal-400"
          onclick="validate_form(document.getElementById('add_caretaker_form'))"
        >
          Confirm
        </button>
      </div>
    </div>
  </div>

  <!---->
  <form class="">
    <p class="text-gray-800 font-medium">Customer information</p>
    <div class="">
      <label class="block text-sm text-gray-00" for="cus_name">Name</label>
      <input class="w-full px-5 py-1 text-gray-700 bg-gray-200 rounded" id="cus_name" name="cus_name" type="text" required="" placeholder="Your Name" aria-label="Name">
    </div>
    <div class="mt-2">
      <label class="block text-sm text-gray-600" for="cus_email">Email</label>
      <input class="w-full px-5  py-4 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Your Email" aria-label="Email">
    </div>
    <div class="mt-2">
      <label class=" block text-sm text-gray-600" for="cus_email">Address</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Street" aria-label="Email">
    </div>
    <div class="mt-2">
      <label class="hidden text-sm block text-gray-600" for="cus_email">City</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="City" aria-label="Email">
    </div>
    <div class="inline-block mt-2 w-1/2 pr-1">
      <label class="hidden block text-sm text-gray-600" for="cus_email">Country</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email" name="cus_email" type="text" required="" placeholder="Country" aria-label="Email">
    </div>
    <div class="inline-block mt-2 -mx-1 pl-1 w-1/2">
      <label class="hidden block text-sm text-gray-600" for="cus_email">Zip</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_email"  name="cus_email" type="text" required="" placeholder="Zip" aria-label="Email">
    </div>
    <p class="mt-4 text-gray-800 font-medium">Payment information</p>
    <div class="">
      <label class="block text-sm text-gray-600" for="cus_name">Card</label>
      <input class="w-full px-2 py-2 text-gray-700 bg-gray-200 rounded" id="cus_name" name="cus_name" type="text" required="" placeholder="Card Number MM/YY CVC" aria-label="Name">
    </div>
    <div class="mt-4">
      <button class="px-4 py-1 text-white font-light tracking-wider bg-gray-900 rounded" type="submit">$3.00</button>
    </div>
  </form>
</template>

<script>
export default {};
</script>
