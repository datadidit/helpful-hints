<template>
	<div id="component-inheritance">
		<h1>Blah:</h1>
		<myChild></myChild>
	</div>
</template>

<script>
import Child from './Child.vue'

export default {
	components: {
		'myChild' : Child
	}
}
</script>