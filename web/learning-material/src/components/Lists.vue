<template>
<div class="numberList">
	<h1>Numbers</h1>
	<md-list>
		<md-list-item v-for="number in numbers">
		<span>{{ number}}</span>
		<md-button class="md-icon-button md-list-action" @click.native="description(number)">
			<md-icon class="md-primary">description</md-icon>
		</md-button>
		<md-divider></md-divider>
		</md-list-item>
	</md-list>
</div>
</template>

<script type="text/javascript">
export default{
	name: 'materialLists',
	data () {
		return {
			numbers: [1, 2, 3, 4, 5]
		}
	},
	methods: {
		description : function(data){
			console.log("Describe!!!! "+data)
		}
	}
}
</script>
<style>
.numberList {
	width: 100px;
}
</style>