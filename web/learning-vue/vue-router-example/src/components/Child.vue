<template>
	<p>{{ greeting }} World!!!!</p>
</template>

<script>
export default {
	name: 'Child',
	data: function(){
		return {
			greeting: 'Hello'
		}
	}
}
</script>