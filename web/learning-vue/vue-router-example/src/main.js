import Vue from 'vue'
//import App from './components/App.vue'
import MyApp from './components/MyApp.vue'
import MyParent from './components/Parent.vue'

/*var defaultApp = new Vue({
  el: '#sap',
  render: h => h(App)
})
*/

var myApp = new Vue({
	el: '#myapp',
	data: {
		items: [
			{'name':'Marcus', 'age': 29},
			{'name':'Joy', 'age':26},
			{'name':'Malcolm', 'age':25},
			{'name':'Hope', 'age':18}
		]
	},
	render: h => h(MyApp)
})


var myParent = new Vue({
	el: '#myParent',
	render: h => h(MyParent)
})