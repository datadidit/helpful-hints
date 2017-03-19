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
import { mapGetters, mapActions } from 'vuex'
//import MoreInfo from './MoreInfo.vue'

export default {
	name: 'myapp',
	data: function(){
		return {
			showModal: false
		}
	},
	components: {
		'addModal' : Addmodal,
		'customrow': Customrow,
	},
	computed: mapGetters([
		 'items'
		])
}
</script>

<style type="text/css">
#modal {
	text-align : left;
}
</style>