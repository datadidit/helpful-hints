<template>
	<div>
		<h1>Name: {{ name }}</h1>
		<h1>Age: {{ age }}</h1>
		<button v-on:click="showMoreInfo = false">Close</button> 
	</div>
	<!--
	<div>
		<h1>Name: Marcus</h1>
		<h1>Age: 29</h1>
	</div>
	-->
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