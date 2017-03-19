<template>
	<div>
		<h1>Name: {{ moreInfoName }}</h1>
		<h1>Age: {{ moreInfoAge }}</h1>
		<button v-on:click="closeMoreInfo">Close</button> 
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
	name: 'MoreInfo',
	methods : {
		closeMoreInfo: function(){
			this.$router.push("/tableExample")
		}
	},
	computed: mapGetters([
		'moreInfoName',
		'moreInfoAge'
		])
}
</script>