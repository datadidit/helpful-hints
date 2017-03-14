<template>
	<div>
		<h1>Name: {{ name }}</h1>
		<h1>Age: {{ age }}</h1>
		<button v-on:click="closeMoreInfo">Close</button> 
	</div>
</template>

<script>
import {EventBus} from './event-bus.js'
var temp;

EventBus.$on('moreInfoItem', function(data){
	temp = data
})

export default {
	name: 'MoreInfo',
	methods : {
		closeMoreInfo: function(){
			this.$router.push("/")
		},
		updateMoreInfo: function(){
			console.log("Received event!!!!")
		}
	},
	computed: {
		name : function(){
			return temp.name
		},
		age: function(){
			return temp.age
		}
	}
}
</script>