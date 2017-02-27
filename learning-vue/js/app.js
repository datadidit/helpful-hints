/**
 * 
 */
var numbers = ['1', '2', '3', '4', '5', '6']
var letters = ['a', 'b', 'c', 'd', 'e', 'f']
var words = ['hello', 'world', 'foo', 'bar']
var myHistory = new Array()

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!',
    showButtonModal: false
  }
})

var catSelect = new Vue({
	el : '#categorySelect',
	data: {
		selected: [],
		options: ['Letters', 'Numbers', 'Words']
	},
	methods: {
		fillValues: function(){
			console.log("Handling click ")
			myTransition.show = false
			if(this.selected == "Letters"){
				valForCat.options = letters
			}else if(this.selected == "Numbers"){
				valForCat.options = numbers
			}else if(this.selected == "Words"){
				valForCat.options = words
			}else{
				console.log("This shit aint working")
			}
		}
	}
})

var valForCat = new Vue({
	el: '#valuesForCategory',
	data: {
		selected: [],
		options: [],
	},
	methods: {
		popUp: function(){
			console.log("Handling Double Click")
			myTransition.message = "Category: "+catSelect.selected+" Value: "+valForCat.selected
			myTransition.show = true
			myTransition.showModal2 = true
			record = new Object()
			record.category = catSelect.selected
			record.category = this.selected
			myHistory.push(record)
		}
	}
})

Vue.component('my-first-component', {
	template: '<div>A custom component!</div>'
})

Vue.component('modal', {
	template: '#modal-template'
})

Vue.component('button-modal', {
	template: '#button-modal-template'
})

Vue.component('history-modal', {
	template: '#history-modal-template'
})

var compExample = new Vue({
	el: '#example'
})

var myTransition = new Vue({
	el: '#transitionPlay',
	data: {
		show: false,
		message: null,
		showModal2: false
	}
})

var myHistory = new Vue({
	el: '#history',
	data: {
		showHistoryModal: false
	}
})