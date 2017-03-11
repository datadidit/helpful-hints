/*
* Solution came from andreyluiz at this link https://laracasts.com/discuss/channels/vue/using-vue-router-with-vue-component-files
*/
import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Foo from './Foo.vue'

Vue.use(VueRouter)

const routes = [
	{path: '/', component: App},
	{path: '/foo', component: Foo}
]

const router = new VueRouter({
	routes
})

new Vue({
  el: '#app',
  router
})

