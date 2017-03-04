var modalData = {
		name: '',
		age: ''
}

Vue.component('custom-row', {
	template: '#custom-row-template',
	methods: {
		moreInfo: function(){
			//TODO: VueRouter should be used for this
			console.log("This could open up a new page using vue-router")
			console.log(this.item)
		},
		deleteRow: function(){
			var index = cRowExample.items.indexOf(this.item)
			if (index > -1) {
				cRowExample.items.splice(index, 1);
			}
		}
	},
	props: ['item'],
	computed: {
		age: function(){
			return this.item.age
		},
		name: function(){
			return this.item.name
		}
	}
})

Vue.component('add-modal',{
	template: '#add-modal-template',
	data: function(){
		return modalData;
	},
	methods: {
		addData: function(){
			var newRow = new Object();
			newRow.name = this.name;
			newRow.age = this.age;
			console.log(newRow)
			cRowExample.items.push(newRow)
			
			//Reset modal data 
			modalData.name = ''
			modalData.age = ''
			this.$emit('close')
		}
	}
})

var modalExample = new Vue({
	el: '#modalExample',
	data: {
		showAddModal: false
	}
})
var cRowExample = new Vue({
	el: '#customRowExample',
	data: {
		items:  [
			{'name':'Marcus', 'age': 29},
			{'name':'Joy', 'age':26},
			{'name':'Malcolm', 'age':25},
			{'name':'Hope', 'age':18}
		]
	}
})