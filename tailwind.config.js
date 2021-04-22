const colors = require('tailwindcss/colors')
module.exports = {
  purge: { content: ['./public/**/*.html', './src/**/*.vue'] },
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        sea: '#0fa9e6',
        fuchsia: colors.fuchsia,
        salmon: {
          darkest: '#EF6351',
          dark: '#F38375',
          DEFAULT: '#F7A399',
          light: '#FBC3BC',
          lightest: '#FFE3E0'
        }
      }
    }
  },
  variants: {
    extend: {}
  },
  plugins: []
}
