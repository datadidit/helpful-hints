<template id="hello">
	<p>{{ greeting }} World!</p>
</template>
<script>
module.exports = {
	component: 'hello',
	template: '#hello',
	data: function(){
		return {
			greeting: 'Holla'
		}
	}
}
</script>