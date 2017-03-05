import Vue from 'vue'
import App from './components/App.vue' //Default app with template
import MyApp from './components/MyApp.vue' //My Application for playing with Vue Router 
//import MyParent from './components/Parent.vue' //Simple App to figure out inheritance of Vue components

var defaultApp = new Vue({
  el: '#sap',
  render: h => h(App)
})

/*
var myParent = new Vue({
	el: '#myParent',
	render: h => h(MyParent)
})
*/

var myApp = new Vue({
	el: '#test',
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


