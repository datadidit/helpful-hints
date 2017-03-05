<template>
	<div id="myapp">
		<div id="modal">
			<span>Interactive Table<button v-on:click="showModal = true">Add</button></span>
			<addModal v-bind:items="items" v-if="showModal" v-on:close='showModal = false'>
			</addModal>
		</div>
		<div id="myTable">
			<table>
				<tr>
					<th>Name</th>
					<th>Age</th>
					<th>More Info</th>
					<th>Delete</th>
				</tr>
				<!--<tr is"customrow"
					v-for="item in items"
					v-bind:"item",
					v-bind:key="item.name"
				>
				</tr>
				-->
				<customrow 
					v-for="(item, index) in items"
					v-bind:item="item"
					v-bind:items="items"
					v-bind:index="index"
					v-bind:key="item.name">
				</customrow>
			</table>
		</div>
	</div>
</template>

<script>
import Addmodal from './Addmodal.vue'
import Customrow from './Customrow.vue'

export default {
	name: 'myapp',
	data: function(){
		return {
			showModal: false,
			items: [
				{'name':'Marcus', 'age': 29},
				{'name':'Joy', 'age': 26}
			]
		}
	},
	components: {
		'addModal' : Addmodal,
		'customrow': Customrow
	}
}
</script>