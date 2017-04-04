<template>
	<tr>
		<td>{{ name }}</td><td> {{ age }}</td><td><button v-on:click="moreInfo">More Info</button></td><td><button v-on:click="deleteRow">Delete</button></td>
		</tr>
</template>

<script>

export default {
	name: 'Customrow',
	methods: {
		moreInfo: function(){
			console.log("Do vue-router stuff")	
			this.$store.dispatch('updateMoreInfo', this.item)
			this.$router.push({path:"/tableExample/moreInfo/"+this.name})
		},
		deleteRow: function(){
			console.log("Do a delete "+this.index)
			//if(this.index > -1){
			//	this.items.splice(this.index, 1)
			//}
			this.$store.dispatch('deleteItem', this.index)
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