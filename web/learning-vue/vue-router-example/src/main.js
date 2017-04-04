import Vue from 'vue'
import App from './components/App.vue' //Default app with template
import MyApp from './components/MyApp.vue' //My Application for playing with Vue Router 
import VueRouter from 'vue-router'
import MoreInfo from './components/MoreInfo.vue'
import MyParent from './components/Parent.vue' //Simple App to figure out inheritance of Vue components

//https://forum-archive.vuejs.org/topic/3019/vue-router-with-webpack-components-build/4 Likely has answer
//https://github.com/vuejs/vue-hackernews/

Vue.use(VueRouter)

/*
* What routes do you need?
*/
const routes = [
	{path: '/', component: MyApp},
	{path: '/moreInfo/:name', component: MoreInfo}
]

const router = new VueRouter({
	routes
})

var app = new Vue({
	el: '#app',
	data: {
		items: [
				{'name':'Marcus', 'age': 29},
				{'name':'Joy', 'age':26},
				{'name':'Malcolm', 'age':25},
				{'name':'Hope', 'age':18}
			],
		showModal: true
	},
	components: {
		'myapp' : MyApp
	},	
	router
})

/*
var defaultApp = new Vue({
  el: '#app',
  components: {
  	'sap' : App
  }
})

//const Foo = { template: '<div>foo</div>' }
//const Bar = { template: '<div>bar</div>' }

const myroutes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar }
]

const myrouter = new VueRouter({
	route : myroutes
})

var myParent = new Vue({
	el: '#myParent',
	components: {
		'parent' : MyParent
	},
	myrouter
})

//myrouter.start(myParent, '#myParent')

var myApp = new Vue({
	el: '#embedrouter',
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
	},
	myrouter
//	render: h => h(MyApp) Only need render if you want to create a new vue instance
})

//Hello Vue Router stuff 
// 1. Define route components.
// These can be imported from other files
const User = {template: '<div>User {{ $route.params.id }}</div>'}

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// Vue.extend(), or just a component options object.
// We'll talk about nested routes later.
const example_routes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar },
  { path: '/user/:id', component: User},
  { path: '/moreinfo', component: MoreInfo}
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({
  routes : example_routes // short for routes: routes
})

// 4. Create and mount the root instance.
// Make sure to inject the router with the router option to make the
// whole app router-aware.
const routerApp = new Vue({
  el: '#routerApp',
  router
})
*/