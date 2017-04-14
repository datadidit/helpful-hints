<template>
<div>
	<md-input-container>
		<label>Filter</label>
		<md-input v-model="filter"></md-input>
	</md-input-container>
	<md-table md-sort="name" @sort="onSort" @select="onSelect">
		<md-table-header>
			<md-table-row>
				<md-table-head md-sort-by="name">Name</md-table-head>
				<md-table-head md-sort-by="age">Age</md-table-head>
			</md-table-row>
		</md-table-header>
		<md-table-body>
			<md-table-row v-for="(row, index) in items" :key="index" :md-item="row" md-auto-select md-selection>
				<md-table-cell v-for="(col, index) in row" :key="index">
					{{ col }}
				</md-table-cell>
			</md-table-row>
		</md-table-body>
	</md-table>
</div>
</template>

<script>
	export default{
		name: 'table',
		data(){
			return{
				sortKey: '',
				sortType: '',
				filter: '',
				items: [
				{'name':'Marcus', 'age': 29},
				{'name':'Joy', 'age':26},
				{'name':'Malcolm', 'age':25},
				{'name':'Hope', 'age':18}
				] 
			}
		},
		watch: {
			filter() {
				console.log('Data Entered '+this.filter)
				if(this.filter){
					console.log('Do filter')
				}else{
					console.log('Nothing to filter....')
				}
			}
		},
		methods: {
			onSort(sort){
				this.sortKey = sort.name
				this.sortType = sort.type
				var self = this

				if(this.sortKey=='name'){
					this.items.sort(function(a, b){
						if(self.sortType=='asc'){
							return a.name.localeCompare(b.name)
						}else{
							return a.name.localeCompare(b.name)*-1
						}
					})
				}else{
					this.items.sort(function (a, b) {
						var aNum = a[self.sortKey]
						var bNum = b[self.sortKey]
						if(self.sortType=='asc'){
							return (aNum < bNum ? -1 : (aNum > bNum ? 1 : 0))
						}else{
							return (aNum < bNum ? -1 : (aNum > bNum ? 1 : 0)) * -1
						}
					});
				}
			},
			onSelect(selection){
				console.log('Selection has been made')
				console.log(selection)
			}
		}
	}
</script>