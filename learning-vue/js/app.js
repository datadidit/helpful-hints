/**
 * 
 */
var numbers = ['1', '2', '3', '4', '5', '6']
var letters = ['a', 'b', 'c', 'd', 'e', 'f']
var words = ['hello', 'world', 'foo', 'bar']

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
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
		options: []
	},
	methods: {
		popUp: function(){
			console.log("Handling Double Click")
			myTransition.message = "Category: "+catSelect.selected+" Value: "+valForCat.selected
			myTransition.show = true
		}
	}
})

var  myTransition = new Vue({
	el: '#transitionPlay',
	data: {
		show: false,
		message: null
	}
})