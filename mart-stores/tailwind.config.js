/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    screens: {
    'tablet': '640px',
    // => @media (min-width: 640px) { ... }

    'laptop': '1024px',
    // => @media (min-width: 1024px) { ... }

    'desktop': '1280px',
    // => @media (min-width: 1280px) { ... }
  },
    fontFamily: {
      kanit: ["Kanit", "sans-serif"],
      popins: ["Poppins", "sans-serif"],
      rye: ["Rye","cursive"]
    },
    extend: {},
  },
  plugins: [],
}
