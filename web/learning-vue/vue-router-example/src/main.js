import Vue from 'vue'
import App from './components/App.vue' //Default app with template
import MyApp from './components/MyApp.vue' //My Application for playing with Vue Router 
import VueRouter from 'vue-router'
//import MyParent from './components/Parent.vue' //Simple App to figure out inheritance of Vue components

Vue.use(VueRouter)

var defaultApp = new Vue({
  el: '#app',
  components: {
  	'sap' : App
  }
})

/*
var myParent = new Vue({
	el: '#myParent',
	render: h => h(MyParent)
})
*/

var myApp = new Vue({
	el: '#myApp',
	data: {
		items: [
				{'name':'Marcus', 'age': 29},
				{'name':'Joy', 'age':26},
				{'name':'Malcolm', 'age':25},
				{'name':'Hope', 'age':18}
			],
		showModal: false
	},
	components: {
		'myapp' : MyApp
	}
//	render: h => h(MyApp) Only need render if you want to create a new vue instance
})

//Hello Vue Router stuff 
// 1. Define route components.
// These can be imported from other files
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// Vue.extend(), or just a component options object.
// We'll talk about nested routes later.
const routes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar }
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({
  routes // short for routes: routes
})

// 4. Create and mount the root instance.
// Make sure to inject the router with the router option to make the
// whole app router-aware.
const routerApp = new Vue({
  router
}).$mount('#routerApp')
