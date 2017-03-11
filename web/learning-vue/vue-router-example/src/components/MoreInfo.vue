<template>
	<div>
		<h1>Name: {{ name }}</h1>
		<h1>Age: {{ age }}</h1>
		<button v-on:click="closeMoreInfo">Close</button> 
	</div>
</template>

<script>
export default {
	name: 'MoreInfo',
	data: function(){
		return {
			age: '',
		}
	},
	methods : {
		closeMoreInfo: function(){
			this.$router.push("/")
		},
		updateMoreInfo: function(){
			console.log("Received event!!!!")
		}
	}
}
</script>