import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
    plugins: [react()],
    base: "/okiror_samuel_vinald/", // Set base for GitHub Pages
});
