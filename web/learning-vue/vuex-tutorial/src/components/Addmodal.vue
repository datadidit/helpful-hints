<template id="add-modal-template">
	<transition name="add">
		<div class="modal-mask">
      		<div class="modal-wrapper">
        		<div class="modal-container">
					<div>
						<span>Name: <input v-model="name" placeholder="Enter Name"></input></span><br>
						<span>Age: <input v-model="age" placeholder="Enter Age"></input></span>
					</div>
					<div>
						<button @click="addData">Add</button>
						<button @click="$emit('close')">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>
export default {
	name: 'Addmodal',
	template: 'add-modal-template',
	data: function(){
		return {
			name: '',
			age: ''
		}
	},
	methods: {
		addData: function(){
			console.log("Add Data")
			var newRow = new Object();
			newRow.name = this.name;
			newRow.age = this.age;
			//console.log(newRow)
			//console.log(this.items)
			//this.items.push(newRow) //TODO: This works too but should use a mutation 
			this.$store.dispatch('addItem', newRow)
			
			this.$emit('close')
		}
	},
	props: ['items']
}
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.button-wrapper {
	display: table-cell;
	vertical-align: middle;
}

.modal-container {
  width: 300px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}
</style>