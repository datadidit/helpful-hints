<template>
	<!--
	Tried binding the items as well: 
		<div v-bind:items="items" v-bind:showModal="showModal" id="myapp">
	-->
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
		<div>
			<h1>Dynamic Routing</h1>
			<div v-on:showMoreInfo="showMoreInfo">
			</div>
			<!--<moreinfo v-on:show="showMoreInfo"></moreinfo>-->
			<!--<router-view></router-view>-->
		</div>
	</div>
</template>

<script>
import Addmodal from './Addmodal.vue'
import Customrow from './Customrow.vue'
import MoreInfo from './MoreInfo.vue'

export default {
	name: 'myapp',
	components: {
		'addModal' : Addmodal,
		'customrow': Customrow,
		'moreinfo' : MoreInfo
	},
	methods: {
		showMoreInfo: function(){
			console.log("Saw Show!!!!!")
		}
	},
	props: ['items', 'showModal']
}
</script>