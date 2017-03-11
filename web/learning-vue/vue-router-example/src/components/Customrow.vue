<template>
	<tr>
		<td>{{ name }}</td><td> {{ age }}</td><td><button v-on:click="moreInfo">More Info</button></td><td><button v-on:click="deleteRow">Delete</button></td>
		</tr>
</template>

<script>
import {EventBus} from './event-bus.js'

export default {
	name: 'Customrow',
	methods: {
		moreInfo: function(){
			console.log("Do vue-router stuff")
			console.log(this.item)
			console.log(this.name)
			//this.$emit('moreInfoItem', this.item)
			EventBus.$emit('moreInfoItem', this.item)
			this.$router.push({path:"/moreInfo"})
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