<template id="custom-row-template">
	<tr><td>{{ name }}</td><td> {{ age }}</td><td><button v-on:click="moreInfo">More Info</button></td><td><button v-on:click="deleteRow">Delete</button></td></tr>
</template>
<script>
module.exports = {
	template: '#custom-row-template',
	methods: {
		moreInfo: function(){
			console.log("Do vue-router stuff")
			console.log(this.item)
		}
	},
	props: ['item']
}
</script>