<template>
	<div @show="showMoreInfo = true" v-if="showMoreInfo">
		<h1>Name: {{ name }}</h1>
		<h1>Age: {{ age }}</h1>
		<button v-on:click="showMoreInfo = false">Close</button> 
	</div>
</template>

<script>
export default {
	name: 'MoreInfo',
	data: function(){
		return {
			showMoreInfo : false,
			name: '',
			age: ''
		}
	},
}
</script>