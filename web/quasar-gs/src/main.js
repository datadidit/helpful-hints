// === DEFAULT / CUSTOM STYLE ===
// WARNING! always comment out ONE of the two require() calls below.
// 1. use next line to activate CUSTOM STYLE (./src/themes)
// require(`./themes/app.${__THEME}.styl`)
// 2. or, use next line to activate DEFAULT QUASAR STYLE
require(`quasar/dist/quasar.${__THEME}.css`)
// ==============================

import Vue from 'vue'
import Quasar from 'quasar'
import VueRouter from 'vue-router'
import Index from 'components/Index.vue'
import Error404 from 'components/Error404.vue'

Vue.use(Quasar) // Install Quasar Framework
Vue.use(VueRouter)

const routes = [
    { path: '/', component: Index }, // Default
    { path: '*', component: Error404 } // Not found
]

const router = new VueRouter({
	routes
}) 

Quasar.start(() => {
  new Vue({
    el: '#q-app',
    components: {
    	'quasar-gs' : Index,
    	'error404' : Error404
    },
    router
  })
})
