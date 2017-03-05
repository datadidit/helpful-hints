<template>
	<tr>
		<td>{{ name }}</td><td> {{ age }}</td><td><button v-on:click="moreInfo">More Info</button></td><td><button v-on:click="deleteRow">Delete</button></td></tr>
</template>

<script>
export default {
	name: 'Customrow',
	data: function(){
		return {
			myRow: "BLAH!!!!"
		}
	},
	methods: {
		moreInfo: function(){
			console.log("Do vue-router stuff")
			console.log(this.item)
		},
		deleteRow: function(){
			console.log("Do a delete "+this.index)
			if(this.index > -1){
				this.items.splice(this.index, 1)
			}
		}
	},
	props: ['item', 'items', 'index'],
	computed: {
		name: function(){
			return this.item.name;
		},
		age: function(){
			return this.item.age;
		}
	}
}
</script>